package user;

import java.util.ArrayList;
import java.util.Random;

import food.*;

/**
 * Represents a restaurant, inheriting attributes from the User class.
 */
public class Restaurant extends User {

    // Variables specific to Restaurant
    private Location location;
    private int completedOrders;
    private ArrayList<Courier> availableCouriers;
    private Menu menu;
    private Double discountFactor;

    /**
     * Constructs a Restaurant object with the specified attributes.
     * 
     * @param username the username of the restaurant
     * @param password the password of the restaurant
     * @param userType the type of user (e.g., restaurant)
     * @param name     the name of the restaurant
     * @param location the location of the restaurant
     */
    public Restaurant(String username, String password, String userType, String name, Location location) {
        super(username, password, userType, name);
        this.location = location;
        this.discountFactor = 10.0; // Default discount factor
        this.availableCouriers = new ArrayList<>();
        this.menu = new Menu(); // Initialize with an empty menu
        this.setCompletedOrders(0);
    }

    // Getters and setters
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Double getDiscountFactor() {
        return discountFactor;
    }

    public void setDiscountFactor(Double newDiscount) {
        menu.updateSpecialPrice(this.discountFactor, newDiscount);
        this.discountFactor = newDiscount;
    }

    public ArrayList<Courier> getAvailableCouriers() {
        return new ArrayList<>(availableCouriers); // Return a copy to maintain encapsulation
    }

    public void addAvailableCourier(Courier courier) {
        if (!availableCouriers.contains(courier)) {
            availableCouriers.add(courier);
        }
    }

    public void removeAvailableCourier(Courier courier) {
        availableCouriers.remove(courier);
    }

    public Menu getMenu() {
        return menu;
    }

    /**
     * Retrieves the menu for a given customer based on their fidelity status.
     * 
     * @param c the customer for whom the menu is retrieved
     */
    public void getMenu(Customer c) {
        String fidelity = c.getFidelity();
        if (fidelity.equalsIgnoreCase("Points") || fidelity == null) {
            System.out.println("****** MENU ******\n");
            System.out.println("*     Dishes     *");
            for (Dishes d : this.getMenu().getAvailDishes()) {
                System.out.println(d);
            }
            System.out.println("\n*      Meals     *");
            for (Meal m : this.getMenu().getAvailMeals()) {
                if (!m.getDeal()) {
                    System.out.println(m);
                }
            }
        } else if (fidelity.equalsIgnoreCase("Basic")) {
            System.out.println(this.menu);
        } else if (fidelity.equalsIgnoreCase("Lottery")) {
            System.out.println("****** MENU ******\n");
            System.out.println("*     Dishes     *");
            for (Dishes d : this.getMenu().getAvailDishes()) {
                System.out.println(d);
            }
            System.out.println("\n*      Meals     *");
            for (Meal m : this.getMenu().getAvailMeals()) {
                if (!m.getDeal()) {
                    System.out.println(m);
                }
            }
            // Create a Random object
            Random rand = new Random();
            // Generate a random number between 0 and 9
            int randomNumber = rand.nextInt(10);
            if (randomNumber == 2) {
                System.out.println("Your next meal is free!");
                c.setFreeOrder(true);
                return;
            }
            c.setFreeOrder(false);
            return;
        }
    }

    public void addToMenu(Dishes dish) {
        menu.addDish(dish);
    }

    public void addDishToMeal(Meal meal, Dishes dish) {
        menu.addDishToMeal(meal, dish);
    }

    public void addMeal(Meal meal) {
        menu.addMeal(meal);
    }

    public void showMeal(String mealName) {
        menu.showMeal(mealName);
    }

    public int getCompletedOrders() {
        return completedOrders;
    }

    public void setCompletedOrders(int completedOrders) {
        this.completedOrders += completedOrders;
    }

    // Other methods for managing dishes, meals, and special discounts can be added here

    @Override
    public String toString() {
        return "Restaurant Name: " + getName() + ", Completed Orders: " + completedOrders;
    }

}
