package com.example.order_processing.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.order_processing.config.RabbitConfig.EXCHANGE;
import static com.example.order_processing.config.RabbitConfig.ROUTING_KEY;
import com.example.order_processing.model.Order;

@RestController
@RequestMapping("/orders")
public class OrderProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping
    public String placeOrder(@RequestBody Order order) {
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, order);
        return "Order placed!";
    }
}
