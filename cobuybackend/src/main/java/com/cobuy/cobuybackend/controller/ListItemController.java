package com.cobuy.cobuybackend.controller;

import com.cobuy.cobuybackend.model.ListItem;
import com.cobuy.cobuybackend.model.ShoppingList;
import com.cobuy.cobuybackend.repository.ListItemRepository;
import com.cobuy.cobuybackend.repository.ShoppingListRepository;
import org.springframework.web.bind.annotation.*;

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

    // GET /lists/{listId}/items
    @GetMapping("/{listId}/items")
    public List<ListItem> getItemsForList(
            @PathVariable Integer listId,
            @RequestParam(name = "onlyOpen", required = false) Boolean onlyOpen
    ) {
        ShoppingList list = shoppingListRepository.findById(listId).orElse(null);
        if (list == null) {
            return List.of(); // lista não existe -> devolve []
        }

        if (Boolean.TRUE.equals(onlyOpen)) {
            // só itens ainda por comprar (done = false)
            return listItemRepository.findByListAndDone(list, false);
        }

        // todos os itens da lista
        return listItemRepository.findByList(list);
    }
}