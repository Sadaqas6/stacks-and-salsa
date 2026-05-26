package com.pluralsight.tacostore.models;

import com.pluralsight.tacostore.enums.DrinkSize;
import com.pluralsight.tacostore.interfaces.OrderItem;
import com.pluralsight.tacostore.interfaces.Printable;

public class Drink implements OrderItem, Printable {

    private DrinkSize size;
    private String flavor;

    public Drink(DrinkSize size, String flavor) {
        this.size = size;
        this.flavor = flavor;
    }


    @Override
    public double getPrice() {
        return this.size.getPrice();
    }

    @Override
    public String getDescription() {
        return String.format("[ DRINK ]  %s  •  %s  •  $%.2f",
                this.size.getItemName(),
                this.flavor,
                getPrice());
    }


    @Override
    public String formatDisplay() {
        return String.join("\n",
                "╔══════════════════════════════════════╗",
                row("DRINK 🥤"),
                "╠══════════════════════════════════════╣",
                row("Size:    " + this.size.getItemName()),
                row("Flavor:  " + this.flavor),
                "╠══════════════════════════════════════╣",
                row(String.format("Total:  $%.2f", getPrice())),
                "╚══════════════════════════════════════╝"
        );
    }


    @Override
    public String formatReceipt() {
        return String.format("  >> DRINK  (%s - %s)%n     Subtotal: $%.2f%n",
                this.size.getItemName(),
                this.flavor,
                getPrice());
    }


    public String row(String text){
        return String.format("║  %-36s║", text);
    }

    public DrinkSize getSize() {
        return size;
    }

    public void setSize(DrinkSize size) {
        this.size = size;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    @Override
    public String toString() {
        return getDescription();
    }

}


