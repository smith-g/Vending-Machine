package com.techelevator.inventory;

public class Drink extends Product {

    public Drink(String name, int price) {
        super(name, price);
    }

    public Drink(String name, double price) {
        super(name, price);
    }

    @Override
    public String purchaseNoise() {
        return "Glug Glug, Yum!";
    }
}

