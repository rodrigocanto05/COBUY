package com.cobuy.cobuybackend.controller;

import com.cobuy.cobuybackend.model.Membership;
import com.cobuy.cobuybackend.model.User;
import com.cobuy.cobuybackend.repository.GroupRepository;
import com.cobuy.cobuybackend.repository.MembershipRepository;
import com.cobuy.cobuybackend.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping
public class MembershipController {

    private final MembershipRepository membershipRepo;
    private final GroupRepository groupRepo;
    private final UserRepository userRepo;

    public MembershipController(MembershipRepository membershipRepo,
                                GroupRepository groupRepo,
                                UserRepository userRepo) {
        this.membershipRepo = membershipRepo;
        this.groupRepo = groupRepo;
        this.userRepo = userRepo;
    }

    @GetMapping("/users/{userId}/memberships")
    public ResponseEntity<List<GroupDTO>> getUserGroups(@PathVariable Integer userId) {
        if (!userRepo.existsById(userId)) {
            return ResponseEntity.notFound().build();
        }

        var memberships = membershipRepo.findByUserId(userId);
        var dto = memberships.stream()
                .map(m -> new GroupDTO(
                        m.getGroup().getId(),
                        m.getGroup().getName(),
                        m.getRole()
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(dto);
    }

    public record GroupDTO(Integer id, String name, String role) {}

    @GetMapping("/groups/{groupId}/members")
    public ResponseEntity<List<MemberDTO>> getMembers(@PathVariable Integer groupId) {
        if (!groupRepo.existsById(groupId)) {
            return ResponseEntity.notFound().build();
        }
        var dto = membershipRepo.findByGroupId(groupId).stream()
                .map(m -> new MemberDTO(
                        m.getUser().getId(),
                        m.getUser().getName(),
                        m.getUser().getEmail(),
                        m.getRole()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/memberships/{groupId}/add/{userId}")
    public ResponseEntity<?> addMember(@AuthenticationPrincipal User requester,
                                       @PathVariable Integer groupId,
                                       @PathVariable Integer userId,
                                       @RequestParam(defaultValue = "member") String role) {

        var owner = membershipRepo.findByUserIdAndGroupId(requester.getId(), groupId)
                .filter(m -> "owner".equalsIgnoreCase(m.getRole()));
        if (!owner.isPresent()) {
            return ResponseEntity.status(403).build();
        }

        if (membershipRepo.findByUserIdAndGroupId(userId, groupId).isPresent()) {
            return ResponseEntity.status(409).body("Já é membro");
        }

        var g = groupRepo.findById(groupId).orElse(null);
        var u = userRepo.findById(userId).orElse(null);
        if (g == null || u == null) return ResponseEntity.notFound().build();

        var mem = new Membership();
        mem.setGroup(g);
        mem.setUser(u);
        mem.setRole(role);
        membershipRepo.save(mem);

        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/memberships/{groupId}/remove/{userId}")
    public ResponseEntity<Void> remove(@PathVariable Integer groupId,
                                       @PathVariable Integer userId) {
        var mem = membershipRepo.findByUserIdAndGroupId(userId, groupId);
        if (!mem.isPresent()) return ResponseEntity.notFound().build();

        membershipRepo.delete(mem.get());
        return ResponseEntity.noContent().build();
    }

    public record MemberDTO(Integer id, String name, String email, String role) {}
}
