package com.pluralsight.tacostore.enums;

public enum ShellType {

    CORN ("Corn"),
    FLOUR("Flour"),
    HARD_SHELl("Hard Shell"),
    BOWL("Bowl");

    private final String itemName;

    ShellType(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }

    @Override
    public String toString() {
        return itemName;
    }
}
