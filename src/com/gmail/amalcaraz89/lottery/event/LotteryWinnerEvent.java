package com.gmail.amalcaraz89.lottery.event;

import com.gmail.amalcaraz89.lottery.model.Ticket;
import io.nuls.contract.sdk.Event;

public class LotteryWinnerEvent implements Event {

    private Long lotteryId;
    private Long ticketId;
    private Ticket ticket;

    public LotteryWinnerEvent(Long lotteryId, Long ticketId, Ticket ticket) {
        this.lotteryId = lotteryId;
        this.ticketId = ticketId;
        this.ticket = ticket;
    }

    public Long getLotteryId() {
        return lotteryId;
    }

    public Long getTicketId() { return ticketId; }

    public Ticket getTicket() {
        return ticket;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LotteryWinnerEvent that = (LotteryWinnerEvent) o;

        if (lotteryId != null ? !lotteryId.equals(that.lotteryId) : that.lotteryId != null) return false;
        if (ticketId != null ? !ticketId.equals(that.ticketId) : that.ticketId != null) return false;
        if (ticket != null ? !ticket.equals(that.ticket) : that.ticket != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = lotteryId != null ? lotteryId.hashCode() : 0;
        result = 31 * result + (ticketId != null ? ticketId.hashCode() : 0);
        result = 31 * result + (ticket != null ? ticket.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "{" +
                "lotteryId: " + lotteryId +
                ", ticketId: " + ticketId +
                ", ticket: " + ticket +
                "}";
    }

}
