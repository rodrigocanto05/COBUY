package com.cobuy.cobuybackend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "list_items")
public class ListItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itm_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "itm_lst_id", referencedColumnName = "lst_id")
    private ShoppingList list; // a que lista este item pertence

    @Column(name = "itm_name")
    private String name;

    @Column(name = "itm_qty")
    private Double qty; // DECIMAL(10,2) -> usamos Double

    @Column(name = "itm_unit")
    private String unit; // un, kg, ml...

    @Column(name = "itm_done")
    private Boolean done; // 0/1 -> Boolean

    @Column(name = "itm_updated_at")
    private LocalDateTime updatedAt;

    // GETTERS & SETTERS
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ShoppingList getList() {
        return list;
    }

    public void setList(ShoppingList list) {
        this.list = list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}