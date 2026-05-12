package Main;
import factory.AbstractFactory;
import factory.Drink;
import factory.FactoryProducer;
import factory.Food;
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
 
        // --- Step 1: Choose category ---
        System.out.println("What would you like to order?");
        System.out.println("  1. Food  (pizza, burger)");
        System.out.println("  2. Drink (juice, water)");
        System.out.print("\nEnter category (food / drink): ");
        String category = scanner.nextLine();
 
        AbstractFactory factory = FactoryProducer.getFactory(category);
 
        if (factory == null) {
            System.out.println("Sorry, \"" + category + "\" is not a valid category.");
            scanner.close();
            return;
        }
 
        // --- Step 2: Choose item ---
        System.out.print("Enter item name: ");
        String item = scanner.nextLine();
 
        // --- Step 3: Choose quantity ---
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
 
        // --- Step 4: Create and display ---
        System.out.println("\n===== YOUR ORDER =====");
 
        if (category.trim().equalsIgnoreCase("food")) {
 
            Food food = factory.createFood(item);
 
            if (food == null) {
                System.out.println("Sorry, \"" + item + "\" is not on the menu.");
            } else {
                double total = food.getPrice() * quantity;
                System.out.println("Item:     " + food.getDescription());
                System.out.println("Quantity: " + quantity);
                System.out.println("Price:    " + food.getPrice() + " SAR x " + quantity);
                System.out.println("Total:    " + total + " SAR");
            }
 
        } else {
 
            Drink drink = factory.createDrink(item);
 
            if (drink == null) {
                System.out.println("Sorry, \"" + item + "\" is not on the menu.");
            } else {
                double total = drink.getPrice() * quantity;
                System.out.println("Item:     " + drink.getDescription());
                System.out.println("Quantity: " + quantity);
                System.out.println("Price:    " + drink.getPrice() + " SAR x " + quantity);
                System.out.println("Total:    " + total + " SAR");
            }
        }
 
        System.out.println("======================");
        scanner.close();
    }
}