package sk.upjs.kafka.part2;

import java.io.Serial;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;

public class TimeCheckResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String cardId;
    private LocalDate date;
    private Duration duration;

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }


    public void setDuration(Duration duration) {
        this.duration = duration;
    }


    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "TimeCheckResponse{" +
                "cardId='" + cardId + '\'' +
                ", date=" + date +
                ", hours=" + duration.toHours() +
                ", minutes=" + duration.toMinutes() % 60 +
                '}';
    }
}
