package com.healthcare.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthcare.entity.Orders;
import com.healthcare.service.OrdersService;

@RestController
@RequestMapping("orders")
@CrossOrigin
public class OrdersController {
	
	@Autowired 
	OrdersService ordersService;
	
	@PostMapping(value = "place", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> orderPlace(@RequestBody Orders orders) {
        String result = ordersService.placeOrder(orders);
  
        if (result.contains("successfully")) {
            return ResponseEntity.status(HttpStatus.OK).body(result); 
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result); 
        }
    }

	@GetMapping(value = "find",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Orders> findAllOrders() {
		return ordersService.findAllOrders();
	}
	
	/*
	 * @GetMapping(value = "find/details/{oid}", produces =
	 * MediaType.APPLICATION_JSON_VALUE) public ResponseEntity<Orders>
	 * getOrderWithProductDetails(@PathVariable int oid) { Optional<Orders> orderOpt
	 * = ordersService.getOrderWithProductDetails(oid); if (orderOpt.isPresent()) {
	 * return ResponseEntity.ok(orderOpt.get()); } else { return
	 * ResponseEntity.notFound().build(); } }
	 */
	
	@GetMapping(value = "find/{emailid}",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Orders> findOrdersByEmail(@PathVariable String emailid) {
		return ordersService.findOrdersByEmail(emailid);
	}
	
	@DeleteMapping(value = "delete/{oid}")
	public ResponseEntity<String> deleteOrder(@PathVariable int oid) {
		return ordersService.deleteOrder(oid);
		
	}

}
