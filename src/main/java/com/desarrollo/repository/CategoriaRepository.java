package com.desarrollo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.desarrollo.model.CategoriaModel;

public interface CategoriaRepository extends JpaRepository<CategoriaModel, Integer>{

}
