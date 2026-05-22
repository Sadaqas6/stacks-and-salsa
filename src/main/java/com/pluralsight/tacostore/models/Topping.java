package com.pluralsight.tacostore.models;

import com.pluralsight.tacostore.enums.TacoSize;
import com.pluralsight.tacostore.interfaces.Printable;

public class Topping implements Printable {

    private String name;
    private boolean isPremium, isMeat, isExtra;


    // Regular Toppings
    public Topping(String name) {
        this.name = name;
        this.isPremium = false;
        this.isMeat = false;
        this.isExtra = false;
    }

    // Premium Toppings w/ meat and cheese
    public Topping(String name, boolean isPremium, boolean isMeat) {
        this.name = name;
        this.isPremium = isPremium;
        this.isMeat = isMeat;
        this.isExtra = false;
    }

    // Premium Toppings with extra options
    public Topping(String name, boolean isPremium, boolean isMeat, boolean isExtra) {
        this.name = name;
        this.isPremium = isPremium;
        this.isMeat = isMeat;
        this.isExtra = isExtra;
    }

    public double getPrice(TacoSize tacoSize) {
        if (!this.isPremium)
            return 0.00;

        double basePrice;
        double extraPrice;

        if (this.isMeat) {
            basePrice = tacoSize.getMeatPrice();
            extraPrice = tacoSize.getExtraMeatPrice();
        } else {
            basePrice = tacoSize.getCheesePrice();
            extraPrice = tacoSize.getExtraCheese();
        }
        return this.isExtra ? basePrice + extraPrice : basePrice;
    }

    @Override
    public String formatDisplay() {
        String extra = this.isExtra ? " (extra)" : "";
        return name + extra;
    }

    @Override
    public String formatReceipt() {
        String type = this.isPremium ? (this.isMeat ? "[Meat]" : "[Cheese]") : "[Regular]";
        String extra = this.isExtra ? " +extra" : "";
        return " - " + name + extra + " " + type;
    }


    public String getName() {return name; }

    public void setName(String name) {this.name = name; }

    public boolean isPremium() {return isPremium;}

    public void setPremium(boolean premium) {isPremium = premium; }

    public boolean isMeat() {return isMeat;}

    public void setMeat(boolean meat) {isMeat = meat;}

    public boolean isExtra() {return isExtra;}

    public void setExtra(boolean extra) {isExtra = extra;}

    @Override
    public String toString() {
        return formatDisplay();
    }
}