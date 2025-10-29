package com.socialmedia.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialmedia.app.entity.Product;
import com.socialmedia.app.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public Product createProduct(String name, String description, Double price, Integer quantity) {
		Product product = new Product();
		product.setName(name);
		product.setDescription(description);
		product.setPrice(price);
		product.setQuantity(quantity);
		productRepository.save(product);
		return product;
	}
	
	public Product updateProduct(Long id, String name, String description, Double price, Integer quantity) {
		Product product = productRepository.findById(id).orElse(null);
		if (product != null) {
			product.setName(name);
			product.setDescription(description);
			product.setPrice(price);
			product.setQuantity(quantity);
			productRepository.save(product);
		}
		return product;
	}
	
	public void updateProductQuantity(Product product, Integer quantity) {
		product.setQuantity(quantity);
		productRepository.save(product);
	}
	
	public Double calculateTotalValue(Product product) {
		return product.getPrice() * product.getQuantity();
		
	}
	
	public String getProductInfo(Product product) {
		return "Product Name: " + product.getName() + ", Description: " + product.getDescription() +
			   ", Price: " + product.getPrice() + ", Quantity: " + product.getQuantity();
	}
	
	public Product getProductById(Long id) {
		return productRepository.findById(id).orElse(null);
	}
	
	public Boolean deleteProduct(Long id) {
		if(getProductById(id) != null) {
		productRepository.deleteById(id);
		return true;
		}
		
		
		else
			return false;
		
	}
	
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}
	

}
