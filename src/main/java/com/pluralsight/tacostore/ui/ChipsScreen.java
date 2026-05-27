package com.pluralsight.tacostore.ui;

import com.pluralsight.tacostore.models.ChipsAndSalsa;

import java.util.Scanner;

public class ChipsScreen {
    private Scanner sc;

    private static final String[] SALSAS = {
            "Salsa Verde", "Salsa Roja", "Chipotle",
            "Habanero", "Mild", "Extra Hot"
    };

    public ChipsScreen(Scanner scanner) {
        this.sc = scanner;
    }

    public ChipsAndSalsa display() {
        System.out.println("\n" + String.join("\n",
                "▐████████████████████████████████████▌",
                headerRow("CHIPS & SALSA  —  $1.50"),
                "▐───────────────────────────────────▌",
                row("SELECT YOUR SALSA:"),
                row("1)  Salsa Verde"),
                row("2)  Salsa Roja"),
                row("3)  Chipotle"),
                row("4)  Habanero"),
                row("5)  Mild"),
                row("6)  Extra Hot"),
                row("0)  Back"),
                "▐████████████████████████████████████▌"
        ));
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

    private String row(String text) {
        return String.format("▐█  %-34s█▌", text);
    }

    private String headerRow(String text) {
        return String.format("▐██ %-33s██▌", text);
    }
}

