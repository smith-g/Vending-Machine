package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

public class ChangeTest {

    @Test
    public void changeTest1() {
        Change change = new Change(123);
        int expectedQuarters = 4;
        int expectedDimes = 2;
        int expectedNickels = 0;
        int expectedCents = 3;
        Assert.assertEquals("Incorrect number of quarters", expectedQuarters, change.getQuarters());
        Assert.assertEquals("Incorrect number of dimes", expectedDimes, change.getDimes());
        Assert.assertEquals("Incorrect number of nickels", expectedNickels, change.getNickels());
        Assert.assertEquals("Incorrect number of cents", expectedCents, change.getCents());
    }

    @Test
    public void changeTest2() {
        Change change = new Change(20);
        int expectedQuarters = 0;
        int expectedDimes = 2;
        int expectedNickels = 0;
        int expectedCents = 0;
        Assert.assertEquals("Incorrect number of quarters", expectedQuarters, change.getQuarters());
        Assert.assertEquals("Incorrect number of dimes", expectedDimes, change.getDimes());
        Assert.assertEquals("Incorrect number of nickels", expectedNickels, change.getNickels());
        Assert.assertEquals("Incorrect number of cents", expectedCents, change.getCents());
    }

    @Test
    public void changeTest3() {
        Change change = new Change(16);
        int expectedQuarters = 0;
        int expectedDimes = 1;
        int expectedNickels = 1;
        int expectedCents = 1;
        Assert.assertEquals("Incorrect number of quarters", expectedQuarters, change.getQuarters());
        Assert.assertEquals("Incorrect number of dimes", expectedDimes, change.getDimes());
        Assert.assertEquals("Incorrect number of nickels", expectedNickels, change.getNickels());
        Assert.assertEquals("Incorrect number of cents", expectedCents, change.getCents());
    }
}
