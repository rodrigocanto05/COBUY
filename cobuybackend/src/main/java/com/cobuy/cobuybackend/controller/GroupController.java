package com.cobuy.cobuybackend.controller;

import com.cobuy.cobuybackend.model.Group;
import com.cobuy.cobuybackend.repository.GroupRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController {

    private final GroupRepository groupRepository;

    public GroupController(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    // GET /groups
    @GetMapping
    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    // GET /groups/{id}
    @GetMapping("/{id}")
    public Group getGroupById(@PathVariable Integer id) {
        return groupRepository.findById(id).orElse(null);
    }

    // POST /groups -> criar grupo
    @PostMapping
    public Group createGroup(@RequestBody Group group) {
        group.setCreatedAt(LocalDateTime.now()); // atribui timestamp
        return groupRepository.save(group);
    }
}