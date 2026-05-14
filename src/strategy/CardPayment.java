package strategy;

/**
 *
 * @author Jory
 */

// Class for card payment
public class CardPayment implements PaymentStrategy {

    // Store card number
    private String cardNumber;

    // Constructor to initialize card number
    public CardPayment(String cardNumber) {
        this.cardNumber = cardNumber;

    }

    // Process payment using card
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " SAR using Card.");
        System.out.println("Card Number: " + cardNumber);
    }

}
