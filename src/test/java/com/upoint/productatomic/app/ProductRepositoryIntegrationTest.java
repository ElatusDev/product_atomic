package com.upoint.productatomic.app;


import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.upoint.model.product.Product;


@SpringBootTest(classes = {App.class})
@TestPropertySource("classpath:qa-config/qa-product_atomic.properties")
public class ProductRepositoryIntegrationTest {
	
	@Autowired
	private ProductRepository repository;
	private Product product;
	
	@Test
	public void givenProductExists_whenFindingByName_thenGetObject() {
		final String expectedName = "NAME ONE";
		final Optional<Product> found = repository.findByName(expectedName);
		
		Assertions.assertTrue(found.isPresent());
	}

}
