package com.demo.Cursos.services;

import com.demo.Cursos.requests.ExamenDTO;

import jakarta.persistence.EntityNotFoundException;

import com.demo.Cursos.models.ClaseEntity;
import com.demo.Cursos.models.ExamenEntity;
import com.demo.Cursos.repository.ExamenRepository;
import com.demo.Cursos.repository.ClaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExamenService {

    @Autowired
    private ExamenRepository examenRepository;

    @Autowired
    private ClaseRepository claseRepository;

    @Transactional
    public ExamenDTO createExamen(ExamenDTO examenDTO) {
        // Verificar que la clase asociada exista
        ClaseEntity claseEntity = claseRepository.findById(examenDTO.getIdClase())
                .orElseThrow(() -> new EntityNotFoundException("Clase no encontrada"));

        // Crear la entidad Examen con la ClaseEntity
        ExamenEntity examenEntity = ExamenEntity.builder()
                .nombreExamen(examenDTO.getNombreExamen())
                .tipoExamen(examenDTO.getTipoExamen())
                .obligatorio(examenDTO.isObligatorio())
                .estado(examenDTO.getEstado())
                .clase(claseEntity)  // Asignar la ClaseEntity
                .build();

        // Guardar el Examen en la base de datos
        examenEntity = examenRepository.save(examenEntity);
        return mapToDTO(examenEntity);
    }

    public List<ExamenDTO> getAllExamenes() {
        List<ExamenEntity> examenes = examenRepository.findAll();
        return examenes.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public ExamenDTO getExamenById(long idExamen) {
        Optional<ExamenEntity> examenEntityOpt = examenRepository.findById(idExamen);
        return examenEntityOpt.map(this::mapToDTO).orElse(null);
    }

    @Transactional
    public ExamenDTO updateExamen(long idExamen, ExamenDTO examenDTO) {
        // Buscar la entidad Examen en la base de datos
        Optional<ExamenEntity> examenEntityOpt = examenRepository.findById(idExamen);

        if (examenEntityOpt.isPresent()) {
            ExamenEntity examenEntity = examenEntityOpt.get();

            // Verificar y obtener la clase asociada
            ClaseEntity claseEntity = claseRepository.findById(examenDTO.getIdClase())
                    .orElseThrow(() -> new EntityNotFoundException("Clase no encontrada"));

            // Actualizar los campos de la entidad Examen
            examenEntity.setNombreExamen(examenDTO.getNombreExamen());
            examenEntity.setTipoExamen(examenDTO.getTipoExamen());
            examenEntity.setObligatorio(examenDTO.isObligatorio());
            examenEntity.setEstado(examenDTO.getEstado());
            examenEntity.setClase(claseEntity); // Actualizar la referencia a la ClaseEntity

            // Guardar la entidad Examen actualizada en la base de datos
            examenEntity = examenRepository.save(examenEntity);

            // Convertir y devolver el DTO actualizado
            return mapToDTO(examenEntity);
        } else {
            throw new EntityNotFoundException("Examen no encontrado");
        }
    }

    @Transactional
    public void deleteExamen(long idExamen) {
        if (!examenRepository.existsById(idExamen)) {
            throw new EntityNotFoundException("Examen no encontrado");
        }
        examenRepository.deleteById(idExamen);
    }

    private ExamenDTO mapToDTO(ExamenEntity examenEntity) {
        return ExamenDTO.builder()
                .idExamen(examenEntity.getIdExamen())
                .nombreExamen(examenEntity.getNombreExamen())
                .tipoExamen(examenEntity.getTipoExamen())
                .obligatorio(examenEntity.isObligatorio())
                .estado(examenEntity.getEstado())
                .idClase(examenEntity.getClase().getIdClase()) // Obtener el ID de la Clase
                .build();
    }
}
