package com.salesianostriana.dam.proyectoAlejandroRecetas.service;

import com.salesianostriana.dam.proyectoAlejandroRecetas.exception.EntidadNoEncontradaException;
import com.salesianostriana.dam.proyectoAlejandroRecetas.exception.NombreDuplicadoException;
import com.salesianostriana.dam.proyectoAlejandroRecetas.model.Categoria;
import com.salesianostriana.dam.proyectoAlejandroRecetas.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    public Categoria findById(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new EntidadNoEncontradaException("Categoría no encontrada con id: " + id));
    }

    public Categoria save(Categoria categoria) {
        if (categoriaRepository.existsByNombre(categoria.getNombre())) {
            throw new NombreDuplicadoException("Ya existe una categoría con el nombre: " + categoria.getNombre());
        }
        return categoriaRepository.save(categoria);
    }

    public Categoria update(Long id, Categoria categoria) {
        Categoria existing = findById(id);

        if (!existing.getNombre().equals(categoria.getNombre()) &&
                categoriaRepository.existsByNombre(categoria.getNombre())) {
            throw new NombreDuplicadoException("Ya existe una categoría con el nombre: " + categoria.getNombre());
        }

        existing.setNombre(categoria.getNombre());
        existing.setDescripcion(categoria.getDescripcion());

        return categoriaRepository.save(existing);
    }

    public void delete(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new EntidadNoEncontradaException("Categoría no encontrada con id: " + id);
        }
        categoriaRepository.deleteById(id);
    }
}