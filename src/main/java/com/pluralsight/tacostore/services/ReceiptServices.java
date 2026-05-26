package com.pluralsight.tacostore.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class ReceiptServices {

    private static final String receiptDir = "receipts";

    // Read


    public String readReceipt(String fileName) {
        Path filePath = Paths.get(receiptDir, fileName);

        if (!Files.exists(filePath)) {
            return String.format("""
                    ▐████████████████████████████████████▌
                    ▐██ RECEIPT NOT FOUND               ██▌
                    ▐───────────────────────────────────▌
                    ▐█  File: %-26s█▌
                    ▐████████████████████████████████████▌
                    """, fileName);
        }

        try {
            return Files.readString(filePath);
        } catch (IOException e) {
            return "  ❌ Error reading receipt: " + e.getMessage();
        }
    }

    public List<String> listAllReceipts() {
        Path dir = Paths.get(receiptDir);

        if (!Files.exists(dir)) {
            return Collections.emptyList();
        }

        try (Stream<Path> files = Files.list(dir)) {
            return files
                    .filter(f -> f.toString().endsWith(".txt"))
                    .map(f -> f.getFileName().toString())
                    .sorted(Collections.reverseOrder())  // most recent first
                    .collect(Collectors.toList());

        } catch (IOException e) {
            System.out.println("  ❌ Error listing receipts: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    //  Display

    public String formatReceiptList() {
        List<String> receipts = listAllReceipts();

        if (receipts.isEmpty()) {
            return String.join("\n",
                    "▐████████████████████████████████████▌",
                    headerRow("SAVED RECEIPTS"),
                    "▐───────────────────────────────────▌",
                    row("No receipts found."),
                    "▐████████████████████████████████████▌"
            );
        }

        // number each receipt
        String receiptLines = Stream.iterate(0, i -> i + 1)
                .limit(receipts.size())
                .map(i -> row((i + 1) + ".  " + receipts.get(i)))
                .collect(Collectors.joining("\n"));

        String countLine = row(receipts.size() + " receipt(s) found");

        return String.join("\n",
                "▐████████████████████████████████████▌",
                headerRow("SAVED RECEIPTS"),
                "▐───────────────────────────────────▌",
                receiptLines,
                "▐───────────────────────────────────▌",
                countLine,
                "▐████████████████████████████████████▌"
        );
    }

    // Delete
    public boolean deleteReceipt(String fileName) {
        Path filePath = Paths.get(receiptDir, fileName);

        try {
            return Files.deleteIfExists(filePath);
        } catch (IOException e) {
            System.out.println("  ❌ Error deleting receipt: " + e.getMessage());
            return false;
        }
    }


    private String row(String text) {
        return String.format("▐█  %-34s█▌", text);
    }

    private String headerRow(String text) {
        return String.format("▐██ %-33s██▌", text);
    }
}