package com.cobuy.cobuybackend.controller;

import com.cobuy.cobuybackend.dto.AddRecipeIngredientRequest;
import com.cobuy.cobuybackend.model.Recipe;
import com.cobuy.cobuybackend.model.RecipeIngredient;
import com.cobuy.cobuybackend.repository.RecipeIngredientRepository;
import com.cobuy.cobuybackend.repository.RecipeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recipes")
public class RecipeIngredientController {

    private final RecipeRepository recipeRepository;
    private final RecipeIngredientRepository ingredientRepository;

    public RecipeIngredientController(RecipeRepository recipeRepository,
                                      RecipeIngredientRepository ingredientRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @PostMapping("/{id}/ingredients")
    public ResponseEntity<?> addIngredient(@PathVariable Integer id,
                                           @RequestBody AddRecipeIngredientRequest req) {
        Recipe r = recipeRepository.findById(id).orElse(null);
        if (r == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recipe not found");

        if (req == null || req.name == null || req.name.isBlank() || req.qtyServing == null) {
            return ResponseEntity.badRequest().body("Fields 'name' and 'qtyServing' are required");
        }

        RecipeIngredient ing = new RecipeIngredient();
        ing.setRecipe(r);
        ing.setName(req.name.trim());
        ing.setQtyServing(req.qtyServing);
        ing.setUnit((req.unit == null || req.unit.isBlank()) ? null : req.unit.trim());

        return ResponseEntity.status(HttpStatus.CREATED).body(ingredientRepository.save(ing));
    }

    @DeleteMapping("/{id}/ingredients/{ingredientId}")
    public ResponseEntity<?> deleteIngredient(@PathVariable Integer id,
                                              @PathVariable Integer ingredientId) {
        Recipe r = recipeRepository.findById(id).orElse(null);
        if (r == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recipe not found");

        return ingredientRepository.findById(ingredientId)
                .map(ing -> {
                    if (!ing.getRecipe().getId().equals(r.getId())) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body("Ingredient doesn't belong to this recipe");
                    }
                    ingredientRepository.delete(ing);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ingredient not found"));
    }
}