package com.cobuy.cobuybackend.service;

import com.cobuy.cobuybackend.model.Group;
import com.cobuy.cobuybackend.model.ShoppingList;
import com.cobuy.cobuybackend.repository.GroupRepository;
import com.cobuy.cobuybackend.repository.ShoppingListRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingListService {

    private final ShoppingListRepository shoppingListRepository;
    private final GroupRepository groupRepository;

    public ShoppingListService(ShoppingListRepository shoppingListRepository,
                               GroupRepository groupRepository) {
        this.shoppingListRepository = shoppingListRepository;
        this.groupRepository = groupRepository;
    }

    public List<ShoppingList> getAllLists() {
        return shoppingListRepository.findAll();
    }

    public List<ShoppingList> getListsByGroup(Integer groupId) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found: " + groupId));

        return shoppingListRepository.findByGroup(group);
    }

    public Optional<ShoppingList> getListById(Integer id) {
        return shoppingListRepository.findById(id);
    }

    public ShoppingList createList(Integer groupId, String title) {

        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found: " + groupId));

        ShoppingList sl = new ShoppingList();
        sl.setGroup(group);
        sl.setTitle(title);
        sl.setCreatedAt(LocalDateTime.now());

        return shoppingListRepository.save(sl);
    }

    public void deleteList(Integer id) {
        if (!shoppingListRepository.existsById(id)) {
            throw new RuntimeException("List not found: " + id);
        }
        shoppingListRepository.deleteById(id);
    }
}