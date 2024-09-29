package com.demo.Cursos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.demo.Cursos.models.ExamenEntity;

@Repository
public interface ExamenRepository extends JpaRepository<ExamenEntity, Long>{

    List<ExamenEntity> findByClase_IdClase(long idClase);
} 
