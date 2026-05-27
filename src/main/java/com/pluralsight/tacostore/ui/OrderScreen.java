package com.pluralsight.tacostore.ui;

import com.pluralsight.tacostore.models.ChipsAndSalsa;
import com.pluralsight.tacostore.models.Drink;
import com.pluralsight.tacostore.models.Order;
import com.pluralsight.tacostore.models.Taco;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class OrderScreen {

    private Scanner sc;
    private Order order;

    public OrderScreen(Scanner sc) {
        this.sc = sc;
        this.order = new Order();
    }

    public void display() {
        boolean ordering = true;

        while (ordering) {
            displayOrderSummary();
            displayMenu();

            String option = sc.nextLine().trim();

            switch (option) {
                case "1" -> {
                    TacoScreen tacoScreen = new TacoScreen(sc);
                    Taco taco = tacoScreen.display();
                    if (taco != null) {
                        order.addItem(taco);
                        System.out.println("\n" +
                                " 🌮 Taco added to your order!\n");
                    }
                }
                case "2" -> {
                    DrinkScreen drinkScreen = new DrinkScreen(sc);
                    Drink drink = drinkScreen.display();
                    if (drink != null) {
                        order.addItem(drink);
                        System.out.println("\n" +
                                " 🥤 Drink added to your order!\n");
                    }
                }
                case "3" -> {
                    ChipsScreen chipsScreen = new ChipsScreen(sc);
                    ChipsAndSalsa chips = chipsScreen.display();
                    if (chips != null) {
                        order.addItem(chips);
                        System.out.println("\n" +
                                " 🥗 Chips & Salsa added to your order!\n");
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
        boolean hasTaco = order.getItems().stream().anyMatch(item -> item instanceof Taco);
        boolean hasDrink = order.getItems().stream().anyMatch(item -> item instanceof Drink);
        boolean hasChips = order.getItems().stream().anyMatch(item -> item instanceof ChipsAndSalsa);

        if (order.getItems().isEmpty()) {
            System.out.println("""
                    
                    ▐████████████████████████████████████▌
                    ▐██ CANNOT CHECKOUT                ██▌
                    ▐───────────────────────────────────▌
                    ▐█  Your order is empty!            █▌
                    ▐████████████████████████████████████▌
                    """);
            return false;
        }
        if (!hasTaco && !hasDrink && !hasChips) {
            System.out.println("""
                    
                    ▐████████████████████████████████████▌
                    ▐██ CANNOT CHECKOUT                 ██▌
                    ▐───────────────────────────────────▌
                    ▐█  No tacos? Add a drink or        █▌
                    ▐█  chips & salsa to continue.      █▌
                    ▐████████████████████████████████████▌
                    """);
            return false;

        }
        if (!hasTaco && !hasDrink && !hasChips) {
            System.out.println("\n  ❌ No tacos? You must add a drink or chips & salsa.\n");
            return false;
        }
        return true;
    }

    private void  displayOrderSummary(){

        System.out.println("""
                
                ▐████████████████████████████████████▌
                ▐██       STACKS & SALSA           ██▌
                ▐██        CURRENT ORDER           ██▌
                ▐████████████████████████████████████▌
                """);

        List<String> items = new ArrayList<>();
        for (var item : order.getItems()) {
            items.add(item.getDescription());
        }
// reverse so newest shows first
        Collections.reverse(items);

        if (items.isEmpty()) {
            System.out.println(row("No items yet."));
        } else {
            items.forEach(i -> System.out.println(row(i)));
        }
        System.out.println("▐───────────────────────────────────▌");
        System.out.println(headerRow(String.format("RUNNING TOTAL:  $%.2f", order.getTotal())));
        System.out.println("▐████████████████████████████████████▌\n");
    }

    private void displayMenu() {
        System.out.println(String.join("\n",
                "▐████████████████████████████████████▌",
                headerRow("WHAT WOULD YOU LIKE?"),
                "▐───────────────────────────────────▌",
                row("1)  Add Taco"),
                row("2)  Add Drink"),
                row("3)  Add Chips & Salsa"),
                row("4)  Checkout"),
                row("0)  Cancel Order"),
                "▐████████████████████████████████████▌"
        ));
        System.out.print("\n  Select an option: ");
    }

    private void displayCancelled() {
        System.out.println("""
                
                ▐████████████████████████████████████▌
                ▐██ ORDER CANCELLED                ██▌
                ▐───────────────────────────────────▌
                ▐█  Your order has been deleted.    █▌
                ▐█  Returning to main menu...       █▌
                ▐████████████████████████████████████▌
                """);
    }

    // ── Helpers ───────────────────────────────────────────────────
    private String row(String text) {
        return String.format("▐█  %-34s█▌", text);
    }

    private String headerRow(String text) {
        return String.format("▐██ %-33s██▌", text);
    }
}



