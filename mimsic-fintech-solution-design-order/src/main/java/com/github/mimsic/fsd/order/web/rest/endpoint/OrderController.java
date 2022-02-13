package com.github.mimsic.fsd.order.web.rest.endpoint;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.mimsic.base.common.json.ObjectMapperUtil;
import com.github.mimsic.base.common.message.MessageWrapper;
import com.github.mimsic.fsd.api.feign.customer.CustomerClient;
import com.github.mimsic.fsd.order.persistence.entity.Order;
import com.github.mimsic.fsd.order.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class OrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private CustomerClient customerClient;

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/private/v1/order", method = RequestMethod.POST, produces = "application/json")
    public MessageWrapper<JsonNode, JsonNode> order(@RequestBody JsonNode message) throws IOException {

        JsonNode data = Optional.ofNullable(message.get("data"))
                .orElseThrow(() -> new IllegalArgumentException("data is null"));

        Long userId = Optional.ofNullable(data.get("userId"))
                .map(JsonNode::asLong)
                .orElseThrow(() -> new IllegalArgumentException("userId is null"));

        ObjectNode responseData = ObjectMapperUtil.createObjectNode();
        ObjectNode responseMetadata = ObjectMapperUtil.createObjectNode();

        try {
            MessageWrapper<JsonNode, JsonNode> wrapper = customerClient.customer(message);

            JsonNode customer = wrapper.getData();
            JsonNode customerMetadata = wrapper.getMetadata();

            ArrayNode msgNode = Optional.ofNullable((ArrayNode) customerMetadata.get("msg"))
                    .orElseGet(ObjectMapperUtil::createArrayNode);
            responseMetadata.set("msg", msgNode);

            if (customerMetadata.get("code").asInt() == 0) {
                Order order = Order.builder()
                        .id(UUID.randomUUID().toString())
                        .customerId(customer.get("id").asLong())
                        .created(ZonedDateTime.now())
                        .lastUpdate(ZonedDateTime.now())
                        .data(ObjectMapperUtil.createObjectNode())
                        .build();

                orderService.save(order);
                responseData.putPOJO("customer", customer);
                responseData.putPOJO("order", order);
                responseMetadata.put("code", 0);
            } else {
                msgNode.add(String.format("Order can not be created for user with id: %s .", userId));
                responseMetadata.put("code", 1);
            }

        } catch (Exception ex) {
            ArrayNode msgNode = responseMetadata.putArray("msg");
            msgNode.add(String.format("Order can not be created for user with id: %s . Try Later", userId));
            responseMetadata.put("code", 1);
            LOGGER.error("cache error", ex);
        }

        return new MessageWrapper<>(responseData, responseMetadata, "");
    }
}
