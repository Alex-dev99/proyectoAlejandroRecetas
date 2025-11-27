package com.salesianostriana.dam.proyectoAlejandroRecetas.service;

import com.salesianostriana.dam.proyectoAlejandroRecetas.exception.EntidadNoEncontradaException;
import com.salesianostriana.dam.proyectoAlejandroRecetas.exception.NombreDuplicadoException;
import com.salesianostriana.dam.proyectoAlejandroRecetas.model.Ingrediente;
import com.salesianostriana.dam.proyectoAlejandroRecetas.repository.IngredienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IngredienteService {

    private final IngredienteRepository ingredienteRepository;

    public List<Ingrediente> findAll() {
        return ingredienteRepository.findAll();
    }

    public Ingrediente findById(Long id) {
        return ingredienteRepository.findById(id)
                .orElseThrow(() -> new EntidadNoEncontradaException("Ingrediente no encontrado con id: " + id));
    }

    public Ingrediente save(Ingrediente ingrediente) {
        if (ingredienteRepository.existsByNombre(ingrediente.getNombre())) {
            throw new NombreDuplicadoException("Ya existe un ingrediente con el nombre: " + ingrediente.getNombre());
        }
        return ingredienteRepository.save(ingrediente);
    }

    public Ingrediente update(Long id, Ingrediente ingrediente) {
        Ingrediente existing = findById(id);

        if (!existing.getNombre().equals(ingrediente.getNombre()) &&
                ingredienteRepository.existsByNombre(ingrediente.getNombre())) {
            throw new NombreDuplicadoException("Ya existe un ingrediente con el nombre: " + ingrediente.getNombre());
        }

        existing.setNombre(ingrediente.getNombre());
        return ingredienteRepository.save(existing);
    }

    public void delete(Long id) {
        if (!ingredienteRepository.existsById(id)) {
            throw new EntidadNoEncontradaException("Ingrediente no encontrado con id: " + id);
        }
        ingredienteRepository.deleteById(id);
    }
}