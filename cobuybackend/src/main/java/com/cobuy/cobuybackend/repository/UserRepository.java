package com.cobuy.cobuybackend.repository;

import com.cobuy.cobuybackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}