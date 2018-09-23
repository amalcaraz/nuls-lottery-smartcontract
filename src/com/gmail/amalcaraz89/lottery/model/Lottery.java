package com.gmail.amalcaraz89.lottery.model;

import java.util.Map;

public class Lottery extends LotteryResume {

    private Map<Long, Ticket> ticketMap;

    public Map<Long, Ticket> getTicketMap() { return ticketMap; }

    public void setTicketMap(Map<Long, Ticket> ticketMap) { this.ticketMap = ticketMap; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lottery that = (Lottery) o;

        if (!super.equals(that)) return false;
        if (ticketMap != null ? ticketMap.equals(that.ticketMap) : that.ticketMap == null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (ticketMap != null ? ticketMap.hashCode() : 0);

        return result;
    }

    @Override
    public String toString() {
        return "{" +
                "id: " + id +
                ", title: \"" + title + "\"" +
                ", desc: \"" + desc + "\"" +
                ", status: " + status +
                ", minParticipants: " + minParticipants +
                ", startTime: " + startTime  +
                ", endTime: " + endTime  +
                ", totalPot: " + totalPot +
                ", ticketPrice: " + ticketPrice +
                ", secondPrizes: " + secondPrizes +
                ", supportAddress: \"" + supportAddress + "\"" +
                ", supportPercentage: " + supportPercentage +
                ", ticketMap: " + ticketMap.toString() +
                '}';
    }
}
