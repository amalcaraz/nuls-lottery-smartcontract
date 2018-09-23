package com.gmail.amalcaraz89.lottery.event;

import com.gmail.amalcaraz89.lottery.model.Lottery;
import io.nuls.contract.sdk.Event;

public class NewLotteryEvent implements Event {

    private Long lotteryId;
    private Lottery lottery;

    public NewLotteryEvent(Long lotteryId, Lottery lottery) {
        this.lotteryId = lotteryId;
        this.lottery = lottery;
    }

    public Long getLotteryId() {
        return lotteryId;
    }

    public Lottery getLottery() {
        return lottery;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NewLotteryEvent that = (NewLotteryEvent) o;

        if (lotteryId != null ? !lotteryId.equals(that.lotteryId) : that.lotteryId != null) return false;
        if (lottery != null ? !lottery.equals(that.lottery) : that.lottery != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = lotteryId != null ? lotteryId.hashCode() : 0;
        result = 31 * result + (lottery != null ? lottery.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "{" +
                "lotteryId: " + lotteryId +
                ", lottery: " + lottery +
                "}";
    }
}
