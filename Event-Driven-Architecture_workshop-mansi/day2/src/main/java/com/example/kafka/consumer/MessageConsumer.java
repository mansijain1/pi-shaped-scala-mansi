package main.java.com.example.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumer {

    @KafkaListener(topics = "demo-topic", groupId = "demo-group")
    public void consume(String message) {
        System.out.println("Consumed: " + message);
    }
}
