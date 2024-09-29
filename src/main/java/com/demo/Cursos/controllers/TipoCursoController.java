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

import com.demo.Cursos.requests.TipoCursoDTO;
import com.demo.Cursos.services.TipoCursoService;

import java.util.List;

@RestController
@RequestMapping("/api/tipoCurso")
@CrossOrigin(origins = "*") 
public class TipoCursoController {
     @Autowired
    private TipoCursoService tipoCursoService;

    @PostMapping
    public ResponseEntity<TipoCursoDTO> createTipoCurso(@RequestBody TipoCursoDTO tipoCursoDTO) {
        TipoCursoDTO createdTipoCurso = tipoCursoService.createTipoCurso(tipoCursoDTO);
        return ResponseEntity.ok(createdTipoCurso);
    }

    @GetMapping
    public ResponseEntity<List<TipoCursoDTO>> getAllTipoCursos() {
        List<TipoCursoDTO> tipoCursos = tipoCursoService.getAllTipoCursos();
        return ResponseEntity.ok(tipoCursos);
    }

    @GetMapping("/{idTipoCurso}")
    public ResponseEntity<TipoCursoDTO> getTipoCursoById(@PathVariable long idTipoCurso) {
        TipoCursoDTO tipoCursoDTO = tipoCursoService.getTipoCursoById(idTipoCurso);
        if (tipoCursoDTO != null) {
            return ResponseEntity.ok(tipoCursoDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{idTipoCurso}")
    public ResponseEntity<TipoCursoDTO> updateTipoCurso(@PathVariable long idTipoCurso, @RequestBody TipoCursoDTO tipoCursoDTO) {
        TipoCursoDTO updatedTipoCurso = tipoCursoService.updateTipoCurso(idTipoCurso, tipoCursoDTO);
        if (updatedTipoCurso != null) {
            return ResponseEntity.ok(updatedTipoCurso);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{idTipoCurso}")
    public ResponseEntity<Void> deleteTipoCurso(@PathVariable long idTipoCurso) {
        tipoCursoService.deleteTipoCurso(idTipoCurso);
        return ResponseEntity.noContent().build();
    }
}
