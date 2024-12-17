package sk.upjs.kafka.part2;

import java.time.Duration;
import java.time.LocalDate;

public class TimeCheckResponse {

    private String cardId;
    private LocalDate date;
    private Duration duration;

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "TimeCheckResponse{" +
                "cardId='" + cardId + '\'' +
                ", date=" + date +
                ", hours=" + duration.toHours()+
                ", minutes=" + duration.toMinutes()+
                '}';
    }
}
