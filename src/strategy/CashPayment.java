package strategy;

/**
 *
 * @author Jory
 */

// Class for cash payment
public class CashPayment implements PaymentStrategy {

    // Process payment using cash
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " SAR using Cash.");

    }

}
