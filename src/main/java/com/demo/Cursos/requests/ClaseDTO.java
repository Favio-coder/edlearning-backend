package com.demo.Cursos.requests;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.Builder;

@Data 
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClaseDTO {
    private long idClase;
    private String nombreClase;
    private String descripcion;
    private boolean estadoClase;
    private long idModulo;

    //private List<Long> examenes;

    private List<ExamenDTO> examenes;
}
