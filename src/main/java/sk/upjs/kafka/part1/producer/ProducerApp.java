package sk.upjs.kafka.part1.producer;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;
import sk.upjs.kafka.part1.AccessRequest;

import java.time.LocalDateTime;

@SpringBootApplication
public class ProducerApp {

    private final KafkaTemplate<String, AccessRequest> kafkaTemplate;

    public ProducerApp(KafkaTemplate<String, AccessRequest> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostConstruct
    private void generateAccessRequest() {
        AccessRequest request = new AccessRequest();
        request.setCardId("67890");
        request.setLocation("Main Gate");
        request.setTimestamp(LocalDateTime.now());
        request.setDirection("O"); // Nastavíme ENUM z ParkingGate

        kafkaTemplate.send("parkingGateTopic", request);
        System.out.println("Sent access request message: " + request);
    }

    public static void main(String[] args) {
        SpringApplication.run(ProducerApp.class, args);
    }
}
