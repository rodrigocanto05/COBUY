package com.cobuy.cobuybackend.controller;

import com.cobuy.cobuybackend.model.Ingredient;
import com.cobuy.cobuybackend.model.Unit;
import com.cobuy.cobuybackend.repository.IngredientRepository;
import com.cobuy.cobuybackend.repository.UnitRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {

    private final IngredientRepository ingredientRepository;
    private final UnitRepository unitRepository;

    public IngredientController(IngredientRepository ingredientRepository,
                                UnitRepository unitRepository) {
        this.ingredientRepository = ingredientRepository;
        this.unitRepository = unitRepository;
    }

    // DTO para criação/edição
    public record IngredientDTO(String name, Integer unitId) {}

    // GET /ingredients - lista todos
    @GetMapping
    public List<Ingredient> getAll() {
        return ingredientRepository.findAll();
    }

    // GET /ingredients/{id} - busca por id
    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getById(@PathVariable Integer id) {
        return ingredientRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /ingredients - cria ingrediente
    @PostMapping
    public ResponseEntity<?> create(@RequestBody IngredientDTO body) {
        if (body == null || body.name() == null || body.name().isBlank()) {
            return ResponseEntity.badRequest().body("Field 'name' is required");
        }
        if (body.unitId() == null) {
            return ResponseEntity.badRequest().body("Field 'unitId' is required");
        }

        Unit unit = unitRepository.findById(body.unitId()).orElse(null);
        if (unit == null) {
            return ResponseEntity.badRequest().body("Unit not found with id: " + body.unitId());
        }

        Ingredient ing = new Ingredient();
        ing.setName(body.name().trim());
        ing.setUnit(unit);

        try {
            Ingredient saved = ingredientRepository.save(ing);
            return ResponseEntity
                    .created(URI.create("/ingredients/" + saved.getId()))
                    .body(saved);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(409).body("Ingredient name already exists");
        }
    }

    // PUT /ingredients/{id} - atualiza ingrediente
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id,
                                    @RequestBody IngredientDTO body) {
        return ingredientRepository.findById(id)
                .map(existing -> {
                    if (body.name() != null && !body.name().isBlank()) {
                        existing.setName(body.name().trim());
                    }
                    if (body.unitId() != null) {
                        Unit unit = unitRepository.findById(body.unitId()).orElse(null);
                        if (unit == null) {
                            return ResponseEntity.badRequest()
                                    .body("Unit not found with id: " + body.unitId());
                        }
                        existing.setUnit(unit);
                    }

                    try {
                        Ingredient saved = ingredientRepository.save(existing);
                        return ResponseEntity.ok(saved);
                    } catch (DataIntegrityViolationException e) {
                        return ResponseEntity.status(409).body("Ingredient name already exists");
                    }
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /ingredients/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (!ingredientRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        ingredientRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}