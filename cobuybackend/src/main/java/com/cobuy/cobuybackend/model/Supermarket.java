package com.cobuy.cobuybackend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "supermarkets")
public class Supermarket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sup_id")
    private Integer id;

    @Column(name = "sup_name")
    private String name;

    @Column(name = "sup_rating")
    private Double rating;      

    @Column(name = "sup_distance")
    private Double distance;  

    public Supermarket() {}   

    // GETTERS
    public Integer getId() { return id; }
    public String getName() { return name; }
    public Double getRating() { return rating; }
    public Double getDistance() { return distance; }

    // SETTERS
    public void setId(Integer id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setRating(Double rating) { this.rating = rating; }
    public void setDistance(Double distance) { this.distance = distance; }
}