package com.pluralsight.tacostore.enums;

public enum DrinkSize {

    SMALL("Small", 2.00),
    MEDIUM("Medium", 2.50),
    LARGE("Large", 3.00);

    private final String itemName;
    private final double price;

    DrinkSize(String itemName, double price) {
        this.itemName = itemName;
        this.price = price;
    }

    public double getPrice(){
        return price;
    }


    public String getItemName() {
        return itemName;
    }

    @Override
    public String toString() {
        return itemName + " ($" + String.format("%.2f", price) + ")";
    }
}

