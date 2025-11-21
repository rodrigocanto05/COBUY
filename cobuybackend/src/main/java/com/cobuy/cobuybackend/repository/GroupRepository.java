package com.cobuy.cobuybackend.repository;

import com.cobuy.cobuybackend.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, Integer> {

    Optional<Group> findByCode(String code);
}