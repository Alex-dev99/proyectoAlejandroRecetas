package com.salesianostriana.dam.proyectoAlejandroRecetas.dto;

import lombok.Data;

@Data
public class AddIngredienteToRecetaDTO {
    private Long ingredienteId;
    private String cantidad; // "200 gramos", "1 litro", etc.
}