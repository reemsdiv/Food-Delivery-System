package factory;

/**
 *
 * @author shaha
 */
/**
 * Burger - Concrete Food product.
 * Created by FoodFactory when type "burger" is requested.
 */
public class Burger implements Food {
 
    @Override
    public String getDescription() {
        return "Burger";
    }
 
    @Override
    public double getPrice() {
        return 20.0;
    }
}
 