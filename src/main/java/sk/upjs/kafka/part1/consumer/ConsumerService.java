package sk.upjs.kafka.part1.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.upjs.kafka.part1.AccessRequest;
import sk.upjs.kafka.part1.repository.EmployeeRepository;
import sk.upjs.kafka.part1.entity.ParkingGate;
import sk.upjs.kafka.part1.repository.ParkingGateRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class ConsumerService {

    @Autowired
    private ParkingGateRepository parkingGateRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public void saveLog(String cardId, String name, String direction, LocalDateTime timestamp) {
        ParkingGate pg = new ParkingGate();
        pg.setCardId(cardId);
        pg.setName(name);
        pg.setDirection(direction);
        pg.setTimestamp(timestamp);
        parkingGateRepository.save(pg);
    }

    public boolean isEmployee(String cardId) {
        return employeeRepository.existsByCardId(cardId);
    }

    public String getDirection(String cardId) {
        if (parkingGateRepository.findLatestLogDirectionByCardId(cardId) == null || parkingGateRepository.findLatestLogDirectionByCardId(cardId).isEmpty()) {
            return "N";
        } else {
            return parkingGateRepository.findLatestLogDirectionByCardId(cardId);
        }
    }

    public void validateLog(AccessRequest request) {
        try {
            if (isEmployee(request.getCardId())) {
                System.out.println("Employee with card ID " + request.getCardId() + " is allowed to enter.");
                String lastDirection = getDirection(request.getCardId());
                String currentDirection = request.getDirection();

                System.out.println("Last direction: " + lastDirection);
                System.out.println("Current direction: " + currentDirection);

                // validate log direction
                if ((lastDirection.equals("I") && currentDirection.equals("O")) ||
                        (lastDirection.equals("O") && currentDirection.equals("I")) ||
                        (lastDirection.equals("N") && (currentDirection.equals("I") || currentDirection.equals("O")))) {
                    System.out.println("Log is valid");
                    saveLog(request.getCardId(), request.getLocation(), currentDirection, request.getTimestamp());
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
