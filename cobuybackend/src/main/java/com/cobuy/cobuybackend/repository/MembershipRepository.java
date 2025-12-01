package com.cobuy.cobuybackend.repository;

import com.cobuy.cobuybackend.model.Membership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MembershipRepository extends JpaRepository<Membership, Integer> {

    @Query("SELECT m FROM Membership m JOIN FETCH m.user WHERE m.group.id = :groupId")
    List<Membership> findByGroupId(@Param("groupId") Integer groupId);

    @Query("SELECT m FROM Membership m WHERE m.user.id = :userId")
    List<Membership> findByUserId(@Param("userId") Integer userId);

    @Query("SELECT m FROM Membership m WHERE m.user.id = :userId AND m.group.id = :groupId")
    Optional<Membership> findByUserIdAndGroupId(
            @Param("userId") Integer userId,
            @Param("groupId") Integer groupId
    );
}
