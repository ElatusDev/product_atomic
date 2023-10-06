package com.upoint.productatomic.app;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.upoint.dto.product.ProductDto;
import com.upoint.dto.product.UpdateProductDto;
import com.upoint.model.product.Product;
import com.upoint.utilities.mapper.Mapper;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import com.upoint.productatomic.exception.EntityNotFoundException;
import com.upoint.productatomic.util.Message;
import com.upoint.utilities.mapper.ProductMapper;

@Service
public class ProductService {
	
	private static final Logger LOG = LoggerFactory.getLogger(ProductService.class);
	
	@Autowired
	private Message message;
	
	@Autowired
	private ProductRepository repository;
	
	
	@Autowired
	@Qualifier("productMapper")
	private Mapper<ProductDto, Product> productMapper;
	
	public ProductDto findProductByName(String name) throws EntityNotFoundException {
		Optional<Product> result = repository.findByName(name);
		if(result.isPresent()) {
			return productMapper.mapTo(result.get());
		} else {
			throw new EntityNotFoundException(message.getEntityNotFoundByName(name));
		}
	}
	
	public Long save(ProductDto dto) {
		Product product = productMapper.mapFrom(dto);
		Product saved = repository.save(product);
		LOG.info("product entity saved with id: " + saved.getId());
		return saved.getId();
		
	}
	
	public void delete(Long productId) throws EntityNotFoundException {
		Optional<Product> result = repository.findById(productId);
		if(result.isPresent()) {
			repository.delete(result.get());
		} else {
			throw new EntityNotFoundException(message.getEntityNotFound(productId));
		}
		
	}
	
	public ProductDto getProductById(Long productId) throws EntityNotFoundException {
		Optional<Product> result = repository.findById(productId);
		if(result.isPresent()) {
			return productMapper.mapTo(result.get());
		} else {
			throw new EntityNotFoundException(message.getEntityNotFound(productId));
		}
		
	}
	
	public String update(UpdateProductDto dto) throws EntityNotFoundException {
		Optional <Product> result = repository.findById(dto.getId());
		if(result.isPresent()) {
			repository.update(dto.getName(), dto.getDescription(), dto.getId());
			return message.getProductUpdated(dto.getId());
		} else {
			throw new EntityNotFoundException(message.getEntityNotFound(dto.getId()));
		}
	}

}
