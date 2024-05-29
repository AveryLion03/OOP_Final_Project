package user;

import java.util.ArrayList;
import food.*;
import Code.Location;

public class Restaurant extends User {
    
    // Variables specific to Restaurant
    private Location loc;
    private ArrayList<Customer> visited = new ArrayList<>();
    private ArrayList<Courier> available = new ArrayList<>();
    private Menu menu = new Menu(); // Initialize with an empty menu

    // Constructor
    public Restaurant(String username, String password, String userType, String name, Location loc) {
        super(username, password, userType, name);
        this.loc = loc;
    }

    // Getters and setters
    public Location getLocation() {
        return loc;
    }

    public void setLocation(Location loc) {
        this.loc = loc;
    }

    public ArrayList<Customer> getVisitedCustomers() {
        return new ArrayList<>(visited); // Return a copy to maintain encapsulation
    }

    public void addVisitedCustomer(Customer customer) {
        visited.add(customer);
    }

    public ArrayList<Courier> getAvailableCouriers() {
        return new ArrayList<>(available); // Return a copy to maintain encapsulation
    }

    public void addAvailableCourier(Courier courier) {
        if (!available.contains(courier)) {
            available.add(courier);
        }
    }

    public void removeAvailableCourier(Courier courier) {
        available.remove(courier);
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    // Other methods for managing dishes, meals, and special discounts can be added here
}
