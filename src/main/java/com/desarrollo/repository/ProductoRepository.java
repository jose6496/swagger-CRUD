package com.desarrollo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.desarrollo.model.ProductoModel;

public interface ProductoRepository extends JpaRepository<ProductoModel, Integer>{

}
