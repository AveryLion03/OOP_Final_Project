package food;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import user.*;
import food.*;
import Code.*;

/**
 * Represents an order consisting of meals and dishes.
 */
public class Order {
    /** The name of the order. */
    protected String orderName;

    /** The date of the order. */
    protected String date;

    /** The total profit of the order. */
    protected Double profit;

    /** Indicates if the delivery for the order is completed. */
    protected boolean completedDelivery;

    /** The list of meals in the order. */
    protected ArrayList<Meal> meals;

    /** The list of dishes in the order. */
    protected ArrayList<Dishes> dish;

    /** The restaurant associated with the order. */
    protected Restaurant r;

    /** The driver assigned to deliver the order. */
    protected Courier driver;

    /** The customer who placed the order. */
    protected Customer c;

    /**
     * Constructs an order with the given name and associated restaurant.
     * 
     * @param orderName the name of the order
     * @param r         the restaurant associated with the order
     */
    public Order(String orderName, Restaurant r) {
        this.orderName = orderName;
        this.date = null;
        this.r = r;
        this.meals = new ArrayList<>();
        this.dish = new ArrayList<>();
        this.profit = 0.0;
        this.driver = null;
        this.completedDelivery = false;
    }

    /**
     * Retrieves the customer who placed the order.
     * 
     * @return the customer who placed the order
     */
    public Customer getCustomer() {
        return this.c;
    }

    /**
     * Retrieves the list of meals in the order.
     * 
     * @return the list of meals in the order
     */
    public ArrayList<Meal> getMeals() {
        return this.meals;
    }

    /**
     * Retrieves the list of dishes in the order.
     * 
     * @return the list of dishes in the order
     */
    public ArrayList<Dishes> getDishes() {
        return this.dish;
    }

    /**
     * Sets the completion status of the delivery for the order.
     * 
     * @param t the completion status of the delivery
     */
    public void setDelivery(boolean t) {
        this.completedDelivery = t;
    }

    /**
     * Adds a meal to the order and updates the profit.
     * 
     * @param m the meal to be added to the order
     */
    public void addMeal(Meal m) {
        this.meals.add(m);
        this.profit += m.getPrice();
    }

    /**
     * Adds a dish to the order and updates the profit.
     * 
     * @param d the dish to be added to the order
     */
    public void addDish(Dishes d) {
        this.dish.add(d);
        profit += d.getUnitPrice();
    }

    /**
     * Sets the completion status of the order.
     * 
     * @param t the completion status of the order
     */
    public void setCompleted(Boolean t) {
        this.completedDelivery = t;
    }

    /**
     * Retrieves the total profit of the order.
     * 
     * @return the total profit of the order
     */
    public double getProfit() {
        return this.profit;
    }

    /**
     * Sets the date of the order.
     * 
     * @param date the date of the order
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Sets the restaurant associated with the order.
     * 
     * @param r the restaurant associated with the order
     */
    public void setRestaurant(Restaurant r) {
        this.r = r;
    }

    /**
     * Retrieves the name of the order.
     * 
     * @return the name of the order
     */
    public String getOrderName() {
        return this.orderName;
    }

    /**
     * Retrieves the restaurant associated with the order.
     * 
     * @param orderName the name of the order
     * @return the restaurant associated with the order
     */
    public Restaurant getRestaurant(String orderName) {
        if (this.orderName.equalsIgnoreCase(orderName)) {
            return this.r;
        } else
            return null;
    }

    /**
     * Retrieves the restaurant associated with the order.
     * 
     * @return the restaurant associated with the order
     */
    public Restaurant getRestaurant() {
        return this.r;
    }    /**
     * Adds a meal and a dish to the order if they are not null.
     * 
     * @param m the meal to be added to the order
     * @param d the dish to be added to the order
     */
    public void add2Order(Meal m, Dishes d) {
        if (m != null) {
            this.addMeal(m);
        }
        if (d != null) {
            this.addDish(d);
        }
        return;
    }

    /**
     * Finalizes the order by setting completion status, date, applying policies,
     * and allocating a courier for delivery.
     * 
     * @param date the date the order is finalized
     * @param s    the system state containing pricing and delivery policies
     */
    public void finalizeOrder(String date, SystemState s) {
        this.r.setCompletedOrders(1);
        this.setDate(date);
        this.applyPolicies(s);

        if (c.getFidelity().equalsIgnoreCase("Lottery")) {
            if (c.isFreeOrder()) {
                this.profit = 0.0;
            }
        } else if (c.getFidelity().equalsIgnoreCase("Points")) {
            if (c.getPoints() >= 100) {
                this.profit *= 0.90;
                c.setPoints(-100);
                if (this.profit > 10) {
                    c.setPoints((int) (this.profit / 10));
                }
            }
        }

        @SuppressWarnings("resource")
        Scanner inputLine = new Scanner(System.in);
        String payment = null;
        String[] e = { "empty", "empty" };
        if (!s.getAuto()) {
            while (true) {
                System.out.printf("Please confirm payment of order (Y/N): $%.2f%n", this.getProfit());
                payment = inputLine.nextLine().toString();
                e = payment.split(" ");
                if (e.length != 1) {
                    System.out.println("Incorrect format. Enter in the following format: <Y/N>");
                } else {
                    break;
                }
            }
        } else {
            String nextLine = null;
            BufferedReader br = s.getBr();
            try {
                nextLine = br.readLine();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            if (nextLine != null) {
                e = nextLine.split(" ");
                if (e.length != 1) {
                    System.out.println("Incorrect format. Enter in the following format: <Y/N>");
                }
            } else {
                System.out.println("No more lines found in the file.");
            }
        }
        if (e[0].equalsIgnoreCase("N")) {
            System.out.println("Order canceled.");
            this.c.setActiveOrder(null);
            return;
        }
        s.addActiveOrder(this);

        ArrayList<Courier> c = s.getAvailableCourier();
        if (c == null || c.size() == 0) {
            System.out.println("Courier not available right now. Manager must manually find courier\n");
            return;
        }
        Courier active = c.get(0);
        active.setOrder(this);
        System.out.printf("Courier %s is on their way with your order!%n", active.getName());
        return;
    }

    /**
     * Sets the customer associated with the order.
     * 
     * @param c the customer to be associated with the order
     */
    public void setCustomer(Customer c) {
        this.c = c;
    }

    /**
     * Retrieves the date of the order split into an array of strings.
     * 
     * @return an array containing the date of the order
     */
    public String[] getDate() {
        String[] orderDate = this.date.split("/");
        return orderDate;
    }

    /**
     * Applies pricing and delivery policies based on the system state.
     * 
     * @param systemState the system state containing pricing and delivery policies
     */
    public void applyPolicies(SystemState systemState) {

		String targetPolicy = systemState.getPricingPolicy();
		double deliveryCost = systemState.getDeliveryCost();
		double serviceFee = systemState.getServiceFee();
		double markupPercentage = systemState.getMarkupPercent();
		double targetProfit = systemState.getTargetProfit() + this.profit; 
		// We want the target of this fucntion to be as follows: targetProfit = Increased price - restaurant price
		// Thus, targetProfit will serve as increased price in this scenario ^
		
		switch (targetPolicy) {
		case "targetProfit_DeliveryCost":
		// Calculate deliveryCost based on target profit, service fee, and markup percentage
		deliveryCost = (this.getProfit() * markupPercentage) + serviceFee - targetProfit;
		systemState.setDeliveryCost(deliveryCost);
		break;
		case "targetProfit_ServiceFee":
		// Calculate serviceFee based on target profit, delivery cost, and markup percentage
		serviceFee = deliveryCost + targetProfit - (this.getProfit() * markupPercentage);
		systemState.setServiceFee(serviceFee);
		break;
		case "targetProfit_Markup":
		// Calculate markupPercentage based on target profit, delivery cost, and service fee
		markupPercentage = (targetProfit + deliveryCost - serviceFee)/this.getProfit();
		systemState.setMarkupPercent(markupPercentage);
		break;
		default:
		System.out.println("Invalid target policy.");
		break;
		}
		this.profit = (this.profit*markupPercentage) + serviceFee - deliveryCost;
	}
    /**
     * Returns a string representation of the order.
     * 
     * @return a string containing order details such as name, number of items,
     *         total price, and date
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("- Order Name: ").append(orderName).append("\n");
        builder.append("- Number of Items Ordered: ").append(meals.size() + dish.size()).append("\n");
        builder.append("- Total Price: $").append(String.format("%.2f", profit)).append("\n");
        builder.append("- Date Ordered: ").append(date).append("\n");
        return builder.toString();
    }

}
