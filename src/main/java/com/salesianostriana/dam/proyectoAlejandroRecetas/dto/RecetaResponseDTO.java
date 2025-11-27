package com.salesianostriana.dam.proyectoAlejandroRecetas.dto;

import com.salesianostriana.dam.proyectoAlejandroRecetas.model.Dificultad;
import lombok.Data;
import java.util.List;

@Data
public class RecetaResponseDTO {
    private Long id;
    private String nombre;
    private Integer tiempoPreparacionMin;
    private Dificultad dificultad;
    private CategoriaDTO categoria;
    private List<RecetaIngredienteDTO> ingredientes;
}