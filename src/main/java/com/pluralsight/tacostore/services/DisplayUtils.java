package com.pluralsight.tacostore.services;

public class DisplayUtils {

    public static final String GREEN = "\u001B[32m";
    public static final String WHITE = "\u001B[97m";
    public static final String RED   = "\u001B[31m";
    public static final String RESET = "\u001B[0m";
    public static final String BOLD  = "\u001B[1m";


    public static final String THICK = "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━";
    public static final String THIN  = "───────────────────────────────────────";



    public static String row(String text) {
        return "  " + text;
    }

    public static String headerRow(String text) {
        return "  " + text;
    }


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


    public static void showLivePrice(double price) {
        System.out.println(
                THIN + "\n" +
                        String.format("  💰 Running Total:  $%.2f", price) + "\n" +
                        THIN
        );
    }



    public static void clearScreen(int lines) {
        for (int i = 0; i < lines; i++) {
            System.out.println();
        }
    }

}