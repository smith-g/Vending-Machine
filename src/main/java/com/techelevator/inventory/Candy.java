package com.techelevator.inventory;

public class Candy extends Product {

    public Candy(String name, int price) {
        super(name, price);
    }

    public Candy(String name, double price) {
        super(name, price);
    }

    @Override
    public String purchaseNoise() {
        return "Munch Munch, Yum!";
    }

    @Override
    public String toString() {
        return "Candy" + super.toString();
    }
}
