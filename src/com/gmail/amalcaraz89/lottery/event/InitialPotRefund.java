package com.gmail.amalcaraz89.lottery.event;

import io.nuls.contract.sdk.Event;
import java.math.BigInteger;

public class InitialPotRefund implements Event {

    protected Long lotteryId;
    protected BigInteger amount;

    public InitialPotRefund(Long lotteryId, BigInteger amount) {
        this.lotteryId = lotteryId;
        this.amount = amount;
    }

    public Long getLotteryId() {
        return lotteryId;
    }

    public BigInteger getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InitialPotRefund that = (InitialPotRefund) o;

        if (lotteryId != null ? !lotteryId.equals(that.lotteryId) : that.lotteryId != null) return false;
        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = lotteryId != null ? lotteryId.hashCode() : 0;
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "{" +
                "lotteryId: " + lotteryId +
                ", amount: " + amount +
                "}";
    }
}
