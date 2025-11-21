package com.cobuy.cobuybackend.repository;

import com.cobuy.cobuybackend.model.Item;
import com.cobuy.cobuybackend.model.Unit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {

    List<Item> findByNameContainingIgnoreCase(String name);

    List<Item> findByUnit(Unit unit);
}