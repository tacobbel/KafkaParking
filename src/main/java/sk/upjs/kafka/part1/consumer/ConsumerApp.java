package sk.upjs.kafka.part1.consumer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.EnableKafka;
import sk.upjs.kafka.part1.AccessRequest;

@EnableKafka
@SpringBootApplication(scanBasePackages = "sk.upjs.kafka.part1")
@EnableJpaRepositories(basePackages = "sk.upjs.kafka.part1.repository")
@EntityScan(basePackages = "sk.upjs.kafka.part1.entity")
public class ConsumerApp {

    @Autowired
    private ConsumerService consumerService;

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApp.class, args);
    }

    @KafkaListener(topics = "parkingGateTopic", groupId = "parkingGateGroup")
    public void validateEmployee(AccessRequest request) {
        System.out.println("Received JSON message: " + request);
        consumerService.validateLog(request);
    }
}
