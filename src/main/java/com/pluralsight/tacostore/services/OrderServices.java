package com.pluralsight.tacostore.services;

import com.pluralsight.tacostore.models.ChipsAndSalsa;
import com.pluralsight.tacostore.models.Drink;
import com.pluralsight.tacostore.models.Order;
import com.pluralsight.tacostore.models.Taco;

public class OrderServices {


    public static boolean isEmpty(Order order) {
        return order.getItems().isEmpty();
    }

    public static boolean isValidOrder(Order order) {
        if (isEmpty(order)) return false;

        boolean hasTaco  = order.getItems().stream().anyMatch(i -> i instanceof Taco);
        boolean hasDrink = order.getItems().stream().anyMatch(i -> i instanceof Drink);
        boolean hasChips = order.getItems().stream().anyMatch(i -> i instanceof ChipsAndSalsa);

        return hasTaco || hasDrink || hasChips;
    }


    public static String getValidationMessage(Order order) {
        if (isEmpty(order)) {
            return "❌ Your order is empty!";
        }

        boolean hasTaco  = order.getItems().stream().anyMatch(i -> i instanceof Taco);
        boolean hasDrink = order.getItems().stream().anyMatch(i -> i instanceof Drink);
        boolean hasChips = order.getItems().stream().anyMatch(i -> i instanceof ChipsAndSalsa);

        if (!hasTaco && !hasDrink && !hasChips) {
            return "❌ No tacos? Add a drink or chips & salsa to continue.";
        }

        return "";
    }


    public double getTotal(Order order) {
        return order.getTotal();
    }


    public int getItemCount(Order order) {
        return order.getItems().size();
    }
}
