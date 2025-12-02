package com.cobuy.cobuybackend.repository;

import com.cobuy.cobuybackend.model.Supermarket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SupermarketRepository extends JpaRepository<Supermarket, Integer> {

    Optional<Supermarket> findByName(String name);

    @Query("SELECT s FROM Supermarket s WHERE s.lat = :lat AND s.lng = :lng")
    Optional<Supermarket> findByCoordinates(Double lat, Double lng);
}