package com.upoint.productatomic.app;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.upoint.model.product.Product;

import jakarta.transaction.Transactional;


public interface ProductRepository extends JpaRepository <Product, Long > {
	@Query("SELECT P from Product P WHERE name = ?1")
	public Optional <Product> findByName(String name);
	
	@Modifying
	@Query("UPDATE Product SET name = ?1, description = ?2 WHERE id = ?3")
	@Transactional
	public void update(String name, String description, Long id);

}


