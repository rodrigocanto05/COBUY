package com.cobuy.cobuybackend.controller;

import com.cobuy.cobuybackend.model.*;
import com.cobuy.cobuybackend.repository.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/lists")
public class ListItemController {

    private final ListItemRepository listItemRepository;
    private final ShoppingListRepository shoppingListRepository;
    private final ItemRepository itemRepository;
    private final UnitRepository unitRepository;
    private final UserRepository userRepository;
    private final MembershipRepository membershipRepository;

    public ListItemController(
            ListItemRepository listItemRepository,
            ShoppingListRepository shoppingListRepository,
            ItemRepository itemRepository,
            UnitRepository unitRepository,
            UserRepository userRepository,
            MembershipRepository membershipRepository
    ) {
        this.listItemRepository = listItemRepository;
        this.shoppingListRepository = shoppingListRepository;
        this.itemRepository = itemRepository;
        this.unitRepository = unitRepository;
        this.userRepository = userRepository;
        this.membershipRepository = membershipRepository;
    }

    public record AddItemDTO(String name, Double qty, Integer unitId, Integer userId) {}

    private boolean userInGroup(Integer userId, Integer groupId) {
        return membershipRepository.findByUserIdAndGroupId(userId, groupId).isPresent();
    }

    @GetMapping("/{listId}/items")
    public ResponseEntity<?> getItems(
            @PathVariable Integer listId,
            @RequestParam Integer userId
    ) {
        ShoppingList list = shoppingListRepository.findById(listId).orElse(null);
        if (list == null) return ResponseEntity.notFound().build();

        if (!userInGroup(userId, list.getGroup().getId()))
            return ResponseEntity.status(403).body("Não pertence ao grupo");

        return ResponseEntity.ok(listItemRepository.findByList(list));
    }

    @PostMapping("/{listId}/items")
    public ResponseEntity<?> addItem(
            @PathVariable Integer listId,
            @RequestBody AddItemDTO dto
    ) {
        ShoppingList list = shoppingListRepository.findById(listId).orElse(null);
        if (list == null)
            return ResponseEntity.status(404).body("Lista não encontrada");

        if (!userInGroup(dto.userId(), list.getGroup().getId()))
            return ResponseEntity.status(403).body("Não pertence ao grupo");

        if (dto.name() == null || dto.name().isBlank())
            return ResponseEntity.badRequest().body("Nome do item é obrigatório");

        Unit unit = unitRepository.findById(dto.unitId()).orElse(null);
        if (unit == null) return ResponseEntity.status(404).body("Unidade não encontrada");

        User user = userRepository.findById(dto.userId()).orElse(null);
        if (user == null) return ResponseEntity.status(404).body("Utilizador não encontrado");

        // Criar item se não existir
        Item item = itemRepository.findByName(dto.name().trim()).orElse(null);
        if (item == null) {
            item = new Item();
            item.setName(dto.name().trim());
            item.setUnit(unit);
            item = itemRepository.save(item);
        }

        ListItem li = new ListItem();
        li.setList(list);
        li.setItem(item);
        li.setUnit(unit);
        li.setUser(user);
        li.setQty(dto.qty() != null ? BigDecimal.valueOf(dto.qty()) : BigDecimal.ONE);
        li.setDone(false);

        return ResponseEntity.status(201).body(listItemRepository.save(li));
    }

    @PatchMapping("/{listId}/items/{itemId}/done")
    public ResponseEntity<?> toggleDone(
            @PathVariable Integer listId,
            @PathVariable Integer itemId,
            @RequestParam Integer userId
    ) {
        ShoppingList list = shoppingListRepository.findById(listId).orElse(null);
        if (list == null) return ResponseEntity.notFound().build();

        if (!userInGroup(userId, list.getGroup().getId()))
            return ResponseEntity.status(403).body("Não pertence ao grupo");

        return listItemRepository.findById(itemId)
                .map(item -> {
                    if (!item.getList().getId().equals(listId))
                        return ResponseEntity.badRequest().body("Item não pertence à lista");
                    item.setDone(!item.getDone());
                    return ResponseEntity.ok(listItemRepository.save(item));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{listId}/items/{itemId}")
    public ResponseEntity<?> deleteItem(
            @PathVariable Integer listId,
            @PathVariable Integer itemId,
            @RequestParam Integer userId
    ) {
        ShoppingList list = shoppingListRepository.findById(listId).orElse(null);
        if (list == null) return ResponseEntity.notFound().build();

        if (!userInGroup(userId, list.getGroup().getId()))
            return ResponseEntity.status(403).body("Não pertence ao grupo");

        return listItemRepository.findById(itemId)
                .map(item -> {
                    if (!item.getList().getId().equals(listId))
                        return ResponseEntity.badRequest().body("Item não pertence à lista");
                    listItemRepository.delete(item);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}