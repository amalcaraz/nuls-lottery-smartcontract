package com.gmail.amalcaraz89.lottery.event;

import com.gmail.amalcaraz89.lottery.model.Ticket;
import io.nuls.contract.sdk.Event;

import java.math.BigInteger;
import java.util.List;

public class TicketsRefundEvent implements Event {

    private Long lotteryId;
    private BigInteger amount;
    private List<Ticket> tickets;

    public TicketsRefundEvent(Long lotteryId, BigInteger amount, List<Ticket> tickets) {
        this.lotteryId = lotteryId;
        this.amount = amount;
        this.tickets = tickets;
    }

    public Long getLotteryId() {
        return lotteryId;
    }

    public BigInteger getAmount() {
        return amount;
    }

    public List<Ticket> tickets() {
        return tickets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TicketsRefundEvent that = (TicketsRefundEvent) o;

        if (lotteryId != null ? !lotteryId.equals(that.lotteryId) : that.lotteryId != null) return false;
        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        if (tickets != null ? !tickets.equals(that.tickets) : that.tickets != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = lotteryId != null ? lotteryId.hashCode() : 0;
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (tickets != null ? tickets.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "{" +
                "lotteryId: " + lotteryId +
                ", amount: " + amount +
                ", tickets: " + tickets +
                "}";
    }
}
