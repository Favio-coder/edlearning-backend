package com.demo.Cursos.models;

import java.sql.Timestamp;  // Cambiar a java.sql.Timestamp
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.FetchType;  // Asegúrate de importar FetchType
import jakarta.persistence.CascadeType;  // Asegúrate de importar CascadeType
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "curso")
public class CursoEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCurso;

    private String nombreCurso;

    private Timestamp fechaInicio;

    private long progreso;

    private long estudiantesMatriculados;

    @Lob
    private byte[] imagenReferencial;

    @OneToMany(mappedBy = "curso")
    @JsonManagedReference
    private List<ModuloEntity> modulos;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = TipoCursoEntity.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE}) 
    @JoinTable(name = "cursoTipoCurso",
               joinColumns = @JoinColumn(name = "cursoId"),
               inverseJoinColumns = @JoinColumn(name = "tipoCursoId"))
    private Set<TipoCursoEntity> tipoCurso;   
}
