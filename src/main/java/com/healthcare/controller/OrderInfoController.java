package com.healthcare.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthcare.entity.OrderInfo;
import com.healthcare.entity.Orders;
import com.healthcare.repository.OrdersRepository;
import com.healthcare.service.OrderInfoService;

@RestController
@RequestMapping("orderinfo")
@CrossOrigin
public class OrderInfoController {
	
	@Autowired
	OrdersRepository ordersRepository;
	
	@Autowired
	OrderInfoService orderInfoService;
	
	@PostMapping(value = "store",consumes = MediaType.APPLICATION_JSON_VALUE)
	public String storeOrderInfo(@RequestBody OrderInfo orderInfo) {
		return orderInfoService.storeOrdreInfo(orderInfo);
	}
	
	@GetMapping(value = "find",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<OrderInfo> findAllOrderinfo() {
		return orderInfoService.findAllOrderInfo();
	}
	
	@GetMapping(value = "find/orders/{oid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<OrderInfo> findByOrderId(@PathVariable int oid) {
	    // Retrieve the Orders object by its ID
	    Orders orders = ordersRepository.findById(oid).orElse(null);
	    
	    // Check if the Orders object exists, then fetch related OrderInfo
	    if (orders != null) {
	        return orderInfoService.findByOrders(orders);  // Pass the Orders object to the service method
	    } else {
	        // Return an empty list if the order is not found
	        return new ArrayList<>();
	    }
	}

	
	
	@DeleteMapping(value = "delete/{orderInfoId}")
	public String deleteOrderInfo(@PathVariable int orderInfoId) {
		return orderInfoService.deleteOrderInfo(orderInfoId);
	}

}
