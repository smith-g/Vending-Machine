package com.techelevator.inventory;

public class Gum extends Product{

    public Gum(String name, int price) {
        super(name, price);
    }

    @Override
    public String purchaseNoise() {
        return "Chew Chew, Yum!";
    }
}
