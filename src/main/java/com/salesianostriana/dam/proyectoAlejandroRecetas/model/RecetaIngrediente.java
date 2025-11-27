package com.salesianostriana.dam.proyectoAlejandroRecetas.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecetaIngrediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Receta receta;

    @ManyToOne
    private Ingrediente ingrediente;

    private String cantidad;
}