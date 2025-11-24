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
import java.util.Map;

@RestController
@RequestMapping("/api/groups")   // <- prefixo correto para o SecurityConfig
public class GroupController {

    private final GroupRepository groupRepository;
    private final MembershipRepository membershipRepository;
    private final UserRepository userRepository;
    private final SecureRandom random = new SecureRandom();

    public GroupController(
            GroupRepository groupRepository,
            MembershipRepository membershipRepository,
            UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.membershipRepository = membershipRepository;
        this.userRepository = userRepository;
    }

    // -------------------------------------------------------
    // GET ALL GROUPS
    // -------------------------------------------------------
    @GetMapping
    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    // -------------------------------------------------------
    // GET GROUP BY ID
    // -------------------------------------------------------
    @GetMapping("/{id}")
    public ResponseEntity<?> getGroupById(@PathVariable Integer id) {
        var opt = groupRepository.findById(id);

        if (opt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Grupo não encontrado"));
        }

        return ResponseEntity.ok(opt.get());
    }

    // -------------------------------------------------------
    // GET GROUP BY CODE
    // -------------------------------------------------------
    @GetMapping("/code/{code}")
    public ResponseEntity<?> getGroupByCode(@PathVariable String code) {
        var opt = groupRepository.findByCode(code);

        if (opt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Código inválido"));
        }

        return ResponseEntity.ok(opt.get());
    }

    // -------------------------------------------------------
    // CREATE GROUP
    // -------------------------------------------------------
    @PostMapping("/create")
    public ResponseEntity<?> createGroup(
            @RequestParam Integer userId,
            @RequestBody Group groupBody) {

        var optUser = userRepository.findById(userId);
        if (optUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Utilizador não encontrado"));
        }

        User owner = optUser.get();

        Group group = new Group();
        group.setName(groupBody.getName());
        group.setOwner(owner);
        group.setCreatedAt(LocalDateTime.now());
        group.setCode(generateUniqueCode());

        Group saved = groupRepository.save(group);

        Membership membership = new Membership();
        membership.setGroup(saved);
        membership.setUser(owner);
        membership.setRole("owner");
        membershipRepository.save(membership);

        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // -------------------------------------------------------
    // JOIN GROUP BY CODE
    // -------------------------------------------------------
    @PostMapping("/join/{code}")
    public ResponseEntity<?> joinGroup(
            @PathVariable String code,
            @RequestParam Integer userId) {

        var optGroup = groupRepository.findByCode(code);
        if (optGroup.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Código inválido"));
        }

        var optUser = userRepository.findById(userId);
        if (optUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Utilizador não encontrado"));
        }

        Group group = optGroup.get();
        User user = optUser.get();

        if (membershipRepository.findByUserIdAndGroupId(userId, group.getId()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("error", "Já pertences a este grupo"));
        }

        Membership mem = new Membership();
        mem.setGroup(group);
        mem.setUser(user);
        mem.setRole("member");
        membershipRepository.save(mem);

        return ResponseEntity.status(HttpStatus.CREATED).body(group);
    }

    // -------------------------------------------------------
    // DELETE GROUP (ONLY OWNER)
    // -------------------------------------------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGroup(
            @PathVariable Integer id,
            @RequestParam Integer userId) {

        var optGroup = groupRepository.findById(id);
        if (optGroup.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Grupo não encontrado"));
        }

        Group group = optGroup.get();

        var optMembership = membershipRepository.findByUserIdAndGroupId(userId, id);

        if (optMembership.isEmpty() ||
                !"owner".equalsIgnoreCase(optMembership.get().getRole())) {

            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(Map.of("error", "Apenas o owner pode apagar o grupo"));
        }

        membershipRepository.deleteAll(membershipRepository.findByGroupId(id));
        groupRepository.delete(group);

        return ResponseEntity.noContent().build();
    }

    // -------------------------------------------------------
    // UNIQUE GROUP CODE GENERATOR
    // -------------------------------------------------------
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
