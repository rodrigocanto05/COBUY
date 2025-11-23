package com.cobuy.cobuybackend.controller;

import com.cobuy.cobuybackend.model.*;
import com.cobuy.cobuybackend.repository.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipes")
public class RecipeIngredientController {

    private final RecipeRepository recipeRepository;
    private final RecipeIngredientRepository recipeIngredientRepository;
    private final ShoppingListRepository shoppingListRepository;
    private final ListItemRepository listItemRepository;
    private final UserRepository userRepository;
    private final MembershipRepository membershipRepository;
    private final ItemRepository itemRepository;   // <-- ADICIONADO

    public RecipeIngredientController(
            RecipeRepository recipeRepository,
            RecipeIngredientRepository recipeIngredientRepository,
            ShoppingListRepository shoppingListRepository,
            ListItemRepository listItemRepository,
            UserRepository userRepository,
            MembershipRepository membershipRepository,
            ItemRepository itemRepository
    ) {
        this.recipeRepository = recipeRepository;
        this.recipeIngredientRepository = recipeIngredientRepository;
        this.shoppingListRepository = shoppingListRepository;
        this.listItemRepository = listItemRepository;
        this.userRepository = userRepository;
        this.membershipRepository = membershipRepository;
        this.itemRepository = itemRepository;
    }

    public record AddToListRequest(Integer userId, List<Integer> ingredients) {}

    @GetMapping("/{id}/ingredients")
    public ResponseEntity<?> getIngredients(@PathVariable Integer id) {

        Recipe recipe = recipeRepository.findById(id).orElse(null);
        if (recipe == null)
            return ResponseEntity.status(404).body("Recipe not found");

        List<RecipeIngredient> list = recipeIngredientRepository.findByRecipe(recipe);

        return ResponseEntity.ok(
                list.stream().map(ri -> new Object() {
                    public final Integer id = ri.getId();
                    public final String ingredient = ri.getIngredient().getName();
                    public final String unit = ri.getUnit().getName();
                    public final Number qty = ri.getQty();
                }).toList()
        );
    }

    @PostMapping("/{recipeId}/add-to-list/{listId}")
    public ResponseEntity<?> addRecipeItemsToList(
            @PathVariable Integer recipeId,
            @PathVariable Integer listId,
            @RequestBody AddToListRequest body
    ) {
        Recipe recipe = recipeRepository.findById(recipeId).orElse(null);
        if (recipe == null)
            return ResponseEntity.status(404).body("Recipe not found");

        ShoppingList list = shoppingListRepository.findById(listId).orElse(null);
        if (list == null)
            return ResponseEntity.status(404).body("List not found");

        Integer groupId = list.getGroup().getId();

        if (membershipRepository.findByUserIdAndGroupId(body.userId(), groupId).isEmpty())
            return ResponseEntity.status(403).body("User not in group");

        List<RecipeIngredient> selected = recipeIngredientRepository.findAllById(body.ingredients());

        for (RecipeIngredient ri : selected) {

            // 1. Converter Ingredient → Item
            Item item = itemRepository.findByName(ri.getIngredient().getName()).orElse(null);

            if (item == null) {
                item = new Item();
                item.setName(ri.getIngredient().getName());
                item.setUnit(ri.getUnit());
                item = itemRepository.save(item);
            }

            // 2. Criar ListItem
            ListItem li = new ListItem();
            li.setList(list);
            li.setItem(item);
            li.setQty(ri.getQty());
            li.setUnit(ri.getUnit());
            li.setUser(userRepository.findById(body.userId()).orElse(null));
            li.setDone(false);

            listItemRepository.save(li);
        }

        return ResponseEntity.ok("Ingredients added to list");
    }
}