package com.demo.Cursos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.Cursos.models.CursoEntity;
import com.demo.Cursos.models.ExamenEntity;
import com.demo.Cursos.models.ModuloEntity;
import com.demo.Cursos.models.ClaseEntity;
import com.demo.Cursos.repository.CursoRepository;
import com.demo.Cursos.repository.ModuloRepository;
import com.demo.Cursos.requests.ModuloDTO;
import com.demo.Cursos.requests.ClaseDTO;
import com.demo.Cursos.requests.ExamenDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import jakarta.transaction.Transactional;

@Service
public class ModuloService {

    @Autowired
    private ModuloRepository moduloRepository;
    
    @Autowired
    private CursoRepository cursoRepository;

    @Transactional
    public ModuloDTO createModulo(ModuloDTO moduloDTO) {
        CursoEntity cursoEntity = cursoRepository.findById(moduloDTO.getIdCurso())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        ModuloEntity moduloEntity = ModuloEntity.builder()
                .nombreModulo(moduloDTO.getNombreModulo())
                .descripcion(moduloDTO.getDescripcion())
                .estadoModulo(moduloDTO.isEstadoModulo())
                .curso(cursoEntity)
                .build();

        moduloEntity = moduloRepository.save(moduloEntity);

        return mapToDTO(moduloEntity);
    }

    public List<ModuloDTO> getAllModulos() {
        List<ModuloEntity> modulos = moduloRepository.findAll();
        return modulos.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public ModuloDTO getModuloById(long idModulo) {
        Optional<ModuloEntity> moduloEntityOpt = moduloRepository.findById(idModulo);
        return moduloEntityOpt.map(this::mapToDTO).orElse(null);
    }

    @Transactional
    public ModuloDTO updateModulo(long idModulo, ModuloDTO moduloDTO) {
        Optional<ModuloEntity> moduloEntityOpt = moduloRepository.findById(idModulo);
        if (moduloEntityOpt.isPresent()) {
            ModuloEntity moduloEntity = moduloEntityOpt.get();
            CursoEntity cursoEntity = cursoRepository.findById(moduloDTO.getIdCurso())
                    .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
            moduloEntity.setNombreModulo(moduloDTO.getNombreModulo());
            moduloEntity.setDescripcion(moduloDTO.getDescripcion());
            moduloEntity.setEstadoModulo(moduloDTO.isEstadoModulo());
            moduloEntity.setCurso(cursoEntity);

            moduloEntity = moduloRepository.save(moduloEntity);
            return mapToDTO(moduloEntity);
        }
        throw new RuntimeException("Módulo no encontrado");
    }

    @Transactional
    public void deleteModulo(long idModulo) {
        if (!moduloRepository.existsById(idModulo)) {
            throw new RuntimeException("Módulo no encontrado");
        }
        moduloRepository.deleteById(idModulo);
    }

    private ModuloDTO mapToDTO(ModuloEntity moduloEntity) {
        List<ClaseDTO> claseDTOs = moduloEntity.getClases().stream()
                .map(this::mapClaseToDTO)
                .collect(Collectors.toList());

        return ModuloDTO.builder()
                .idModulo(moduloEntity.getIdModulo())
                .nombreModulo(moduloEntity.getNombreModulo())
                .descripcion(moduloEntity.getDescripcion())
                .estadoModulo(moduloEntity.isEstadoModulo())
                .idCurso(moduloEntity.getCurso().getIdCurso())
                .clases(claseDTOs) // Incluir la lista completa de clases
                .build();
    }

    private ClaseDTO mapClaseToDTO(ClaseEntity claseEntity) {
        List<ExamenDTO> examenDTOs = claseEntity.getExamenes().stream()
                .map(this::mapExamenToDTO)
                .collect(Collectors.toList());

        return ClaseDTO.builder()
                .idClase(claseEntity.getIdClase())
                .nombreClase(claseEntity.getNombreClase())
                .descripcion(claseEntity.getDescripcion())
                .estadoClase(claseEntity.isEstadoClase())
                .idModulo(claseEntity.getModulo().getIdModulo())
                .examenes(examenDTOs) // Incluir la lista completa de exámenes
                .build();
    }

    private ExamenDTO mapExamenToDTO(ExamenEntity examenEntity) {
        return ExamenDTO.builder()
                .idExamen(examenEntity.getIdExamen())
                .nombreExamen(examenEntity.getNombreExamen())
                .tipoExamen(examenEntity.getTipoExamen())
                .obligatorio(examenEntity.isObligatorio())
                .estado(examenEntity.getEstado())
                .idClase(examenEntity.getClase().getIdClase())
                .build();
    }
}
