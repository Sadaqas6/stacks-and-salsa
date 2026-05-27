package com.pluralsight.tacostore.models;

import com.pluralsight.tacostore.enums.ShellType;
import com.pluralsight.tacostore.enums.TacoSize;

public class BurritoStack extends Taco {

    public BurritoStack(){
        super(TacoSize.BURRITO, ShellType.FLOUR);
        this.addTopping(new Topping("Pollo", true, true));
        this.addTopping(new Topping("Queso Fresco", true, false));
        this.addTopping(new Topping("Guacamole"));
        this.addTopping(new Topping("Corn"));
        this.setCovered(true);
    }
}
