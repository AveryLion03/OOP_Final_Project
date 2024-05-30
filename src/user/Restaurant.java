package user;

import java.util.ArrayList;
import food.*;
import Code.Location;

public class Restaurant extends User {
    
    // Variables specific to Restaurant
    private Location location;
    private ArrayList<Customer> visitedCustomers;
    private ArrayList<Courier> availableCouriers;
    private Menu menu;
    private Double discountFactor;

    // Constructor
    public Restaurant(String username, String password, String userType, String name, Location location) {
        super(username, password, userType, name);
        this.location = location;
        this.discountFactor = 10.0; // Default discount factor
        this.visitedCustomers = new ArrayList<>();
        this.availableCouriers = new ArrayList<>();
        this.menu = new Menu(); // Initialize with an empty menu
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
    
    public ArrayList<Customer> getVisitedCustomers() {
        return new ArrayList<>(visitedCustomers); // Return a copy to maintain encapsulation
    }

    public void addVisitedCustomer(Customer customer) {
        visitedCustomers.add(customer);
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

    // Other methods for managing dishes, meals, and special discounts can be added here
}
