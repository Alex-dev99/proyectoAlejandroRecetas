package com.salesianostriana.dam.proyectoAlejandroRecetas.repository;

import com.salesianostriana.dam.proyectoAlejandroRecetas.model.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IngredienteRepository extends JpaRepository<Ingrediente, Long> {

    Optional<Ingrediente> findByNombre(String nombre);

    boolean existsByNombre(String nombre);
}