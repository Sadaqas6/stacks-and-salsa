package com.pluralsight.tacostore.ui;

import com.pluralsight.tacostore.enums.DrinkSize;
import com.pluralsight.tacostore.models.Drink;

import java.util.Scanner;

public class DrinkScreen {

    private Scanner sc;

    private static final String[] FLAVORS = {"Coke", "Mexican Coke", "Jarritos Lime", "Jarritos Mandarin",
            "Horchata", "Jamaica (Hibiscus)", "Water"
    };

    public DrinkScreen(Scanner sc){
        this.sc = sc;
    }

    public Drink display(){
        DrinkSize size = selectSize();
        if (size == null) return null;

        String flavor = selectFlavor();
        if(flavor == null) return null;

        Drink drink = new Drink(size, flavor);
        System.out.println("\n" + drink.formatDisplay() + "\n");
    return drink;
    }
    private DrinkSize selectSize() {
        System.out.println("\n" + String.join("\n",
                "▐████████████████████████████████████▌",
                headerRow("SELECT DRINK SIZE"),
                "▐───────────────────────────────────▌",
                row("1)  Small          $2.00"),
                row("2)  Medium         $2.50"),
                row("3)  Large          $3.00"),
                row("0)  Back"),
                "▐████████████████████████████████████▌"
        ));
        System.out.print("\n  Select an option: ");

        return switch (sc.nextLine().trim()) {
            case "1" -> DrinkSize.SMALL;
            case "2" -> DrinkSize.MEDIUM;
            case "3" -> DrinkSize.LARGE;
            case "0" -> null;
            default  -> { System.out.println("  ❌ Invalid."); yield null; }
        };
    }

    private String selectFlavor() {
        System.out.println("\n" + String.join("\n",
                "▐████████████████████████████████████▌",
                headerRow("SELECT FLAVOR"),
                "▐───────────────────────────────────▌",
                row("1)  Coke"),
                row("2)  Mexican Coke"),
                row("3)  Jarritos Lime"),
                row("4)  Jarritos Mandarin"),
                row("5)  Horchata"),
                row("6)  Jamaica (Hibiscus)"),
                row("7)  Water"),
                row("0)  Back"),
                "▐████████████████████████████████████▌"
        ));
        System.out.print("\n  Select an option: ");

        String choice = sc.nextLine().trim();
        if (choice.equals("0")) return null;

        try {
            int index = Integer.parseInt(choice) - 1;
            if (index >= 0 && index < FLAVORS.length) {
                return FLAVORS[index];
            }
        } catch (NumberFormatException e) {
            System.out.println("  ❌ Invalid selection.");
        }
        return null;
    }

    private String row(String text) {
        return String.format("▐█  %-34s█▌", text);
    }

    private String headerRow(String text) {
        return String.format("▐██ %-33s██▌", text);
    }
}


