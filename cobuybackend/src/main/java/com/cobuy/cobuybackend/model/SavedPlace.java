package com.cobuy.cobuybackend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "saved_places")
public class SavedPlace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sav_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "sav_usr_id", referencedColumnName = "usr_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "sav_sup_id", referencedColumnName = "sup_id", nullable = false)
    private Supermarket supermarket;

    @Column(name = "sav_label")
    private String label;

    @Column(name = "sav_distance")
    private Double distance;       

    @Column(name = "sav_created_at")
    private LocalDateTime createdAt;

    @PrePersist
    void onInsert() {
        if (createdAt == null) createdAt = LocalDateTime.now();
    }

    // GETTERS & SETTERS
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Supermarket getSupermarket() { return supermarket; }
    public void setSupermarket(Supermarket supermarket) { this.supermarket = supermarket; }

    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }

    public Double getDistance() { return distance; }
    public void setDistance(Double distance) { this.distance = distance; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}