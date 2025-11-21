package com.cobuy.cobuybackend.controller;

import com.cobuy.cobuybackend.model.Ingredient;
import com.cobuy.cobuybackend.model.Recipe;
import com.cobuy.cobuybackend.model.RecipeIngredient;
import com.cobuy.cobuybackend.model.Unit;
import com.cobuy.cobuybackend.repository.IngredientRepository;
import com.cobuy.cobuybackend.repository.RecipeIngredientRepository;
import com.cobuy.cobuybackend.repository.RecipeRepository;
import com.cobuy.cobuybackend.repository.UnitRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/recipes")
public class RecipeIngredientController {

    private final RecipeRepository recipeRepository;
    private final RecipeIngredientRepository ingredientRepository;
    private final IngredientRepository ingredientMasterRepository;
    private final UnitRepository unitRepository;

    public RecipeIngredientController(
            RecipeRepository recipeRepository,
            RecipeIngredientRepository ingredientRepository,
            IngredientRepository ingredientMasterRepository,
            UnitRepository unitRepository
    ) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.ingredientMasterRepository = ingredientMasterRepository;
        this.unitRepository = unitRepository;
    }

    // DTO para adicionar ingrediente à receita
    public record AddRecipeIngredientRequest(
            Integer ingredientId,
            Integer unitId,
            BigDecimal qty
    ) {}

    @PostMapping("/{id}/ingredients")
    public ResponseEntity<?> addIngredient(
            @PathVariable Integer id,
            @RequestBody AddRecipeIngredientRequest req
    ) {
        Recipe recipe = recipeRepository.findById(id).orElse(null);
        if (recipe == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Recipe not found");
        }

        if (req == null || req.ingredientId() == null || req.unitId() == null || req.qty() == null) {
            return ResponseEntity.badRequest()
                    .body("Fields 'ingredientId', 'unitId' e 'qty' são obrigatórios");
        }

        Ingredient ingredient = ingredientMasterRepository.findById(req.ingredientId())
                .orElse(null);
        if (ingredient == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ingredient not found: " + req.ingredientId());
        }

        Unit unit = unitRepository.findById(req.unitId())
                .orElse(null);
        if (unit == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Unit not found: " + req.unitId());
        }

        RecipeIngredient ri = new RecipeIngredient();
        ri.setRecipe(recipe);
        ri.setIngredient(ingredient);
        ri.setUnit(unit);
        ri.setQty(req.qty());

        RecipeIngredient saved = ingredientRepository.save(ri);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @DeleteMapping("/{id}/ingredients/{ingredientId}")
    public ResponseEntity<?> deleteIngredient(
            @PathVariable Integer id,
            @PathVariable Integer ingredientId
    ) {
        Recipe recipe = recipeRepository.findById(id).orElse(null);
        if (recipe == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Recipe not found");
        }

        return ingredientRepository.findById(ingredientId)
                .map(ri -> {
                    if (!ri.getRecipe().getId().equals(recipe.getId())) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body("Ingredient doesn't belong to this recipe");
                    }
                    ingredientRepository.delete(ri);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("RecipeIngredient not found"));
    }
}