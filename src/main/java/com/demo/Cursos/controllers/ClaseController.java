package com.demo.Cursos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.demo.Cursos.requests.ClaseDTO;
import com.demo.Cursos.services.ClaseService;

@RestController
@RequestMapping("/api/clase")
@CrossOrigin(origins = "*") 
public class ClaseController {
    @Autowired
    private ClaseService claseService;

    @PostMapping
    public ResponseEntity<ClaseDTO> createClase(@RequestBody ClaseDTO claseDTO) {
        ClaseDTO createdClase = claseService.createClase(claseDTO);
        return ResponseEntity.ok(createdClase);
    }

    @GetMapping
    public ResponseEntity<List<ClaseDTO>> getAllClases() {
        List<ClaseDTO> clases = claseService.getAllClases();
        return ResponseEntity.ok(clases);
    }

    @GetMapping("/{idClase}")
    public ResponseEntity<ClaseDTO> getClaseById(@PathVariable long idClase) {
        ClaseDTO claseDTO = claseService.getClaseById(idClase);
        if (claseDTO != null) {
            return ResponseEntity.ok(claseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{idClase}")
    public ResponseEntity<ClaseDTO> updateClase(@PathVariable long idClase, @RequestBody ClaseDTO claseDTO) {
        ClaseDTO updatedClase = claseService.updateClase(idClase, claseDTO);
        if (updatedClase != null) {
            return ResponseEntity.ok(updatedClase);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{idClase}")
    public ResponseEntity<Void> deleteClase(@PathVariable long idClase) {
        claseService.deleteClase(idClase);
        return ResponseEntity.noContent().build();
    }
}
