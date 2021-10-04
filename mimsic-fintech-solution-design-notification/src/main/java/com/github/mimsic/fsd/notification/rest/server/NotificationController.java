package com.github.mimsic.fsd.notification.rest.server;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.mimsic.base.common.message.MessageWrapper;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class NotificationController {

    @RequestMapping(value = "/private/v1/notification", method = RequestMethod.POST, produces = "application/json")
    public MessageWrapper<JsonNode, JsonNode> notification(@RequestBody JsonNode message) {

        return new MessageWrapper<>(null, null);
    }
}
