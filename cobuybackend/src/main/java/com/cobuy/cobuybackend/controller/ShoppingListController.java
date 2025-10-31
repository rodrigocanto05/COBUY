package com.cobuy.cobuybackend.controller;

import com.cobuy.cobuybackend.model.Group;
import com.cobuy.cobuybackend.model.ShoppingList;
import com.cobuy.cobuybackend.repository.GroupRepository;
import com.cobuy.cobuybackend.repository.ShoppingListRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lists")
public class ShoppingListController {

    private final ShoppingListRepository shoppingListRepository;
    private final GroupRepository groupRepository;

    public ShoppingListController(ShoppingListRepository shoppingListRepository, GroupRepository groupRepository) {
        this.shoppingListRepository = shoppingListRepository;
        this.groupRepository = groupRepository;
    }

    // GET /lists
    @GetMapping
    public List<ShoppingList> getAllLists() {
        return shoppingListRepository.findAll();
    }

    // GET /groups/{groupId}/lists
    @GetMapping("/group/{groupId}")
    public List<ShoppingList> getListsByGroup(@PathVariable Integer groupId) {
        Group group = groupRepository.findById(groupId).orElse(null);
        if (group == null) return List.of(); // lista vazia se não existir
        return shoppingListRepository.findByGroup(group);
    }
}