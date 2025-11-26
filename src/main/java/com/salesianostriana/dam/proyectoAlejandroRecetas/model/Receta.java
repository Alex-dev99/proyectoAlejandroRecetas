package com.salesianostriana.dam.proyectoAlejandroRecetas.model;
import jakarta.persistence.*;

@Entity
public class Receta {

    @Id
    @GeneratedValue
    long id;

    String nombre;

    Integer tiempoDePreparacion;

    Enum dificultad;
    
}
