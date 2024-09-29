package com.demo.Cursos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.Cursos.models.ClaseEntity;
import com.demo.Cursos.models.ModuloEntity;
import com.demo.Cursos.repository.ClaseRepository;
import com.demo.Cursos.repository.ExamenRepository;
import com.demo.Cursos.repository.ModuloRepository;
import com.demo.Cursos.requests.ClaseDTO;
import com.demo.Cursos.requests.ExamenDTO;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClaseService {

    @Autowired
    private ClaseRepository claseRepository;

    @Autowired
    private ModuloRepository moduloRepository;

    @Autowired
    private ExamenRepository examenRepository; // Asegúrate de inyectar el repositorio de Examen

    @Transactional
    public ClaseDTO createClase(ClaseDTO claseDTO) {
        ModuloEntity moduloEntity = moduloRepository.findById(claseDTO.getIdModulo())
                .orElseThrow(() -> new RuntimeException("Módulo no encontrado"));

        ClaseEntity claseEntity = ClaseEntity.builder()
                .nombreClase(claseDTO.getNombreClase())
                .descripcion(claseDTO.getDescripcion())
                .estadoClase(claseDTO.isEstadoClase())
                .modulo(moduloEntity)
                .build();

        claseEntity = claseRepository.save(claseEntity);
        return mapToDTO(claseEntity);
    }

    public List<ClaseDTO> getAllClases() {
        List<ClaseEntity> clases = claseRepository.findAll();
        return clases.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public ClaseDTO getClaseById(long idClase) {
        Optional<ClaseEntity> claseEntityOpt = claseRepository.findById(idClase);
        return claseEntityOpt.map(this::mapToDTO).orElse(null);
    }

    @Transactional
    public ClaseDTO updateClase(long idClase, ClaseDTO claseDTO) {
        Optional<ClaseEntity> claseEntityOpt = claseRepository.findById(idClase);

        if (claseEntityOpt.isPresent()) {
            ClaseEntity claseEntity = claseEntityOpt.get();
            ModuloEntity moduloEntity = moduloRepository.findById(claseDTO.getIdModulo())
                    .orElseThrow(() -> new RuntimeException("Módulo no encontrado"));

            claseEntity.setNombreClase(claseDTO.getNombreClase());
            claseEntity.setDescripcion(claseDTO.getDescripcion());
            claseEntity.setEstadoClase(claseDTO.isEstadoClase());
            claseEntity.setModulo(moduloEntity);

            claseEntity = claseRepository.save(claseEntity);
            return mapToDTO(claseEntity);
        } else {
            throw new EntityNotFoundException("Clase no encontrada");
        }
    }

    @Transactional
    public void deleteClase(long idClase){
        if (!claseRepository.existsById(idClase)) {
            throw new EntityNotFoundException("Clase no encontrada");
        }
        claseRepository.deleteById(idClase);
    }

    private ClaseDTO mapToDTO(ClaseEntity claseEntity) {
        // Obtener la lista de ExamenDTOs
        List<ExamenDTO> examenes = examenRepository.findByClase_IdClase(claseEntity.getIdClase()).stream()
                .map(examen -> ExamenDTO.builder()
                        .idExamen(examen.getIdExamen())
                        .nombreExamen(examen.getNombreExamen())
                        .tipoExamen(examen.getTipoExamen())
                        .obligatorio(examen.isObligatorio())
                        .estado(examen.getEstado())
                        .idClase(examen.getClase().getIdClase())
                        .build())
                .collect(Collectors.toList());

        return ClaseDTO.builder()
                .idClase(claseEntity.getIdClase())
                .nombreClase(claseEntity.getNombreClase())
                .descripcion(claseEntity.getDescripcion())
                .estadoClase(claseEntity.isEstadoClase())
                .idModulo(claseEntity.getModulo().getIdModulo())
                .examenes(examenes) // Incluir la lista de ExamenDTO
                .build();
    }

    
}
