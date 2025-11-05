package com.cobuy.cobuybackend.controller;

import com.cobuy.cobuybackend.model.Group;
import com.cobuy.cobuybackend.model.ShoppingList;
import com.cobuy.cobuybackend.repository.GroupRepository;
import com.cobuy.cobuybackend.service.ShoppingListService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lists")
public class ShoppingListController {

    private final ShoppingListService shoppingListService;
    private final GroupRepository groupRepository;

    public ShoppingListController(ShoppingListService shoppingListService, GroupRepository groupRepository) {
        this.shoppingListService = shoppingListService;
        this.groupRepository = groupRepository;
    }

    // 🔹 Listar todas as listas
    @GetMapping
    public List<ShoppingList> getAllLists() {
        return shoppingListService.getAllLists();
    }

    // 🔹 Listar listas de um grupo específico
    @GetMapping("/group/{groupId}")
    public ResponseEntity<List<ShoppingList>> getListsByGroup(@PathVariable Integer groupId) {
        Group group = groupRepository.findById(groupId).orElse(null);
        if (group == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(shoppingListService.getListsByGroup(group));
    }

    // 🔹 Buscar lista por ID
    @GetMapping("/{id}")
    public ResponseEntity<ShoppingList> getListById(@PathVariable Integer id) {
        return shoppingListService.getListById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 🔹 Criar nova lista
    @PostMapping
    public ResponseEntity<ShoppingList> createList(@RequestBody ShoppingList list) {
        return ResponseEntity.ok(shoppingListService.createList(list));
    }

    // 🔹 Apagar lista por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteList(@PathVariable Integer id) {
        shoppingListService.deleteList(id);
        return ResponseEntity.noContent().build();
    }
}
