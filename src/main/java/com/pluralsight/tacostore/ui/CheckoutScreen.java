package com.pluralsight.tacostore.ui;

import com.pluralsight.tacostore.models.Order;
import com.pluralsight.tacostore.models.Receipt;
import com.pluralsight.tacostore.services.DisplayUtils;

import java.util.Scanner;

public class CheckoutScreen {
    private Scanner sc;
    private Order order;

    public CheckoutScreen(Scanner sc, Order order){
        this.sc = sc;
        this.order = order;
    }
    public void display(){
        DisplayUtils.clearScreen(5);
        displayOrderReview();
        displayConfirmMenu();

        String option = sc.nextLine().trim();
        switch (option) {
            case "1" -> confirmOrder();
            case "0" -> cancelOrder();
            default  -> {
                System.out.println("\n  ❌ Invalid option. Returning to order screen.\n");
            }
        }
    }

    // ── Confirm ───────────────────────────────────────────────────
    private void confirmOrder() {
        DisplayUtils.animateLoading("Processing your order", 4, 350);
        DisplayUtils.clearScreen(5);

        Receipt receipt = new Receipt(order);
        receipt.saveReceipt();

        System.out.println(receipt.formatDisplay());

        System.out.print("\n  Press Enter to return to the main menu...");
//        sc.nextLine();
        DisplayUtils.clearScreen(5);
    }

    // ── Cancel ────────────────────────────────────────────────────
    private void cancelOrder() {
        DisplayUtils.clearScreen(5);
        System.out.println(String.join("\n",
                "",
                DisplayUtils.THICK,
                DisplayUtils.headerRow("ORDER CANCELLED"),
                DisplayUtils.THIN,
                DisplayUtils.row("Your order has been deleted."),
                DisplayUtils.row("Returning to main menu..."),
                DisplayUtils.THICK,
                ""
        ));

        try { Thread.sleep(1500); } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        DisplayUtils.clearScreen(5);
    }

    private void displayOrderReview() {
        System.out.println(String.join("\n",
                "",
                DisplayUtils.THICK,
                DisplayUtils.headerRow(" STACKS & SALSA 🌯"),
                DisplayUtils.headerRow("REVIEW YOUR ORDER"),
                DisplayUtils.THICK,
                ""
        ));


        order.getItems().forEach(item ->
                System.out.println(item.formatDisplay() + "\n")
        );

        // order total
        System.out.println(String.join("\n",
                DisplayUtils.THICK,
                DisplayUtils.headerRow(String.format("ORDER TOTAL:  $%.2f", order.getTotal())),
                DisplayUtils.THICK,
                ""
        ));
    }

    private void displayConfirmMenu() {
        System.out.println(String.join("\n",
                DisplayUtils.THICK,
                DisplayUtils.headerRow("READY TO PLACE YOUR ORDER?"),
                DisplayUtils.THIN,
                DisplayUtils.row("1)  Confirm  — place order & save receipt"),
                DisplayUtils.row("0)  Cancel   — delete order & go back"),
                DisplayUtils.THICK
        ));
        System.out.print("\n  Select an option: ");
    }
}
