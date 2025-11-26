package com.salesianostriana.dam.proyectoAlejandroRecetas.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Receta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nombre;

    private Integer tiempoPreparacionMin;

    @Enumerated(EnumType.STRING)
    private Dificultad dificultad;

    @ManyToOne
    private Categoria categoria;

    @OneToMany(mappedBy = "receta", cascade = CascadeType.ALL)
    private List<RecetaIngrediente> ingredientes = new ArrayList<>();
}