package Main;
import factory.Food;
import factory.FoodFactory;
import java.util.Scanner;

/**
 * Temporary Main to test the Factory pattern in isolation.
 * This will be replaced by the full Main.java once all parts are merged.
 *
 * @author shaha
 */

public class FoodDeliverySystem {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("===== FOOD DELIVERY SYSTEM =====\n");

        System.out.println("Available items:");
        System.out.println("  1. Pizza  - 25.0 SAR");
        System.out.println("  2. Burger - 20.0 SAR");

        System.out.print("\nEnter food name (pizza / burger): ");
        String input = scanner.nextLine();

        Food food = FoodFactory.createFood(input);

        if (food == null) {
            System.out.println("Sorry, \"" + input + "\" is not on the menu.");
        } else {
            System.out.print("Enter quantity: ");
            int quantity = scanner.nextInt();

            double total = food.getPrice() * quantity;

            System.out.println("\n===== YOUR ORDER =====");
            System.out.println("Item:     " + food.getDescription());
            System.out.println("Quantity: " + quantity);
            System.out.println("Price:    " + food.getPrice() + " SAR x " + quantity);
            System.out.println("Total:    " + total + " SAR");
            System.out.println("======================");
        }

        scanner.close();
    }
}