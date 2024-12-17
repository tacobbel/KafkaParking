package sk.upjs.kafka.part2.producer;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import sk.upjs.kafka.part2.TimeCheckRequest;
import sk.upjs.kafka.part2.TimeCheckResponse;

import java.time.LocalDate;

@SpringBootApplication
public class ProducerApp {

    private KafkaTemplate<String, TimeCheckRequest> kafkaTemplate;

    public ProducerApp(KafkaTemplate<String, TimeCheckRequest> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(ProducerApp.class, args);
    }

    @PostConstruct
    private void generateTimeCheckRequest() {
        TimeCheckRequest request = new TimeCheckRequest();
        request.setCardId("54321");
        request.setDate(LocalDate.parse("2024-12-17"));

        kafkaTemplate.send("timeCheckRequestTopic", request);
        System.out.println("Sent time check request: " + request);
    }

    @KafkaListener(topics = "timeCheckResponseTopic", groupId = "timeResponseGroup")
    public void receiveTimeCheckResponse(TimeCheckResponse response) {
        System.out.println("Received time check response: " + response);
    }


}
