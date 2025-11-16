package com.cobuy.cobuybackend.controller;

import com.cobuy.cobuybackend.model.Group;
import com.cobuy.cobuybackend.model.Membership;
import com.cobuy.cobuybackend.model.User;
import com.cobuy.cobuybackend.repository.GroupRepository;
import com.cobuy.cobuybackend.repository.MembershipRepository;
import com.cobuy.cobuybackend.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController {

    private final GroupRepository groupRepository;
    private final MembershipRepository membershipRepository;
    private final UserRepository userRepository;

    public GroupController(GroupRepository groupRepository,
                           MembershipRepository membershipRepository,
                           UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.membershipRepository = membershipRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Group> getGroupById(@PathVariable Integer id) {
        return groupRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /groups?userId=1
    @PostMapping
    public ResponseEntity<?> createGroup(
            @RequestParam Integer userId,
            @RequestBody Group group) {

        // 1) Criar o grupo
        group.setCreatedAt(LocalDateTime.now());
        Group savedGroup = groupRepository.save(group);

        // 2) Buscar o utilizador que criou
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        // 3) Criar a membership como "owner"
        Membership membership = new Membership();
        membership.setGroup(savedGroup);
        membership.setUser(user);
        membership.setRole("owner");

        membershipRepository.save(membership);

        // 4) Devolver o grupo criado
        return ResponseEntity.status(HttpStatus.CREATED).body(savedGroup);
    }
}
