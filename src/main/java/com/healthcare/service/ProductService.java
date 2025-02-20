package com.healthcare.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.entity.Product;
import com.healthcare.repository.OrderInfoRepository;
import com.healthcare.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductService {
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	OrderInfoRepository orderInfoRepository;
	
	public String storeProduct(Product product) {
		if(productRepository.existsById(product.getPid())) {
			return "Product ID must be unique";
		}
		productRepository.save(product);
		return "Product Stored successfully";
	}
		
	public List<Product> findAllProducts() {
		return productRepository.findAll();
	}
	
	/*
	 * public Optional<Product> getProductWithOrderDetails(int pid) { return
	 * productRepository.findProductWithOrderDetails(pid); }
	 */
	
	@Transactional
    public String updateProduct(Product product) {
        Optional<Product> existingProduct = productRepository.findById(product.getPid());
        if (existingProduct.isPresent()) {
            Product updatedProduct = existingProduct.get();
            updatedProduct.setPname(product.getPname());
            updatedProduct.setPrice(product.getPrice());
            updatedProduct.setDescription(product.getDescription());
            updatedProduct.setStock(product.getStock());
            updatedProduct.setImageurl(product.getImageurl());

            productRepository.save(updatedProduct);
            return "Product updated successfully";
        } else {
            return "Product not found";
        }
    }
	
	@Transactional
    public String deleteProduct(int pid) {
        Optional<Product> productOpt = productRepository.findById(pid);
        if (productOpt.isPresent()) {
            Product product = productOpt.get();

            /*orderInfoRepository.deleteByProduct(product);*/

            productRepository.delete(product);
            return "Product deleted successfully";
        } else {
            return "Product not found";
        }
    }
}
