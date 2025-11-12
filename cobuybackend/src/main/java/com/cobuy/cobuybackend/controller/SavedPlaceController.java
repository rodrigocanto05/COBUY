package com.cobuy.cobuybackend.controller;

import com.cobuy.cobuybackend.dto.SavePlaceRequest;
import com.cobuy.cobuybackend.model.SavedPlace;
import com.cobuy.cobuybackend.model.Supermarket;
import com.cobuy.cobuybackend.model.User;
import com.cobuy.cobuybackend.repository.SavedPlaceRepository;
import com.cobuy.cobuybackend.repository.SupermarketRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/saved-places")
public class SavedPlaceController {

    private final SavedPlaceRepository savedRepo;
    private final SupermarketRepository marketRepo;

    public SavedPlaceController(SavedPlaceRepository savedRepo, SupermarketRepository marketRepo) {
        this.savedRepo = savedRepo;
        this.marketRepo = marketRepo;
    }

    @GetMapping
    public List<SavedPlace> myPlaces(@AuthenticationPrincipal User user) {
        return savedRepo.findByUser(user);
    }

    @PostMapping
    public ResponseEntity<?> save(@AuthenticationPrincipal User user,
                                  @RequestBody SavePlaceRequest req) {
        if (req == null || req.supermarketId == null) {
            return ResponseEntity.badRequest().body("supermarketId é obrigatório");
        }

        Supermarket market = marketRepo.findById(req.supermarketId).orElse(null);
        if (market == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Supermarket não existe");
        }

        boolean exists = savedRepo
                .findByUser_IdAndSupermarket_Id(user.getId(), req.supermarketId)
                .isPresent();
        if (exists) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Já está nos favoritos");
        }

        SavedPlace sp = new SavedPlace();
        sp.setUser(user);
        sp.setSupermarket(market);
        sp.setLabel(req.label);
        sp.setDistance(req.distance);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedRepo.save(sp));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@AuthenticationPrincipal User user,
                                    @PathVariable Integer id) {
        return savedRepo.findById(id)
                .map(sp -> {
                    if (!sp.getUser().getId().equals(user.getId())) {
                        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Não é teu favorito");
                    }
                    savedRepo.delete(sp);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Favorito não existe"));
    }
}