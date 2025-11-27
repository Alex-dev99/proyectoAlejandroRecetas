package com.salesianostriana.dam.proyectoAlejandroRecetas.repository;

import com.salesianostriana.dam.proyectoAlejandroRecetas.model.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecetaRepository extends JpaRepository<Receta, Long> {

    Optional<Receta> findByNombre(String nombre);

    boolean existsByNombre(String nombre);

    @Query("SELECT r FROM Receta r LEFT JOIN FETCH r.ingredientes WHERE r.id = :id")
    Optional<Receta> findByIdWithIngredientes(@Param("id") Long id);
}