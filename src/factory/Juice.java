package factory;

/**
 *
 * @author shaha
 */
public class Juice implements Drink {
 
    @Override
    public String getDescription() {
        return "Juice";
    }
 
    @Override
    public double getPrice() {
        return 8.0;
    }
}
 