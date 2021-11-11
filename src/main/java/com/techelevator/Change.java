package com.techelevator;

public class Change {

    private int QUARTERS = 25;
    private int NICKELS  = 5;
    private int DIMES    = 10;
    private int dimes;
    private int nickels;
    private int quarters;
    private int cents;
    private int totalChangeInCents;

    public Change(int totalChangeInCents) {
        this.totalChangeInCents = totalChangeInCents;
        calculateChange();
    }

    public int getDimes() {
        return dimes;
    }

    public int getNickels() {
        return nickels;
    }

    public int getQuarters() {
        return quarters;
    }

    public int getCents() {
        return cents;
    }

    private void calculateChange() {
        this.quarters = this.totalChangeInCents / QUARTERS;
        this.totalChangeInCents %= QUARTERS;
        this.dimes = this.totalChangeInCents / DIMES;
        this.totalChangeInCents %= DIMES;
        this.nickels = this.totalChangeInCents / NICKELS;
        this.cents = this.totalChangeInCents % NICKELS;
    }

    @Override
    public String toString() {
        return "Change{" +
                "quarters=" + quarters +
                ", dimes=" + dimes +
                ", nickels=" + nickels +
                ", cents=" + cents +
                '}';
    }
}
