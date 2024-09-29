package com.demo.Cursos.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExamenDTO {
    private long idExamen;
    private String nombreExamen;
    private String tipoExamen;
    private boolean obligatorio;
    private String estado;
    private long idClase;
}
