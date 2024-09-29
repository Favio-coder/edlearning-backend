package com.demo.Cursos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.Cursos.models.CursoEntity;
import com.demo.Cursos.models.ModuloEntity;
import com.demo.Cursos.models.TipoCursoEntity;
import com.demo.Cursos.repository.CursoRepository;
import com.demo.Cursos.repository.ModuloRepository;
import com.demo.Cursos.repository.TipoCursoRepository;
import com.demo.Cursos.requests.CursoDTO;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private TipoCursoRepository tipoCursoRepository;

    @Autowired
    private ModuloRepository moduloRepository;

    @Transactional
    public CursoDTO createCurso(CursoDTO cursoDTO) {
        // Mapea los IDs de TipoCurso a entidades
        Set<TipoCursoEntity> tipoCursos = cursoDTO.getTipoCurso().stream()
                .map(id -> tipoCursoRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("TipoCurso no encontrado")))
                .collect(Collectors.toSet());

        // Obtener las entidades ModuloEntity usando los IDs proporcionados
        List<ModuloEntity> modulos = Optional.ofNullable(cursoDTO.getModulos())
                .orElse(List.of()) // Usa lista vacía si getModulos() es null
                .stream()
                .map(id -> moduloRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Modulo no encontrado")))
                .collect(Collectors.toList());

        // Crear la entidad CursoEntity
        CursoEntity cursoEntity = CursoEntity.builder()
                .nombreCurso(cursoDTO.getNombreCurso())
                .fechaInicio(cursoDTO.getFechaInicio())
                .progreso(cursoDTO.getProgreso())
                .estudiantesMatriculados(cursoDTO.getEstudiantesMatriculados())
                .imagenReferencial(cursoDTO.getImagenReferencial())
                .tipoCurso(tipoCursos) // Usa el Set de TipoCursoEntity
                .modulos(modulos) // Usa la lista de ModuloEntity
                .build();

        cursoEntity = cursoRepository.save(cursoEntity);
        return mapToDTO(cursoEntity);
    }

    @Transactional
    public CursoDTO updateCurso(long idCurso, CursoDTO cursoDTO) {
        Optional<CursoEntity> cursoEntityOpt = cursoRepository.findById(idCurso);
        if (cursoEntityOpt.isPresent()) {
            CursoEntity cursoEntity = cursoEntityOpt.get();

            // Mapea las entidades TipoCurso proporcionadas
            Set<TipoCursoEntity> tipoCursos = cursoDTO.getTipoCurso().stream()
                    .map(id -> tipoCursoRepository.findById(id)
                            .orElseThrow(() -> new IllegalArgumentException("TipoCurso no encontrado")))
                    .collect(Collectors.toSet());

            // Obtener las entidades ModuloEntity usando los IDs proporcionados
            List<ModuloEntity> modulos = Optional.ofNullable(cursoDTO.getModulos())
                    .orElse(List.of()) // Usa lista vacía si getModulos() es null
                    .stream()
                    .map(id -> moduloRepository.findById(id)
                            .orElseThrow(() -> new IllegalArgumentException("Modulo no encontrado")))
                    .collect(Collectors.toList());

            cursoEntity.setNombreCurso(cursoDTO.getNombreCurso());
            cursoEntity.setFechaInicio(cursoDTO.getFechaInicio());
            cursoEntity.setProgreso(cursoDTO.getProgreso());
            cursoEntity.setEstudiantesMatriculados(cursoDTO.getEstudiantesMatriculados());
            cursoEntity.setImagenReferencial(cursoDTO.getImagenReferencial());
            cursoEntity.setTipoCurso(tipoCursos); // Actualiza los tipos de curso con entidades
            cursoEntity.setModulos(modulos); // Actualiza los módulos

            cursoEntity = cursoRepository.save(cursoEntity);
            return mapToDTO(cursoEntity);
        }
        return null;
    }

    public CursoDTO getCursoById(long idCurso) {
        Optional<CursoEntity> cursoEntityOpt = cursoRepository.findById(idCurso);
        return cursoEntityOpt.map(this::mapToDTO).orElse(null);
    }

    public List<CursoDTO> getAllCursos() {
        List<CursoEntity> cursos = cursoRepository.findAll();
        return cursos.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteCurso(long idCurso) {
        cursoRepository.deleteById(idCurso);
    }

    private CursoDTO mapToDTO(CursoEntity cursoEntity) {
        return CursoDTO.builder()
                .idCurso(cursoEntity.getIdCurso())
                .nombreCurso(cursoEntity.getNombreCurso())
                .fechaInicio(cursoEntity.getFechaInicio())
                .progreso(cursoEntity.getProgreso())
                .estudiantesMatriculados(cursoEntity.getEstudiantesMatriculados())
                .imagenReferencial(cursoEntity.getImagenReferencial())
                .tipoCurso(cursoEntity.getTipoCurso().stream()
                        .map(TipoCursoEntity::getIdTipoCurso)
                        .collect(Collectors.toSet())) // Convertir entidades a IDs
                .modulos(cursoEntity.getModulos().stream()
                        .map(ModuloEntity::getIdModulo)
                        .collect(Collectors.toList())) // Convertir entidades a IDs
                .build();
    }
}
