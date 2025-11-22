package com.cobuy.cobuybackend.repository;

import com.cobuy.cobuybackend.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Integer> {

    Optional<Item> findByName(String name);

}