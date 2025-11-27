package com.salesianostriana.dam.proyectoAlejandroRecetas.service;

import com.salesianostriana.dam.proyectoAlejandroRecetas.exception.*;
import com.salesianostriana.dam.proyectoAlejandroRecetas.model.Ingrediente;
import com.salesianostriana.dam.proyectoAlejandroRecetas.model.Receta;
import com.salesianostriana.dam.proyectoAlejandroRecetas.repository.RecetaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecetaService {

    private final RecetaRepository recetaRepository;
    private final CategoriaService categoriaService;
    private final IngredienteService ingredienteService;

    public List<Receta> findAll() {
        return recetaRepository.findAll();
    }

    public Receta findById(Long id) {
        return recetaRepository.findById(id)
                .orElseThrow(() -> new EntidadNoEncontradaException("Receta no encontrada con id: " + id));
    }

    public Receta findByIdWithIngredientes(Long id) {
        return recetaRepository.findByIdWithIngredientes(id)
                .orElseThrow(() -> new EntidadNoEncontradaException("Receta no encontrada con id: " + id));
    }

    public Receta save(Receta receta) {
        if (receta.getTiempoPreparacionMin() <= 0) {
            throw new TiempoInvalidoException("El tiempo de preparación debe ser mayor a 0");
        }

        if (recetaRepository.existsByNombre(receta.getNombre())) {
            throw new NombreDuplicadoException("Ya existe una receta con el nombre: " + receta.getNombre());
        }

        return recetaRepository.save(receta);
    }

    public Receta update(Long id, Receta receta) {
        Receta existing = findById(id);

        if (receta.getTiempoPreparacionMin() <= 0) {
            throw new TiempoInvalidoException("El tiempo de preparación debe ser mayor a 0");
        }

        if (!existing.getNombre().equals(receta.getNombre()) &&
                recetaRepository.existsByNombre(receta.getNombre())) {
            throw new NombreDuplicadoException("Ya existe una receta con el nombre: " + receta.getNombre());
        }

        existing.setNombre(receta.getNombre());
        existing.setTiempoPreparacionMin(receta.getTiempoPreparacionMin());
        existing.setDificultad(receta.getDificultad());
        existing.setCategoria(receta.getCategoria());

        return recetaRepository.save(existing);
    }

    public void delete(Long id) {
        if (!recetaRepository.existsById(id)) {
            throw new EntidadNoEncontradaException("Receta no encontrada con id: " + id);
        }
        recetaRepository.deleteById(id);
    }

    @Transactional
    public Receta addIngredienteToReceta(Long recetaId, Long ingredienteId) {
        Receta receta = findById(recetaId);
        Ingrediente ingrediente = ingredienteService.findById(ingredienteId);

        // Verificar si el ingrediente ya está en la receta
        if (receta.getIngredientes().contains(ingrediente)) {
            throw new IngredienteYaAnadidoException("El ingrediente ya está añadido a la receta");
        }

        receta.getIngredientes().add(ingrediente);
        return recetaRepository.save(receta);
    }

    @Transactional
    public void removeIngredienteFromReceta(Long recetaId, Long ingredienteId) {
        Receta receta = findById(recetaId);
        Ingrediente ingrediente = ingredienteService.findById(ingredienteId);

        receta.getIngredientes().removeIf(ing -> ing.getId().equals(ingredienteId));
        recetaRepository.save(receta);
    }
}