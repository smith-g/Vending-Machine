package com.techelevator.exceptions;

public class InvalidSlotException extends VendingMachineException {
    public InvalidSlotException() {
        super("An invalid slot was provided");
    }
}
