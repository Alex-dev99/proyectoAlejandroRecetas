package com.salesianostriana.dam.proyectoAlejandroRecetas.repository;

import com.salesianostriana.dam.proyectoAlejandroRecetas.model.RecetaIngrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RecetaIngredienteRepository extends JpaRepository<RecetaIngrediente, Long> {

    @Query("SELECT ri FROM RecetaIngrediente ri WHERE ri.receta.id = :recetaId AND ri.ingrediente.id = :ingredienteId")
    Optional<RecetaIngrediente> findByRecetaIdAndIngredienteId(@Param("recetaId") Long recetaId,
                                                               @Param("ingredienteId") Long ingredienteId);

    boolean existsByRecetaIdAndIngredienteId(Long recetaId, Long ingredienteId);
}