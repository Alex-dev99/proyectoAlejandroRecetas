package com.salesianostriana.dam.proyectoAlejandroRecetas.controller;

import com.salesianostriana.dam.proyectoAlejandroRecetas.dto.*;
import com.salesianostriana.dam.proyectoAlejandroRecetas.model.Receta;
import com.salesianostriana.dam.proyectoAlejandroRecetas.service.CategoriaService;
import com.salesianostriana.dam.proyectoAlejandroRecetas.service.RecetaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/recetas")
@RequiredArgsConstructor
public class RecetaController {

    private final RecetaService recetaService;
    private final CategoriaService categoriaService;

    private RecetaResponseDTO convertToDto(Receta receta) {
        RecetaResponseDTO dto = new RecetaResponseDTO();
        dto.setId(receta.getId());
        dto.setNombre(receta.getNombre());
        dto.setTiempoPreparacionMin(receta.getTiempoPreparacionMin());
        dto.setDificultad(receta.getDificultad());

        // Convertir categoría
        CategoriaDTO categoriaDTO = new CategoriaDTO();
        categoriaDTO.setId(receta.getCategoria().getId());
        categoriaDTO.setNombre(receta.getCategoria().getNombre());
        categoriaDTO.setDescripcion(receta.getCategoria().getDescripcion());
        dto.setCategoria(categoriaDTO);

        // Convertir ingredientes CON CANTIDAD
        List<RecetaIngredienteDTO> ingredientesDTO = receta.getIngredientes().stream()
                .map(ri -> {
                    RecetaIngredienteDTO riDto = new RecetaIngredienteDTO();
                    riDto.setIngredienteId(ri.getIngrediente().getId());
                    riDto.setNombreIngrediente(ri.getIngrediente().getNombre());
                    riDto.setCantidad(ri.getCantidad());
                    return riDto;
                })
                .collect(Collectors.toList());
        dto.setIngredientes(ingredientesDTO);

        return dto;
    }

    @GetMapping
    public ResponseEntity<List<RecetaResponseDTO>> findAll() {
        List<RecetaResponseDTO> recetas = recetaService.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(recetas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecetaResponseDTO> findById(@PathVariable Long id) {
        Receta receta = recetaService.findByIdWithIngredientes(id);
        return ResponseEntity.ok(convertToDto(receta));
    }

    @PostMapping
    public ResponseEntity<RecetaResponseDTO> create(@RequestBody CreateRecetaDTO createRecetaDTO) {
        Receta receta = new Receta();
        receta.setNombre(createRecetaDTO.getNombre());
        receta.setTiempoPreparacionMin(createRecetaDTO.getTiempoPreparacionMin());
        receta.setDificultad(createRecetaDTO.getDificultad());
        receta.setCategoria(categoriaService.findById(createRecetaDTO.getCategoriaId()));

        Receta saved = recetaService.save(receta);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDto(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecetaResponseDTO> update(@PathVariable Long id, @RequestBody CreateRecetaDTO createRecetaDTO) {
        Receta receta = new Receta();
        receta.setNombre(createRecetaDTO.getNombre());
        receta.setTiempoPreparacionMin(createRecetaDTO.getTiempoPreparacionMin());
        receta.setDificultad(createRecetaDTO.getDificultad());
        receta.setCategoria(categoriaService.findById(createRecetaDTO.getCategoriaId()));

        Receta updated = recetaService.update(id, receta);
        return ResponseEntity.ok(convertToDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        recetaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{recetaId}/ingredientes")
    public ResponseEntity<RecetaResponseDTO> addIngredienteToReceta(
            @PathVariable Long recetaId,
            @RequestBody AddIngredienteToRecetaDTO addIngredienteDTO) {

        Receta receta = recetaService.addIngredienteToReceta(
                recetaId,
                addIngredienteDTO.getIngredienteId(),
                addIngredienteDTO.getCantidad()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDto(receta));
    }

    @DeleteMapping("/{recetaId}/ingredientes/{ingredienteId}")
    public ResponseEntity<?> removeIngredienteFromReceta(
            @PathVariable Long recetaId,
            @PathVariable Long ingredienteId) {

        recetaService.removeIngredienteFromReceta(recetaId, ingredienteId);
        return ResponseEntity.noContent().build();
    }
}