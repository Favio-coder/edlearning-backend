package com.demo.Cursos.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table( name = "tipoCurso")
public class TipoCursoEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idTipoCurso;

    @Enumerated(EnumType.STRING)
    private ETipoCurso nombreTipoCurso;

    private String descripcion;

    @Lob
    private byte[] archivo;

    @ManyToMany(mappedBy = "tipoCurso", fetch = FetchType.EAGER)
    private Set<CursoEntity> cursos;

    public TipoCursoEntity(long idTipoCurso) {
        this.idTipoCurso = idTipoCurso;
    }
}
