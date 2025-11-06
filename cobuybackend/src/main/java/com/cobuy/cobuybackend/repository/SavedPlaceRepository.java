package com.cobuy.cobuybackend.repository;

import com.cobuy.cobuybackend.model.SavedPlace;
import com.cobuy.cobuybackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SavedPlaceRepository extends JpaRepository<SavedPlace, Integer> {
    List<SavedPlace> findByUser(User user);
    Optional<SavedPlace> findByUser_IdAndSupermarket_Id(Integer userId, Integer supermarketId);
}