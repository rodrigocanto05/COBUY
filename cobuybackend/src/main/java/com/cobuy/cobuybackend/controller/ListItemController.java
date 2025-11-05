package com.cobuy.cobuybackend.controller;

import com.cobuy.cobuybackend.dto.AddItemRequest;
import com.cobuy.cobuybackend.model.ListItem;
import com.cobuy.cobuybackend.model.ShoppingList;
import com.cobuy.cobuybackend.repository.ListItemRepository;
import com.cobuy.cobuybackend.repository.ShoppingListRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/lists")
public class ListItemController {

    private final ListItemRepository listItemRepository;
    private final ShoppingListRepository shoppingListRepository;

    public ListItemController(ListItemRepository listItemRepository,
                              ShoppingListRepository shoppingListRepository) {
        this.listItemRepository = listItemRepository;
        this.shoppingListRepository = shoppingListRepository;
    }

    @GetMapping("/{listId}/items")
    public List<ListItem> getItemsForList(
            @PathVariable Integer listId,
            @RequestParam(name = "onlyOpen", required = false) Boolean onlyOpen,
            @RequestParam(name = "onlyDone", required = false) Boolean onlyDone
    ) {
        ShoppingList list = shoppingListRepository.findById(listId).orElse(null);
        if (list == null) return List.of();

        if (Boolean.TRUE.equals(onlyOpen)) {
            return listItemRepository.findByListAndDone(list, false);
        }
        if (Boolean.TRUE.equals(onlyDone)) {
            return listItemRepository.findByListAndDone(list, true);
        }
        return listItemRepository.findByList(list);
    }

    @PostMapping("/{listId}/items")
    public ResponseEntity<?> addItemToList(
            @PathVariable Integer listId,
            @RequestBody AddItemRequest req) {

        ShoppingList list = shoppingListRepository.findById(listId).orElse(null);
        if (list == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("List with id " + listId + " not found");
        }
        if (req == null || req.name == null || req.name.isBlank()) {
            return ResponseEntity.badRequest().body("Field 'name' is required");
        }

        ListItem item = new ListItem();
        item.setList(list);
        item.setName(req.name.trim());
        item.setQty(req.qty != null ? req.qty : 1.0);
        item.setUnit(req.unit != null ? req.unit : "un");
        item.setDone(false);
        item.setUpdatedAt(LocalDateTime.now());

        ListItem saved = listItemRepository.save(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PatchMapping("/{listId}/items/{itemId}/toggle")
    public ResponseEntity<?> toggleItem(
            @PathVariable Integer listId,
            @PathVariable Integer itemId) {

        ShoppingList list = shoppingListRepository.findById(listId).orElse(null);
        if (list == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("List not found");

        return listItemRepository.findById(itemId)
                .map(item -> {
                    if (!item.getList().getId().equals(list.getId())) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body("Item doesn't belong to this list");
                    }
                    item.setDone(Boolean.TRUE.equals(item.getDone()) ? false : true);
                    item.setUpdatedAt(LocalDateTime.now());
                    return ResponseEntity.ok(listItemRepository.save(item));
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found"));
    }

    public static class DoneDTO {
        public Boolean done;
        public Boolean getDone() { return done; }
        public void setDone(Boolean done) { this.done = done; }
    }

    @PatchMapping("/{listId}/items/{itemId}")
    public ResponseEntity<?> setDone(
            @PathVariable Integer listId,
            @PathVariable Integer itemId,
            @RequestBody DoneDTO body) {

        ShoppingList list = shoppingListRepository.findById(listId).orElse(null);
        if (list == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("List not found");

        return listItemRepository.findById(itemId)
                .map(item -> {
                    if (!item.getList().getId().equals(list.getId())) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body("Item doesn't belong to this list");
                    }
                    item.setDone(Boolean.TRUE.equals(body.done));
                    item.setUpdatedAt(LocalDateTime.now());
                    return ResponseEntity.ok(listItemRepository.save(item));
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found"));
    }

    @DeleteMapping("/{listId}/items/{itemId}")
    public ResponseEntity<?> deleteItem(
            @PathVariable Integer listId,
            @PathVariable Integer itemId) {

        ShoppingList list = shoppingListRepository.findById(listId).orElse(null);
        if (list == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("List not found");

        return listItemRepository.findById(itemId)
                .map(item -> {
                    if (!item.getList().getId().equals(list.getId())) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body("Item doesn't belong to this list");
                    }
                    listItemRepository.delete(item);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found"));
    }
}