package com.demo.Cursos.models;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

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
import jakarta.persistence.FetchType;
import jakarta.persistence.CascadeType;
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

    // Relación uno a muchos con Modulos
    @OneToMany(mappedBy = "curso")
    @JsonManagedReference
    private List<ModuloEntity> modulos;

    // Relación muchos a muchos con Tipo de Curso
    @ManyToMany(fetch = FetchType.EAGER, targetEntity = TipoCursoEntity.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE}) 
    @JoinTable(name = "cursoTipoCurso",
               joinColumns = @JoinColumn(name = "cursoId"),
               inverseJoinColumns = @JoinColumn(name = "tipoCursoId"))
    private Set<TipoCursoEntity> tipoCurso;
    
    // Relación de uno a muchos para certificados
    @OneToMany(mappedBy = "curso", cascade=CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<CertificadoEntity> certificados = new HashSet<>();
}
