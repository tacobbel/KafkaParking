package sk.upjs.kafka.part2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sk.upjs.kafka.part2.entity.ParkingGate;

import java.time.LocalDateTime;
import java.util.ArrayList;


@Repository
public interface ParkingGateRepository extends JpaRepository<ParkingGate, Long> {

    ArrayList<ParkingGate> findByCardIdAndTimestampBetween(String cardId, LocalDateTime startDateTime, LocalDateTime endDateTime);

    @Query("SELECT l.cardId, l.name, l.direction, l.timestamp FROM ParkingGate l WHERE l.cardId = :cardId ORDER BY l.timestamp DESC")
    ParkingGate findLatestLogByCardId(@Param("cardId") String cardId);
}
