package com.techelevator.inventory;

public class Chip extends Product {

    public Chip(String name, int price) {
        super(name, price);
    }

    public Chip(String name, double price) {
        super(name, price);
    }

    @Override
    public String purchaseNoise() {
        return "Crunch Crunch, Yum!";
    }
}
