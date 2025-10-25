package com.order.order.service;


import com.order.order.dao.OrderDao;
import com.order.order.dto.OrderDTO;
import com.order.order.model.Orders;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ModelMapper modelMapper;


    public List<OrderDTO> getAllOrders() {
        List<Orders> ordersList = orderDao.findAll();
        return modelMapper.map(ordersList,new TypeToken<List<OrderDTO>>(){}.getType());
    }

    public OrderDTO addOrder(OrderDTO orderDTO) {
        orderDao.save(modelMapper.map(orderDTO,Orders.class));
        return orderDTO;
    }

    public OrderDTO updateOrder(OrderDTO orderDTO) {
        orderDao.save(modelMapper.map(orderDTO,Orders.class));
        return orderDTO;
    }

    public String deleteOrder(Integer orderId) {
        orderDao.deleteById(orderId);
        return "Order deleted successfully";
    }

    public OrderDTO getOrderById(Integer orderId){
        Orders order = orderDao.getOrderById(orderId);
        return modelMapper.map(order,OrderDTO.class);
    }


}
