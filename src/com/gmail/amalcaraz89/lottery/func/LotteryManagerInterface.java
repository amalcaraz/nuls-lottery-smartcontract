package com.gmail.amalcaraz89.lottery.func;

import com.gmail.amalcaraz89.lottery.model.Lottery;
import com.gmail.amalcaraz89.lottery.model.LotteryResult;
import com.gmail.amalcaraz89.lottery.model.LotterySummary;
import com.gmail.amalcaraz89.lottery.model.Ticket;
import io.nuls.contract.sdk.Address;

import java.math.BigInteger;
import java.util.List;

public interface LotteryManagerInterface {

    Lottery createLottery(String title, String desc, BigInteger ticketPrice, long startTime, long endTime, BigInteger value,
                   int minParticipants, boolean secondPrizes, Address creatorAddress, Address supportAddress, int supportPercentage);

    BigInteger buyTickets(long lotteryId, Address address, BigInteger deposit);

    void claimPrizes(long lotteryId);

    void rescueNuls(long lotteryId, Address receiver);

    List<LotterySummary> getLotteryList();

    LotteryResult getLotteryResult(long lotteryId);

    Lottery getLotteryDetails(long lotteryId);

    List<Ticket> getTicketList(long lotteryId);

    List<Ticket> getTicketList(long lotteryId, Address owner);

    Ticket getTicketDetails(long lotteryId, long ticketId);

}
