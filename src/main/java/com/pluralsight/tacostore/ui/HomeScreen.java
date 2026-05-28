package com.pluralsight.tacostore.ui;

import com.pluralsight.tacostore.services.DisplayUtils;
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
                    DisplayUtils.clearScreen(5);
                    OrderScreen orderScreen = new OrderScreen(sc);
                    orderScreen.display();
                }
                case "2" -> viewPastOrders();
                case "0" -> {
                    running = false;
                    DisplayUtils.clearScreen(5);
                    printGoodbye();
                }
                default -> System.out.println("\n ❌ Incorrect option Please try again.\n");
            }

        }
        sc.close();
    }

    private void viewPastOrders() {
        DisplayUtils.clearScreen(5);
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
                System.out.println("\n  ❌ Invalid selection.\n");
            }
        } catch (NumberFormatException e) {
            System.out.println("\n  ❌ Please enter a valid number.\n");
        }

        System.out.print("\n  Press Enter to go back...");
        sc.nextLine();
    }
    private void displayHeader() {
        System.out.println();
        System.out.println(DisplayUtils.THICK);
        System.out.println("  "
                + DisplayUtils.BOLD + DisplayUtils.GREEN + "STACKS"
                + DisplayUtils.RESET + " "
                + DisplayUtils.BOLD + DisplayUtils.WHITE + "&"
                + DisplayUtils.RESET + " "
                + DisplayUtils.BOLD + DisplayUtils.RED + "SALSA"
                + DisplayUtils.RESET
                + "  |  TACO SHOP  |  "
                + DisplayUtils.GREEN + "P"
                + DisplayUtils.WHITE + "O"
                + DisplayUtils.RED + "S"
                + DisplayUtils.RESET);
        System.out.println(DisplayUtils.THICK);
        System.out.println();
    }


    private void displayMenu() {
        System.out.println("  MAIN MENU");
        System.out.println(DisplayUtils.THIN);
        System.out.println("  " + DisplayUtils.GREEN + "1)  New Order"         + DisplayUtils.RESET);
        System.out.println("  " + DisplayUtils.WHITE + "2)  View Past Orders"  + DisplayUtils.RESET);
        System.out.println("  " + DisplayUtils.RED   + "0)  Exit"              + DisplayUtils.RESET);
        System.out.println(DisplayUtils.THIN);
        System.out.print("\n  Select an option: ");
    }

    private void printGoodbye() {
        System.out.println();
        System.out.println(DisplayUtils.THICK);
        System.out.println("  " + DisplayUtils.GREEN + "Thanks for visiting "
                + DisplayUtils.WHITE + "Stacks "
                + DisplayUtils.RED + "& Salsa"
                + DisplayUtils.RESET + "!  🌮");
        System.out.println(DisplayUtils.THICK);
        System.out.println();
    }
}

