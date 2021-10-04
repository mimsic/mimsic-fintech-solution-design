package com.github.mimsic.fsd.api.feign.customer;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.mimsic.base.common.message.MessageWrapper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(name = "customer")
public interface CustomerClient {

    @RequestMapping(value = "/api/private/v1/customer", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    MessageWrapper<JsonNode, JsonNode> customer(@RequestBody JsonNode message);
}
