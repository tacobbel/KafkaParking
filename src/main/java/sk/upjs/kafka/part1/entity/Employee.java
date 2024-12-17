package sk.upjs.kafka.part1.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    @Setter
    @Column(name = "card_id", nullable = false, unique = true)
    private String cardId;

    @Getter
    @Setter
    @Column(name = "name", nullable = false)
    private String name;
}
