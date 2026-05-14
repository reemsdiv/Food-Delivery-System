package Main;

import factory.AbstractFactory;
import factory.Drink;
import factory.FactoryProducer;
import factory.Food;
import Decorator.Cheese;
import Decorator.Fries;
import Decorator.ExtraSauce;
import Observer.DeliveryObserver;
import Observer.KitchenObserver;
import Observer.OrderSubject;
import java.util.Scanner;
import strategy.CardPayment;
import strategy.CashPayment;
import strategy.PaymentContext;

/**
 * Food Delivery System --- Main Entry Point
 * Demonstrates:
 *   - Abstract Factory Pattern  (FoodFactory / DrinkFactory)
 *   - Decorator Pattern         (Cheese / Fries / ExtraSauce)
 *   - Strategy Pattern          (CashPayment / CardPayment)
 *   - Observer Pattern          (KitchenObserver / DeliveryObserver)
 */
public class FoodDeliverySystem {

    // --- Formatting helpers ---------------------------------------------------

    private static final int    WIDTH       = 70; 
    private static final String DIVIDER     = "+" + "=".repeat(WIDTH - 2) + "+";
    private static final String LINE        = "+" + "-".repeat(WIDTH - 2) + "+";

    private static void printHeader(String title) {
        String label = " " + title + " ";
        int totalDashes = WIDTH - label.length();
        int leftDashes = totalDashes / 2;
        int rightDashes = totalDashes - leftDashes;
        
        System.out.println("-".repeat(leftDashes) + label + "-".repeat(rightDashes));
    }

    /** Print a padded line inside the receipt box. */
    private static void boxLine(String text) {
        int space = WIDTH - 2 - text.length();
        int left  = Math.max(space / 2, 0);
        int right = Math.max(space - left, 0);
        System.out.println("|" + " ".repeat(left) + text + " ".repeat(right) + "|");
    }

    private static void boxRow(String key, String value) {
        int gap = WIDTH - 4 - key.length() - value.length();
        if (gap < 1) gap = 1;
        System.out.println("| " + key + " ".repeat(gap) + value + " |");
    }

    // --- Input helpers -------------------------------------------------------

    private static String readLine(Scanner sc, String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = sc.nextLine().trim();
            if (input.isEmpty()) System.out.println("  [!] Input cannot be empty. Try again.");
        } while (input.isEmpty());
        return input;
    }

    private static int readPositiveInt(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();
            try {
                int val = Integer.parseInt(line);
                if (val > 0) return val;
                System.out.println("  [!] Please enter a number greater than 0.");
            } catch (NumberFormatException e) {
                System.out.println("  [!] Invalid number. Try again.");
            }
        }
    }

    private static int readIntInRange(Scanner sc, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();
            try {
                int val = Integer.parseInt(line);
                if (val >= min && val <= max) return val;
                System.out.printf("  [!] Please enter a number between %d and %d.%n", min, max);
            } catch (NumberFormatException e) {
                System.out.println("  [!] Invalid number. Try again.");
            }
        }
    }

    private static boolean readYesNo(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String ans = sc.nextLine().trim().toLowerCase();
            if (ans.equals("yes") || ans.equals("y")) return true;
            if (ans.equals("no")  || ans.equals("n"))  return false;
            System.out.println("  [!] Please enter yes or no.");
        }
    }

    /** Validates that a string contains only digits. */
    private static boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) return false;
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

    // Main 

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Welcome 
        System.out.println();
        System.out.println(DIVIDER);
        boxLine("FOOD DELIVERY SYSTEM");
        boxLine("Fast. Fresh. Delivered.");
        System.out.println(DIVIDER);
        System.out.println();

        // Abstract Factory setup 
        AbstractFactory foodFactory  = FactoryProducer.getFactory("food");
        AbstractFactory drinkFactory = FactoryProducer.getFactory("drink");

        // Food selection 
        printHeader("FOOD MENU");
        System.out.println("  1. Pizza     - 25.00 SAR");
        System.out.println("  2. Burger    - 20.00 SAR");
        System.out.println("-".repeat(WIDTH));

        Food food = null;
        while (food == null) {
            int pick = readIntInRange(scanner, "Select food (1-2): ", 1, 2);
            String foodItem = (pick == 1) ? "pizza" : "burger";
            food = foodFactory.createFood(foodItem);
        }

        int foodQty = readPositiveInt(scanner, "Quantity: ");

        // Decorator: extras 
        System.out.println();
        printHeader("EXTRAS");
        System.out.println("  Cheese      +5.00 SAR");
        System.out.println("  Fries       +7.00 SAR");
        System.out.println("  Extra Sauce +3.00 SAR");
        System.out.println("-".repeat(WIDTH));

        if (readYesNo(scanner, "Add Cheese?      (yes/no): "))     food = new Cheese(food);
        if (readYesNo(scanner, "Add Fries?       (yes/no): "))     food = new Fries(food);
        if (readYesNo(scanner, "Add Extra Sauce? (yes/no): "))     food = new ExtraSauce(food);

        // Drink selection 
        System.out.println();
        printHeader("DRINK MENU");
        System.out.println("  1. Juice    - 8.00 SAR");
        System.out.println("  2. Water    - 3.00 SAR");
        System.out.println("-".repeat(WIDTH));

        Drink drink = null;
        while (drink == null) {
            int pick = readIntInRange(scanner, "Select drink (1-2): ", 1, 2);
            String drinkItem = (pick == 1) ? "juice" : "water";
            drink = drinkFactory.createDrink(drinkItem);
        }

        int drinkQty = readPositiveInt(scanner, "Quantity: ");

        // Totals 
        double foodTotal  = food.getPrice()  * foodQty;
        double drinkTotal = drink.getPrice() * drinkQty;
        double grandTotal = foodTotal + drinkTotal;

        // Payment  
        System.out.println();
        printHeader("PAYMENT");
        System.out.println("  1. Cash");
        System.out.println("  2. Card");
        System.out.println("-".repeat(WIDTH));

        PaymentContext paymentContext = new PaymentContext();
        int payChoice = readIntInRange(scanner, "Select payment method (1-2): ", 1, 2);

        if (payChoice == 1) {
            paymentContext.setPaymentStrategy(new CashPayment());
        } else {
            String cardNumber;
            while (true) {
                System.out.print("Enter card number: ");
                cardNumber = scanner.nextLine().trim();
                if (cardNumber.isEmpty()) {
                    System.out.println("  [!] Card number cannot be empty.");
                } else if (!isNumeric(cardNumber)) {
                    System.out.println("  [!] Error: Card number must contain only digits. Please do not enter letters.");
                } else {
                    break;
                }
            }
            paymentContext.setPaymentStrategy(new CardPayment(cardNumber));
        }

        System.out.println();
        paymentContext.processPayment(grandTotal);

        // Receipt 
        System.out.println();
        System.out.println(DIVIDER);
        boxLine("ORDER RECEIPT");
        System.out.println(LINE);
        boxRow(food.getDescription(), String.format("x%d", foodQty));
        boxRow("  @ " + String.format("%.2f", food.getPrice()) + " SAR each",
               String.format("%.2f SAR", foodTotal));
        System.out.println(LINE);
        boxRow(drink.getDescription(), String.format("x%d", drinkQty));
        boxRow("  @ " + String.format("%.2f", drink.getPrice()) + " SAR each",
               String.format("%.2f SAR", drinkTotal));
        System.out.println(LINE);
        boxRow("TOTAL", String.format("%.2f SAR", grandTotal));
        System.out.println(DIVIDER);

        // Order Tracking 
        String orderDetails = foodQty + "x " + food.getDescription()
                            + " + "
                            + drinkQty + "x " + drink.getDescription();

        System.out.println();
        printHeader("ORDER TRACKING");

        OrderSubject orderSubject = new OrderSubject();
        new KitchenObserver(orderSubject);
        new DeliveryObserver(orderSubject);

        String[] statuses = {"ORDER_PLACED", "PREPARING", "READY", "DELIVERED"};
        for (String status : statuses) {
            orderSubject.setOrderStatus(orderDetails, status);
            System.out.println();
        }

        System.out.println("-".repeat(WIDTH));
        System.out.println("  Thank you for your order! Enjoy your meal.");
        System.out.println("-".repeat(WIDTH));
        System.out.println();

        scanner.close();
    }
}
