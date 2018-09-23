package com.gmail.amalcaraz89.lottery.event;

import com.gmail.amalcaraz89.lottery.model.Ticket;
import io.nuls.contract.sdk.Event;

import java.util.List;

public class TicketsBoughtEvent implements Event {

    private Long lotteryId;
    private List<Ticket> tickets;

    public TicketsBoughtEvent(Long lotteryId, List<Ticket> tickets) {
        this.lotteryId = lotteryId;
        this.tickets = tickets;
    }

    public Long getLotteryId() {
        return lotteryId;
    }

    public List<Ticket> tickets() {
        return tickets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TicketsBoughtEvent that = (TicketsBoughtEvent) o;

        if (lotteryId != null ? !lotteryId.equals(that.lotteryId) : that.lotteryId != null) return false;
        if (tickets != null ? !tickets.equals(that.tickets) : that.tickets != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = lotteryId != null ? lotteryId.hashCode() : 0;
        result = 31 * result + (tickets != null ? tickets.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "{" +
                "lotteryId: " + lotteryId +
                ", tickets: " + tickets +
                "}";
    }
}
