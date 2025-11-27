package com.salesianostriana.dam.proyectoAlejandroRecetas.controller;

import com.salesianostriana.dam.proyectoAlejandroRecetas.dto.IngredienteDTO;
import com.salesianostriana.dam.proyectoAlejandroRecetas.model.Ingrediente;
import com.salesianostriana.dam.proyectoAlejandroRecetas.service.IngredienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/ingredientes")
@RequiredArgsConstructor
public class IngredienteController {

    private final IngredienteService ingredienteService;

    private IngredienteDTO convertToDto(Ingrediente ingrediente) {
        IngredienteDTO dto = new IngredienteDTO();
        dto.setId(ingrediente.getId());
        dto.setNombre(ingrediente.getNombre());
        return dto;
    }

    @GetMapping
    public ResponseEntity<List<IngredienteDTO>> findAll() {
        List<IngredienteDTO> ingredientes = ingredienteService.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(ingredientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IngredienteDTO> findById(@PathVariable Long id) {
        Ingrediente ingrediente = ingredienteService.findById(id);
        return ResponseEntity.ok(convertToDto(ingrediente));
    }

    @PostMapping
    public ResponseEntity<IngredienteDTO> create(@RequestBody IngredienteDTO ingredienteDTO) {
        Ingrediente ingrediente = new Ingrediente();
        ingrediente.setNombre(ingredienteDTO.getNombre());

        Ingrediente saved = ingredienteService.save(ingrediente);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDto(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<IngredienteDTO> update(@PathVariable Long id, @RequestBody IngredienteDTO ingredienteDTO) {
        Ingrediente ingrediente = new Ingrediente();
        ingrediente.setNombre(ingredienteDTO.getNombre());

        Ingrediente updated = ingredienteService.update(id, ingrediente);
        return ResponseEntity.ok(convertToDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        ingredienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}