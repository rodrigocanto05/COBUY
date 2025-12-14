package com.cobuy.cobuybackend.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "list_items")
public class ListItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "li_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "li_lst_id", referencedColumnName = "lst_id", nullable = false)
    private ShoppingList list;

    @ManyToOne
    @JoinColumn(name = "li_item_id", referencedColumnName = "it_id", nullable = false)
    private Item item;

    @ManyToOne
    @JoinColumn(name = "li_usr_id", referencedColumnName = "usr_id", nullable = false)
    private User user;

    @Column(name = "li_qty", precision = 10, scale = 2)
    private BigDecimal qty;

    @ManyToOne
    @JoinColumn(name = "li_unit_id", referencedColumnName = "uni_id", nullable = false)
    private Unit unit;

    @Column(name = "li_done", nullable = false)
    private Boolean done = false;

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

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }
}