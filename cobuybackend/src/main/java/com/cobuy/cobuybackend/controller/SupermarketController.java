package com.cobuy.cobuybackend.controller;

import com.cobuy.cobuybackend.model.Supermarket;
import com.cobuy.cobuybackend.repository.SupermarketRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/supermarkets")
public class SupermarketController {

    private final SupermarketRepository repo;

    public SupermarketController(SupermarketRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Supermarket> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Supermarket> getOne(@PathVariable Integer id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Supermarket market) {
        Supermarket saved = repo.save(market);
        return ResponseEntity.created(URI.create("/supermarkets/" + saved.getId()))
                .body(saved);
    }
}