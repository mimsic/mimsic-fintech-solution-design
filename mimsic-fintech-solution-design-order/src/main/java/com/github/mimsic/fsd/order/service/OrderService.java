package com.github.mimsic.fsd.order.service;

import com.github.mimsic.base.persistence.config.SchemaRoutingContextHolder;
import com.github.mimsic.fsd.order.persistence.entity.Order;
import com.github.mimsic.fsd.order.persistence.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order findById(String id) {
        return orderRepository.findById(id).orElseGet(() -> null);
    }

    public List<Order> saveAll(List<Order> orders) {
        return orderRepository.saveAll(orders);
    }

    public Order save(Order order) {

        Long customerId = Optional.ofNullable(order.getCustomerId())
                .orElseThrow(IllegalArgumentException::new);

        String schema;
        if (customerId % 2 == 0) {
            schema = "tenant_2";
        } else {
            schema = "tenant_1";
        }
        SchemaRoutingContextHolder.set(schema);
        try {
            return orderRepository.save(order);
        } finally {
            SchemaRoutingContextHolder.clear();
        }
    }

    public void delete(Order order) {
        orderRepository.delete(order);
    }
}
