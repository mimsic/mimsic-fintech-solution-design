package com.github.mimsic.fsd.customer.rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.mimsic.base.common.message.MessageWrapper;
import com.github.mimsic.fsd.api.feign.customer.CustomerClient;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController implements CustomerClient {

    @Override
    public MessageWrapper<JsonNode, JsonNode> customer(JsonNode message) {

        ((ObjectNode) message).put("3", "3");

        return new MessageWrapper<>(message, null);
    }
}
