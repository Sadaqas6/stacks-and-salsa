package com.pluralsight.tacostore.ui;

import com.pluralsight.tacostore.enums.ShellType;
import com.pluralsight.tacostore.enums.TacoSize;
import com.pluralsight.tacostore.models.*;

import java.util.Scanner;

public class TacoScreen {

    private Scanner sc;

    private static final String[] MEATS = {"Carne Asada", "Al Pastor", "Carnitas", "Pollo", "Chorizo", "Pescado"};
    private static final String[] CHEESES  = {"Queso Fresco", "Oaxaca", "Cotija", "Cheddar"};
    private static final String[] REGULARS = {"Lettuce", "Cilantro", "Onions",
        "Tomatoes", "Jalapeños", "Radishes", "Pico de Gallo", "Guacamole", "Corn"};
    private static final String[] SAUCES   = {"Salsa Verde", "Salsa Roja", "Chipotle",
        "Habanero", "Mild", "Extra Hot"};
    private static final String[] SIDES    = {"Lime Wedges", "Crema"};

    public TacoScreen(Scanner sc){
        this.sc = sc;
    }

    public Taco display(){
        displayBanner("LET'S BUILD YOUR TACO");


        System.out.println(String.join("\n",
                "▐████████████████████████████████████▌",
                headerRow("HOW WOULD YOU LIKE TO START?"),
                "▐───────────────────────────────────▌",
                row("1)  Start from scratch"),
                row("2)  Street Tacos  (signature)"),
                row("3)  Super Burrito  (signature)"),
                row("4)  Burrito Stack  (signature) ★"),
                row("0)  Back"),
                "▐████████████████████████████████████▌"
        ));
        System.out.print("\n  Select an option: ");
        String option = sc.nextLine().trim();

        return switch (option) {
            case "1" -> buildCustomTaco();
            case "2" -> customizeSignature(new StreetTaco(),  "STREET TACOS");
            case "3" -> customizeSignature(new SuperBurrito(), "SUPER BURRITO");
            case "4" -> customizeSignature(new BurritoStack(), "BURRITO STACK");
            case "0" -> null;
            default  -> {
                System.out.println("\n  ❌ Invalid option.\n");
                yield null;
            }
        };
    }

    private Taco buildCustomTaco(){
        TacoSize size = selectSize();
        if(size == null) return null;

        ShellType shell = selectShell();
        if(shell == null) return null;

        Taco taco = new Taco(size, shell);

        selectMeat(taco);

        selectCheese(taco);

        selectRegularToppings(taco);

        selectSauces(taco);

        selectSides(taco);

        taco.setCovered(askCovered());

        displayPreview(taco);
        return taco;
    }
    private Taco customizeSignature(Taco taco, String name) {
        displayBanner(name + "  ☆");
        System.out.println("\n" + taco.formatDisplay());

        System.out.println(String.join("\n",
                "▐████████████████████████████████████▌",
                headerRow("CUSTOMIZE YOUR " + name + "?"),
                "▐───────────────────────────────────▌",
                row("1)  Add extra toppings"),
                row("2)  Remove a topping"),
                row("3)  Keep as is"),
                row("0)  Back"),
                "▐████████████████████████████████████▌"
        ));
        System.out.print("\n  Select an option: ");

        String option = sc.nextLine().trim();

        switch (option) {
            case "1" -> {
                selectRegularToppings(taco);
                selectSauces(taco);
            }
            case "2" -> removeTopping(taco);
            case "3" -> { }
            case "0" -> { return null; }
            default  -> System.out.println("\n  ❌  Invalid option.\n");
        }

        displayPreview(taco);
        return taco;
    }
    private TacoSize selectSize() {
        System.out.println("\n" + String.join("\n",
                "▐████████████████████████████████████▌",
                headerRow("SELECT SIZE"),
                "▐───────────────────────────────────▌",
                row("1)  Single Taco       $3.50"),
                row("2)  3-Taco Plate      $9.00"),
                row("3)  Burrito           $8.50"),
                row("0)  Back"),
                "▐████████████████████████████████████▌"
        ));
        System.out.print("\n  Select an option: ");

        return switch (sc.nextLine().trim()) {
            case "1" -> TacoSize.SINGLE;
            case "2" -> TacoSize.THREE_TACO;
            case "3" -> TacoSize.BURRITO;
            case "0" -> null;
            default  -> { System.out.println("  ❌  Invalid."); yield null; }
        };
    }

    private ShellType selectShell() {
        System.out.println("\n" + String.join("\n",
                "▐████████████████████████████████████▌",
                headerRow("SELECT SHELL"),
                "▐───────────────────────────────────▌",
                row("1)  Corn Tortilla"),
                row("2)  Flour Tortilla"),
                row("3)  Hard Shell"),
                row("4)  Bowl"),
                row("0)  Back"),
                "▐████████████████████████████████████▌"
        ));
        System.out.print("\n  Select an option: ");

        return switch (sc.nextLine().trim()) {
            case "1" -> ShellType.CORN;
            case "2" -> ShellType.FLOUR;
            case "3" -> ShellType.HARD_SHELl;
            case "4" -> ShellType.BOWL;
            case "0" -> null;
            default  -> { System.out.println("  ❌ Invalid."); yield null; }
        };
    }

    private void selectMeat(Taco taco) {
        System.out.println("\n" + String.join("\n",
                "▐████████████████████████████████████▌",
                headerRow("SELECT MEAT  (premium)"),
                "▐───────────────────────────────────▌",
                row("1)  Carne Asada"),
                row("2)  Al Pastor"),
                row("3)  Carnitas"),
                row("4)  Pollo"),
                row("5)  Chorizo"),
                row("6)  Pescado"),
                row("0)  No meat"),
                "▐████████████████████████████████████▌"
        ));
        System.out.print("\n  Select an option: ");

        String choice = sc.nextLine().trim();
        if (choice.equals("0")) return;

        try {
            int index = Integer.parseInt(choice) - 1;
            if (index >= 0 && index < MEATS.length) {
                taco.addTopping(new Topping(MEATS[index], true, true));

                // ask for extra meat
                System.out.print("\n  Extra " + MEATS[index] + "? (y/n): ");
                if (sc.nextLine().trim().equalsIgnoreCase("y")) {
                    taco.addTopping(new Topping(MEATS[index], true, true, true));
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("  ❌ Invalid selection.");
        }
    }

    private void selectCheese(Taco taco) {
        System.out.println("\n" + String.join("\n",
                "▐████████████████████████████████████▌",
                headerRow("SELECT CHEESE  (premium)"),
                "▐───────────────────────────────────▌",
                row("1)  Queso Fresco"),
                row("2)  Oaxaca"),
                row("3)  Cotija"),
                row("4)  Cheddar"),
                row("0)  No cheese"),
                "▐████████████████████████████████████▌"
        ));
        System.out.print("\n  Select an option: ");

        String choice = sc.nextLine().trim();
        if (choice.equals("0")) return;

        try {
            int index = Integer.parseInt(choice) - 1;
            if (index >= 0 && index < CHEESES.length) {
                taco.addTopping(new Topping(CHEESES[index], true, false));

                // ask for extra cheese
                System.out.print("\n  Extra " + CHEESES[index] + "? (y/n): ");
                if (sc.nextLine().trim().equalsIgnoreCase("y")) {
                    taco.addTopping(new Topping(CHEESES[index], true, false, true));
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("  ❌ Invalid selection.");
        }
    }

    private void selectRegularToppings(Taco taco) {
        boolean selecting = true;

        while (selecting) {
            System.out.println("\n" + String.join("\n",
                    "▐████████████████████████████████████▌",
                    headerRow("SELECT TOPPINGS  (free)"),
                    "▐───────────────────────────────────▌",
                    row("1)  Lettuce"),
                    row("2)  Cilantro"),
                    row("3)  Onions"),
                    row("4)  Tomatoes"),
                    row("5)  Jalapeños"),
                    row("6)  Radishes"),
                    row("7)  Pico de Gallo"),
                    row("8)  Guacamole"),
                    row("9)  Corn"),
                    row("0)  Done with toppings"),
                    "▐████████████████████████████████████▌"
            ));
            System.out.print("\n  Select an option: ");

            String choice = sc.nextLine().trim();

            if (choice.equals("0")) {
                selecting = false;
            } else {
                try {
                    int index = Integer.parseInt(choice) - 1;
                    if (index >= 0 && index < REGULARS.length) {
                        taco.addTopping(new Topping(REGULARS[index]));
                        System.out.println("  ✅ " + REGULARS[index] + " added!");
                    } else {
                        System.out.println("  ❌ Invalid selection.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("  ❌ Invalid selection.");
                }
            }
        }
    }

    private void selectSauces(Taco taco) {
        boolean selecting = true;

        while (selecting) {
            System.out.println("\n" + String.join("\n",
                    "▐████████████████████████████████████▌",
                    headerRow("SELECT SAUCES  (free)"),
                    "▐───────────────────────────────────▌",
                    row("1)  Salsa Verde"),
                    row("2)  Salsa Roja"),
                    row("3)  Chipotle"),
                    row("4)  Habanero"),
                    row("5)  Mild"),
                    row("6)  Extra Hot"),
                    row("0)  Done with sauces"),
                    "▐████████████████████████████████████▌"
            ));
            System.out.print("\n  Select an option: ");

            String choice = sc.nextLine().trim();

            if (choice.equals("0")) {
                selecting = false;
            } else {
                try {
                    int index = Integer.parseInt(choice) - 1;
                    if (index >= 0 && index < SAUCES.length) {
                        taco.addTopping(new Topping(SAUCES[index]));
                        System.out.println("  ✅ " + SAUCES[index] + " added!");
                    } else {
                        System.out.println("  ❌ Invalid selection.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("  ❌ Invalid selection.");
                }
            }
        }
    }

    private void selectSides(Taco taco) {
        boolean selecting = true;

        while (selecting) {
            System.out.println("\n" + String.join("\n",
                    "▐████████████████████████████████████▌",
                    headerRow("SELECT SIDES  (free)"),
                    "▐───────────────────────────────────▌",
                    row("1)  Lime Wedges"),
                    row("2)  Crema"),
                    row("0)  Done with sides"),
                    "▐████████████████████████████████████▌"
            ));
            System.out.print("\n  Select an option: ");

            String choice = sc.nextLine().trim();

            if (choice.equals("0")) {
                selecting = false;
            } else {
                try {
                    int index = Integer.parseInt(choice) - 1;
                    if (index >= 0 && index < SIDES.length) {
                        taco.addTopping(new Topping(SIDES[index]));
                        System.out.println("  ✅ " + SIDES[index] + " added!");
                    } else {
                        System.out.println("  ❌ Invalid selection.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("  ❌ Invalid selection.");
                }
            }
        }
    }

    private boolean askCovered() {
        System.out.println("\n" + String.join("\n",
                "▐████████████████████████████████████▌",
                headerRow("COVERED IN SALSA & QUESO?"),
                "▐───────────────────────────────────▌",
                row("1)  Yes — smother it!"),
                row("2)  No thanks"),
                "▐████████████████████████████████████▌"
        ));
        System.out.print("\n  Select an option: ");

        return sc.nextLine().trim().equals("1");
    }

    private void removeTopping(Taco taco) {
        System.out.print("\n  Enter topping name to remove: ");
        String name = sc.nextLine().trim();

        if (taco.removeTopping(name)) {
            System.out.println("  ✅ " + name + " removed!");
        } else {
            System.out.println("  ❌ Topping not found: " + name);
        }
    }

    // ── Preview ───────────────────────────────────────────────────

    private void displayPreview(Taco taco) {
        System.out.println("\n" + taco.formatDisplay() + "\n");
    }

    // ── Helpers ───────────────────────────────────────────────────

    private void displayBanner(String text) {
        System.out.println("\n" + String.join("\n",
                "▐████████████████████████████████████▌",
                headerRow(text),
                "▐████████████████████████████████████▌"
        ) + "\n");
    }

    private String row(String text) {
        return String.format("▐█  %-34s█▌", text);
    }

    private String headerRow(String text) {
        return String.format("▐██ %-33s██▌", text);
    }
}
