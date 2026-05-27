package com.pluralsight.tacostore.services;

public class DisplayUtils {
    public static void animateLoading(String message, int dots, int delayMs) {
        System.out.print("\n  " + message + "  ");
        try {
            for (int i = 0; i < dots; i++) {
                Thread.sleep(delayMs);
                System.out.print(". ");
            }
            Thread.sleep(delayMs);
            System.out.println("✅\n");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void animateLoading(String message) {
        animateLoading(message, 3, 400);
    }

    public static void animateItemAdded(String itemName) {
        try {
            System.out.print("\n  Adding " + itemName);
            for (int i = 0; i < 3; i++) {
                Thread.sleep(250);
                System.out.print(" .");
            }
            Thread.sleep(250);
            System.out.println("  ✅\n");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // Live Price Counter
    public static void showLivePrice(double price) {
        System.out.println(
                "▐───────────────────────────────────▌\n" +
                        String.format("▐█  💰 RUNNING TOTAL:  $%-13.2f█▌", price) + "\n" +
                        "▐───────────────────────────────────▌"
        );
    }

    public static final String THICK  = "▐████████████████████████████████████▌";
    public static final String THIN   = "▐───────────────────────────────────▌";

    public static String row(String text) {
        return String.format("▐█  %-34s█▌", text);
    }

    public static String headerRow(String text) {
        return String.format("▐██ %-33s██▌", text);
    }

    // Clear Screen
    public static void clearScreen() {
        try {
            String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls")
                        .inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            // if clear fails just print blank lines
            System.out.println("\n\n\n\n\n");
        }
    }
}

