package com.cobuy.cobuybackend.controller;

import com.cobuy.cobuybackend.model.Item;
import com.cobuy.cobuybackend.model.Unit;
import com.cobuy.cobuybackend.repository.ItemRepository;
import com.cobuy.cobuybackend.repository.UnitRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemRepository itemRepository;
    private final UnitRepository unitRepository;

    public ItemController(ItemRepository itemRepository, UnitRepository unitRepository) {
        this.itemRepository = itemRepository;
        this.unitRepository = unitRepository;
    }

    // DTOs
    public record ItemDTO(Integer id, String name, Integer unitId, String unitName) {}
    public record CreateItemDTO(String name, Integer unitId) {}

    @GetMapping
    public List<ItemDTO> getAll() {
        return itemRepository.findAll().stream()
                .map(i -> new ItemDTO(
                        i.getId(),
                        i.getName(),
                        i.getUnit().getId(),
                        i.getUnit().getName()
                ))
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDTO> getById(@PathVariable Integer id) {
        return itemRepository.findById(id)
                .map(i -> new ItemDTO(
                        i.getId(),
                        i.getName(),
                        i.getUnit().getId(),
                        i.getUnit().getName()
                ))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateItemDTO body) {
        Unit unit = unitRepository.findById(body.unitId())
                .orElse(null);
        if (unit == null) {
            return ResponseEntity.badRequest().body("Unit não encontrada: " + body.unitId());
        }

        Item item = new Item();
        item.setName(body.name());
        item.setUnit(unit);

        Item saved = itemRepository.save(item);

        ItemDTO dto = new ItemDTO(
                saved.getId(),
                saved.getName(),
                saved.getUnit().getId(),
                saved.getUnit().getName()
        );

        return ResponseEntity
                .created(URI.create("/items/" + saved.getId()))
                .body(dto);
    }
}