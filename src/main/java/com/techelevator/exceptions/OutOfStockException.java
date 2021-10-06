package com.techelevator.exceptions;

public class OutOfStockException extends VendingMachineException {

    public OutOfStockException() {
        super("Product is out of stock");
    }
}
