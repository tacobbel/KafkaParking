package sk.upjs.kafka.part2;

import java.time.LocalDate;

public class TimeCheckRequest {

    private String cardId;

    private LocalDate date;

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "TimeCheckRequest{" +
                "cardId='" + cardId + '\'' +
                ", date=" + date +
                '}';
    }
}
