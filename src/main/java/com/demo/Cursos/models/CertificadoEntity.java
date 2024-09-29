package com.demo.Cursos.models;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="certificados")
public class CertificadoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCertificado;  // Cambié a singular

    private Timestamp fechaInscripcion;

    private Timestamp fechaFinalizacion;

    private String rutaArchivoCertificado;

    // Relación de certificado a usuarios
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private UserEntity usuario;

    // Relación de certificado a curso 
    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    private CursoEntity curso;
}
