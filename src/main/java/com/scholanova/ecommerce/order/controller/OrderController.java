package com.scholanova.ecommerce.order.controller;


import com.scholanova.ecommerce.order.NotAllowedException;
import com.scholanova.ecommerce.order.entity.Orders;
import com.scholanova.ecommerce.order.repository.OrderRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@EnableSpringDataWebSupport
@RequestMapping("/orders")
public class OrderController {

    private OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping
    public List<Orders> getAllOrders(Pageable pageable) {
        return orderRepository.findAll(pageable).get().collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Orders getOrderById(@PathVariable("id") Orders cart) { return cart;}

    @PostMapping
    public Orders createOrder() { return orderRepository.save(new Orders()); }

    @GetMapping("/{id}/checkout")
    public Orders addProductToOrder(@PathVariable("id)") Orders order) throws NotAllowedException {
        order.checkout();
        return order;
    }

}
