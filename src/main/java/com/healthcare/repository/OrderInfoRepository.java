package com.healthcare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthcare.entity.OrderInfo;
import com.healthcare.entity.Orders;
import com.healthcare.entity.Product;

import jakarta.transaction.Transactional;



@Repository
public interface OrderInfoRepository extends JpaRepository<OrderInfo,Integer> {

}
