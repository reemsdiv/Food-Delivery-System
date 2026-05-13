
package Decorator;

import factory.Food;
public abstract class FoodDecorator implements Food {
      protected Food decoratedFood;

    public FoodDecorator(Food decoratedFood) {
        this.decoratedFood = decoratedFood;
    }

    @Override
    public String getDescription() {
        return decoratedFood.getDescription();
    }

    @Override
    public double getPrice() {
        return decoratedFood.getPrice();
    }
    
    
}
