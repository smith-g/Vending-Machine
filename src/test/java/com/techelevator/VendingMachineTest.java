package com.techelevator;

import com.techelevator.exceptions.InsufficientFundsException;
import com.techelevator.exceptions.InvalidSlotException;
import com.techelevator.exceptions.OutOfStockException;
import com.techelevator.exceptions.VendingMachineException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileNotFoundException;

import static com.techelevator.InventoryConstants.INVENTORY_FILE;

public class VendingMachineTest {
    private static VendingMachine vendingMachine;

    @BeforeClass
    public static void setup() throws FileNotFoundException {
        System.setProperty(INVENTORY_FILE, "src/test/java/com/techelevator/vendingmachine.csv");
        vendingMachine = new VendingMachine();
    }

    @Test
    public void displayInventory() {
        System.out.println(vendingMachine.displayInventory());
    }

    @Test
    public void feedMoneyTest_rightAmounts() {
        vendingMachine.feedMoney(5);
        vendingMachine.feedMoney(2);
        int expectedMoneyInCents = 700;
        Assert.assertEquals("Wrong amount of money", expectedMoneyInCents, vendingMachine.getCurrentBalanceInCents());

        vendingMachine.returnChange();
        expectedMoneyInCents = 0;
        Assert.assertEquals("Wrong amount of money", expectedMoneyInCents, vendingMachine.getCurrentBalanceInCents());
    }

    @Test
    public void feedMoneyTest_wrongAmounts() {
        vendingMachine.feedMoney(3);
        vendingMachine.feedMoney(2);
        int expectedMoneyInCents = 200;
        Assert.assertEquals("Wrong amount of money", expectedMoneyInCents, vendingMachine.getCurrentBalanceInCents());

        vendingMachine.returnChange();
        expectedMoneyInCents = 0;
        Assert.assertEquals("Wrong amount of money", expectedMoneyInCents, vendingMachine.getCurrentBalanceInCents());
    }

    @Test(expected = OutOfStockException.class)
    public void runOutOfStockTest() throws VendingMachineException {
        vendingMachine.feedMoney(5);
        vendingMachine.feedMoney(5);
        vendingMachine.purchase("D1");
        vendingMachine.purchase("D1");
        vendingMachine.purchase("D1");
        vendingMachine.purchase("D1");
        vendingMachine.purchase("D1");
        vendingMachine.purchase("D1");
    }

    @Test(expected = InsufficientFundsException.class)
    public void purchaseWithoutEnoughFunds() throws VendingMachineException {
        vendingMachine.purchase("D1");
    }

    @Test(expected = InvalidSlotException.class)
    public void incorrectSlotId() throws VendingMachineException {
        vendingMachine.purchase("Z1");
    }

    @Test
    public void successfulPurchase() throws VendingMachineException {
        vendingMachine.feedMoney(5);
        vendingMachine.feedMoney(2);
        vendingMachine.purchase("A1");
        vendingMachine.purchase("B1");
        int expectedBalance = 500 + 200 - 305 - 180;
        Assert.assertEquals("Incorrect balance amount", expectedBalance, vendingMachine.getCurrentBalanceInCents());

        int expectedQuarters = 8;
        int expectedcNickels = 1;
        int expectedDimes = 1;
        int expectedCents = 0;

        Change change = vendingMachine.returnChange();
        Assert.assertEquals("Wrong change", expectedQuarters, change.getQuarters());
        Assert.assertEquals("Wrong change", expectedcNickels, change.getNickels());
        Assert.assertEquals("Wrong change", expectedDimes, change.getDimes());
        Assert.assertEquals("Wrong change", expectedCents, change.getCents());

        expectedBalance = 0;
        Assert.assertEquals("Vending machine kept some money", expectedBalance, vendingMachine.getCurrentBalanceInCents());
    }
}
