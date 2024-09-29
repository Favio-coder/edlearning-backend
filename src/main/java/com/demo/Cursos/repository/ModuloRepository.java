package com.demo.Cursos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.Cursos.models.ModuloEntity;

@Repository
public interface ModuloRepository extends JpaRepository<ModuloEntity, Long>{

}
