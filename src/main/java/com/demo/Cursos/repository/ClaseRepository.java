package com.demo.Cursos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.Cursos.models.ClaseEntity;

@Repository
public interface ClaseRepository extends JpaRepository<ClaseEntity, Long> {

}
