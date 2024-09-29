package com.demo.Cursos.models;

import java.util.Set;

import com.demo.Cursos.models.Enumarated.ERoles;

import java.util.HashSet;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rol")
public class RolEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idRol;

    @Enumerated(EnumType.STRING)
    private ERoles nombreRol;

    private String descripcionRol;

    @ManyToMany(mappedBy = "roles")
    @Builder.Default
    private Set<UserEntity> usuarios = new HashSet<>();
}
