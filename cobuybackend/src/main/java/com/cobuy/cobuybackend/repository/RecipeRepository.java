package com.cobuy.cobuybackend.repository;

import com.cobuy.cobuybackend.model.Recipe;
import com.cobuy.cobuybackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
    List<Recipe> findByUser(User user);
}