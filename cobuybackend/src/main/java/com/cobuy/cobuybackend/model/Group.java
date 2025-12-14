package com.cobuy.cobuybackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "groupss")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grp_id")
    private Integer id;

    @Column(name = "grp_name", nullable = false, length = 80)
    private String name;

    @ManyToOne
    @JoinColumn(name = "grp_owner_usr_id", referencedColumnName = "usr_id", nullable = false)
    private User owner;

    @Column(name = "grp_code", nullable = false, unique = true, length = 5)
    private String code;

    @Column(name = "grp_created_at", nullable = false)
    private LocalDateTime createdAt;

    @OneToMany(
        mappedBy = "group",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    @JsonIgnore
    private List<Membership> memberships;

    @OneToMany(
        mappedBy = "group",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    @JsonIgnore
    private List<ShoppingList> lists;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public User getOwner() { return owner; }
    public void setOwner(User owner) { this.owner = owner; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public List<Membership> getMemberships() { return memberships; }
    public void setMemberships(List<Membership> memberships) { this.memberships = memberships; }

    public List<ShoppingList> getLists() { return lists; }
    public void setLists(List<ShoppingList> lists) { this.lists = lists; }
}