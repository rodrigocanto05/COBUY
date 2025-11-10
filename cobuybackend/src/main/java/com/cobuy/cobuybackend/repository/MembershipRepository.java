package com.cobuy.cobuybackend.repository;

import com.cobuy.cobuybackend.model.Membership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MembershipRepository extends JpaRepository<Membership, Integer> {

    // buscar todos os membros de um grupo
    List<Membership> findByGroupId(Integer groupId);

    // buscar todos os grupos onde um user está
    List<Membership> findByUserId(Integer userId);

    // buscar relação específica user-grupo
    Optional<Membership> findByUserIdAndGroupId(Integer userId, Integer groupId);
}
