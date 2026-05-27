package com.pluralsight.tacostore.ui;

import com.pluralsight.tacostore.services.ReceiptServices;

import java.util.List;
import java.util.Scanner;

public class HomeScreen {

    private Scanner sc;
    private ReceiptServices receiptServices;

    public HomeScreen() {

        this.sc = new Scanner(System.in);
        this.receiptServices = new ReceiptServices();
    }

    public void display(){
        boolean running = true;

        while(running){
             displayHeader();
             displayMenu();

            String option = sc.nextLine().trim();

            switch (option){
                case "1" -> {
                    OrderScreen orderScreen = new OrderScreen(sc);
                    orderScreen.display();
                }
                case "2" -> viewPastOrders();
                case "0" -> {
                    running = false;
                    printGoodbye();
                }
                default -> System.out.println("\n ❌ Incorrect option Please try again.\n");
            }

        }
        sc.close();
    }

    private void viewPastOrders() {
        System.out.println("\n" + receiptServices.formatReceiptList());

        List<String> receipts = receiptServices.listAllReceipts();
        if (receipts.isEmpty()) return;

        System.out.print("\n  Enter receipt number to view (or 0 to go back): ");
        String input = sc.nextLine().trim();

        if (input.equals("0")) return;

        try {
            int index = Integer.parseInt(input) - 1;
            if (index >= 0 && index < receipts.size()) {
                System.out.println("\n" + receiptServices.readReceipt(receipts.get(index)));
            } else {
                System.out.println("\n  ✗ Invalid selection.\n");
            }
        } catch (NumberFormatException e) {
            System.out.println("\n  ✗ Please enter a valid number.\n");
        }

        System.out.print("\n  Press Enter to go back...");
        sc.nextLine();
    }
    private void displayHeader() {
        System.out.println("""
                
                ▐████████████████████████████████████▌
                ▐██                                ██▌
                ▐██       STACKS & SALSA           ██▌
                ▐██      TACO SHOP  |  POS         ██▌
                ▐██                                ██▌
                ▐████████████████████████████████████▌
                """);
    }

    private void displayMenu() {
        System.out.println(String.join("\n",
                "▐████████████████████████████████████▌",
                headerRow("MAIN MENU"),
                "▐───────────────────────────────────▌",
                row("1)  New Order"),
                row("2)  View Past Orders"),
                row("0)  Exit"),
                "▐████████████████████████████████████▌"
        ));
        System.out.print("\n  Select an option: ");
    }

    private void printGoodbye() {
        System.out.println("""
                
                ▐████████████████████████████████████▌
                ▐██                                ██▌
                ▐██      Thanks for visiting       ██▌
                ▐██        STACKS & SALSA!         ██▌
                ▐██             🌮                 ██▌
                ▐████████████████████████████████████▌
                """);
    }

    private String row(String text) {
        return String.format("▐█  %-34s█▌", text);
    }

    private String headerRow(String text) {
        return String.format("▐██ %-33s██▌", text);
    }
}

