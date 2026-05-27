package com.pluralsight.tacostore.interfaces;

public interface OrderItem {

    public double getPrice();
    public String getDescription();
    public String formatReceipt();
    public String formatDisplay();
}
