package strategy;

/**
 *
 * @author Jory
 */

// This class uses a payment strategy selected at runtime
public class PaymentContext{
    private PaymentStrategy paymentStrategy;

    // Method to set payment method dynamically
    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    // Method to process payment using selected strategy
    public void processPayment(double amount) {
        // Check if payment method is selected
        if (paymentStrategy == null) {
            System.out.println("No payment method selected.");
            return;
        }

        paymentStrategy.pay(amount);
    }

}