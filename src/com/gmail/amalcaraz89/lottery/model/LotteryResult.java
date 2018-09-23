package com.gmail.amalcaraz89.lottery.model;

import java.util.List;

public class LotteryResult {

    private Long id;
    private List<Ticket> winnerTickets;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Ticket> getWinnerTickets() {
        return winnerTickets;
    }

    public void setWinnerTickets(List<Ticket> winnerTickets) { this.winnerTickets = winnerTickets; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LotteryResult that = (LotteryResult) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (winnerTickets != null ? winnerTickets.equals(that.winnerTickets) : that.winnerTickets == null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (winnerTickets != null ? winnerTickets.hashCode() : 0);

        return result;
    }

    @Override
    public String toString() {
        return "{" +
                "id: " + id +
                ", winnerTickets: " + winnerTickets.toString() +
                '}';
    }
}
