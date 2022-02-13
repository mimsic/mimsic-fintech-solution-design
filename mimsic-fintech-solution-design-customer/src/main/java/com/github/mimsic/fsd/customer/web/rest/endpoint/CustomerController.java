package com.github.mimsic.fsd.customer.web.rest.endpoint;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.mimsic.base.common.json.ObjectMapperUtil;
import com.github.mimsic.base.common.message.MessageWrapper;
import com.github.mimsic.base.persistence.util.EntityWrapper;
import com.github.mimsic.fsd.api.feign.customer.CustomerClient;
import com.github.mimsic.fsd.customer.persistence.entity.Customer;
import com.github.mimsic.fsd.customer.provider.CustomerProvider;
import com.github.mimsic.fsd.customer.service.CustomerService;
import org.apache.ignite.Ignite;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class CustomerController implements CustomerClient {

    private final CustomerProvider customerProvider;

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService, Ignite ignite) {
        this.customerProvider = new CustomerProvider(ignite, "public");
        this.customerService = customerService;
    }

    @Override
    public MessageWrapper<JsonNode, JsonNode> customer(JsonNode message) {

        JsonNode data = Optional.ofNullable(message.get("data"))
                .orElseThrow(() -> new IllegalArgumentException("data is null"));

        Long userId = Optional.ofNullable(data.get("userId"))
                .map(JsonNode::asLong)
                .orElseThrow(() -> new IllegalArgumentException("userId is null"));

        EntityWrapper<Customer, JsonNode> wrapper = Optional.ofNullable(customerService.findById(customerProvider, userId))
                .orElseThrow(() -> new RuntimeException("customer not found"));

        return new MessageWrapper<>(ObjectMapperUtil.valueToTree(wrapper.getEntity()), wrapper.getMetadata(), "");
    }
}
