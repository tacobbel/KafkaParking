package sk.upjs.kafka.part2.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import sk.upjs.kafka.part2.TimeCheckRequest;
import sk.upjs.kafka.part2.TimeCheckResponse;

@EnableKafka
@SpringBootApplication(scanBasePackages = "sk.upjs.kafka.part2")
@EnableJpaRepositories(basePackages = "sk.upjs.kafka.part2.repository")
@EntityScan(basePackages = "sk.upjs.kafka.part2.entity")
public class ConsumerApp {

    @Autowired
    private ConsumerService consumerService;

    private KafkaTemplate<String, TimeCheckResponse> kafkaTemplate;

    public ConsumerApp(KafkaTemplate<String, TimeCheckResponse> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApp.class, args);
    }

    @KafkaListener(topics = "timeCheckRequestTopic", groupId = "timeCheckGroup")
    private void processTimeCheckRequest(TimeCheckRequest request) {
        System.out.println("Received time check request: " + request);
        TimeCheckResponse response = new TimeCheckResponse();
        response.setCardId(request.getCardId());
        response.setDate(request.getDate());
        response.setDuration(consumerService.calculateDuration(request.getCardId(), request.getDate()));
        kafkaTemplate.send("timeCheckResponseTopic", response);
        System.out.println("Time check response sent");
    }
}
