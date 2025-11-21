package com.cobuy.cobuybackend.repository;

import com.cobuy.cobuybackend.model.Membership;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MembershipRepository extends JpaRepository<Membership, Integer> {

    // todos os membros de um grupo
    List<Membership> findByGroupId(Integer groupId);

    // todos os grupos onde um user está
    List<Membership> findByUserId(Integer userId);

    // relação específica user-grupo
    Optional<Membership> findByUserIdAndGroupId(Integer userId, Integer groupId);
}