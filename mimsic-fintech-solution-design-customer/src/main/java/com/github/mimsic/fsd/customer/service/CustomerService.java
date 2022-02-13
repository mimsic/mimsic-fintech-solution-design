package com.github.mimsic.fsd.customer.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.mimsic.base.common.json.ObjectMapperUtil;
import com.github.mimsic.base.common.provider.Provider;
import com.github.mimsic.base.persistence.config.SchemaRoutingContextHolder;
import com.github.mimsic.base.persistence.util.EntityWrapper;
import com.github.mimsic.fsd.customer.persistence.entity.Customer;
import com.github.mimsic.fsd.customer.persistence.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> findAll(Provider<Long, Customer> provider) {

        SchemaRoutingContextHolder.set(provider.qualifier());
        try {
            return customerRepository.findAll();
        } finally {
            SchemaRoutingContextHolder.clear();
        }
    }

    public EntityWrapper<Customer, JsonNode> findById(Provider<Long, Customer> provider, Long id) {

        ObjectNode node = ObjectMapperUtil.createObjectNode();
        ArrayNode msgNode = node.putArray("msg");
        Customer customer;
        try {
            customer = provider.value(id);
            if (customer != null) {
                msgNode.add(String.format("customer with id: %s loaded from cache.", id));
                node.put("code", 0);
                return new EntityWrapper<>(customer, node);
            }
        } catch (Exception ex) {
            msgNode.add(String.format("customer with id: %s cannot be loaded from cache.", id));
            LOGGER.error("cache error", ex);
        }
        SchemaRoutingContextHolder.set(provider.qualifier());
        try {
            customer = customerRepository.findById(id).orElseGet(() -> null);
            if (customer == null) {
                msgNode.add(String.format("customer with id: %s was not found in database.", id));
                node.put("code", 1);
            } else {
                msgNode.add(String.format("customer with id: %s loaded from database.", id));
                node.put("code", 0);
                try {
                    provider.save(customer.getId(), customer);
                    msgNode.add(String.format("customer with id: %s stored to cache.", id));
                } catch (Exception ex) {
                    msgNode.add(String.format("customer with id: %s cannot be stored to cache.", id));
                    LOGGER.error("cache error", ex);
                }
            }
        } finally {
            SchemaRoutingContextHolder.clear();
        }
        return new EntityWrapper<>(customer, node);
    }

    public Customer findByLoginId(Provider<Long, Customer> provider, String loginId) {

        Customer customer;
        SchemaRoutingContextHolder.set(provider.qualifier());
        try {
            customer = customerRepository.findByLoginId(loginId);
        } finally {
            SchemaRoutingContextHolder.clear();
        }
        return customer;
    }

    public List<Customer> saveAll(Provider<Long, Customer> provider, List<Customer> customers) {

        SchemaRoutingContextHolder.set(provider.qualifier());
        try {
            return customerRepository.saveAll(customers);
        } finally {
            SchemaRoutingContextHolder.clear();
        }
    }

    public Customer save(Provider<Long, Customer> provider, Customer customer) {

        SchemaRoutingContextHolder.set(provider.qualifier());
        try {
            customer = customerRepository.save(customer);
        } finally {
            SchemaRoutingContextHolder.clear();
        }
        provider.save(customer.getId(), customer);
        return customer;
    }

    public void delete(Provider<Long, Customer> provider, Customer customer) {

        SchemaRoutingContextHolder.set(provider.qualifier());
        try {
            customerRepository.delete(customer);
        } finally {
            SchemaRoutingContextHolder.clear();
        }
        provider.delete(customer.getId());
    }
}
