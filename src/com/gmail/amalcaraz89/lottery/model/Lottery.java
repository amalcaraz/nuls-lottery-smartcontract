package com.gmail.amalcaraz89.lottery.model;

import com.gmail.amalcaraz89.lottery.func.LotteryStatus;
import io.nuls.contract.sdk.Address;

import java.math.BigInteger;
import java.util.Map;

public class Lottery {

    private Long id;
    private String title;
    private String desc;
    private int status = LotteryStatus.WAITING;
    private int minParticipants;
    private long startTime;
    private long endTime;
    private BigInteger totalPot = BigInteger.ZERO;
    private BigInteger ticketPrice;
    private boolean secondPrizes;
    private Address supportAddress;
    private int supportPercentage;
    private Map<Long, Ticket> ticketList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) { this.desc = desc; }

    public int getStatus() { return status; }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getMinParticipants() { return minParticipants; }

    public void setMinParticipants(int minParticipants) { this.minParticipants = minParticipants; }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) { this.endTime = endTime; }

    public BigInteger getTotalPot() {
        return totalPot;
    }

    public void setTotalPot(BigInteger totalPot) {
        this.totalPot = totalPot;
    }

    public BigInteger getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(BigInteger ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public boolean isSecondPrizes() { return secondPrizes; }

    public void setSecondPrizes(boolean secondPrizes) { this.secondPrizes = secondPrizes; }

    public Address getSupportAddress() { return supportAddress; }

    public void setSupportAddress(Address supportAddress) { this.supportAddress = supportAddress; }

    public int getSupportPercentage() { return supportPercentage; }

    public void setSupportPercentage(int supportPercentage) { this.supportPercentage = supportPercentage; }

    public Map<Long, Ticket> getTicketList() { return ticketList; }

    public void setTicketList(Map<Long, Ticket> ticketList) { this.ticketList = ticketList; }

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
        if (totalPot != null ? totalPot.equals(that.totalPot) : that.totalPot == null) return false;
        if (ticketPrice != null ? ticketPrice.equals(that.ticketPrice) : that.ticketPrice == null) return false;
        if (secondPrizes != that.secondPrizes) return false;
        if (supportAddress != null ? supportAddress.equals(that.supportAddress) : that.supportAddress == null) return false;
        if (supportPercentage != that.supportPercentage) return false;
        if (ticketList != null ? ticketList.equals(that.ticketList) : that.ticketList == null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (desc != null ? desc.hashCode() : 0);
        result = 31 * result + status;
        result = 31 * result + minParticipants;
        result = 31 * result + (int) (startTime ^ (startTime >>> 32));
        result = 31 * result + (int) (endTime ^ (endTime >>> 32));
        result = 31 * result + (totalPot != null ? totalPot.hashCode() : 0);
        result = 31 * result + (ticketPrice != null ? ticketPrice.hashCode() : 0);
        result = 31 * result + (secondPrizes ? 1 : 0);
        result = 31 * result + (supportAddress != null ? supportAddress.hashCode() : 0);
        result = 31 * result + supportPercentage;
        result = 31 * result + (ticketList != null ? ticketList.hashCode() : 0);

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
                ", ticketList: " + ticketList.toString() +
                '}';
    }
}
