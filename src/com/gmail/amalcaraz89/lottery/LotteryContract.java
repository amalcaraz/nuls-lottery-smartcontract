package com.gmail.amalcaraz89.lottery;

import com.gmail.amalcaraz89.lottery.func.LotteryManager;
import com.gmail.amalcaraz89.lottery.func.LotteryManagerInterface;
import com.gmail.amalcaraz89.lottery.model.Lottery;
import com.gmail.amalcaraz89.lottery.model.LotteryResult;
import com.gmail.amalcaraz89.lottery.model.LotterySummary;
import com.gmail.amalcaraz89.lottery.model.Ticket;
import io.nuls.contract.sdk.Address;
import io.nuls.contract.sdk.Contract;
import io.nuls.contract.sdk.Msg;
import io.nuls.contract.sdk.annotation.Payable;
import io.nuls.contract.sdk.annotation.View;
import io.nuls.contract.sdk.annotation.Required;

import java.math.BigInteger;
import java.util.List;

public class LotteryContract implements Contract {

    private final String name = "Smart Lottery v1.0.0";
    private LotteryManagerInterface lotteryManager;

    public LotteryContract() { this.lotteryManager = new LotteryManager(Msg.sender()); }

    @Payable
    public Lottery createLottery(@Required String title, @Required String desc, @Required double ticketPrice,
                                 @Required long startTime, @Required long endTime, @Required int minParticipants, @Required boolean secondPrizes) {

      return this.createLotteryWithSupportAddress(title, desc, ticketPrice, startTime, endTime, minParticipants, secondPrizes, null, 0);

    }

    @Payable
    public Lottery createLotteryWithSupportAddress(@Required String title, @Required String desc, @Required double ticketPrice,
                                                   @Required long startTime, @Required long endTime, @Required int minParticipants,
                                                   @Required boolean secondPrizes, @Required Address supportAddress, @Required int supportPercentage) {

        // TODO: Change it in the UI before calling the SC (change ticketPrice arg from double to BigInteger)
        BigInteger ticketPriceInt = BigInteger.valueOf((long) (ticketPrice * 100000000));
        BigInteger value = Msg.value();

        return this.lotteryManager.createLottery(title, desc, ticketPriceInt, startTime, endTime, value, minParticipants, secondPrizes, Msg.sender(), supportAddress, supportPercentage);

    }

    @Payable
    public BigInteger buyTickets(@Required long lotteryId) {

        Address sender = Msg.sender();
        BigInteger value = Msg.value();

        return this.lotteryManager.buyTickets(lotteryId, sender, value);

    }

    @Payable
    public void claimPrizes(@Required long lotteryId) {

        this.lotteryManager.claimPrizes(lotteryId);

    }

    @View
    public List<LotterySummary> viewLotteryList() {

        return this.lotteryManager.getLotteryList();

    }

    @View
    public LotteryResult viewLotteryResult(@Required long lotteryId) {

        return this.lotteryManager.getLotteryResult(lotteryId);

    }

    @View
    public Lottery viewLotteryDetails(@Required long lotteryId) {

        return this.lotteryManager.getLotteryDetails(lotteryId);

    }

    @View
    public List<Ticket> viewTicketList(@Required long lotteryId) {

        return this.lotteryManager.getTicketList(lotteryId);

    }

    @View
    public List<Ticket> viewTicketListByOwner(@Required long lotteryId, @Required Address owner) {

        return this.lotteryManager.getTicketList(lotteryId, owner);

    }

    @View
    public Ticket viewTicketDetail(@Required long lotteryId, @Required long ticketId) {

        return this.lotteryManager.getTicketDetails(lotteryId, ticketId);

    }

    @View
    public String name() {
        return name;
    }

    @Payable
    public void rescueNuls(@Required long lotteryId) {

        Address sender = Msg.sender();

        this.lotteryManager.rescueNuls(lotteryId, sender);

    }

}