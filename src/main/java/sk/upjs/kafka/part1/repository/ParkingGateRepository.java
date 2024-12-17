package sk.upjs.kafka.part1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sk.upjs.kafka.part1.entity.ParkingGate;

@Repository
public interface ParkingGateRepository extends JpaRepository<ParkingGate, Long> {

    @Query("SELECT l.direction FROM ParkingGate l WHERE l.cardId = :cardId ORDER BY l.timestamp DESC LIMIT 1")
    String findLatestLogDirectionByCardId(@Param("cardId") String cardId);
}
