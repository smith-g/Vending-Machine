package com.techelevator;

import com.techelevator.exceptions.InsufficientFundsException;
import com.techelevator.exceptions.InvalidSlotException;
import com.techelevator.exceptions.OutOfStockException;
import com.techelevator.exceptions.VendingMachineException;
import com.techelevator.inventory.Product;

import java.io.FileNotFoundException;
import java.util.Map;

public class VendingMachine {
    private final int BILL_1  = 1;
    private final int BILL_2  = 2;
    private final int BILL_5  = 5;
    private final int BILL_10 = 10;

    private int currentBalanceInCents;
    private Map<String, Product> inventory;
    private InventoryProvider inventoryProvider;
    private TransactionLogger transactionLogger;

    public VendingMachine () throws FileNotFoundException {
        inventoryProvider = new InventoryProvider();
        inventory = inventoryProvider.loadInventory();
    }

    public String displayInventory() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String slot: inventory.keySet()) {
            stringBuilder.append(slot);
            stringBuilder.append(": ");
            stringBuilder.append(inventory.get(slot).toString());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public void feedMoney(int dollars) {
        if (dollars == BILL_1 || dollars == BILL_2 || dollars == BILL_5 || dollars == BILL_10) {
            this.currentBalanceInCents += dollars * 100;
        }
    }

    public int getCurrentBalanceInCents() {
        return currentBalanceInCents;
    }

    public Product getProductAtSlot(String slot) throws InvalidSlotException {
        if (inventory.containsKey(slot)) {
            return inventory.get(slot);
        } else {
            throw new InvalidSlotException();
        }
    }

    public int getQuantityRemaining(String slot) throws InvalidSlotException {
        return getProductAtSlot(slot).getQuantity();
    }

    public Product purchase(String slot) throws VendingMachineException {
        Product product = getProductAtSlot(slot);
        if (!isEnoughBalance(product)) {
            throw new InsufficientFundsException();
        }
        if (getQuantityRemaining(slot) > 0) {
            this.currentBalanceInCents -= product.getPriceInCents();
            product.purchase();
        } else {
            throw new OutOfStockException();
        }
        return product;
    }

    private boolean isEnoughBalance(Product product) {
        return currentBalanceInCents > product.getPriceInCents();
    }

    public Change returnChange() {
        int change = this.currentBalanceInCents;
        this.currentBalanceInCents = 0;
        return new Change(change);
    }
}
