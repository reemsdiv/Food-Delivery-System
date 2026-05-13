
package Decorator;

import factory.Food;

public class Cheese extends FoodDecorator {
    
     public Cheese(Food decoratedFood) {
        super(decoratedFood);
    }

    @Override
    public String getDescription() {
        return decoratedFood.getDescription() + " + Cheese";
    }

    @Override
    public double getPrice() {
        return decoratedFood.getPrice() + 5.0;
    }
    
}
