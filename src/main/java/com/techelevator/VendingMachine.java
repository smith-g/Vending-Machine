package com.techelevator;

import com.techelevator.exceptions.InsufficientFundsException;
import com.techelevator.exceptions.InvalidSlotException;
import com.techelevator.exceptions.OutOfStockException;
import com.techelevator.exceptions.VendingMachineException;
import com.techelevator.inventory.Product;

import java.util.Map;

public class VendingMachine {
    private int currentBalanceInCents;
    private Map<String, Product> inventory;
    private InventoryProvider inventoryProvider;
    private TransactionLogger transactionLogger;


    public void feedMoney(int dollars) {
        this.currentBalanceInCents += dollars * 100;
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
        return new Change(this.currentBalanceInCents);
    }
}
