package com.cobuy.cobuybackend.controller;

import com.cobuy.cobuybackend.model.Group;
import com.cobuy.cobuybackend.service.GroupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    public List<Group> getAllGroups() {
        return groupService.getAllGroups();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Group> getGroupById(@PathVariable Integer id) {
        return groupService.getGroupById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Group createGroup(@RequestBody Group group) {
        return groupService.createGroup(group);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Integer id) {
        groupService.deleteGroup(id);
        return ResponseEntity.noContent().build();
    }
}
