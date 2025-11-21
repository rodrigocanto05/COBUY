package com.cobuy.cobuybackend.repository;

import com.cobuy.cobuybackend.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {

    boolean existsByName(String name);

    Ingredient findByName(String name);
}