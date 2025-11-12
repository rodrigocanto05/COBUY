package com.cobuy.cobuybackend.repository;

import com.cobuy.cobuybackend.model.ListItem;
import com.cobuy.cobuybackend.model.ShoppingList;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ListItemRepository extends JpaRepository<ListItem, Integer> {

    // todos os itens de uma lista
    List<ListItem> findByList(ShoppingList list);

    List<ListItem> findByListAndDone(ShoppingList list, Boolean done);
}