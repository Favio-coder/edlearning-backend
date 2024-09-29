package com.demo.Cursos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.Cursos.models.CursoEntity;

@Repository
public interface CursoRepository extends JpaRepository<CursoEntity, Long> {

}
