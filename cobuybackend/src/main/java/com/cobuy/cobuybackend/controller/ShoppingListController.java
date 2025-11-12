package com.cobuy.cobuybackend.controller;

import com.cobuy.cobuybackend.model.Group;
import com.cobuy.cobuybackend.model.ShoppingList;
import com.cobuy.cobuybackend.repository.GroupRepository;
import com.cobuy.cobuybackend.repository.ShoppingListRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
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

    @GetMapping
    public List<ShoppingList> getAllLists() {
        return shoppingListRepository.findAll();
    }

    @GetMapping("/group/{groupId}")
    public List<ShoppingList> getListsByGroup(@PathVariable Integer groupId) {
        Group group = groupRepository.findById(groupId).orElse(null);
        if (group == null)
            return List.of();
        return shoppingListRepository.findByGroup(group);
    }

    @PostMapping
    public ShoppingList createList(@RequestBody CreateListDTO body) {
        Group group = groupRepository.findById(body.groupId())
                .orElseThrow(() -> new org.springframework.web.server.ResponseStatusException(
                        org.springframework.http.HttpStatus.NOT_FOUND, "Group not found: " + body.groupId()));

        ShoppingList sl = new ShoppingList();
        sl.setGroup(group);
        sl.setTitle(body.title());
        sl.setCreatedAt(java.time.LocalDateTime.now());

        return shoppingListRepository.save(sl);
    }

    public record CreateListDTO(@com.fasterxml.jackson.annotation.JsonProperty("group_id") Integer groupId,
            String title) {
    }
}