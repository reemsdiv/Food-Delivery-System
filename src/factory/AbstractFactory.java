package factory;

/**
 *
 * @author shaha
 */
public interface AbstractFactory {
    Food createFood(String type);
    Drink createDrink(String type);
}
 