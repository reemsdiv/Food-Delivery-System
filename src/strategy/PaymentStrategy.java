package strategy;

/**
 *
 * @author Jory
 */

// Interface for all payment methods
public interface PaymentStrategy {

    // Method to process payment
    void pay(double amount);

}