package com.cobuy.cobuybackend.controller;

import com.cobuy.cobuybackend.model.Recipe;
import com.cobuy.cobuybackend.repository.RecipeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    private final RecipeRepository recipeRepository;

    public RecipeController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @GetMapping
    public List<Recipe> getAll(@RequestParam(value = "userId", required = false) Integer userId) {
        return recipeRepository.findAll();
    }

}