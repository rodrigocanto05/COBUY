package com.cobuy.cobuybackend.model;

import jakarta.persistence.*;

@Entity
@Table(
    name = "ingredients",
    uniqueConstraints = {
        @UniqueConstraint(name = "uq_ing_name", columnNames = "ing_name")
    }
)
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ing_id")
    private Integer id;

    @Column(name = "ing_name", nullable = false, length = 120)
    private String name;

    @ManyToOne
    @JoinColumn(
        name = "ing_unit_id",
        referencedColumnName = "uni_id",
        nullable = false
    )
    private Unit unit;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}