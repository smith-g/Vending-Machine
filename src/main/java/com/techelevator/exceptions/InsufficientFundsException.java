package com.techelevator.exceptions;

public class InsufficientFundsException extends VendingMachineException {

    public InsufficientFundsException() {
        super("Not enough funds for this transaction");
    }
}
