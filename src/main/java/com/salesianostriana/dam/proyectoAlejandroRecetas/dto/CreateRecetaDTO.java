package com.salesianostriana.dam.proyectoAlejandroRecetas.dto;

import com.salesianostriana.dam.proyectoAlejandroRecetas.model.Dificultad;
import lombok.Data;

@Data
public class CreateRecetaDTO {
    private String nombre;
    private Integer tiempoPreparacionMin;
    private Dificultad dificultad;
    private Long categoriaId;
}