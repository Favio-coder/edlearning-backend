package com.demo.Cursos.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.Cursos.models.TipoCursoEntity;
import com.demo.Cursos.repository.TipoCursoRepository;
import com.demo.Cursos.requests.TipoCursoDTO;

import jakarta.transaction.Transactional;

@Service
public class TipoCursoService {
    @Autowired
    private TipoCursoRepository tipoCursoRepository;

    @Transactional
    public TipoCursoDTO createTipoCurso(TipoCursoDTO tipoCursoDTO) {
        TipoCursoEntity tipoCursoEntity = TipoCursoEntity.builder()
                .nombreTipoCurso(tipoCursoDTO.getNombreTipoCurso())
                .archivo(tipoCursoDTO.getArchivo())
                .descripcion(tipoCursoDTO.getDescripcion()) // Asegúrate de incluir todos los campos
                .build();

        tipoCursoEntity = tipoCursoRepository.save(tipoCursoEntity);
        return mapToDTO(tipoCursoEntity);
    }

    public List<TipoCursoDTO> getAllTipoCursos() {
        List<TipoCursoEntity> tipoCursos = tipoCursoRepository.findAll();
        return tipoCursos.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public TipoCursoDTO getTipoCursoById(long idTipoCurso) {
        TipoCursoEntity tipoCursoEntity = tipoCursoRepository.findById(idTipoCurso)
                .orElseThrow(() -> new RuntimeException("TipoCurso no encontrado"));
        return mapToDTO(tipoCursoEntity);
    }

    @Transactional
    public TipoCursoDTO updateTipoCurso(long idTipoCurso, TipoCursoDTO tipoCursoDTO) {
        TipoCursoEntity tipoCursoEntity = tipoCursoRepository.findById(idTipoCurso)
                .orElseThrow(() -> new RuntimeException("TipoCurso no encontrado"));

        tipoCursoEntity.setNombreTipoCurso(tipoCursoDTO.getNombreTipoCurso());
        tipoCursoEntity.setDescripcion(tipoCursoDTO.getDescripcion()); // Asegúrate de actualizar todos los campos
        tipoCursoEntity.setArchivo(tipoCursoDTO.getArchivo());

        tipoCursoEntity = tipoCursoRepository.save(tipoCursoEntity);
        return mapToDTO(tipoCursoEntity);
    }

    @Transactional
    public void deleteTipoCurso(long idTipoCurso) {
        if (!tipoCursoRepository.existsById(idTipoCurso)) {
            throw new RuntimeException("TipoCurso no encontrado");
        }
        tipoCursoRepository.deleteById(idTipoCurso);
    }

    private TipoCursoDTO mapToDTO(TipoCursoEntity tipoCursoEntity) {
        return TipoCursoDTO.builder()
                .idTipoCurso(tipoCursoEntity.getIdTipoCurso())
                .nombreTipoCurso(tipoCursoEntity.getNombreTipoCurso())
                .descripcion(tipoCursoEntity.getDescripcion()) // Incluye todos los campos necesarios
                .archivo(tipoCursoEntity.getArchivo())
                .build();
    }
}
