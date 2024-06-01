package Code;

import java.util.ArrayList;
import user.*;
import food.*;

public class SystemState {
    protected ArrayList<User> activeMembers;
    protected ArrayList<User> deactiveMembers;
    protected ArrayList<Order> activeOrders;
    protected ArrayList<Order> completedOrders;
    private static Boolean run = true;
    private Meal newMeal;
    protected int userLoggedIn = 0;
    private Restaurant r;
    private Customer c;
    private Courier d;
    private Manager m;

    public SystemState() {
        this.activeMembers = new ArrayList<>() ;
        this.deactiveMembers = new ArrayList<>();
        this.activeOrders = new ArrayList<>();
        this.completedOrders = new ArrayList<>();
        this.newMeal = null;
        this.r = null;
        this.c = null;
        this.d = null;
        this.m = null;
    }

    // Getters and setters for the state variables
    public ArrayList<User> getActiveMembers() { return activeMembers; }
    public ArrayList<User> getDeactiveMembers() { return deactiveMembers; }
    public void addActiveOrder(Order o) { this.activeOrders.add(o);}
    public void addCompletedOrder (Order o) {
    	this.activeOrders.remove(o);
    	this.completedOrders.add(o);
    }
    public void setActiveMembers(ArrayList<User> activeMembers) { this.activeMembers = activeMembers; }
    public ArrayList<Order> getActiveOrders(){ return this.activeOrders;}
    public Boolean getRun() { return run; }
    public void setRun(Boolean run) { this.run = run; }
    public Meal getNewMeal() { return newMeal; }
    public void setNewMeal(Meal newMeal, boolean set) { this.newMeal = set ? newMeal : null; }
    public int getUserLoggedIn() { return userLoggedIn; }
    public void setUserLoggedIn(int userLoggedIn) { this.userLoggedIn = userLoggedIn; }
    public Restaurant getR() { return r; }
    public void setR(Restaurant r) { this.r = r; }
    public Customer getC() { return c; }
    public void setC(Customer c) { this.c = c; }
    public Courier getD() { return d; }
    public void setD(Courier d) { this.d = d; }
    public Manager getM() { return m; }
    public void setM(Manager m) { this.m = m; }

    // Exit loop
    public void exitLoop() {
        run = false;
    }

    // Show Restaurants
    public void showRestaurants() {
        for (User user : getActiveMembers()) {
            if (user.getUserType().equalsIgnoreCase("Restaurant")) {
                System.out.println(user.getName());
            }
        }
    }

    // Show Customers
    protected void showCustomers() {
        for (User user : getActiveMembers()) {
            if (user.getUserType().equalsIgnoreCase("Customer")) {
                System.out.println(user.getName());
            }
        }
    }

    // Show Couriers
    protected void showCouriers() {
        for (User user : getActiveMembers()) {
            if (user.getUserType().equalsIgnoreCase("Courier")) {
                System.out.println(user.getName());
            }
        }
    }
    
    public ArrayList<Courier> getAvailableCourier(){ //MAKE THIS BASED ON DELIVERY POLICY
    	ArrayList<Courier> available = new ArrayList<>();
    	Courier c = null;
    	for (User user : getActiveMembers()) {
            if (user.getUserType().equalsIgnoreCase("Courier")) {
            	c = (Courier) user;
            	if(c.available()) {
            		available.add(c);
            	}
            }
        }
    	bubbleSortCouriersByDeliveries(available);
    	return available;
    }

    // Bubble Sort Couriers By Deliveries
    public void bubbleSortCouriersByDeliveries(ArrayList<Courier> couriers) {
        int n = couriers.size();
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (couriers.get(j).getOrderCount() < couriers.get(j + 1).getOrderCount()) {
                    Courier temp = couriers.get(j);
                    couriers.set(j, couriers.get(j + 1));
                    couriers.set(j + 1, temp);
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    // Print Commands
    public void printCommands() {
        switch (userLoggedIn) {
            case 0:
                System.out.println("Commands Available for Anyone:");
                System.out.println("1. login <username> <password>");
                System.out.println("2. logout <>");
                System.out.println("3. runTest <testScenario-file>");
                System.out.println("4. help <>");
                System.out.println("5. end <>");
                break;
            case 1:
                System.out.println("Commands Available for Managers:");
                System.out.println("1. setDeliveryPolicy <delPolicyName>");
                System.out.println("2. setProfitPolicy <ProfitPolicyName>");
                System.out.println("3. associateCard <userName> <cardType>");
                System.out.println("4. showCourierDeliveries <>");
                System.out.println("5. showRestaurantTop <>");
                System.out.println("6. showCustomers <>");
                System.out.println("7. showMenuItem <restaurant-name>");
                System.out.println("8. showTotalProfit <>");
                System.out.println("9. showTotalProfit <startDate> <endDate>");
                System.out.println("10. registerRestaurant <name> <address> <username> <password>");
                System.out.println("11. registerCustomer <firstName> <lastName> <username> <address> <password>");
                System.out.println("12. registerCourier <firstName> <lastName> <username> <position> <password>");
                break;
            case 2:
                System.out.println("Commands Available for Restaurants:");
                System.out.println("1. findDeliverer <orderName>");
                System.out.println("2. addDishRestauarantMenu <dishName> <dishCategory> <foodCategory> <unitPrice>");
                System.out.println("3. createMeal <mealName>");
                System.out.println("4. addDish2Meal <dishName> <mealName>");
                System.out.println("5. showMeal <mealName>");
                System.out.println("6. saveMeal <mealName>");
                System.out.println("7. removeFromSpecialOffer <mealName>");
                System.out.println("8. setSpecialOffer <mealName>");
                System.out.println("9. setDiscountPercentage <discount-Factor>");
                break;
            case 3:
                System.out.println("Commands Available for Customers:");
                System.out.println("1. createOrder <restaurantName> <orderName>");
                System.out.println("2. addItem2Order <orderName> <itemName>");
                System.out.println("3. endOrder <orderName> <date>");
                System.out.println("4. registerFidelity <option> -> option = register or unregister");
                System.out.println("5. accountInformation <>"); // Should give user information regarding order history, fidelity card, etc.
                System.out.println("6. setNotification <option> -> option = register or unregister");
                System.out.println("7. showRestaurants <>");
                break;
            case 4:
                System.out.println("Commands Available for Couriers:");
                System.out.println("1. onDuty <username>");
                System.out.println("2. offDuty <username>");
                System.out.println("3. acceptDelivery <response> -> response = true or false");
                System.out.println("4. finishDelivery <response> -> response = true");
                System.out.println("5. getOrderPos <>");
                System.out.println("6. updatePosition <latitude> <longitude>");
                
                break;
            default:
                System.out.println("Invalid user type");
                break;
        }
    }
}