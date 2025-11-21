package com.cobuy.cobuybackend.repository;

import com.cobuy.cobuybackend.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
    // Por agora, sem filtros por user para não rebentar o arranque
}