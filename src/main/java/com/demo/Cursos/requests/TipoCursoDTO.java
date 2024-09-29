package com.demo.Cursos.requests;

import com.demo.Cursos.models.Enumarated.ETipoCurso;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TipoCursoDTO {
    private long idTipoCurso;
    private ETipoCurso  nombreTipoCurso;
    private String descripcion;
    private byte[] archivo;
}
