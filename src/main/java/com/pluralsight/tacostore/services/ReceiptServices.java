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


    public String readReceipt(String fileName) {
        Path filePath = Paths.get(receiptDir , fileName);

        if (!Files.exists(filePath)) {
            return "\n  ❌ Receipt not found: " + fileName + "\n";
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
                    .sorted(Collections.reverseOrder())
                    .collect(Collectors.toList());

        } catch (IOException e) {
            System.out.println("  ❌ Error listing receipts: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public String formatReceiptList() {
            List<String> receipts = listAllReceipts();

            String header = "\n" + DisplayUtils.THICK + "\n"
                    + "  SAVED RECEIPTS\n"
                    + DisplayUtils.THIN + "\n";

            String footer = DisplayUtils.THIN + "\n"
                    + "  " + receipts.size() + " receipt(s) found\n"
                    + DisplayUtils.THICK;

            if (receipts.isEmpty()) {
                return "\n" + DisplayUtils.THICK + "\n"
                        + "  SAVED RECEIPTS\n"
                        + DisplayUtils.THIN + "\n"
                        + "  No receipts found.\n"
                        + DisplayUtils.THICK;
            }

            String receiptLines = Stream.iterate(0, i -> i + 1)
                    .limit(receipts.size())
                    .map(i -> "  " + (i + 1) + ")  " + receipts.get(i))
                    .collect(Collectors.joining("\n"));

            return header + receiptLines + "\n" + footer;
        }

    public boolean deleteReceipt(String fileName) {
        Path filePath = Paths.get(receiptDir, fileName);

        try {
            return Files.deleteIfExists(filePath);
        } catch (IOException e) {
            System.out.println("  ❌ Error deleting receipt: " + e.getMessage());
            return false;
        }
    }
}