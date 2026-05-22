package com.pluralsight.tacostore.enums;

public enum TacoSize {

    SINGLE("Single Taco", 3.50),
    THREE_TACO("3-Taco Plate", 9.00),
    BURRITO("Burrito", 8.50);

    public final String itemName;
    public final double basePrice;

    TacoSize(String itemName, double basePrice) {
        this.itemName = itemName;
        this.basePrice = basePrice;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public double getMeatPrice(){
        return switch (this){
            case SINGLE -> 1.00;
            case THREE_TACO -> 2.00;
            case BURRITO ->  3.00;
        };
    }

    public double getExtraMeatPrice(){
        return  switch (this){
            case SINGLE -> .50;
            case THREE_TACO -> 1.00;
            case BURRITO -> 1.50;
        };
    }

    public double getCheesePrice(){
        return switch(this){
            case SINGLE -> 0.75;
            case THREE_TACO -> 1.50;
            case BURRITO -> 2.25;
        };
    }
    public double getExtraCheese(){
        return switch(this){case SINGLE -> 0.30; case THREE_TACO -> 0.60; case BURRITO -> 0.90;};
    }

    public String getItemName() {
        return itemName;
    }

    @Override
    public String toString() {
       return itemName + " ($" + String.format("%.2f", basePrice) + ")";
    }
}

