package com.cobuy.cobuybackend.controller;

import com.cobuy.cobuybackend.model.*;
import com.cobuy.cobuybackend.repository.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/lists")
public class ListItemController {

    private final ListItemRepository listItemRepository;
    private final ShoppingListRepository shoppingListRepository;
    private final ItemRepository itemRepository;
    private final UnitRepository unitRepository;
    private final UserRepository userRepository;

    public ListItemController(
            ListItemRepository listItemRepository,
            ShoppingListRepository shoppingListRepository,
            ItemRepository itemRepository,
            UnitRepository unitRepository,
            UserRepository userRepository
    ) {
        this.listItemRepository = listItemRepository;
        this.shoppingListRepository = shoppingListRepository;
        this.itemRepository = itemRepository;
        this.unitRepository = unitRepository;
        this.userRepository = userRepository;
    }

    // DTO para criar item
    public record AddItemDTO(Integer itemId, BigDecimal qty, Integer unitId, Integer userId) {}

    @GetMapping("/{listId}/items")
    public ResponseEntity<List<ListItem>> getItems(@PathVariable Integer listId) {
        ShoppingList list = shoppingListRepository.findById(listId).orElse(null);
        if (list == null) return ResponseEntity.notFound().build();

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

        Item item = itemRepository.findById(dto.itemId()).orElse(null);
        if (item == null)
            return ResponseEntity.status(404).body("Item não encontrado");

        Unit unit = unitRepository.findById(dto.unitId()).orElse(null);
        if (unit == null)
            return ResponseEntity.status(404).body("Unidade não encontrada");

        User user = userRepository.findById(dto.userId()).orElse(null);
        if (user == null)
            return ResponseEntity.status(404).body("Utilizador não encontrado");

        ListItem li = new ListItem();
        li.setList(list);
        li.setItem(item);
        li.setUnit(unit);
        li.setUser(user);
        li.setQty(dto.qty() != null ? dto.qty() : BigDecimal.ONE);
        li.setDone(false);

        ListItem saved = listItemRepository.save(li);
        return ResponseEntity.status(201).body(saved);
    }

    @PatchMapping("/{listId}/items/{itemId}/done")
    public ResponseEntity<?> toggleDone(
            @PathVariable Integer listId,
            @PathVariable Integer itemId
    ) {
        ShoppingList list = shoppingListRepository.findById(listId).orElse(null);
        if (list == null) return ResponseEntity.notFound().build();

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
            @PathVariable Integer itemId
    ) {
        ShoppingList list = shoppingListRepository.findById(listId).orElse(null);
        if (list == null) return ResponseEntity.notFound().build();

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