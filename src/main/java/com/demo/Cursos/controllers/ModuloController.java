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
import com.demo.Cursos.requests.ModuloDTO;
import com.demo.Cursos.services.ModuloService;



@RestController
@RequestMapping("/api/modulo")
@CrossOrigin(origins = "*") 
public class ModuloController {
     @Autowired
    private ModuloService moduloService;

    @PostMapping
    public ResponseEntity<ModuloDTO> createModulo(@RequestBody ModuloDTO moduloDTO) {
        ModuloDTO createdModulo = moduloService.createModulo(moduloDTO);
        return ResponseEntity.ok(createdModulo);
    }

    @GetMapping
    public ResponseEntity<List<ModuloDTO>> getAllModulos() {
        List<ModuloDTO> modulos = moduloService.getAllModulos();
        return ResponseEntity.ok(modulos);
    }

    @GetMapping("/{idModulo}")
    public ResponseEntity<ModuloDTO> getModuloById(@PathVariable long idModulo) {
        ModuloDTO moduloDTO = moduloService.getModuloById(idModulo);
        if (moduloDTO != null) {
            return ResponseEntity.ok(moduloDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{idModulo}")
    public ResponseEntity<ModuloDTO> updateModulo(@PathVariable long idModulo, @RequestBody ModuloDTO moduloDTO) {
        ModuloDTO updatedModulo = moduloService.updateModulo(idModulo, moduloDTO);
        if (updatedModulo != null) {
            return ResponseEntity.ok(updatedModulo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{idModulo}")
    public ResponseEntity<Void> deleteModulo(@PathVariable long idModulo) {
        moduloService.deleteModulo(idModulo);
        return ResponseEntity.noContent().build();
    }
}
