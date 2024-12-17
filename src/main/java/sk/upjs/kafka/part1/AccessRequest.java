package sk.upjs.kafka.part1;


import java.io.Serializable;
import java.time.LocalDateTime;

public class AccessRequest implements Serializable {

    private String cardId;

    private String location;

    private LocalDateTime timestamp;

    private String direction;

    public String getLocation() {
        return location;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDirection() {
        return direction;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getCardId() {
        return cardId;
    }

    @Override
    public String toString() {
        return "AccessRequest{" +
                "cardId='" + cardId + '\'' +
                ", location='" + location + '\'' +
                ", timestamp=" + timestamp +
                ", direction=" + direction +
                '}';
    }

}
