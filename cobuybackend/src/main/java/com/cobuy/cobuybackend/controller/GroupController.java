package com.cobuy.cobuybackend.controller;

import com.cobuy.cobuybackend.model.Group;
import com.cobuy.cobuybackend.model.Membership;
import com.cobuy.cobuybackend.model.User;
import com.cobuy.cobuybackend.repository.GroupRepository;
import com.cobuy.cobuybackend.repository.MembershipRepository;
import com.cobuy.cobuybackend.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public Group getGroupById(@PathVariable Integer id) {
        return groupRepository.findById(id).orElse(null);
    }

    @PostMapping
    public ResponseEntity<Group> createGroup(@RequestBody Group group,
                                             @AuthenticationPrincipal User requester) {
        group.setCreatedAt(LocalDateTime.now());
        Group savedGroup = groupRepository.save(group);

        if (requester != null) {
            Membership membership = new Membership();
            membership.setGroup(savedGroup);
            membership.setUser(requester);
            membership.setRole("owner");
            membershipRepository.save(membership);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(savedGroup);
    }
}
