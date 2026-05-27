package com.pluralsight.tacostore.models;

import com.pluralsight.tacostore.enums.ShellType;
import com.pluralsight.tacostore.enums.TacoSize;

public class StreetTaco extends Taco {

    public StreetTaco(){
        super(TacoSize.THREE_TACO, ShellType.CORN);
        this.addTopping(new Topping("Carne Asada", true, true));
        this.addTopping(new Topping("Onions"));
        this.addTopping(new Topping("Cilantro"));
        this.addTopping(new Topping("Salsa Verde"));
        this.addTopping(new Topping("Lime Wedges"));
    }
}
