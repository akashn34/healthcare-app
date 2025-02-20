package com.healthcare.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.healthcare.entity.OrderInfo;
import com.healthcare.entity.Orders;
import com.healthcare.entity.Product;
import com.healthcare.repository.OrderInfoRepository;
import com.healthcare.repository.OrdersRepository;
import com.healthcare.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class OrdersService {
	
	@Autowired
	OrdersRepository ordersRepository;
	
	@Autowired
	OrderInfoRepository orderInfoRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Transactional
	public String placeOrder(Orders orders) {
	    orders.setOrderdate(LocalDateTime.now());
	    ordersRepository.save(orders);
	    return "{\"message\": \"Order placed successfully\"}";
	}



	public List<Orders> findAllOrders() {
		return ordersRepository.findAll();
	}
	
	/*
	 * public Optional<Orders> getOrderWithProductDetails(int oid) { return
	 * ordersRepository.findOrderWithProductDetails(oid); }
	 */
	
	public List<Orders> findOrdersByEmail(String emailid) {
		return ordersRepository.findByEmailid(emailid);
	}
	@Transactional
	public ResponseEntity<String> deleteOrder(int oid) {
	    if (ordersRepository.existsById(oid)) {
	        Orders orders = ordersRepository.findById(oid).get(); // Fetch the Orders entity

	        // Delete associated order info using the deleteByOrders method
	       

	        // Now delete the Order itself
	        ordersRepository.deleteById(oid);

	        return ResponseEntity.ok("Order deleted successfully");
	    } else {
	        return ResponseEntity.status(404).body("Order not found");
	    }
	}

	
}
