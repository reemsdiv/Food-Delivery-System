package factory;

/**
 *
 * @author shaha
 */


public class DrinkFactory implements AbstractFactory {

    public Drink createDrink(String type) {

        if (type == null) return null;

        String t = type.trim().toLowerCase();

        if (t.equals("juice"))
            return new Juice();

        if (t.equals("water"))
            return new Water();

        System.out.println("[DrinkFactory] Unknown drink type: \"" + type + "\"");
        return null;
    }

 
    public Food createFood(String type) {
        throw new UnsupportedOperationException("DrinkFactory does not create food. Use FoodFactory.");
    }
}