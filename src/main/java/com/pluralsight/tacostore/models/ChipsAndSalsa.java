package com.pluralsight.tacostore.models;

import com.pluralsight.tacostore.interfaces.OrderItem;
import com.pluralsight.tacostore.interfaces.Printable;

public class ChipsAndSalsa implements OrderItem, Printable {

    private String salsaType;
    private static final double price = 1.50;

    public ChipsAndSalsa(String salsaType) {
        this.salsaType = salsaType;
    }

    public String getSalsaType() {
        return salsaType;
    }

    public void setSalsaType(String salsaType) {
        this.salsaType = salsaType;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public String getDescription() {
        return String.format("[ CHIPS & SALSA ]  %s  •  $%.2f" , this.salsaType, getPrice());
    }

    @Override
    public String formatDisplay() {
        return String.join("\n",
                "╔══════════════════════════════════════╗",
                row("CHIPS & SALSA"),
                "╠══════════════════════════════════════╣",
                row("Salsa:  " + this.salsaType),
                "╠══════════════════════════════════════╣",
                row(String.format("Total:  $%.2f", getPrice())),
                "╚══════════════════════════════════════╝"
        );
    }

    @Override
    public String formatReceipt() {
        return String.format(" >> CHIPS & SALSA  (%s)%n     Subtotal: $%.2f%n", this.salsaType, getPrice());

    }

    public String row(String text){
        return String.format("║  %-36s║", text);
    }


    @Override
    public String toString() {
        return getDescription();
    }

}
