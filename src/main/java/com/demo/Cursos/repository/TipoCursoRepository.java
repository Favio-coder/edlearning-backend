package com.demo.Cursos.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.Cursos.models.TipoCursoEntity;

@Repository
public interface TipoCursoRepository extends JpaRepository<TipoCursoEntity, Long>{

}
