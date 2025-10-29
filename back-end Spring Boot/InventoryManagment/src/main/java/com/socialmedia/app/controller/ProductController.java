package com.socialmedia.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.socialmedia.app.entity.Product;
import com.socialmedia.app.service.ProductService;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping("/add")
	public ResponseEntity<Product> createProduct(@RequestBody Product product) {
		Product createdProduct = productService.createProduct(product.getName(), product.getDescription(), product.getPrice(), product.getQuantity());
		return ResponseEntity.ok(createdProduct);
	}
	
	@PutMapping("/update/{id}")
	public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
		return productService.updateProduct(id, product.getName(), product.getDescription(), product.getPrice(), product.getQuantity());
	}
	
	@GetMapping("/{id}")
	public Product getProduct(@PathVariable Long id) {
		return productService.getProductById(id);
	}
	
	
	
	@PostMapping("/updateQuantity/{id}/{quantity}")
	public ResponseEntity<String> updateProductQuantity(@PathVariable Long id, @PathVariable Integer quantity) {
		Product product = productService.getProductById(id);
		if (product != null) {
			productService.updateProductQuantity(product, quantity);
			return ResponseEntity.ok("Product quantity updated successfully.");
		}
		else {
			return ResponseEntity.status(404).body("Product not found.");
		}
	}
	
	@GetMapping("/totalValue/{id}")
	public Double calculateTotalValue(@PathVariable Long id) {
		Product product = productService.getProductById(id);
		if (product != null) {
			return productService.calculateTotalValue(product);
		}
		return null;
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
		if(productService.deleteProduct(id))
		return ResponseEntity.ok("Product deleted successfully.");
		else
			return ResponseEntity.status(404).body("Product not found.");
	}
	
	@GetMapping("/products")
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}

}
