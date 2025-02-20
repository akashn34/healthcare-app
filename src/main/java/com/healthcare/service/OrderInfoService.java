package com.healthcare.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.entity.OrderInfo;
import com.healthcare.entity.Orders;
import com.healthcare.repository.OrderInfoRepository;

@Service
public class OrderInfoService {
	
	@Autowired
	OrderInfoRepository orderInfoRepository;
	
	public String storeOrdreInfo(OrderInfo orderInfo) {
		try {
			orderInfoRepository.save(orderInfo);
			return "Order info stored";
		} catch (Exception e) {
			System.err.println(e);
			return "Order info not stored "+e.getMessage();
		}
		
	}
	
	public List<OrderInfo> findAllOrderInfo() {
		return orderInfoRepository.findAll();
	}
	
	
	public List<OrderInfo> findByOrders(Orders orders) { 
		List<Integer> orderItemIdList = new ArrayList();
		
		for(OrderInfo orderInfo: orders.getListoforders()) {
			orderItemIdList.add(orderInfo.getOrderItemId());
		}
		
		return orderInfoRepository.findAllById(orderItemIdList); 
	}
	 
	
	public String deleteOrderInfo(int orderInfoId) {
		Optional<OrderInfo> orderinfo = orderInfoRepository.findById(orderInfoId);
		if(orderinfo.isPresent()) {
			orderInfoRepository.deleteById(orderInfoId);
			return "Order info deleted successfully";
		} else {
			return "order info not found";
		}
	}
		

}
