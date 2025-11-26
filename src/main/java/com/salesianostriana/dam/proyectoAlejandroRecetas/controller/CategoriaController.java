package com.salesianostriana.dam.proyectoAlejandroRecetas.controller;

import com.salesianostriana.dam.proyectoAlejandroRecetas.dto.CategoriaDTO;
import com.salesianostriana.dam.proyectoAlejandroRecetas.model.Categoria;
import com.salesianostriana.dam.proyectoAlejandroRecetas.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    private CategoriaDTO convertToDto(Categoria categoria) {
        CategoriaDTO dto = new CategoriaDTO();
        dto.setId(categoria.getId());
        dto.setNombre(categoria.getNombre());
        dto.setDescripcion(categoria.getDescripcion());
        return dto;
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> findAll() {
        List<CategoriaDTO> categorias = categoriaService.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> findById(@PathVariable Long id) {
        Categoria categoria = categoriaService.findById(id);
        return ResponseEntity.ok(convertToDto(categoria));
    }

    @PostMapping
    public ResponseEntity<CategoriaDTO> create(@RequestBody CategoriaDTO categoriaDTO) {
        Categoria categoria = new Categoria();
        categoria.setNombre(categoriaDTO.getNombre());
        categoria.setDescripcion(categoriaDTO.getDescripcion());

        Categoria saved = categoriaService.save(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDto(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> update(@PathVariable Long id, @RequestBody CategoriaDTO categoriaDTO) {
        Categoria categoria = new Categoria();
        categoria.setNombre(categoriaDTO.getNombre());
        categoria.setDescripcion(categoriaDTO.getDescripcion());

        Categoria updated = categoriaService.update(id, categoria);
        return ResponseEntity.ok(convertToDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}