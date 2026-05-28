package com.pluralsight.tacostore.ui;

import com.pluralsight.tacostore.models.ChipsAndSalsa;
import com.pluralsight.tacostore.models.Drink;
import com.pluralsight.tacostore.models.Order;
import com.pluralsight.tacostore.models.Taco;
import com.pluralsight.tacostore.services.DisplayUtils;
import com.pluralsight.tacostore.services.OrderServices;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class OrderScreen {

    private Scanner sc;
    private Order order;

    public OrderScreen(Scanner sc) {
        this.sc    = sc;
        this.order = new Order();
    }

    public void display() {
        boolean ordering = true;

        while (ordering) {
            DisplayUtils.clearScreen(2);
            displayOrderSummary();
            displayMenu();

            String option = sc.nextLine().trim();

            switch (option) {
                case "1" -> {
                    TacoScreen tacoScreen = new TacoScreen(sc);
                    Taco taco = tacoScreen.display();
                    if (taco != null) {
                        order.addItem(taco);
                        System.out.println("\n  🌮 Taco added to your order!\n");
                    }
                }
                case "2" -> {
                    DrinkScreen drinkScreen = new DrinkScreen(sc);
                    Drink drink = drinkScreen.display();
                    if (drink != null) {
                        order.addItem(drink);
                        System.out.println("\n  🥤 Drink added to your order!\n");
                    }
                }
                case "3" -> {
                    ChipsScreen chipsScreen = new ChipsScreen(sc);
                    ChipsAndSalsa chips = chipsScreen.display();
                    if (chips != null) {
                        order.addItem(chips);
                        System.out.println("\n  🥗 Chips & Salsa added to your order!\n");
                    }
                }
                case "4" -> {
                    if (canCheckout()) {
                        CheckoutScreen checkoutScreen = new CheckoutScreen(sc, order);
                        checkoutScreen.display();
                        ordering = false;
                    }
                }
                case "0" -> {
                    ordering = false;
                    displayCancelled();
                }
                default -> System.out.println("\n  ❌ Invalid option. Please try again.\n");
            }
        }
    }


    private boolean canCheckout() {
            if (!OrderServices.isValidOrder(order)) {
                System.out.println("\n  " + OrderServices.getValidationMessage(order) + "\n");
                return false;
            }
            return true;
        }


    private void displayOrderSummary() {
        System.out.println();
        System.out.println(DisplayUtils.THICK);
        System.out.println("  STACKS & SALSA  |  CURRENT ORDER");
        System.out.println(DisplayUtils.THICK);
        System.out.println();

        List<String> items = new ArrayList<>();
        for (var item : order.getItems()) {
            items.add(item.getDescription());
        }

        Collections.reverse(items);

        if (items.isEmpty()) {
            System.out.println("  No items yet.");
        } else {
            items.forEach(i -> System.out.println("  " + i));
        }

        System.out.println();
        System.out.println(DisplayUtils.THIN);
        System.out.printf("  Running Total:  $%.2f%n", order.getTotal());
        System.out.println(DisplayUtils.THIN);
        System.out.println();
    }


    private void displayMenu() {
        System.out.println("  WHAT WOULD YOU LIKE?");
        System.out.println(DisplayUtils.THIN);
        System.out.println("  1)  Add Taco 🌮");
        System.out.println("  2)  Add Drink 🥤");
        System.out.println("  3)  Add Chips & Salsa 🥗");
        System.out.println("  4)  Checkout 🛒");
        System.out.println("  0)  Cancel Order");
        System.out.println(DisplayUtils.THIN);
        System.out.print("\n  Select an option: ");
    }


    private void displayCancelled() {
        DisplayUtils.clearScreen(5);
        System.out.println();
        System.out.println(DisplayUtils.THICK);
        System.out.println("  ORDER CANCELLED");
        System.out.println(DisplayUtils.THIN);
        System.out.println("  Your order has been deleted.");
        System.out.println("  Returning to main menu...");
        System.out.println(DisplayUtils.THICK);
        System.out.println();

        try { Thread.sleep(1500); } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}