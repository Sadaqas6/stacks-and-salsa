package com.pluralsight.tacostore.models;

import com.pluralsight.tacostore.interfaces.Printable;
import com.pluralsight.tacostore.services.DisplayUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.time.format.DateTimeFormatter;

public class Receipt implements Printable {

    private Order order;
    private String fileName;

    private static final String receiptDir = "receipts";
    private static final DateTimeFormatter fileFormatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");

    public Receipt(Order order) {
        this.order = order;
        this.fileName = order.getOrderTime().format(fileFormatter) + ".txt";
    }

    public boolean saveReceipt(){
        try {
            new File(receiptDir).mkdirs();
            String filePath = receiptDir + "/" + this.fileName;
            try(BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))){
                bw.write(formatReceipt());
            }
            System.out.println("\n ✅ Receipt saves to: " + filePath);
            return true;

        } catch (IOException e) {
            System.out.println("\n ❌ There was an error saving receipt: " + e.getMessage());
            return false;
        }
    }
    @Override
    public String formatDisplay() {
        return String.join("\n",
                DisplayUtils.THICK,
                "ORDER CONFIRMED ✅",
                DisplayUtils.THIN,
                "Receipt saved to:",
                "File: " + fileName,
                DisplayUtils.THIN,
                String.format("TOTAL CHARGED:  $%.2f", order.getTotal()),
                DisplayUtils.THICK
        );
    }

    @Override
    public String formatReceipt() {
        DateTimeFormatter displayFormat =
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String itemLines = order.getItems().stream()
                .map(item -> item.formatReceipt() +
                        "\n-----------------------------------------")
                .reduce("", String::concat);

        return String.format("""
                =========================================
                     STACKS & SALSA  |  ORDER RECEIPT
                =========================================
                  Date: %s
                -----------------------------------------
                %s
                  TOTAL:  $%.2f
                =========================================
                     Thank you! See you next time. 🌮
                =========================================
                """,
                order.getOrderTime().format(displayFormat),
                itemLines,
                order.getTotal());
    }

    public Order getOrder()      { return order; }
    public String getFileName()  { return fileName; }


}
