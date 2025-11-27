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

    // CORRECTO: Many-to-One hacia Receta
    @ManyToOne
    private Receta receta;

    // CORRECTO: Many-to-One hacia Ingrediente
    @ManyToOne
    private Ingrediente ingrediente;

    private String cantidad;
}