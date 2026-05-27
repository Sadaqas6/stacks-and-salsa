package com.pluralsight.tacostore.models;

import com.pluralsight.tacostore.enums.ShellType;
import com.pluralsight.tacostore.enums.TacoSize;

public class SuperBurrito extends Taco {

    public SuperBurrito() {
        super(TacoSize.BURRITO, ShellType.FLOUR);
        this.addTopping(new Topping("Carnitas", true, true));
        this.addTopping(new Topping("Cheddar", true, false));
        this.addTopping(new Topping("Pico de Gallo"));
        this.addTopping(new Topping("Lettuce"));
        this.addTopping(new Topping("Tomatoes"));
        this.setCovered(true);
    }
}

