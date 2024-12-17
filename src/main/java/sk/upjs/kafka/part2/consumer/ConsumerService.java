package sk.upjs.kafka.part2.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.upjs.kafka.part2.entity.ParkingGate;
import sk.upjs.kafka.part2.repository.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

@Service
public class ConsumerService {

    @Autowired
    private ParkingGateRepository parkingGateRepository;

    public Duration calculateDuration(String cardId, LocalDate date) {
        ArrayList<ParkingGate> logs = parkingGateRepository.findByCardIdAndTimestampBetween(cardId, date.atStartOfDay(), date.plusDays(1).atStartOfDay());
        System.out.println("Logs :" + logs);

        // if there are no logs for the given day, but there is an entry log from the previous day, duration is calculated from the entry log time to the end of the day or current time today
        if (logs.isEmpty()) {
            ParkingGate lastLog = parkingGateRepository.findLatestLogByCardId(cardId);
            if (lastLog != null && Objects.equals(lastLog.getDirection(), "I")) {
                if (lastLog.getTimestamp().toLocalDate().equals(LocalDate.now())) {
                    return Duration.between(lastLog.getTimestamp(), LocalDateTime.now());
                } else {
                    return Duration.between(lastLog.getTimestamp(), date.plusDays(1).atStartOfDay());
                }
            }
        }

        // if there is only one exit log, duration is calculated from the start of the day to the exit log time
        if (logs.size() == 1 && logs.getFirst().getDirection().equals("O")) {
            return Duration.between(date.atStartOfDay(), logs.getFirst().getTimestamp());
        }

        Duration totalDuration = Duration.ZERO;
        LocalDateTime lastEntry = null;

        for (ParkingGate log : logs) {
            if (log.getDirection().equals("I")) {
                lastEntry = log.getTimestamp();
            } else if (log.getDirection().equals("O") && lastEntry != null) {
                totalDuration = totalDuration.plus(Duration.between(lastEntry, log.getTimestamp()));
                lastEntry = null;
            }
        }

        // if last log is entry, duration is calculated from the entry log time to the end of the day or current time today
        if (lastEntry != null) {
            if (lastEntry.toLocalDate().equals(LocalDate.now())) {
                totalDuration = totalDuration.plus(Duration.between(lastEntry, LocalDateTime.now()));
            } else {
                totalDuration = totalDuration.plus(Duration.between(lastEntry, date.plusDays(1).atStartOfDay()));
            }
        }

        return totalDuration;
    }

}
