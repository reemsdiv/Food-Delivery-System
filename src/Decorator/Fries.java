
package Decorator;

import factory.Food;

public class Fries extends FoodDecorator {

    public Fries(Food decoratedFood) {
        super(decoratedFood);
    }

    @Override
    public String getDescription() {
        return decoratedFood.getDescription() + " + Fries";
    }

    @Override
    public double getPrice() {
        return decoratedFood.getPrice() + 7.0;
    }
}
