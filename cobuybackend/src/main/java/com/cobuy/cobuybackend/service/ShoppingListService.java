package com.cobuy.cobuybackend.service;

import com.cobuy.cobuybackend.model.Group;
import com.cobuy.cobuybackend.model.ShoppingList;
import com.cobuy.cobuybackend.repository.ShoppingListRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingListService {

    private final ShoppingListRepository shoppingListRepository;

    public ShoppingListService(ShoppingListRepository shoppingListRepository) {
        this.shoppingListRepository = shoppingListRepository;
    }

    public List<ShoppingList> getAllLists() {
        return shoppingListRepository.findAll();
    }

    public List<ShoppingList> getListsByGroup(Group group) {
        return shoppingListRepository.findByGroup(group);
    }

    public Optional<ShoppingList> getListById(Integer id) {
        return shoppingListRepository.findById(id);
    }

    public ShoppingList createList(ShoppingList list) {
        list.setCreatedAt(LocalDateTime.now());
        return shoppingListRepository.save(list);
    }

    public void deleteList(Integer id) {
        shoppingListRepository.deleteById(id);
    }
}
