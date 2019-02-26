package com.pet.petorderservice.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.pet.petorderservice.domain.Product;
import com.pet.petorderservice.domain.ProductResposne;
import com.pet.petorderservice.domain.ProductResposneList;

@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	RestTemplate restTemplate;

	public List<Product> findAllProducts() {
		List<Product> productList = null;
		try {
			ProductResposneList productResposne = restTemplate
					.getForObject(
							"https://vrwiht4anb.execute-api.us-east-1.amazonaws.com/default/product/",
							ProductResposneList.class);
			if (productResposne != null
					&& productResposne.getStatusCode().equals("200")) {
				productList = Arrays.asList(productResposne.getBody());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return productList;
	}

	public Product findByName(String name) {
		return null;
	}

	public Product findById(Long id) {

		Product product = null;
		try {
			ProductResposne productResposne = restTemplate.getForObject(
					"https://vrwiht4anb.execute-api.us-east-1.amazonaws.com/default/product/"
							+ id, ProductResposne.class);
			if (productResposne != null
					&& productResposne.getStatusCode().equals("200")) {
				product = productResposne.getBody();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return product;
	}
}
