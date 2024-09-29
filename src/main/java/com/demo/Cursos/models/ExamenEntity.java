package com.demo.Cursos.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "examen")
public class ExamenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idExamen;

    private String nombreExamen;

    private String tipoExamen;

    private boolean obligatorio;

    private String estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name = "idClase")
    @JsonBackReference
    private ClaseEntity clase;


}
