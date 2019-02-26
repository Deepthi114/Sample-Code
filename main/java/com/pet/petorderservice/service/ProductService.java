package com.pet.petorderservice.service;

import java.util.List;

import com.pet.petorderservice.domain.Product;

public interface ProductService {
	
	Product findByName(String name);
		
	List<Product> findAllProducts();

	Product findById(Long id);
	

}
