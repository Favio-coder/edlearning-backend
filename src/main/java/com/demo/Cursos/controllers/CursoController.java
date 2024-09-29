package com.demo.Cursos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.demo.Cursos.requests.CursoDTO;
import com.demo.Cursos.services.CursoService;

@RestController
@RequestMapping("/api/curso")
@CrossOrigin(origins = "*") 
public class CursoController {

    @Autowired
    private CursoService cursoService;

    // Crear un nuevo curso
    @PostMapping
    public ResponseEntity<CursoDTO> createCurso(@RequestBody CursoDTO cursoDTO) {
        CursoDTO createdCurso = cursoService.createCurso(cursoDTO);
        return ResponseEntity.ok(createdCurso);
    }

    // Obtener todos los cursos
    @GetMapping
    public ResponseEntity<List<CursoDTO>> getAllCursos() {
        List<CursoDTO> cursos = cursoService.getAllCursos();
        return ResponseEntity.ok(cursos);
    }

    // Obtener un curso por ID
    @GetMapping("/{idCurso}")
    public ResponseEntity<CursoDTO> getCursoById(@PathVariable long idCurso) {
        CursoDTO cursoDTO = cursoService.getCursoById(idCurso);
        if (cursoDTO != null) {
            return ResponseEntity.ok(cursoDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Actualizar un curso por ID
    @PutMapping("/{idCurso}")
    public ResponseEntity<CursoDTO> updateCurso(@PathVariable long idCurso, @RequestBody CursoDTO cursoDTO) {
        CursoDTO updatedCurso = cursoService.updateCurso(idCurso, cursoDTO);
        if (updatedCurso != null) {
            return ResponseEntity.ok(updatedCurso);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un curso por ID
    @DeleteMapping("/{idCurso}")
    public ResponseEntity<Void> deleteCurso(@PathVariable long idCurso) {
        cursoService.deleteCurso(idCurso);
        return ResponseEntity.noContent().build();
    }
}
