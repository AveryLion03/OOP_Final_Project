package Code;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

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
    
    // Constructor
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
    public ArrayList<User> getActiveMembers() { return activeMembers; }
    public ArrayList<User> getDeactiveMembers() { return deactiveMembers; }
    public void addActiveOrder(Order o) { this.activeOrders.add(o);}
    public void addCompletedOrder (Order o) {
    	this.activeOrders.remove(o);
    	this.completedOrders.add(o);
    }
    public void setDeliveryPolicy(String set) {this.systemPolicies[0] = set; }
    public String getDeliveryPolicy() {return this.systemPolicies[0]; }
    public void setPricingPolicy(String set) {this.systemPolicies[1] = set; }
    public String getPricingPolicy() {return this.systemPolicies[1]; }
    public void setActiveMembers(ArrayList<User> activeMembers) { this.activeMembers = activeMembers; }
    public ArrayList<Order> getActiveOrders(){ return this.activeOrders;}
    public ArrayList<Order> getCompletedOrders(){ return this.completedOrders;}
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

    public static double haversine(double lat1, double lon1, double lat2, double lon2) {
        final double R = 6371.0; // Earth's radius in kilometers
        
        // Convert latitude and longitude from degrees to radians
        double phi1 = Math.toRadians(lat1);
        double phi2 = Math.toRadians(lat2);
        double deltaPhi = Math.toRadians(lat2 - lat1);
        double deltaLambda = Math.toRadians(lon2 - lon1);
        
        // Haversine formula
        double a = Math.sin(deltaPhi / 2) * Math.sin(deltaPhi / 2) +
                   Math.cos(phi1) * Math.cos(phi2) *
                   Math.sin(deltaLambda / 2) * Math.sin(deltaLambda / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        
        // Calculate the distance
        double distance = R * c;
        
        return distance;
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

	public Double getMarkupPercent() {
		return markupPercent;
	}

	public void setMarkupPercent(Double markupPercent) {
		this.markupPercent = markupPercent;
	}

	public Double getDeliveryCost() {
		return deliveryCost;
	}

	public void setDeliveryCost(Double deliveryCost) {
		this.deliveryCost = deliveryCost;
	}

	public Double getServiceFee() {
		return serviceFee;
	}

	public void setServiceFee(Double serviceFee) {
		this.serviceFee = serviceFee;
	}

	public Double getTargetProfit() {
		return targetProfit;
	}

	public void setTargetProfit(Double targetProfit) {
		this.targetProfit = targetProfit;
	}

	public Boolean getAuto() {
		return this.auto;
	}

	public void setAuto(Boolean auto) {
		this.auto = auto;
	}

	public Parse getFoodSys() {
		return foodSys;
	}

	public void setFoodSys(Parse foodSys) {
		this.foodSys = foodSys;
	}

	public CommandExecutor getCommandExecutor() {
		return commandExecutor;
	}

	public void setCommandExecutor(CommandExecutor commandExecutor) {
		this.commandExecutor = commandExecutor;
	}

	public BufferedReader getBr() {
		return br;
	}

	public void setBr(BufferedReader br) {
		this.br = br;
	}
}