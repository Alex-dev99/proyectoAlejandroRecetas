package com.salesianostriana.dam.proyectoAlejandroRecetas.dto;

import lombok.Data;

@Data
public class RecetaIngredienteDTO {
    private Long ingredienteId;
    private String nombreIngrediente;
    private String cantidad;
}