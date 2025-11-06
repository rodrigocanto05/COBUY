package com.cobuy.cobuybackend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rec_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "rec_usr_id", referencedColumnName = "usr_id", nullable = true)
    private User user;         // pode ser null (ON DELETE SET NULL)

    @Column(name = "rec_name", nullable = false, length = 120)
    private String name;

    @Column(name = "rec_serves", nullable = false)
    private Integer serves;

    // getters/setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Integer getServes() { return serves; }
    public void setServes(Integer serves) { this.serves = serves; }
}