package com.cobuy.cobuybackend.repository;

import com.cobuy.cobuybackend.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
}