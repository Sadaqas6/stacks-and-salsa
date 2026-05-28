package com.pluralsight.tacostore.ui;

import com.pluralsight.tacostore.enums.ShellType;
import com.pluralsight.tacostore.enums.TacoSize;
import com.pluralsight.tacostore.models.*;
import com.pluralsight.tacostore.services.DisplayUtils;

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

        System.out.println();

        System.out.println("  HOW WOULD YOU LIKE TO START?");
        System.out.println(DisplayUtils.THIN);
        System.out.println("  1)  Build your own");
        System.out.println("  2)  Street Tacos       (signature)");
        System.out.println("  3)  Super Burrito      (signature)");
        System.out.println("  4)  Burrito Stack      (signature) ★");
        System.out.println("  0)  Back");
        System.out.println(DisplayUtils.THIN);
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

        DisplayUtils.clearScreen(5);
        displayPreview(taco);
        return taco;
    }
    private Taco customizeSignature(Taco taco, String name) {

        DisplayUtils.clearScreen(5);

        System.out.println();
        System.out.println(DisplayUtils.THICK);
        System.out.println("  " + name);
        System.out.println(DisplayUtils.THICK);
        System.out.println();

        // show the preset using box style
        System.out.println(taco.formatDisplay());
        System.out.println();

        System.out.println("  CUSTOMIZE THIS ORDER?");
        System.out.println(DisplayUtils.THIN);
        System.out.println("  1)  Add extra toppings");
        System.out.println("  2)  Remove a topping");
        System.out.println("  3)  Keep as is");
        System.out.println("  0)  Back");
        System.out.println(DisplayUtils.THIN);
        System.out.print("\n  Select an option: ");

        String choice = sc.nextLine().trim();

        switch (choice) {
            case "1" -> {
                selectRegularToppings(taco);
                selectSauces(taco);
            }
            case "2" -> removeTopping(taco);
            case "3" -> { }
            case "0" -> { return null; }
            default  -> System.out.println("\n  ❌ Invalid option.\n");
        }

        DisplayUtils.clearScreen(5);
        displayPreview(taco);
        return taco;
    }

    private TacoSize selectSize() {
        DisplayUtils.clearScreen(5);
        System.out.println();
        System.out.println(DisplayUtils.THICK);
        System.out.println("  SELECT SIZE");
        System.out.println(DisplayUtils.THICK);
        System.out.println();
        System.out.println("  1)  Single Taco        $3.50");
        System.out.println("  2)  3-Taco Plate        $9.00");
        System.out.println("  3)  Burrito             $8.50");
        System.out.println("  0)  Back");
        System.out.println(DisplayUtils.THIN);
        System.out.print("\n  Select an option: ");

        return switch (sc.nextLine().trim()) {
            case "1" -> TacoSize.SINGLE;
            case "2" -> TacoSize.THREE_TACO;
            case "3" -> TacoSize.BURRITO;
            case "0" -> null;
            default  -> { System.out.println("  ❌ Invalid."); yield null; }
        };
    }

    private ShellType selectShell() {
        DisplayUtils.clearScreen(5);
        System.out.println();
        System.out.println(DisplayUtils.THICK);
        System.out.println("  SELECT SHELL");
        System.out.println(DisplayUtils.THICK);
        System.out.println();
        System.out.println("  1)  Corn Tortilla");
        System.out.println("  2)  Flour Tortilla");
        System.out.println("  3)  Hard Shell");
        System.out.println("  4)  Bowl");
        System.out.println("  0)  Back");
        System.out.println(DisplayUtils.THIN);
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
        DisplayUtils.clearScreen(5);
        System.out.println();
        System.out.println(DisplayUtils.THICK);
        System.out.println("  SELECT MEAT  (premium)");
        System.out.println(DisplayUtils.THICK);
        System.out.println();
        System.out.println("  1)  Carne Asada");
        System.out.println("  2)  Al Pastor");
        System.out.println("  3)  Carnitas");
        System.out.println("  4)  Pollo");
        System.out.println("  5)  Chorizo");
        System.out.println("  6)  Pescado");
        System.out.println("  0)  No meat");
        System.out.println(DisplayUtils.THIN);
        System.out.print("\n  Select an option: ");

        String choice = sc.nextLine().trim();
        if (choice.equals("0")) return;

        try {
            int index = Integer.parseInt(choice) - 1;
            if (index >= 0 && index < MEATS.length) {
                taco.addTopping(new Topping(MEATS[index], true, true));
                DisplayUtils.animateItemAdded(MEATS[index]);
                DisplayUtils.showLivePrice(taco.getPrice());

                System.out.print("  Extra " + MEATS[index] + "? (y/n): ");
                if (sc.nextLine().trim().equalsIgnoreCase("y")) {
                    taco.addTopping(new Topping(MEATS[index], true, true, true));
                    DisplayUtils.showLivePrice(taco.getPrice());
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("  ❌ Invalid selection.");
        }
    }

    private void selectCheese(Taco taco) {

            DisplayUtils.clearScreen(5);
            System.out.println();
            System.out.println(DisplayUtils.THICK);
            System.out.println("  SELECT CHEESE  (premium)");
            System.out.println(DisplayUtils.THICK);
            System.out.println();
            System.out.println("  1)  Queso Fresco");
            System.out.println("  2)  Oaxaca");
            System.out.println("  3)  Cotija");
            System.out.println("  4)  Cheddar");
            System.out.println("  0)  No cheese");
            System.out.println(DisplayUtils.THIN);
            System.out.print("\n  Select an option: ");

            String choice = sc.nextLine().trim();
            if (choice.equals("0")) return;

            try {
                int index = Integer.parseInt(choice) - 1;
                if (index >= 0 && index < CHEESES.length) {
                    taco.addTopping(new Topping(CHEESES[index], true, false));
                    DisplayUtils.animateItemAdded(CHEESES[index]);
                    DisplayUtils.showLivePrice(taco.getPrice());

                    System.out.print("  Extra " + CHEESES[index] + "? (y/n): ");
                    if (sc.nextLine().trim().equalsIgnoreCase("y")) {
                        taco.addTopping(new Topping(CHEESES[index], true, false, true));
                        DisplayUtils.showLivePrice(taco.getPrice());
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("  ❌ Invalid selection.");
            }
        }

    private void selectRegularToppings(Taco taco) {
        boolean selecting = true;

        while (selecting) {
            DisplayUtils.clearScreen(5);
            System.out.println();
            System.out.println(DisplayUtils.THICK);
            System.out.println("  SELECT TOPPINGS  (free)");
            System.out.println(DisplayUtils.THICK);
            System.out.println();
            System.out.println("  1)  Lettuce");
            System.out.println("  2)  Cilantro");
            System.out.println("  3)  Onions");
            System.out.println("  4)  Tomatoes");
            System.out.println("  5)  Jalapeños");
            System.out.println("  6)  Radishes");
            System.out.println("  7)  Pico de Gallo");
            System.out.println("  8)  Guacamole");
            System.out.println("  9)  Corn");
            System.out.println("  0)  Done with toppings");
            System.out.println(DisplayUtils.THIN);
            DisplayUtils.showLivePrice(taco.getPrice());
            System.out.print("\n  Select an option: ");

            String choice = sc.nextLine().trim();

            if (choice.equals("0")) {
                selecting = false;
            } else {
                try {
                    int index = Integer.parseInt(choice) - 1;
                    if (index >= 0 && index < REGULARS.length) {
                        taco.addTopping(new Topping(REGULARS[index]));
                        DisplayUtils.animateItemAdded(REGULARS[index]);
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
            DisplayUtils.clearScreen(5);
            System.out.println();
            System.out.println(DisplayUtils.THICK);
            System.out.println("  SELECT SAUCES  (free)");
            System.out.println(DisplayUtils.THICK);
            System.out.println();
            System.out.println("  1)  Salsa Verde");
            System.out.println("  2)  Salsa Roja");
            System.out.println("  3)  Chipotle");
            System.out.println("  4)  Habanero");
            System.out.println("  5)  Mild");
            System.out.println("  6)  Extra Hot");
            System.out.println("  0)  Done with sauces");
            System.out.println(DisplayUtils.THIN);
            System.out.print("\n  Select an option: ");

            String choice = sc.nextLine().trim();

            if (choice.equals("0")) {
                selecting = false;
            } else {
                try {
                    int index = Integer.parseInt(choice) - 1;
                    if (index >= 0 && index < SAUCES.length) {
                        taco.addTopping(new Topping(SAUCES[index]));
                        DisplayUtils.animateItemAdded(SAUCES[index]);
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
            DisplayUtils.clearScreen(5);
            System.out.println();
            System.out.println(DisplayUtils.THICK);
            System.out.println("  SELECT SIDES  (free)");
            System.out.println(DisplayUtils.THICK);
            System.out.println();
            System.out.println("  1)  Lime Wedges");
            System.out.println("  2)  Crema");
            System.out.println("  0)  Done with sides");
            System.out.println(DisplayUtils.THIN);
            System.out.print("\n  Select an option: ");

            String choice = sc.nextLine().trim();

            if (choice.equals("0")) {
                selecting = false;
            } else {
                try {
                    int index = Integer.parseInt(choice) - 1;
                    if (index >= 0 && index < SIDES.length) {
                        taco.addTopping(new Topping(SIDES[index]));
                        DisplayUtils.animateItemAdded(SIDES[index]);
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
        DisplayUtils.clearScreen(5);
        System.out.println();
        System.out.println(DisplayUtils.THICK);
        System.out.println("  COVERED IN SALSA & QUESO?");
        System.out.println(DisplayUtils.THICK);
        System.out.println();
        System.out.println("  1)  Yes — smother it!");
        System.out.println("  2)  No thanks");
        System.out.println(DisplayUtils.THIN);
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


    private void displayPreview(Taco taco) {
        System.out.println();
        System.out.println(DisplayUtils.THIN);
        System.out.println("  TACO PREVIEW");
        System.out.println(DisplayUtils.THIN);
        System.out.println();
        System.out.println(taco.formatDisplay());
        System.out.println();
    }


    private void displayBanner(String text) {
        System.out.println();
        System.out.println(DisplayUtils.THICK);
        System.out.println(" STACKS & SALSA 🌯 |  LET'S BUILD A TACO ");
        System.out.println("  Handcrafted  •  Fresh  •  Made your way");
        System.out.println(DisplayUtils.THICK);
        System.out.println();
    }
}
