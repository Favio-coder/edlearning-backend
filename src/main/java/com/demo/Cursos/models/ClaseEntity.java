package com.demo.Cursos.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "clases")
public class ClaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idClase;

    private String nombreClase;

    private String descripcion;

    private boolean estadoClase;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idModulo")
    private ModuloEntity modulo;

    @OneToMany(mappedBy = "clase", cascade = CascadeType.REMOVE,  orphanRemoval = true)
    @JsonManagedReference
    private List<ExamenEntity> examenes;
}
