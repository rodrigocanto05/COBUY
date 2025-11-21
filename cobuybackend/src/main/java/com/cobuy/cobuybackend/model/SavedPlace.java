package com.cobuy.cobuybackend.model;

import jakarta.persistence.*;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Supermarket getSupermarket() {
        return supermarket;
    }

    public void setSupermarket(Supermarket supermarket) {
        this.supermarket = supermarket;
    }
}