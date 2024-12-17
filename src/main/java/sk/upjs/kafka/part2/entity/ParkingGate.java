package sk.upjs.kafka.part2.entity;

import jakarta.persistence.*;


import java.time.LocalDateTime;


@Entity
@Table(name = "parking_gate_log")
public class ParkingGate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "card_id", nullable = false)
    private String cardId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "direction", nullable = false)
    private String direction;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;


    public LocalDateTime getTimestamp() {
        return timestamp;
    }


    public String getDirection() {
        return direction;
    }


    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }


}