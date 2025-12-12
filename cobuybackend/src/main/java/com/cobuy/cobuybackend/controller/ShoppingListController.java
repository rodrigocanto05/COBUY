package com.cobuy.cobuybackend.controller;

import com.cobuy.cobuybackend.model.Group;
import com.cobuy.cobuybackend.model.Membership;
import com.cobuy.cobuybackend.model.ShoppingList;
import com.cobuy.cobuybackend.repository.GroupRepository;
import com.cobuy.cobuybackend.repository.MembershipRepository;
import com.cobuy.cobuybackend.repository.ShoppingListRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/lists")
public class ShoppingListController {

    private final ShoppingListRepository shoppingListRepository;
    private final GroupRepository groupRepository;
    private final MembershipRepository membershipRepository;

    public ShoppingListController(
            ShoppingListRepository shoppingListRepository,
            GroupRepository groupRepository,
            MembershipRepository membershipRepository) {
        this.shoppingListRepository = shoppingListRepository;
        this.groupRepository = groupRepository;
        this.membershipRepository = membershipRepository;
    }

    @GetMapping("/group/{groupId}")
    public ResponseEntity<?> getListsByGroup(
            @PathVariable Integer groupId,
            @RequestParam Integer userId) {

        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        var membership = membershipRepository.findByUserIdAndGroupId(userId, groupId);

        if (membership.isEmpty())
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Sem acesso ao grupo");

        List<ShoppingList> lists = shoppingListRepository.findByGroup(group);
        return ResponseEntity.ok(lists);
    }

    @PostMapping
    public ResponseEntity<?> createList(
            @RequestBody CreateListDTO body,
            @RequestParam Integer userId) {

        Group group = groupRepository.findById(body.groupId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        var membership = membershipRepository.findByUserIdAndGroupId(userId, group.getId());
        if (membership.isEmpty())
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Sem acesso ao grupo");

        ShoppingList sl = new ShoppingList();
        sl.setGroup(group);
        sl.setTitle(body.title());
        sl.setCreatedAt(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.CREATED).body(shoppingListRepository.save(sl));
    }

    @DeleteMapping("/{listId}")
    public ResponseEntity<?> deleteList(
            @PathVariable Integer listId,
            @RequestParam Integer userId) {

        ShoppingList list = shoppingListRepository.findById(listId)
                .orElse(null);

        if (list == null)
            return ResponseEntity.notFound().build();

        Group group = list.getGroup();

        var membership = membershipRepository.findByUserIdAndGroupId(userId, group.getId());

        if (membership.isEmpty())
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Sem acesso ao grupo");

        shoppingListRepository.delete(list);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
public ResponseEntity<?> getListsForUser(@PathVariable Integer userId) {

    List<Membership> memberships = membershipRepository.findByUserId(userId);

    List<Integer> groupIds = memberships.stream()
            .map(m -> m.getGroup().getId())
            .toList();

    List<ShoppingList> lists = shoppingListRepository.findByGroupIdIn(groupIds);

    return ResponseEntity.ok(lists);
}

    public record CreateListDTO(
            @com.fasterxml.jackson.annotation.JsonProperty("group_id") Integer groupId,
            String title) {
    }
}
