package factory;

/**
 *
 * @author shaha
 */
public class Water implements Drink {
 
    @Override
    public String getDescription() {
        return "Water";
    }
 
    @Override
    public double getPrice() {
        return 3.0;
    }
}
 