package com.cobuy.cobuybackend.dto;

public class AddItemRequest {
    public String name;     // Nome do item
    public Double qty;      // Quantidade
    public Integer unitId;  // FK para a tabela unit
    public Integer userId;  // Quem adicionou o item
}