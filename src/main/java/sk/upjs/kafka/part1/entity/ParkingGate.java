package sk.upjs.kafka.part1.entity;

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

    public Long getId() {
        return id;
    }


    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }


    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "direction", nullable = false)
    private String direction;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

}