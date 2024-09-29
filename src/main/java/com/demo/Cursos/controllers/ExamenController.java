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

import com.demo.Cursos.requests.ExamenDTO;
import com.demo.Cursos.services.ExamenService;

import java.util.List;

@RestController
@RequestMapping("/api/examen")
@CrossOrigin(origins = "*") 
public class ExamenController {
    @Autowired
    private ExamenService examenService;

    @PostMapping
    public ResponseEntity<ExamenDTO> createExamen(@RequestBody ExamenDTO examenDTO) {
        ExamenDTO createdExamen = examenService.createExamen(examenDTO);
        return ResponseEntity.ok(createdExamen);
    }

    @GetMapping
    public ResponseEntity<List<ExamenDTO>> getAllExamenes() {
        List<ExamenDTO> examenes = examenService.getAllExamenes();
        return ResponseEntity.ok(examenes);
    }

    @GetMapping("/{idExamen}")
    public ResponseEntity<ExamenDTO> getExamenById(@PathVariable long idExamen) {
        ExamenDTO examenDTO = examenService.getExamenById(idExamen);
        if (examenDTO != null) {
            return ResponseEntity.ok(examenDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{idExamen}")
    public ResponseEntity<ExamenDTO> updateExamen(@PathVariable long idExamen, @RequestBody ExamenDTO examenDTO) {
        ExamenDTO updatedExamen = examenService.updateExamen(idExamen, examenDTO);
        if (updatedExamen != null) {
            return ResponseEntity.ok(updatedExamen);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{idExamen}")
    public ResponseEntity<Void> deleteExamen(@PathVariable long idExamen) {
        examenService.deleteExamen(idExamen);
        return ResponseEntity.noContent().build();
    }
}
