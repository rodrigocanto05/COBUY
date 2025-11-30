package com.cobuy.cobuybackend.controller;

import com.cobuy.cobuybackend.dto.JoinGroupRequest;
import com.cobuy.cobuybackend.model.Group;
import com.cobuy.cobuybackend.model.Membership;
import com.cobuy.cobuybackend.model.User;
import com.cobuy.cobuybackend.repository.GroupRepository;
import com.cobuy.cobuybackend.repository.MembershipRepository;
import com.cobuy.cobuybackend.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping ("/api")
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

    // DTOs
    public record GroupDTO(Integer id, String name, String role) {
    }

    public record MemberDTO(Integer id, String name, String email, String role) {
    }

    public record LeaveGroupRequest(Integer userId, Integer groupId) {
    }

    @GetMapping("/users/{userId}/memberships")
    public ResponseEntity<?> getUserGroups(@PathVariable Integer userId) {
        if (!userRepo.existsById(userId))
            return ResponseEntity.notFound().build();

        List<Membership> memberships = membershipRepo.findByUserId(userId);

        List<GroupDTO> dto = memberships.stream()
                .map(m -> new GroupDTO(
                        m.getGroup().getId(),
                        m.getGroup().getName(),
                        m.getRole()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/groups/{groupId}/members")
    public ResponseEntity<?> getMembers(@PathVariable Integer groupId) {
        if (!groupRepo.existsById(groupId))
            return ResponseEntity.notFound().build();

        List<Membership> memberships = membershipRepo.findByGroupId(groupId);

        List<MemberDTO> dto = memberships.stream()
                .map(m -> new MemberDTO(
                        m.getUser().getId(),
                        m.getUser().getName(),
                        m.getUser().getEmail(),
                        m.getRole()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(dto);
    }

    @PostMapping("/memberships/{groupId}/add/{userId}")
    public ResponseEntity<?> addMember(@PathVariable Integer groupId,
            @PathVariable Integer userId,
            @RequestParam(defaultValue = "member") String role) {
        return ResponseEntity.status(403)
                .body("Só se pode entrar num grupo através de código.");
    }

    @PostMapping("/memberships/join")
    public ResponseEntity<?> joinGroupByCode(@RequestBody JoinGroupRequest req) {

        if (req == null || req.code == null || req.code.isBlank() || req.userId == null)
            return ResponseEntity.badRequest().body("code e userId são obrigatórios");

        var groupOpt = groupRepo.findByCode(req.code.trim());
        if (groupOpt.isEmpty())
            return ResponseEntity.status(404).body("Código inválido");

        Group group = groupOpt.get();
        User user = userRepo.findById(req.userId).orElse(null);
        if (user == null)
            return ResponseEntity.status(404).body("User não existe");

        if (membershipRepo.findByUserIdAndGroupId(user.getId(), group.getId()).isPresent()) {
            return ResponseEntity.status(409).body("User já é membro deste grupo");
        }

        Membership mem = new Membership();
        mem.setGroup(group);
        mem.setUser(user);
        mem.setRole("member");
        membershipRepo.save(mem);

        return ResponseEntity.status(201).body("Entrou no grupo com sucesso");
    }

    @DeleteMapping("/memberships/leave")
    public ResponseEntity<?> leaveGroup(@RequestBody LeaveGroupRequest req) {

        if (req == null || req.userId == null || req.groupId == null)
            return ResponseEntity.badRequest().body("userId e groupId são obrigatórios");

        var memOpt = membershipRepo.findByUserIdAndGroupId(req.userId, req.groupId);
        if (memOpt.isEmpty())
            return ResponseEntity.status(404).body("Não pertence ao grupo");

        Membership leaving = memOpt.get();
        Group group = leaving.getGroup();
        boolean isOwner = "owner".equalsIgnoreCase(leaving.getRole());

        List<Membership> all = membershipRepo.findByGroupId(req.groupId);
        List<Membership> others = all.stream()
                .filter(m -> !m.getId().equals(leaving.getId()))
                .collect(Collectors.toList());

        if (isOwner) {
            if (others.isEmpty()) {
                membershipRepo.delete(leaving);
                groupRepo.delete(group);
                return ResponseEntity.noContent().build();
            } else {
                Membership newOwner = others.stream()
                        .min(Comparator.comparing(Membership::getJoinedAt))
                        .orElseThrow();

                newOwner.setRole("owner");
                membershipRepo.save(newOwner);

                group.setOwner(newOwner.getUser());
                groupRepo.save(group);
            }
        }

        membershipRepo.delete(leaving);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/memberships/{groupId}/remove/{userId}")
    public ResponseEntity<?> remove(@PathVariable Integer groupId,
            @PathVariable Integer userId,
            @RequestParam Integer requesterId) {

        var requesterMemOpt = membershipRepo.findByUserIdAndGroupId(requesterId, groupId);
        if (requesterMemOpt.isEmpty() ||
                !"owner".equalsIgnoreCase(requesterMemOpt.get().getRole())) {
            return ResponseEntity.status(403).body("Só o owner pode remover membros.");
        }

        if (requesterId.equals(userId)) {
            return ResponseEntity.badRequest().body("Owner deve usar /memberships/leave para sair.");
        }

        var mem = membershipRepo.findByUserIdAndGroupId(userId, groupId);
        if (mem.isEmpty())
            return ResponseEntity.status(404).body("Membro não encontrado");

        membershipRepo.delete(mem.get());
        return ResponseEntity.noContent().build();
    }
}