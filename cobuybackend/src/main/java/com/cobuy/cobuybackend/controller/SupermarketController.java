package com.cobuy.cobuybackend.controller;

import com.cobuy.cobuybackend.dto.ResolveSupermarketRequest;
import com.cobuy.cobuybackend.model.Supermarket;
import com.cobuy.cobuybackend.repository.SupermarketRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/supermarkets")
public class SupermarketController {

    private final SupermarketRepository supermarketRepository;

    public SupermarketController(SupermarketRepository supermarketRepository) {
        this.supermarketRepository = supermarketRepository;
    }


    @GetMapping
    public List<Supermarket> getAll() {
        return supermarketRepository.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Supermarket> getOne(@PathVariable Integer id) {
        return supermarketRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<?> create(@RequestBody Supermarket market) {
        Supermarket saved = supermarketRepository.save(market);
        return ResponseEntity.created(URI.create("/supermarkets/" + saved.getId()))
                .body(saved);
    }


    @PostMapping("/resolve")
    public ResponseEntity<?> resolveMarket(@RequestBody ResolveSupermarketRequest req) {

        if (req.lat == null || req.lng == null) {
            return ResponseEntity.badRequest().body("Latitude e longitude são obrigatórias.");
        }

        var existing = supermarketRepository.findByCoordinates(req.lat, req.lng);
        if (existing.isPresent()) {
            return ResponseEntity.ok(existing.get());
        }

        Supermarket newMarket = new Supermarket();
        newMarket.setName(req.name != null ? req.name : "Supermercado");
        newMarket.setLat(req.lat);
        newMarket.setLng(req.lng);

        Supermarket saved = supermarketRepository.save(newMarket);

        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}