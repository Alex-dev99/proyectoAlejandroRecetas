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
public class Ingrediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nombre;

    @OneToMany(mappedBy = "ingrediente", cascade = CascadeType.ALL)
    private List<RecetaIngrediente> recetas = new ArrayList<>();
}