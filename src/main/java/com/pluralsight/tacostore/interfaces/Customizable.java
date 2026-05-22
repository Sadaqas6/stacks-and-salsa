package com.pluralsight.tacostore.interfaces;

import com.pluralsight.tacostore.models.Topping;
import java.util.List;

public interface Customizable {

    void addTopping(Topping t);
    boolean removeTopping(String toppingName);

    List<Topping> getTopping();  // returns list of toppings on item


}
