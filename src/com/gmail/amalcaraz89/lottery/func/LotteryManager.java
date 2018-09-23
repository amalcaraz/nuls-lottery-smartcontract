package com.gmail.amalcaraz89.lottery.func;

import com.gmail.amalcaraz89.lottery.event.TicketsRefundEvent;
import com.gmail.amalcaraz89.lottery.event.NewLotteryEvent;
import com.gmail.amalcaraz89.lottery.event.LotteryWinnerEvent;
import com.gmail.amalcaraz89.lottery.event.SupportTransferEvent;
import com.gmail.amalcaraz89.lottery.event.TicketsBoughtEvent;
import com.gmail.amalcaraz89.lottery.model.Lottery;
import com.gmail.amalcaraz89.lottery.model.LotteryResult;
import com.gmail.amalcaraz89.lottery.model.Ticket;
import io.nuls.contract.sdk.Address;
import io.nuls.contract.sdk.Block;
import io.nuls.contract.sdk.Msg;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.nuls.contract.sdk.Utils.emit;
import static io.nuls.contract.sdk.Utils.require;

public class LotteryManager implements LotteryManagerInterface {

    private static long rescueMinWait = 1000L * 60 * 60 * 24 * 30;
    private static int defaultMinParticipants = 5;
    private Address owner;
    private Map<Long, Lottery> lotteryList = new HashMap<Long, Lottery>();

    public LotteryManager(Address owner) {
        this.owner = owner;
    }

    @Override
    public Lottery createLottery(String title, String desc, BigInteger ticketPrice, long startTime, long endTime, BigInteger value,
                                 int minParticipants, boolean secondPrizes, Address supportAddress, int supportPercentage) {

        require(title != null, "title can not be empty");
        require(desc != null, "desc can not be empty");
        require(ticketPrice != null && ticketPrice.compareTo(BigInteger.ZERO) > 0, "ticketPrice can not be empty or 0");
        require(startTime <= endTime, "start time should be lower than end time");
        require(endTime >= Block.timestamp(), "end time cant't be lower than now");
        require(minParticipants >= defaultMinParticipants, "Minimum participants is 5");

        Long lotteryId = Long.valueOf(lotteryList.size() + 1);
        Lottery lottery = new Lottery();

        lottery.setId(lotteryId);
        lottery.setTitle(title);
        lottery.setDesc(desc);
        lottery.setTicketPrice(ticketPrice);
        lottery.setMinParticipants(minParticipants);
        lottery.setStartTime(startTime);
        lottery.setEndTime(endTime);
        lottery.setSecondPrizes(secondPrizes);
        lottery.setTicketList(new HashMap<Long, Ticket>());

        if (supportAddress != null) {

            require(supportPercentage > 0 && supportPercentage <= 50, "suppPercentage should be greater than 0 and less than 50");

            lottery.setSupportAddress(supportAddress);
            lottery.setSupportPercentage(supportPercentage);
        }

        if (value.compareTo(BigInteger.ZERO) >= 0) {
            lottery.setTotalPot(value);
        }

        this.lotteryList.put(lotteryId, lottery);
        emit(new NewLotteryEvent(lotteryId, lottery));

        return lottery;
    }

    @Override
    public BigInteger buyTickets(long lotteryId, Address sender, BigInteger value) {

        Lottery lottery = this.getLotteryById(lotteryId);

        this.checkStatus(lottery);

        require(lottery.getStatus() == LotteryStatus.OPEN, "This lottery is not open");

        BigInteger ticketPrice = lottery.getTicketPrice();

        require(value.compareTo(ticketPrice) >= 0, "Value sent is not enough to buy a ticket");

        BigInteger tickets = value.divide(ticketPrice);
        BigInteger spent = tickets.multiply(ticketPrice);
        BigInteger remainder = value.subtract(spent);

        require(spent.add(remainder).equals(value), "Error calculating bought tickets");

        if (spent.compareTo(BigInteger.ZERO) > 0) {
            lottery.setTotalPot(lottery.getTotalPot().add(spent));
            this.generateTickets(lottery, sender, tickets.intValue());
        }

        if (remainder.compareTo(BigInteger.ZERO) > 0) {
            sender.transfer(remainder);
        }

        return tickets;

    }

    @Override
    public boolean claimPrizes(long lotteryId) {

        Lottery lottery = this.getLotteryById(lotteryId);

        return this.checkStatus(lottery);
    }

    @Override
    public LotteryResult getLotteryResult(long lotteryId) {

        Lottery lottery = this.getLotteryById(lotteryId);

        require(lottery.getStatus() == LotteryStatus.CLOSE, "This lottery is still open");

        LotteryResult result = new LotteryResult();
        result.setId(lotteryId);

        Map<Long, Ticket> ticketMap = lottery.getTicketList();

        List<Ticket> tickets = new ArrayList<Ticket>();

        for (long i = 1; i <= ticketMap.size(); i++) {
            Ticket t = ticketMap.get(i);
            if (t.getPrize() > 0) {
                tickets.add(t);
            }
        }

        result.setWinnerTickets(tickets);

        return result;

    }

    @Override
    public Lottery getLotteryDetails(long lotteryId) {

        return this.getLotteryById(lotteryId);
    }

    @Override
    public List<Ticket> getTicketList(long lotteryId) {

        Lottery lottery = this.getLotteryById(lotteryId);

        Map<Long, Ticket> ticketMap = lottery.getTicketList();

        List<Ticket> tickets = new ArrayList<Ticket>();

        for (long i = 1; i <= ticketMap.size(); i++) {
            tickets.add(ticketMap.get(i));
        }

        return tickets;
    }

    @Override
    public List<Ticket> getTicketList(long lotteryId, final Address address) {

        Lottery lottery = this.getLotteryById(lotteryId);

        Map<Long, Ticket> ticketMap = lottery.getTicketList();

        List<Ticket> tickets = new ArrayList<Ticket>();

        for (long i = 1; i <= ticketMap.size(); i++) {
            Ticket t = ticketMap.get(i);
            if (address.equals(t.getOwner())) {
                tickets.add(t);
            }
        }

        return tickets;
    }

    @Override
    public Ticket getTicketDetails(long lotteryId, long ticketId) {

        Lottery lottery = this.getLotteryById(lotteryId);

        Map<Long, Ticket> ticketMap = lottery.getTicketList();

        Ticket ticket = ticketMap.get(ticketId);

        require(ticket != null, "Ticket doesn't exist");

        return ticket;
    }

    @Override
    public void rescueNuls(long lotteryId, Address receiver) {

        require(receiver.equals(this.owner), "Only owner can rescue the balance");

        Lottery lottery = this.getLotteryById(lotteryId);

        require(lottery.getStatus() == LotteryStatus.CLOSE, "This lottery is not close");
        require(Block.timestamp() >= (lottery.getEndTime() + rescueMinWait), "Can't rescue balance till: " + (lottery.getEndTime() + rescueMinWait));

        BigInteger pot = lottery.getTotalPot();
        require(pot.compareTo(BigInteger.ZERO) > 0 && pot.compareTo(Msg.address().balance()) <= 0, "Not enough balance to rescue");

        receiver.transfer(pot);

    }

    private Lottery getLotteryById(long lotteryId) {

        require(lotteryId > 0L, "Lottery id should be greater than 0");

        Lottery lottery = this.lotteryList.get(lotteryId);
        require(lottery != null, "Lottery not found by id");

        return lottery;
    }


    private void generateTickets(Lottery lottery, Address address, int  numTickets) {

        Map<Long, Ticket> ticketMap = lottery.getTicketList();
        List<Ticket> tickets = new ArrayList<Ticket>();

        int next = ticketMap.size() + 1;

        for (long i = next; i < (next + numTickets); i++) {
            Ticket ticket = new Ticket(i, address);
            ticketMap.put(i, ticket);
            tickets.add(ticket);
        }

        emit(new TicketsBoughtEvent(lottery.getId(), tickets));

    }

    private boolean checkStatus(Lottery lottery) {


        if (lottery.getStartTime() > Block.timestamp()) {

            return false;

        } else if (lottery.getStatus() == LotteryStatus.WAITING) {

            lottery.setStatus(LotteryStatus.OPEN);

        }

        if (lottery.getEndTime() < Block.timestamp()) {

            if (lottery.getStatus() != LotteryStatus.CLOSE) {

                this.resolveWinners(lottery);
                lottery.setStatus(LotteryStatus.CLOSE);

            }

            return false;

        }

        return true;

    }

    //
    // Prizes are calculated from: (totalPot - support%)
    // First prize: 65%
    // Second prize: 25%
    // Third prize: 10%
    //
    private void resolveWinners(Lottery lottery) {

        require(lottery.getStatus() == LotteryStatus.OPEN, "This lottery is not open");

        Map<Long, Ticket> ticketMap = lottery.getTicketList();

        long numTickets = ticketMap.size();

        if (numTickets > 0) {

            if (numTickets >= lottery.getMinParticipants()) {

                this.payoutWinners(lottery, ticketMap);

            } else {

                this.refundTickets(lottery, ticketMap);

            }

        }

    }

    private void payoutWinners(Lottery lottery, Map<Long, Ticket> ticketMap) {

        long numTickets = ticketMap.size();

        long seed = (numTickets << 32) + numTickets + 3;
        long winnerIndex = (long) (CustomMath.random(seed) * numTickets) + 1;
        Ticket ticket = ticketMap.get(winnerIndex);

        BigInteger totalPot = lottery.getTotalPot();
        Address supportAddress = lottery.getSupportAddress();

        if (supportAddress != null) {

            BigInteger supportAmount = BigInteger.valueOf(lottery.getSupportPercentage()).multiply(totalPot).divide(BigInteger.valueOf(100));
            totalPot = totalPot.subtract(supportAmount);

            supportAddress.transfer(supportAmount);
            emit(new SupportTransferEvent(lottery.getId(), supportAddress, supportAmount));

        }

        if (lottery.isSecondPrizes()) {

            long secondWinnerIndex = ((winnerIndex ^ ((1L << 48) - 1)) % numTickets) + 1;
            if (secondWinnerIndex == winnerIndex) {
                secondWinnerIndex = ((winnerIndex + 1) % numTickets) + 1;
            }

            BigInteger secondPrizeAmount = BigInteger.valueOf(25).multiply(totalPot).divide(BigInteger.valueOf(100));
            totalPot = totalPot.subtract(secondPrizeAmount);

            Ticket secondPrizeTicket = ticketMap.get(secondWinnerIndex);
            this.setWinnerTicket(lottery, secondPrizeTicket, 2, secondPrizeAmount);


            long thirdWinnerIndex = ((secondWinnerIndex << 24) % numTickets) + 1;
            while (thirdWinnerIndex == winnerIndex || thirdWinnerIndex == secondWinnerIndex) {
                thirdWinnerIndex = (++thirdWinnerIndex % numTickets) + 1;
            }

            BigInteger thirdPrizeAmount = BigInteger.valueOf(10).multiply(totalPot).divide(BigInteger.valueOf(100));
            totalPot = totalPot.subtract(thirdPrizeAmount);

            Ticket thirdPrizeTicket = ticketMap.get(thirdWinnerIndex);
            this.setWinnerTicket(lottery, thirdPrizeTicket, 3, thirdPrizeAmount);

        }

        this.setWinnerTicket(lottery, ticket, 1, totalPot);

    }

    private void setWinnerTicket(Lottery lottery, Ticket ticket, int prize, BigInteger amount) {

        ticket.getOwner().transfer(amount);
        lottery.setTotalPot(lottery.getTotalPot().subtract(amount));
        ticket.setPrize(prize);
        ticket.setPrizeAmount(amount);

        emit(new LotteryWinnerEvent(lottery.getId(), ticket.getId(), ticket));

    }

    private void refundTickets(Lottery lottery, Map<Long, Ticket> ticketMap) {

        long numTickets = ticketMap.size();

        // Not Iterator nor Set supported (two data structures needed)
        Map<Address, List<Ticket>> ownersTickets = new HashMap<Address, List<Ticket>>();
        List<Address> owners = new ArrayList<Address>();

        for (int i = 1; i <= numTickets; i++) {

            Ticket ticket = ticketMap.get(i);
            Address owner = ticket.getOwner();
            List<Ticket> ownerTickets = ownersTickets.get(owner);

            if (ownerTickets == null) {
                ownerTickets = new ArrayList<Ticket>();
                ownersTickets.put(owner, ownerTickets);
            }

            ownerTickets.add(ticket);

            if (!owners.contains(owner)) {
                owners.add(owner);
            }
        }

        for (int i = 0; i < owners.size(); i++) {
            Address owner = owners.get(i);
            List<Ticket> tickets = ownersTickets.get(owner);
            BigInteger amount = lottery.getTicketPrice().multiply(BigInteger.valueOf(tickets.size()));
            owner.transfer(amount);
            lottery.setTotalPot(lottery.getTotalPot().subtract(amount));

            emit(new TicketsRefundEvent(lottery.getId(), amount, tickets));

        }

    }

}
