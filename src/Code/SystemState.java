package Code;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import Commands.*;
import user.*;
import food.*;
// Core of our system
public class SystemState {
	// Users of the system and orders
    protected ArrayList<User> activeMembers;
    protected ArrayList<User> deactiveMembers;
    protected ArrayList<Order> activeOrders;
    protected ArrayList<Order> completedOrders;
    // Loop variable
    private static Boolean run = true;
    private Boolean auto;
    //Determines the type of user logged in
    protected int userLoggedIn = 0;
    
    //Current users logged in
    private Restaurant r;
    private Customer c;
    private Courier d;
    private Manager m;
    private Parse foodSys;
    private CommandExecutor commandExecutor;
    private BufferedReader br;
    // Current order being placed
    private Meal newMeal;
    // Pricing information
    protected String[] systemPolicies = {"Fast", "targetProfit_Markup"}; // first index relates to delivery policy, second to profit policy
    private Double markupPercent;
    private Double deliveryCost;
    private Double serviceFee;
    private Double targetProfit;
    
    /**
     * Constructor for SystemState.
     * @param p The Parse object.
     */
    public SystemState(Parse p) {
        this.activeMembers = new ArrayList<>() ;
        this.deactiveMembers = new ArrayList<>();
        this.activeOrders = new ArrayList<>();
        this.completedOrders = new ArrayList<>();
        this.newMeal = null;
        this.foodSys = p;
        // Set these as the default values
        this.setMarkupPercent(1.20);
        this.setDeliveryCost(2.0);
        this.setServiceFee(3.0);
        this.setTargetProfit(2.0);
        this.r = null;
        this.c = null;
        this.d = null;
        this.m = null;
        this.auto = true;
    }

    // Getters and setters for the state variables
    /**
     * Retrieves the list of active members in the system.
     * @return An ArrayList containing active User objects.
     */
    public ArrayList<User> getActiveMembers() { return activeMembers; }

    /**
     * Retrieves the list of inactive members in the system.
     * @return An ArrayList containing inactive User objects.
     */
    public ArrayList<User> getDeactiveMembers() { return deactiveMembers; }

    /**
     * Removes a user from the list of active members and adds them to the list of inactive members.
     * @param u The user to be removed.
     */
    public void removeActiveMember(User u) {
        this.deactiveMembers.add(u);
        this.activeMembers.remove(u);
    }

    /**
     * Reactivates a user by moving them from the list of inactive members to the list of active members.
     * @param u The user to be reactivated.
     */
    public void reactivateMember(User u) {
        this.activeMembers.add(u);
        this.deactiveMembers.remove(u);
    }

    /**
     * Removes a user from the system based on their username and user type.
     * If the user is found and removed successfully, a success message is printed.
     * If the user is not found, an error message is printed.
     * @param username The username of the user to be removed.
     * @param userType The type of the user to be removed.
     */
    public void removeUser(String username, String userType) {
        for (User u : this.getActiveMembers()) {
            if (u.getUsername().equalsIgnoreCase(username) && (u.getUserType().equalsIgnoreCase(userType))) {
                this.activeMembers.remove(u);
                System.out.println("Successfully removed user.");
                return;
            }
        }
        for (User u : this.getDeactiveMembers()) {
            if (u.getUsername().equalsIgnoreCase(username) && (u.getUserType().equalsIgnoreCase(userType))) {
                this.deactiveMembers.remove(u);
                System.out.println("Successfully removed user.");
                return;
            }
        }
        System.out.println("Unable to find user.");
    }

    /**
     * Adds an active order to the system.
     * @param o The order to be added.
     */
    public void addActiveOrder(Order o) { this.activeOrders.add(o); }

    /**
     * Moves a completed order from the list of active orders to the list of completed orders.
     * @param o The completed order to be moved.
     */
    public void addCompletedOrder(Order o) {
        this.activeOrders.remove(o);
        this.completedOrders.add(o);
    }

    /**
     * Sets the delivery policy for the system.
     * @param set The delivery policy to be set.
     */
    public void setDeliveryPolicy(String set) { this.systemPolicies[0] = set; }

    /**
     * Retrieves the delivery policy currently set for the system.
     * @return The current delivery policy.
     */
    public String getDeliveryPolicy() { return this.systemPolicies[0]; }

    /**
     * Sets the pricing policy for the system.
     * @param set The pricing policy to be set.
     */
    public void setPricingPolicy(String set) { this.systemPolicies[1] = set; }

    /**
     * Retrieves the pricing policy currently set for the system.
     * @return The current pricing policy.
     */
    public String getPricingPolicy() { return this.systemPolicies[1]; }

    /**
     * Sets the list of active members in the system.
     * @param activeMembers The list of active members to be set.
     */
    public void setActiveMembers(ArrayList<User> activeMembers) { this.activeMembers = activeMembers; }

    /**
     * Retrieves the list of active orders in the system.
     * @return An ArrayList containing active Order objects.
     */
    public ArrayList<Order> getActiveOrders(){ return this.activeOrders;}

    /**
     * Retrieves the list of completed orders in the system.
     * @return An ArrayList containing completed Order objects.
     */
    public ArrayList<Order> getCompletedOrders(){ return this.completedOrders;}

    /**
     * Retrieves the boolean value indicating whether the system is running or not.
     * @return True if the system is running, false otherwise.
     */
    public Boolean getRun() { return run; }

    /**
     * Sets the boolean value indicating whether the system should run or not.
     * @param run The boolean value to be set.
     */
    public void setRun(Boolean run) { this.run = run; }

    /**
     * Retrieves the current meal being placed in the system.
     * @return The current meal object.
     */
    public Meal getNewMeal() { return newMeal; }

    /**
     * Sets the current meal being placed in the system.
     * @param newMeal The meal to be set.
     * @param set     True to set the meal, false to clear it.
     */
    public void setNewMeal(Meal newMeal, boolean set) { this.newMeal = set ? newMeal : null; }

    /**
     * Retrieves the ID of the user currently logged into the system.
     * @return The ID of the logged-in user.
     */
    public int getUserLoggedIn() { return userLoggedIn; }

    /**
     * Sets the ID of the user currently logged into the system.
     * @param userLoggedIn The ID of the user to be set as logged in.
     */
    public void setUserLoggedIn(int userLoggedIn) { this.userLoggedIn = userLoggedIn; }

    /**
     * Retrieves the restaurant object associated with the system.
     * @return The restaurant object.
     */
    public Restaurant getR() { return r; }

    /**
     * Sets the restaurant object associated with the system.
     * @param r The restaurant object to be set.
     */
    public void setR(Restaurant r) { this.r = r; }

    /**
     * Retrieves the customer object associated with the system.
     * @return The customer object.
     */
    public Customer getC() { return c; }

    /**
     * Sets the customer object associated with the system.
     * @param c The customer object to be set.
     */
    public void setC(Customer c) { this.c = c; }

    /**
     * Retrieves the courier object associated with the system.
     * @return The courier object.
     */
    public Courier getD() { return d; }

    /**
     * Sets the courier object associated with the system.
     * @param d The courier object to be set.
     */
    public void setD(Courier d) { this.d = d; }

    /**
     * Retrieves the manager object associated with the system.
     * @return The manager object.
     */
    public Manager getM() { return m; }

    /**
     * Sets the manager object associated with the system.
     * @param m The manager object to be set.
     */
    public void setM(Manager m) { this.m = m; }
    /**
     * Exits the loop by setting the 'run' flag to false.
     */
    public void exitLoop() {
        run = false;
    }

    /**
     * Retrieves the markup percentage for pricing.
     * @return The markup percentage.
     */
    public Double getMarkupPercent() {
        return markupPercent;
    }
    
    /**
     * Sets the markup percentage for pricing.
     * @param markupPercent The markup percentage to be set.
     */
    public void setMarkupPercent(Double markupPercent) {
        this.markupPercent = markupPercent;
    }

    /**
     * Retrieves the delivery cost.
     * @return The delivery cost.
     */
    public Double getDeliveryCost() {
        return deliveryCost;
    }

    /**
     * Sets the delivery cost.
     * @param deliveryCost The delivery cost to be set.
     */
    public void setDeliveryCost(Double deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    /**
     * Retrieves the service fee.
     * @return The service fee.
     */
    public Double getServiceFee() {
        return serviceFee;
    }

    /**
     * Sets the service fee.
     * @param serviceFee The service fee to be set.
     */
    public void setServiceFee(Double serviceFee) {
        this.serviceFee = serviceFee;
    }

    /**
     * Retrieves the target profit.
     * @return The target profit.
     */
    public Double getTargetProfit() {
        return targetProfit;
    }

    /**
     * Sets the target profit.
     * @param targetProfit The target profit to be set.
     */
    public void setTargetProfit(Double targetProfit) {
        this.targetProfit = targetProfit;
    }

    /**
     * Retrieves the boolean value indicating whether the system operates in automatic mode.
     * @return True if automatic mode is enabled, false otherwise.
     */
    public Boolean getAuto() {
        return this.auto;
    }

    /**
     * Sets the boolean value indicating whether the system should operate in automatic mode.
     * @param auto True to enable automatic mode, false otherwise.
     */
    public void setAuto(Boolean auto) {
        this.auto = auto;
    }

    /**
     * Retrieves the food system parser.
     * @return The food system parser.
     */
    public Parse getFoodSys() {
        return foodSys;
    }

    /**
     * Sets the food system parser.
     * @param foodSys The food system parser to be set.
     */
    public void setFoodSys(Parse foodSys) {
        this.foodSys = foodSys;
    }

    /**
     * Retrieves the command executor.
     * @return The command executor.
     */
    public CommandExecutor getCommandExecutor() {
        return commandExecutor;
    }

    /**
     * Sets the command executor.
     * @param commandExecutor The command executor to be set.
     */
    public void setCommandExecutor(CommandExecutor commandExecutor) {
        this.commandExecutor = commandExecutor;
    }

    /**
     * Retrieves the buffered reader for input.
     * @return The buffered reader.
     */
    public BufferedReader getBr() {
        return br;
    }

    /**
     * Sets the buffered reader for input.
     * @param br The buffered reader to be set.
     */
    public void setBr(BufferedReader br) {
        this.br = br;
    }
    // Show Restaurants
    public void showRestaurants() {
        for (User user : getActiveMembers()) {
            if (user.getUserType().equalsIgnoreCase("Restaurant")) {
                System.out.println("- " + user.getName());
            }
        }
        System.out.printf("%n%n");
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
    
    public ArrayList<Courier> getAvailableCourier() {
        ArrayList<Courier> available = new ArrayList<>();
        ArrayList<Courier> allCouriers = new ArrayList<>();
        
        // Filter available couriers
        for (User user : getActiveMembers()) {
            if (user.getUserType().equalsIgnoreCase("Courier")) {
                Courier courier = (Courier) user;
                if (courier.available()) {
                    allCouriers.add(courier);
                }
            }
        }
        
        if (allCouriers.isEmpty()) {
            // No couriers are available right now.
            return null;
        }

        // Sort couriers based on the delivery policy
        switch (getDeliveryPolicy()) {
            case "Fast":
                // Sort by shortest distance to cover
                // Collections.sort(allCouriers, (c1, c2) -> Double.compare(c1.getDistanceToRestaurant(), c2.getDistanceToRestaurant()));
                break;
            case "Fair":
                // Sort by least number of completed deliveries
                // Collections.sort(allCouriers, Comparator.comparingInt(Courier::getCompletedDeliveries));
                break;
            default:
                // Default sorting
                break;
        }

        // Allocate orders to the sorted couriers
        // Add all couriers to available list
        available.addAll(allCouriers);

        return available;
    }


    /**
     * Bubble Sort Couriers By Deliveries
     * @param couriers The array list of courier users.
     */
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
    /**
     * Bubble Sort Restaurants
     * @param restaurants The array list of restaurant users.
     */
    public void bubbleSortByCompletedDeliveries(ArrayList<Restaurant> restaurants) {
        int n = restaurants.size();
        boolean swapped;
        
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (restaurants.get(j).getCompletedOrders() < restaurants.get(j + 1).getCompletedOrders()) {
                    // Swap restaurants if the number of completed deliveries is greater
                    Restaurant temp = restaurants.get(j);
                    restaurants.set(j, restaurants.get(j + 1));
                    restaurants.set(j + 1, temp);
                    swapped = true;
                }
            }
            // If no two elements were swapped in the inner loop, then the array is already sorted
            if (!swapped) {
                break;
            }
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
                System.out.println("1.  setDeliveryPolicy <delPolicyName>");
                System.out.println("2.  setProfitPolicy <ProfitPolicyName>");
                System.out.println("3.  associateCard <userName> <cardType>");
                System.out.println("4.  showCourierDeliveries <>");
                System.out.println("5.  showRestaurantTop <>");
                System.out.println("6.  showCustomers <>");
                System.out.println("7.  showMenuItem <restaurant-name>");
                System.out.println("8.  showTotalProfit <>");
                System.out.println("9.  showTotalProfit <startDate> <endDate>");
                System.out.println("10. registerRestaurant <name> <address> <username> <password>");
                System.out.println("11. registerCustomer <firstName> <lastName> <username> <address> <password>");
                System.out.println("12. registerCourier <firstName> <lastName> <username> <position> <password>");
                System.out.println("12. registerCourier <firstName> <lastName> <username> <position> <password>");
                System.out.println("13. registerManager <name> <lastname> <username> <password>");
                System.out.println("14. deactiveUser <name> <userType>");
                System.out.println("15. activateUser <name> <userType>");
                System.out.println("16. sortOrders <sortType> <restaurant_name>");
                System.out.println("17. removeUser <username> <userType>");
                break;
            case 2:
                System.out.println("Commands Available for Restaurants:");
                System.out.println("1.  findDeliverer <orderName>");
                System.out.println("2.  addDishRestauarantMenu <dishName> <dishCategory> <foodCategory> <unitPrice>");
                System.out.println("3.  createMeal <mealName>");
                System.out.println("4.  addDish2Meal <dishName> <mealName>");
                System.out.println("5.  showMeal <mealName>");
                System.out.println("6.  saveMeal <mealName>");
                System.out.println("7.  removeFromSpecialOffer <mealName>");
                System.out.println("8.  setSpecialOffer <mealName>");
                System.out.println("9.  setDiscountPercentage <discount-Factor>");
                System.out.println("10. removeMeal <mealName>");
                System.out.println("11. removeDish <dishName>");
                System.out.println("12. sortOrders <restaurant_name>");
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
                System.out.println("3. finishDelivery <response> -> response = true");
                System.out.println("4. updatePos <latitude> <longtitude>");
                break;
            default:
                System.out.println("Invalid user type");
                break;
        }
    }
}