package com.upoint.productatomic.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.upoint.dto.product.ProductDto;
import com.upoint.dto.product.UpdateProductDto;
import com.upoint.productatomic.exception.EntityNotFoundException;
import com.upoint.productatomic.util.Message;


@RestController
@RequestMapping("/product")
public class ProductAtomic {
	
	@Autowired
	private ProductService service;
	
	@GetMapping(value = "/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ProductDto findProductByName(@PathVariable String name) throws EntityNotFoundException {
		return service.findProductByName(name);
	}
	
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public Long save(@RequestBody ProductDto product) {
		Long saved = service.save(product);
		return saved;
	}
	
	@DeleteMapping(value = "/{productId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> delete(@PathVariable Long productId) throws EntityNotFoundException {
		service.delete(productId);
		return new ResponseEntity<>(Message.DELETED, HttpStatus.OK);

	}
	
	@GetMapping(value = "/{productId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ProductDto  getProductById(@PathVariable Long productId) throws EntityNotFoundException {
		return service.getProductById(productId);
		
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public String update(@RequestBody UpdateProductDto product) throws EntityNotFoundException {
		return service.update(product);
	}
}
