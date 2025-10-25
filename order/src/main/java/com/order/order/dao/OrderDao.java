package com.order.order.dao;

import com.order.order.model.Orders;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDao extends JpaRepository<Orders,Integer> {

    @Query(value = "SELECT * FROM order WHERE id =?1",nativeQuery = true)
    Orders getOrderById(Integer orderId);
}
