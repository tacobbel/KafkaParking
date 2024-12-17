package sk.upjs.kafka.part1.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
        if(parkingGateRepository.findLatestLogDirectionByCardId(cardId) == null || parkingGateRepository.findLatestLogDirectionByCardId(cardId).isEmpty()) {
            return "N";
        } else {
            return parkingGateRepository.findLatestLogDirectionByCardId(cardId);
        }
    }
}
