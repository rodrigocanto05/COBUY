package com.cobuy.cobuybackend.repository;

import com.cobuy.cobuybackend.model.ShoppingList;
import com.cobuy.cobuybackend.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ShoppingListRepository extends JpaRepository<ShoppingList, Integer> {
    List<ShoppingList> findByGroup(Group group);
}