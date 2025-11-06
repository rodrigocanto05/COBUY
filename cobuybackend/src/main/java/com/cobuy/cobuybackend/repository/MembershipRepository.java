package com.cobuy.cobuybackend.repository;

import com.cobuy.cobuybackend.model.Membership;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MembershipRepository extends JpaRepository<Membership, Integer> {
    List<Membership> findByGroupId(Integer groupId);
    Optional<Membership> findByUserIdAndGroupId(Integer userId, Integer groupId);
}