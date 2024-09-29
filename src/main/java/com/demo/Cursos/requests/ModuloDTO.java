package com.demo.Cursos.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ModuloDTO {
    private long idModulo;

    private String nombreModulo;

    private String descripcion;

    private boolean estadoModulo;

    private long idCurso;

    private List<ClaseDTO> clases;
}
