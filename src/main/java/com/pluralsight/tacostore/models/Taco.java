package com.pluralsight.tacostore.models;

import com.pluralsight.tacostore.enums.ShellType;
import com.pluralsight.tacostore.enums.TacoSize;
import com.pluralsight.tacostore.interfaces.Customizable;
import com.pluralsight.tacostore.interfaces.OrderItem;
import com.pluralsight.tacostore.interfaces.Printable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Taco implements OrderItem, Printable, Customizable {

private TacoSize size;
private ShellType shell;
private List<Topping> toppings;
private boolean covered;

    public Taco(TacoSize size, ShellType shell) {
        this.size = size;
        this.shell = shell;
        this.toppings = new ArrayList<>();
        this.covered = false;
    }



    @Override
    public String getDescription() {
        int toppingCount = toppings.size();
        String toppingText = toppingCount == 1 ? "1 topping" : toppingCount + " toppings";

        return String.format("[ %s ] %s • %s • $%.2f",
        this.size.getItemName().toUpperCase(), this.shell.getItemName(), toppingText, getPrice());
    }

    @Override
    public String formatDisplay() {
        String divider  = "╠══════════════════════════════════════╣";
        String top      = "╔══════════════════════════════════════╗";
        String bottom   = "╚══════════════════════════════════════╝";

        String header = row(this.size.getItemName().toUpperCase() + "  |  " + this.shell.getItemName());
        String coveredSection = row("Covered in salsa & queso: " + (this.covered ? "Yes ✔️" : "No ❌"));
        String priceLine = row(String.format("Total:  $%.2f", getPrice()));

        String toppingSection = this.toppings.isEmpty() ? row("No toppings were added")
                : this.toppings.stream()
                .map(topping ->
                        row("+ " + topping.formatDisplay()))
                            .collect(Collectors.joining("\n"));

        return String.join("\n", top, header, divider, toppingSection, divider, coveredSection, priceLine, bottom);
    }

    private String row(String text) {
        return  String.format("║  %-36s║", text);
    }

    @Override
    public String formatReceipt() {
        String header = String.format(" >> %s  (%s)%n", this.size.getItemName().toUpperCase(), this.shell.getItemName());

        String toppingLines = this.toppings.isEmpty() ? "    (no toppings)\n" : this.toppings.stream()
                .map(topping -> "     " + topping.formatReceipt() + "\n")
                .collect(Collectors.joining());

        String footer =  String.format("    Covered: %s%n     Subtotal: $%.2f%n", this.covered ? "Yes" : "No", getPrice());
        return header + toppingLines + footer ;
    }

    @Override
    public void addTopping(Topping t) {
        this.toppings.add(t);
    }

    @Override
    public boolean removeTopping(String toppingName) {
        return this.toppings.removeIf(topping -> topping.getName()
                .equalsIgnoreCase(toppingName));
    }

    @Override
    public List<Topping> getTopping() {
        return List.of();
    }

    @Override
    public double getPrice() {
        double totalPrice = this.size.getBasePrice();
        for(Topping topping : this.toppings){
            totalPrice += topping.getPrice(this.size);
        }

        return totalPrice;
    }

    public TacoSize getSize() {
        return size;
    }

    public void setSize(TacoSize size) {
        this.size = size;
    }

    public ShellType getShell() {
        return shell;
    }

    public void setShell(ShellType shell) {
        this.shell = shell;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    public void setToppings(List<Topping> toppings) {
        this.toppings = toppings;
    }

    public boolean isCovered() {
        return covered;
    }

    public void setCovered(boolean covered) {
        this.covered = covered;
    }

    @Override
    public String toString(){
        return getDescription();
    }
}
