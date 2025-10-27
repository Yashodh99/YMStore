package com.order.order.controller;


import com.order.order.common.OrderResponse;
import com.order.order.dto.OrderDTO;
import com.order.order.dto.OrderEventDTO;
import com.order.order.kafka.OrderProducer;
import com.order.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value ="api/v1/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderProducer orderProducer;

    @GetMapping
    public List<OrderDTO> getOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/{orderId}")
    public OrderDTO getOrderById(@PathVariable Integer orderId){
        return orderService.getOrderById(orderId);
    }

    @PutMapping
    public OrderDTO updateOrder(@RequestBody OrderDTO orderDTO){
    return orderService.updateOrder(orderDTO);
    }

    @DeleteMapping
    public String deleteOrder(@PathVariable Integer orderId){
        return orderService.deleteOrder(orderId);
    }

    @PostMapping
    public OrderResponse createOrder(@RequestBody OrderDTO orderDTO){

        OrderEventDTO orderEventDTO = new OrderEventDTO();
        orderEventDTO.setMessage("Order is committed");
        orderEventDTO.setStatus("pending");
        orderProducer.sendMessage(orderEventDTO);

        return orderService.addOrder(orderDTO);
    }
}