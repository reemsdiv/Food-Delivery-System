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
import java.util.ArrayList;
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
        // Width used for formatting menu and receipt output
    private static final int WIDTH = 70;

    // Main receipt/menu divider lines
    private static final String DIVIDER = "+" + "=".repeat(WIDTH - 2) + "+";
    private static final String LINE = "+" + "-".repeat(WIDTH - 2) + "+";

    // Prints centered text inside a box
    private static void boxLine(String text) {
        int space = WIDTH - 2 - text.length();
        int left = Math.max(space / 2, 0);
        int right = Math.max(space - left, 0);

        System.out.println("|" + " ".repeat(left) + text + " ".repeat(right) + "|");
    }

    // Prints a clean boxed section title
    private static void printSection(String title) {
        System.out.println();
        System.out.println(LINE);
        boxLine(title);
        System.out.println(LINE);
    }

    // Prints menu option 
    private static void menuOption(int number, String name, String price) {
    String text;

    // Menu option without price
    if (price == null) {

        text = String.format("  %d. %s",
                number,
                name);
    }

    // Menu option with price
    else {

        text = String.format("  %d. %-28s %10s SAR",
                number,
                name,
                price);
    }

    // Calculate remaining spaces dynamically
    int spaces = WIDTH - text.length() - 2;

    // Print formatted row
    System.out.println("|" + text + " ".repeat(spaces) + "|");
}

    // Prints one row inside the receipt box with left and right values
    private static void boxRow(String key, String value) {
        int gap = WIDTH - 4 - key.length() - value.length();

        if (gap < 1) {
            gap = 1;
        }

        System.out.println("| " + key + " ".repeat(gap) + value + " |");
    }

    // Reads a positive integer from the user
    private static int readPositiveInt(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();

            try {
                int val = Integer.parseInt(line);

                if (val > 0) {
                    return val;
                }

                System.out.println("  [!] Please enter a number greater than 0.");

            } catch (NumberFormatException e) {
                System.out.println("  [!] Invalid number. Try again.");
            }
        }
    }

    // Reads an integer within a specific range
    private static int readIntInRange(Scanner sc, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();

            try {
                int val = Integer.parseInt(line);

                if (val >= min && val <= max) {
                    return val;
                }

                System.out.printf("  [!] Please enter a number between %d and %d.%n", min, max);

            } catch (NumberFormatException e) {
                System.out.println("  [!] Invalid number. Try again.");
            }
        }
    }

    // Reads yes/no input from the user
    private static boolean readYesNo(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String ans = sc.nextLine().trim().toLowerCase();

            if (ans.equals("yes") || ans.equals("y")) {
                return true;
            }

            if (ans.equals("no") || ans.equals("n")) {
                return false;
            }

            System.out.println("  [!] Please enter yes or no.");
        }
    }

    // Checks if a string contains only digits
    private static boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }

        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        // Create scanner object for user input
        Scanner scanner = new Scanner(System.in);

        // Create a cart to store multiple ordered items
        ArrayList<OrderItem> cart = new ArrayList<>();

        // Display welcome message
        System.out.println();
        System.out.println(DIVIDER);
        boxLine("FOOD DELIVERY SYSTEM");
        boxLine("Fast. Fresh. Delivered.");
        System.out.println(DIVIDER);

        // Create factories using Abstract Factory Pattern
        AbstractFactory foodFactory = FactoryProducer.getFactory("food");
        AbstractFactory drinkFactory = FactoryProducer.getFactory("drink");

        // Controls the ordering loop
        boolean ordering = true;

        // Main ordering loop: user can add many food/drink items
        while (ordering) {

            // Display main menu
            printSection("MAIN MENU");
            menuOption(1, "Add Food", null);
            menuOption(2, "Add Drink", null);
            menuOption(3, "Checkout", null);
            System.out.println(LINE);

            // Read user's menu choice
            int option = readIntInRange(scanner, "Select option (1-3): ", 1, 3);

            // Option 1: Add food item
            if (option == 1) {

                // Display food menu
                printSection("FOOD MENU");
                menuOption(1, "Pizza", "25.00");
                menuOption(2, "Burger", "20.00");
                System.out.println(LINE);

                // Read selected food
                int pick = readIntInRange(scanner, "Select food (1-2): ", 1, 2);

                // Convert numeric choice to food name
                String foodItem = (pick == 1) ? "pizza" : "burger";

                // Create food object using Factory Pattern
                Food food = foodFactory.createFood(foodItem);

                // Read food quantity
                int foodQty = readPositiveInt(scanner, "Quantity: ");

                // Display available extras
                printSection("EXTRAS");
                menuOption(1, "Cheese", "+5.00");
                menuOption(2, "Fries", "+7.00");
                menuOption(3, "Extra Sauce", "+3.00");
                System.out.println(LINE);

                // Add cheese using Decorator Pattern
                if (readYesNo(scanner, "Add Cheese? (yes/no): ")) {
                    food = new Cheese(food);
                }

                // Add fries using Decorator Pattern
                if (readYesNo(scanner, "Add Fries? (yes/no): ")) {
                    food = new Fries(food);
                }

                // Add extra sauce using Decorator Pattern
                if (readYesNo(scanner, "Add Extra Sauce? (yes/no): ")) {
                    food = new ExtraSauce(food);
                }

                // Add the final decorated food item to the cart
                cart.add(new OrderItem(
                        food.getDescription(),
                        food.getPrice(),
                        foodQty
                ));

                // Confirm item was added
                System.out.println();
                System.out.println("  [OK] Added to cart: " + food.getDescription() + " x" + foodQty);
            }

            // Option 2: Add drink item
            else if (option == 2) {

                // Display drink menu
                printSection("DRINK MENU");
                menuOption(1, "Juice", "8.00");
                menuOption(2, "Water", "3.00");
                System.out.println(LINE);

                // Read selected drink
                int pick = readIntInRange(scanner, "Select drink (1-2): ", 1, 2);

                // Convert numeric choice to drink name
                String drinkItem = (pick == 1) ? "juice" : "water";

                // Create drink object using Factory Pattern
                Drink drink = drinkFactory.createDrink(drinkItem);

                // Read drink quantity
                int drinkQty = readPositiveInt(scanner, "Quantity: ");

                // Add drink item to the cart
                cart.add(new OrderItem(
                        drink.getDescription(),
                        drink.getPrice(),
                        drinkQty
                ));

                // Confirm item was added
                System.out.println();
                System.out.println("  [OK] Added to cart: " + drink.getDescription() + " x" + drinkQty);
            }

            // Option 3: Checkout
            else if (option == 3) {

                // Prevent checkout if cart is empty
                if (cart.isEmpty()) {
                    System.out.println();
                    System.out.println("  [!] Cart is empty. Please add at least one item.");
                } else {
                    ordering = false;
                }
            }
        }

        // Variable to store total price
        double grandTotal = 0;

        // Print receipt header
        System.out.println();
        System.out.println(DIVIDER);
        boxLine("ORDER RECEIPT");
        System.out.println(LINE);

        // Print each item in the cart
        for (OrderItem item : cart) {

            // Calculate item subtotal
            double subtotal = item.getSubtotal();

            // Add subtotal to grand total
            grandTotal += subtotal;

            // Print item description and quantity
            boxRow(item.getDescription(), "x" + item.getQuantity());

            // Print item price and subtotal
            boxRow(
                    "  @ " + String.format("%.2f", item.getPrice()) + " SAR each",
                    String.format("%.2f SAR", subtotal)
            );

            System.out.println(LINE);
        }

        // Print final total
        boxRow("TOTAL", String.format("%.2f SAR", grandTotal));
        System.out.println(DIVIDER);

        // Display payment section
        printSection("PAYMENT");
        menuOption(1, "Cash", null);
        menuOption(2, "Card", null);
        System.out.println(LINE);

        // Create payment context for Strategy Pattern
        PaymentContext paymentContext = new PaymentContext();

        // Read selected payment method
        int payChoice = readIntInRange(scanner, "Select payment method (1-2): ", 1, 2);

        // Use cash payment strategy
        if (payChoice == 1) {

            paymentContext.setPaymentStrategy(new CashPayment());

        } else {

            String cardNumber;

            // Validate card number input
            while (true) {
                System.out.print("Enter card number: ");
                cardNumber = scanner.nextLine().trim();

                if (cardNumber.isEmpty()) {
                    System.out.println("  [!] Card number cannot be empty.");
                } else if (!isNumeric(cardNumber)) {
                    System.out.println("  [!] Error: Card number must contain only digits.");
                } else {
                    break;
                }
            }

            // Use card payment strategy
            paymentContext.setPaymentStrategy(new CardPayment(cardNumber));
        }

        // Process payment using selected strategy
        System.out.println();
        paymentContext.processPayment(grandTotal);

        // Build order details string for tracking
        String orderDetails = "";

        for (int i = 0; i < cart.size(); i++) {
            OrderItem item = cart.get(i);

            orderDetails += item.getQuantity() + "x " + item.getDescription();

            if (i < cart.size() - 1) {
                orderDetails += " + ";
            }
        }

        // Display order tracking section
        printSection("ORDER TRACKING");

        // Create subject for Observer Pattern
        OrderSubject orderSubject = new OrderSubject();

        // Register kitchen and delivery observers
        new KitchenObserver(orderSubject);
        new DeliveryObserver(orderSubject);

        // Simulate order status updates
        String[] statuses = {"ORDER_PLACED", "PREPARING", "READY", "DELIVERED"};

        // Update order status and notify all observers
        for (String status : statuses) {
            orderSubject.setOrderStatus(orderDetails, status);
            System.out.println();
        }

        // Print closing message
        System.out.println(LINE);
        System.out.println("  Thank you for your order! Enjoy your meal.");
        System.out.println(LINE);
        System.out.println();

        // Close scanner
        scanner.close();
    
    }
}
