package com.cobuy.cobuybackend.controller;

import com.cobuy.cobuybackend.model.Membership;
import com.cobuy.cobuybackend.model.User; // <-- FALTAVA
import com.cobuy.cobuybackend.repository.GroupRepository;
import com.cobuy.cobuybackend.repository.MembershipRepository;
import com.cobuy.cobuybackend.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal; // <-- FALTAVA
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // GET /groups/{groupId}/members
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
                .toList();
        return ResponseEntity.ok(dto);
    }

    // POST /memberships/{groupId}/add/{userId}?role=member|owner
    @PostMapping("/memberships/{groupId}/add/{userId}")
    public ResponseEntity<?> addMember(@AuthenticationPrincipal User requester,
                                       @PathVariable Integer groupId,
                                       @PathVariable Integer userId,
                                       @RequestParam(defaultValue = "member") String role) {

        // requester tem de ser 'owner' do grupo
        var owner = membershipRepo.findByUserIdAndGroupId(requester.getId(), groupId)
                                  .filter(m -> "owner".equalsIgnoreCase(m.getRole()));
        if (!owner.isPresent()) { // usa isPresent() para evitar o erro com Java 8
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

    // DELETE /memberships/{groupId}/remove/{userId}
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