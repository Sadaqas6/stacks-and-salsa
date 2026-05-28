package com.pluralsight.tacostore.ui;

import com.pluralsight.tacostore.models.ChipsAndSalsa;
import com.pluralsight.tacostore.services.DisplayUtils;

import java.util.Scanner;

public class ChipsScreen {
    private Scanner sc;

    private static final String[] SALSAS = {
            "Salsa Verde", "Salsa Roja", "Chipotle",
            "Habanero", "Mild", "Extra Hot"
    };

    public ChipsScreen(Scanner sc) {
        this.sc = sc;
    }

    public ChipsAndSalsa display() {
        DisplayUtils.clearScreen(5);
        System.out.println();
        System.out.println(DisplayUtils.THICK);
        System.out.println("  CHIPS & SALSA  🥗  —  $1.50");
        System.out.println(DisplayUtils.THICK);
        System.out.println();
        System.out.println("  SELECT YOUR SALSA:");
        System.out.println(DisplayUtils.THIN);
        System.out.println("  1)  Salsa Verde");
        System.out.println("  2)  Salsa Roja");
        System.out.println("  3)  Chipotle");
        System.out.println("  4)  Habanero");
        System.out.println("  5)  Mild");
        System.out.println("  6)  Extra Hot");
        System.out.println("  0)  Back");
        System.out.println(DisplayUtils.THIN);
        System.out.print("\n  Select an option: ");

        String choice = sc.nextLine().trim();
        if (choice.equals("0")) return null;

        try {
            int index = Integer.parseInt(choice) - 1;
            if (index >= 0 && index < SALSAS.length) {
                ChipsAndSalsa chips = new ChipsAndSalsa(SALSAS[index]);
                System.out.println("\n" + chips.formatDisplay() + "\n");
                return chips;
            }
        } catch (NumberFormatException e) {
            System.out.println("  ✗ Invalid selection.");
        }
        return null;
    }
}
