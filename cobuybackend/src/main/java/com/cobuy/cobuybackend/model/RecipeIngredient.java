package com.cobuy.cobuybackend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "recipe_ingredients")
public class RecipeIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rin_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "rin_rec_id", referencedColumnName = "rec_id", nullable = false)
    private Recipe recipe;

    @Column(name = "rin_name", nullable = false, length = 120)
    private String name;

    @Column(name = "rin_qty_serving", nullable = false)
    private Double qtyServing;     // DECIMAL(10,2) → Double para simplificar

    @Column(name = "rin_unit", length = 16)
    private String unit;

    // getters/setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Recipe getRecipe() { return recipe; }
    public void setRecipe(Recipe recipe) { this.recipe = recipe; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Double getQtyServing() { return qtyServing; }
    public void setQtyServing(Double qtyServing) { this.qtyServing = qtyServing; }
    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }
}