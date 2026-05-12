package factory;

/**
 *
 * @author shaha
 */
public class FoodFactory {

    public static Food createFood(String type) {

        if (type == null) return null;

        String t = type.trim().toLowerCase();

        if (t.equals("pizza"))
            return new Pizza();

        if (t.equals("burger"))
            return new Burger();

        System.out.println("Unknown food type: \"" + type + "\"");
        return null;
    }
}