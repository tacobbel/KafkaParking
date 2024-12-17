package sk.upjs.kafka.part1.producer;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;
import sk.upjs.kafka.part1.AccessRequest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class ProducerApp {

    private final KafkaTemplate<String, AccessRequest> kafkaTemplate;

    public ProducerApp(KafkaTemplate<String, AccessRequest> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostConstruct
    private void generateAccessRequest() {
        AccessRequest request = new AccessRequest();
        request.setCardId("54321");
        request.setLocation("Main Gate");
//        request.setTimestamp(LocalDateTime.parse("2024-12-16 08:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        request.setTimestamp(LocalDateTime.now());
        request.setDirection("O");

        kafkaTemplate.send("parkingGateTopic", request);
        System.out.println("Sent access request message: " + request);
    }

    public static void main(String[] args) {
        SpringApplication.run(ProducerApp.class, args);
    }
}
