package com.example.order_processing.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.example.order_processing.model.Order;

@Component
public class OrderConsumer {

    @RabbitListener(queues = "order.queue")
    public void receiveOrder(Order order) {
        System.out.println("Received order: " + order.getOrderId());
        System.out.println("Item: " + order.getItem() + ", Quantity: " + order.getQuantity());
        System.out.println("Order fulfilled!\n");
    }
}
