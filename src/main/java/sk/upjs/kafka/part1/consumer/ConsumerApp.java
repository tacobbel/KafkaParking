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

        try {
            if (consumerService.isEmployee(request.getCardId())) {
                System.out.println("Employee with card ID " + request.getCardId() + " is allowed to enter.");
                String lastDirection = consumerService.getDirection(request.getCardId());
                String currentDirection = request.getDirection();

                System.out.println("Last direction: " + lastDirection);
                System.out.println("Current direction: " + currentDirection);

                // validate log direction
                if ((lastDirection.equals("I") && currentDirection.equals("O")) ||
                        (lastDirection.equals("O") && currentDirection.equals("I")) ||
                        (lastDirection.equals("N") && (currentDirection.equals("I") || currentDirection.equals("O")))) {
                    System.out.println("Log is valid");
                    consumerService.saveLog(request.getCardId(), request.getLocation(), currentDirection, request.getTimestamp());
                } else {
                    throw new IllegalArgumentException("Invalid log for card ID " + request.getCardId());
                }
            } else {
                throw new IllegalArgumentException("Access denied for card ID " + request.getCardId());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
