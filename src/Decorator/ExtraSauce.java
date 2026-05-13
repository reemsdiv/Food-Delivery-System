
package Decorator;

import factory.Food;

public class ExtraSauce extends FoodDecorator {

    public ExtraSauce(Food decoratedFood) {
        super(decoratedFood);
    }

    @Override
    public String getDescription() {
        return decoratedFood.getDescription() + " + Extra Sauce";
    }

    @Override
    public double getPrice() {
        return decoratedFood.getPrice() + 3.0;
    }
    
}
