package com.cobuy.cobuybackend.repository;

import com.cobuy.cobuybackend.model.Supermarket;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SupermarketRepository extends JpaRepository<Supermarket, Integer> {
  Optional<Supermarket> findByName(String name);
}