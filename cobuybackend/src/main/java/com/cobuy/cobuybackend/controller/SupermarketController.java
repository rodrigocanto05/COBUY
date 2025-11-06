package com.cobuy.cobuybackend.controller;

import com.cobuy.cobuybackend.model.Supermarket;
import com.cobuy.cobuybackend.repository.SupermarketRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/supermarkets")
public class SupermarketController {
  private final SupermarketRepository repo;
  public SupermarketController(SupermarketRepository repo){ this.repo = repo; }

  @GetMapping
  public List<Supermarket> getAll() { return repo.findAll(); }
}