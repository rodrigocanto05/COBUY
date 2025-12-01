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

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/groups")
public class GroupController {

    private final GroupRepository groupRepository;
    private final MembershipRepository membershipRepository;
    private final UserRepository userRepository;
    private final SecureRandom random = new SecureRandom();

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

    // >>> NOVO ENDPOINT: lista de membros de um grupo <<<
    @GetMapping("/{id}/members")
public ResponseEntity<List<Membership>> getGroupMembers(@PathVariable Integer id) {
    if (groupRepository.findById(id).isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    List<Membership> members = membershipRepository.findByGroupId(id);
    return ResponseEntity.ok(members);
}

    @GetMapping("/code/{code}")
    public ResponseEntity<Group> getGroupByCode(@PathVariable String code) {
        return groupRepository.findByCode(code)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createGroup(
            @RequestParam Integer userId,
            @RequestBody Group groupBody) {

        User owner = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        Group group = new Group();
        group.setName(groupBody.getName());
        group.setOwner(owner);
        group.setCreatedAt(LocalDateTime.now());
        group.setCode(generateUniqueCode()); // <--- código gerado aqui

        Group savedGroup = groupRepository.save(group);

        Membership membership = new Membership();
        membership.setGroup(savedGroup);
        membership.setUser(owner);
        membership.setRole("owner");
        membershipRepository.save(membership);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedGroup);
    }

    @PostMapping("/join/{code}")
    public ResponseEntity<?> joinGroup(
            @PathVariable String code,
            @RequestParam Integer userId) {

        var optGroup = groupRepository.findByCode(code);
        if (optGroup.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Grupo com código " + code + " não existe");
        }

        var optUser = userRepository.findById(userId);
        if (optUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Utilizador não encontrado");
        }

        Group group = optGroup.get();
        User user = optUser.get();

        if (membershipRepository.findByUserIdAndGroupId(userId, group.getId()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("User já está neste grupo");
        }

        Membership mem = new Membership();
        mem.setGroup(group);
        mem.setUser(user);
        mem.setRole("member");

        membershipRepository.save(mem);

        return ResponseEntity.status(HttpStatus.CREATED).body(group);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGroup(
            @PathVariable Integer id,
            @RequestParam Integer userId) {

        Group group = groupRepository.findById(id).orElse(null);
        if (group == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Grupo não encontrado");

        var membership = membershipRepository.findByUserIdAndGroupId(userId, id);
        if (membership.isEmpty() || !"owner".equalsIgnoreCase(membership.get().getRole())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Apenas o owner pode apagar o grupo");
        }

        membershipRepository.deleteAll(membershipRepository.findByGroupId(id));
        groupRepository.delete(group);

        return ResponseEntity.noContent().build();
    }

    private String generateUniqueCode() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        while (true) {
            StringBuilder sb = new StringBuilder(5);
            for (int i = 0; i < 5; i++) {
                sb.append(chars.charAt(random.nextInt(chars.length())));
            }
            String code = sb.toString();

            if (groupRepository.findByCode(code).isEmpty()) {
                return code;
            }
        }
    }
}
