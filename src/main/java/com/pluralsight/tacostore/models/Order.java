package com.pluralsight.tacostore.models;

import com.pluralsight.tacostore.interfaces.OrderItem;
import com.pluralsight.tacostore.interfaces.Printable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Order implements Printable {

    private List<OrderItem> items;
    private LocalDateTime orderTime;

    public Order() {
        this.items = new ArrayList<>();
        this.orderTime = LocalDateTime.now();
    }

    public double getTotal(){
        return this.items.stream()
                .mapToDouble(OrderItem :: getPrice)
                .sum();
    }

    public void addItem(OrderItem item){
        this.items.add(item);
    }

    public boolean removeItem(OrderItem item){
        return this.items.remove(item);
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    @Override
    public String formatDisplay() {
        if(this.items.isEmpty())
            return "  No items in the order.\n";
        return this.items.stream()
                .map(OrderItem::getDescription)
                .collect(Collectors.joining("\n"));
    }

    @Override
    public String formatReceipt() {
       String itemLines = this.items.stream()
               .map(OrderItem::formatReceipt)
               .collect(Collectors.joining("\n"));


        return String.format("""
                ================================
                ORDER RECEIPT
                Date: %s
                ================================
                %s
                ================================
                TOTAL:  $%.2f
                ================================
                """, orderTime, itemLines, getTotal());
    }


}
