package com.cobuy.cobuybackend.repository;

import com.cobuy.cobuybackend.model.Recipe;
import com.cobuy.cobuybackend.model.RecipeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, Integer> {
    List<RecipeIngredient> findByRecipe(Recipe recipe);
}