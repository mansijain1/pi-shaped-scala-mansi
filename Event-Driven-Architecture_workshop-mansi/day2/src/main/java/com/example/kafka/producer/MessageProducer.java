package main.java.com.example.kafka.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

@Service
public class MessageProducer {

   public void sendMessageFromFile() {
    try {
        ObjectMapper mapper = new ObjectMapper();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("message.json");

        if (inputStream == null) {
            throw new FileNotFoundException("message.json not found in resources");
        }

        Map<String, Object> message = mapper.readValue(inputStream, Map.class);
        sendMessage(message);
    } catch (Exception e) {
        e.printStackTrace();
    }
}

}
