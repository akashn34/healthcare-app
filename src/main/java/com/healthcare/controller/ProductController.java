package com.healthcare.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

import com.healthcare.entity.Product;
import com.healthcare.service.ProductService;

@RestController
@RequestMapping("product")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {
	@Autowired
	ProductService productService;
	
	@PostMapping(value = "store", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> storeProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.storeProduct(product));
    }
	
	 @GetMapping(value = "find", produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<List<Product>> findAll() {
	        return ResponseEntity.ok(productService.findAllProducts());
	    }
	 
		/*
		 * @GetMapping(value = "find/{pid}", produces =
		 * MediaType.APPLICATION_JSON_VALUE) public ResponseEntity<Product>
		 * getProductWithOrderDetails(@PathVariable int pid) { Optional<Product>
		 * productOpt = productService.getProductWithOrderDetails(pid); if
		 * (productOpt.isPresent()) { return ResponseEntity.ok(productOpt.get()); } else
		 * { return ResponseEntity.notFound().build(); } }
		 */

	
	 @PutMapping(value = "update/{pid}", consumes = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<String> updateProduct(@PathVariable int pid, @RequestBody Product product) {
	        product.setPid(pid);
	        return ResponseEntity.ok(productService.updateProduct(product));
	    }


	
	 @DeleteMapping(value = "delete/{pid}")
	    public ResponseEntity<String> deleteProduct(@PathVariable int pid) {
	        return ResponseEntity.ok(productService.deleteProduct(pid));
	    }

}
