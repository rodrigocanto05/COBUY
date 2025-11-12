package com.cobuy.cobuybackend.controller;

import com.cobuy.cobuybackend.model.*;
import com.cobuy.cobuybackend.repository.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;
    private final RecipeIngredientRepository ingredientRepository;

    public RecipeController(RecipeRepository recipeRepository,
                            UserRepository userRepository,
                            RecipeIngredientRepository ingredientRepository) {
        this.recipeRepository = recipeRepository;
        this.userRepository = userRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @GetMapping
    public List<Recipe> getAll(@RequestParam(value = "userId", required = false) Integer userId) {
        if (userId == null) return recipeRepository.findAll();
        User u = userRepository.findById(userId).orElse(null);
        return (u == null) ? List.of() : recipeRepository.findByUser(u);
    }

    @GetMapping("/{id}/ingredients")
    public ResponseEntity<?> getIngredients(@PathVariable Integer id) {
        Recipe r = recipeRepository.findById(id).orElse(null);
        if (r == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(ingredientRepository.findByRecipe(r));
    }
}