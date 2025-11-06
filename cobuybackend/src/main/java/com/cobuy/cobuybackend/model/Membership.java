package com.cobuy.cobuybackend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "memberships")
public class Membership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mem_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "mem_usr_id", referencedColumnName = "usr_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "mem_grp_id", referencedColumnName = "grp_id")
    private Group group;

    @Column(name = "mem_role")
    private String role; // 'owner' / 'member'

    @Column(name = "mem_joined_at")
    private LocalDateTime joinedAt;

    @PrePersist
    public void prePersist() {
        if (joinedAt == null) joinedAt = LocalDateTime.now();
        if (role == null) role = "member";
    }

    // Getters e Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Group getGroup() { return group; }
    public void setGroup(Group group) { this.group = group; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public LocalDateTime getJoinedAt() { return joinedAt; }
    public void setJoinedAt(LocalDateTime joinedAt) { this.joinedAt = joinedAt; }
}