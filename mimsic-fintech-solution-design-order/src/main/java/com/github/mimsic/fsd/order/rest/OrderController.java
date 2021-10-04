package com.github.mimsic.fsd.order.rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.mimsic.base.common.json.ObjectMapperUtil;
import com.github.mimsic.base.common.message.MessageWrapper;
import com.github.mimsic.fsd.api.feign.customer.CustomerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private CustomerClient customerClient;

    @RequestMapping(value = "/private/v1/order", method = RequestMethod.POST, produces = "application/json")
    public MessageWrapper<JsonNode, JsonNode> order(@RequestBody JsonNode message) throws IOException {

        ObjectNode objectNode = ObjectMapperUtil.createObjectNode();
        objectNode.put("1", "1");
        objectNode.put("2", "2");

        MessageWrapper<JsonNode, JsonNode> result = customerClient.customer(objectNode);

        return result;
    }
}
