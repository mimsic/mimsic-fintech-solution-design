package com.github.mimsic.fsd.order.persistence.repository;

import com.github.mimsic.fsd.order.persistence.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {

    @Query("SELECT o FROM Order o WHERE o.customerId = ?1")
    List<Order> findByCustomerId(Long customerId);
}
