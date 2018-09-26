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

        if (status != that.status) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (desc != null ? !desc.equals(that.desc) : that.desc != null) return false;
        if (minParticipants != that.minParticipants) return false;
        if (startTime != that.startTime) return false;
        if (endTime != that.endTime) return false;
        if (initialPot != null ? initialPot.equals(that.initialPot) : that.initialPot == null) return false;
        if (currentPot != null ? currentPot.equals(that.currentPot) : that.currentPot == null) return false;
        if (totalPot != null ? totalPot.equals(that.totalPot) : that.totalPot == null) return false;
        if (ticketPrice != null ? ticketPrice.equals(that.ticketPrice) : that.ticketPrice == null) return false;
        if (secondPrizes != that.secondPrizes) return false;
        if (creatorAddress != null ? creatorAddress.equals(that.creatorAddress) : that.creatorAddress == null) return false;
        if (supportAddress != null ? supportAddress.equals(that.supportAddress) : that.supportAddress == null) return false;
        if (supportPercentage != that.supportPercentage) return false;
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
                ", endTime: " + endTime +
                ", initialPot: " + initialPot +
                ", currentPot: " + currentPot +
                ", totalPot: " + totalPot +
                ", ticketPrice: " + ticketPrice +
                ", secondPrizes: " + secondPrizes +
                ", creatorAddress: \"" + creatorAddress + "\"" +
                ", supportAddress: \"" + supportAddress + "\"" +
                ", supportPercentage: " + supportPercentage +
                ", ticketMap: " + ticketMap.toString() +
                '}';
    }
}
