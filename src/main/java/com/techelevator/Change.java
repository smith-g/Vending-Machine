package com.techelevator;

public class Change {
    private int dimes;
    private int nickels;
    private int quarters;
    private int totalChangeInCents;

    public Change(int totalChangeInCents) {
        this.totalChangeInCents = totalChangeInCents;
    }



    @Override
    public String toString() {
        return "Change{" +
                "dimes=" + dimes +
                ", nickels=" + nickels +
                ", quarters=" + quarters +
                '}';
    }
}
