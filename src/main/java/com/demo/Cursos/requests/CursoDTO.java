package com.demo.Cursos.requests;



import java.sql.Timestamp;
import java.util.List;
import java.util.Set;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CursoDTO {
    private long idCurso;

    private String nombreCurso;

    private Timestamp fechaInicio;


    private long progreso;

    private long estudiantesMatriculados;

    private byte[] imagenReferencial;

    @Builder.Default
    private List<Long> modulos = List.of(); // Inicializa como lista vacía

    @Builder.Default
    private Set<Long> tipoCurso = Set.of(); // Inicializa como set vacío
}
