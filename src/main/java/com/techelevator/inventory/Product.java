package com.techelevator.inventory;

public abstract class Product {
    final static int INITIAL_STOCK_AMOUNT = 5;
    private String name;
    private int priceInCents;
    private int quantity;

    public Product(String name, int price) {
        this.name = name;
        priceInCents = price;
        quantity = INITIAL_STOCK_AMOUNT;
    }

    public Product(String name, double price) {
        this(name, (int)(price * 100));
    }

    public abstract String purchaseNoise();

    public int getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public int getPriceInCents() {
        return priceInCents;
    }

    public void purchase() {
        quantity--;
//        quantity -= 1;
//        quantity = quantity - 1;
    }

    @Override
    public String toString() {
        return String.format("%15s \tprice: $%.2f \t quantity: %d", name, priceInCents/100.0, quantity);
    }
}
