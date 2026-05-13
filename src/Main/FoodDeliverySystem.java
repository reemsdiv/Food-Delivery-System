package Main;
import factory.AbstractFactory;
import factory.Drink;
import factory.FactoryProducer;
import factory.Food;
import Decorator.Cheese;
import Decorator.Fries;
import Decorator.ExtraSauce;
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

        // Create factories using Abstract Factory Pattern
        AbstractFactory foodFactory = FactoryProducer.getFactory("food");
        AbstractFactory drinkFactory = FactoryProducer.getFactory("drink");

        // ---------------- Choose food item ----------------
        System.out.print("Enter food item (pizza / burger): ");
        String foodItem = scanner.nextLine();

        System.out.print("Enter food quantity: ");
        int foodQuantity = scanner.nextInt();
        scanner.nextLine(); // Clear the input buffer

        // Create food object using FoodFactory
        Food food = foodFactory.createFood(foodItem);

        // Validate food item
        if (food == null) {
            System.out.println("Sorry, \"" + foodItem + "\" is not on the menu.");
            scanner.close();
            return;
        }

        // ---------------- Add food extras ----------------
        // Apply Decorator Pattern to add extras without changing the original food class

        System.out.print("Add cheese? (yes/no): ");
        String cheese = scanner.nextLine();

        if (cheese.equalsIgnoreCase("yes")) {
            food = new Cheese(food);
        }

        System.out.print("Add fries? (yes/no): ");
        String fries = scanner.nextLine();

        if (fries.equalsIgnoreCase("yes")) {
            food = new Fries(food);
        }

        System.out.print("Add extra sauce? (yes/no): ");
        String sauce = scanner.nextLine();

        if (sauce.equalsIgnoreCase("yes")) {
            food = new ExtraSauce(food);
        }

        // ---------------- Choose drink item ----------------
        System.out.print("Enter drink item (juice / water): ");
        String drinkItem = scanner.nextLine();

        System.out.print("Enter drink quantity: ");
        int drinkQuantity = scanner.nextInt();

        // Create drink object using DrinkFactory
        Drink drink = drinkFactory.createDrink(drinkItem);

        // Validate drink item
        if (drink == null) {
            System.out.println("Sorry, \"" + drinkItem + "\" is not on the menu.");
            scanner.close();
            return;
        }

        // ---------------- Calculate totals ----------------
        double foodTotal = food.getPrice() * foodQuantity;
        double drinkTotal = drink.getPrice() * drinkQuantity;
        double finalTotal = foodTotal + drinkTotal;

        // ---------------- Display final order ----------------
        System.out.println("\n===== YOUR ORDER =====");

        System.out.println("Food:     " + food.getDescription());
        System.out.println("Quantity: " + foodQuantity);
        System.out.println("Price:    " + food.getPrice() + " SAR x " + foodQuantity);
        System.out.println("Subtotal: " + foodTotal + " SAR");

        System.out.println();

        System.out.println("Drink:    " + drink.getDescription());
        System.out.println("Quantity: " + drinkQuantity);
        System.out.println("Price:    " + drink.getPrice() + " SAR x " + drinkQuantity);
        System.out.println("Subtotal: " + drinkTotal + " SAR");

        System.out.println();

        System.out.println("Total:    " + finalTotal + " SAR");
        System.out.println("======================");

        scanner.close();
    }
    }
