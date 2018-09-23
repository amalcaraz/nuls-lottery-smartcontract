package com.gmail.amalcaraz89.lottery.event;

import io.nuls.contract.sdk.Address;
import io.nuls.contract.sdk.Event;

import java.math.BigInteger;

public class SupportTransferEvent implements Event {

    private Long lotteryId;
    private Address supportAddress;
    private BigInteger amount;

    public SupportTransferEvent(Long lotteryId, Address supportAddress, BigInteger amount) {
        this.lotteryId = lotteryId;
        this.supportAddress = supportAddress;
        this.amount = amount;
    }

    public Long getLotteryId() {
        return lotteryId;
    }

    public Address getSupportAddress() {
        return supportAddress;
    }

    public BigInteger getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SupportTransferEvent that = (SupportTransferEvent) o;

        if (lotteryId != null ? !lotteryId.equals(that.lotteryId) : that.lotteryId != null) return false;
        if (supportAddress != null ? !supportAddress.equals(that.supportAddress) : that.supportAddress != null) return false;
        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = lotteryId != null ? lotteryId.hashCode() : 0;
        result = 31 * result + (supportAddress != null ? supportAddress.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "{" +
                "lotteryId: " + lotteryId +
                ", supportAddress: " + supportAddress +
                ", amount: " + amount +
                "}";
    }
}
