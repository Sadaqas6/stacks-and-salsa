package com.pluralsight.tacostore.ui;

import com.pluralsight.tacostore.enums.DrinkSize;
import com.pluralsight.tacostore.models.Drink;
import com.pluralsight.tacostore.services.DisplayUtils;

import java.util.Scanner;

public class DrinkScreen {

    private Scanner sc;

    private static final String[] FLAVORS = {
            "Agua de Sandia", "Mexican Coke", "Jarritos Lime", "Jarritos Mandarin",
            "Horchata", "Mangonada", "Water"
    };

    public DrinkScreen(Scanner sc) {
        this.sc = sc;
    }

    public Drink display() {
        DrinkSize size = selectSize();
        if (size == null) return null;

        String flavor = selectFlavor();
        if (flavor == null) return null;

        Drink drink = new Drink(size, flavor);
        System.out.println("\n" + drink.formatDisplay() + "\n");
        return drink;
    }

    private DrinkSize selectSize() {
        DisplayUtils.clearScreen(5);
        System.out.println();
        System.out.println(DisplayUtils.THICK);
        System.out.println("  SELECT DRINK SIZE  🥤");
        System.out.println(DisplayUtils.THICK);
        System.out.println();
        System.out.println("  1)  Small          $2.00");
        System.out.println("  2)  Medium         $2.50");
        System.out.println("  3)  Large          $3.00");
        System.out.println("  0)  Back");
        System.out.println(DisplayUtils.THIN);
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
        DisplayUtils.clearScreen(5);
        System.out.println();
        System.out.println(DisplayUtils.THICK);
        System.out.println("  SELECT FLAVOR");
        System.out.println(DisplayUtils.THICK);
        System.out.println();
        System.out.println("  1)  Agua de Sandia");
        System.out.println("  2)  Mexican Coke");
        System.out.println("  3)  Jarritos Lime");
        System.out.println("  4)  Jarritos Mandarin");
        System.out.println("  5)  Horchata");
        System.out.println("  6)  Mangonada");
        System.out.println("  7)  Water");
        System.out.println("  0)  Back");
        System.out.println(DisplayUtils.THIN);
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
}
