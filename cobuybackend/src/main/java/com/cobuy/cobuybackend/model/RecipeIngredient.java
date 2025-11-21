package com.cobuy.cobuybackend.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "recipe_ingredients")
public class RecipeIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rgi_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "rgi_rec_id", referencedColumnName = "rec_id", nullable = false)
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "rgi_ing_id", referencedColumnName = "ing_id", nullable = false)
    private Ingredient ingredient;

    @Column(name = "rgi_qty", nullable = false, precision = 10, scale = 2)
    private BigDecimal qty;

    @ManyToOne
    @JoinColumn(name = "rgi_unit_id", referencedColumnName = "uni_id", nullable = false)
    private Unit unit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}