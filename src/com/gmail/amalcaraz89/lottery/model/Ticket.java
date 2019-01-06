package com.gmail.amalcaraz89.lottery.model;

import io.nuls.contract.sdk.Address;

import java.math.BigInteger;

public class Ticket {

    private Long id;
    private Address owner;
    private int prize = 0;
    private BigInteger prizeAmount = BigInteger.ZERO;

    public Ticket(Long id, Address owner) {
        this.id = id;
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Address getOwner() {
        return owner;
    }

    public void setOwner(Address owner) {
        this.owner = owner;
    }

    public int getPrize() {
        return prize;
    }

    public void setPrize(int prize) {
        this.prize = prize;
    }

    public BigInteger getPrizeAmount() {
        return prizeAmount;
    }

    public void setPrizeAmount(BigInteger prizeAmount) {
        this.prizeAmount = prizeAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ticket that = (Ticket) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (owner != null ? !owner.equals(that.owner) : that.owner != null) return false;
        if (prize != that.prize) return false;
        if (prizeAmount != null ? !prizeAmount.equals(that.prizeAmount) : that.prizeAmount != null) return false;

        return true;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        result = 31 * result + prize;
        result = 31 * result + (prizeAmount != null ? prizeAmount.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\": " + id +
                ", \"owner\": " + (owner != null ? ("\"" + owner + "\"") : "\"\"") +
                ", \"prize\": " + prize +
                ", \"prizeAmount\": " + prizeAmount +
                "}";
    }

}
