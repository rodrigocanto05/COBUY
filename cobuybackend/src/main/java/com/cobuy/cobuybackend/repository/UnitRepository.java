package com.cobuy.cobuybackend.repository;

import com.cobuy.cobuybackend.model.Unit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UnitRepository extends JpaRepository<Unit, Integer> {

    Optional<Unit> findByName(String name);

    boolean existsByName(String name);
}