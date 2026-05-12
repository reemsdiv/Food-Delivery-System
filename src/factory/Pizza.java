package factory;

/**
 *
 * @author shaha
 */
/**
 * Pizza - Concrete Food product.
 * Created by FoodFactory when type "pizza" is requested.
 */
public class Pizza implements Food {
 
    @Override
    public String getDescription() {
        return "Pizza";
    }
 
    @Override
    public double getPrice() {
        return 25.0;
    }
}
 