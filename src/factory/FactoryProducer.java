package factory;

/**
 *
 * @author shaha
 */

public class FactoryProducer {

    public static AbstractFactory getFactory(String category) {

        if (category == null) return null;

        String c = category.trim().toLowerCase();

        if (c.equals("food"))
            return new FoodFactory();

        if (c.equals("drink"))
            return new DrinkFactory();

        System.out.println("Unknown category: \"" + category + "\"");
        return null;
    }
}
