package com.cobuy.cobuybackend.controller;

import com.cobuy.cobuybackend.model.Group;
import com.cobuy.cobuybackend.repository.GroupRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController {

    private final GroupRepository groupRepository;

    // injeção via construtor
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
}