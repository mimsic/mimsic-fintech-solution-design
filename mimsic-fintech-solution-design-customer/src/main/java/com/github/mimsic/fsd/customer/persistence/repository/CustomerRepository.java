package com.github.mimsic.fsd.customer.persistence.repository;

import com.github.mimsic.fsd.customer.persistence.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByLoginId(String loginId);
}
