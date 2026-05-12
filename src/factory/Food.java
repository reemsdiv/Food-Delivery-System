package factory;

/**
 *
 * @author shaha
 */

/**
 * Food Interface - defines the contract for all food items.
 */
public interface Food {
 
    //Returns a human-readable description of the food item. 
    String getDescription();
 
    //Returns the base price of the food item in SAR. 
    double getPrice();
}