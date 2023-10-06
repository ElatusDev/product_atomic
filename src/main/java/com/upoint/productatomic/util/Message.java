package com.upoint.productatomic.util;

import org.springframework.stereotype.Component;

@Component
public class Message {
	
	public static final String DELETED = "Succesfull deletion";
	
	private static final String ENTITY_NOT_FOUND = "No content found for provided id: %1s";
	
	private static final String PRODUCT_UPDATED = "Product with id: %1s successfully updated ";
	
	private static final String ENTITY_NOT_FOUND_BY_NAME = "Product with name: %1s was not found ";
	
	public String getEntityNotFoundByName(String name) {
		return String.format(ENTITY_NOT_FOUND_BY_NAME, name);
	}
	
	public String getProductUpdated(Long id) {
		return String.format(PRODUCT_UPDATED, id);
	}
	
	public String getEntityNotFound(Long id) {
		return String.format(ENTITY_NOT_FOUND, id);
	}
}
