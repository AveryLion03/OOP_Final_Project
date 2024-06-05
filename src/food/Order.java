package food;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import user.*;
import food.*;
import Code.*;

public class Order {
	// Order Information
	protected String orderName;
	protected String date;
	protected Double profit;
	protected boolean completedDelivery;
	protected ArrayList<Meal> meals;
	protected ArrayList<Dishes> dish;
	// Users involved in Order
	protected Restaurant r;
	protected Courier driver;
	protected Customer c;
	
	public Order(String orderName, Restaurant r) {
		this.orderName = orderName;
		this.date = null;
		this.r = r;
		this.meals = new ArrayList<>();
		this.dish = new ArrayList<>();
		this.profit = 0.0;
		this.driver = null;
		this.completedDelivery = false;
		// this.delivery = null;
	}
	public Customer getCustomer(){
		return this.c;
	}
	public ArrayList<Meal> getMeals(){
		return this.meals;
	}
	public ArrayList<Dishes> getDishes(){
		return this.dish;
	}
	public void setDelivery(boolean t) {
		this.completedDelivery = t;
	}
	public void addMeal(Meal m) {
		this.meals.add(m);
		this.profit += m.getPrice();
		return;
	}
	public void addDish(Dishes d) {
		this.dish.add(d);
		profit += d.getUnitPrice();
		return;
	}
	public void setCompleted(Boolean t) {
		this.completedDelivery = t;
	}
	public double getProfit() {
		return this.profit;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public void setRestaurant(Restaurant r) {
		this.r = r;
	}
	public String getOrderName() {
		return this.orderName;
	}
	/*
	public void createOrder(String orderName, Restaurant r) {
    	this.orderName = orderName;
    	this.setRestaurant(r);
    	// o.getRestaurant().getMenu();
    	//this.activeOrder = o;
    }
    */
    public Restaurant getRestaurant(String orderName) {
    	if(this.orderName.equalsIgnoreCase(orderName)) {
    		return this.r;
    	}
    	else return null;
    }
    public Restaurant getRestaurant() {
    	return this.r;
    }
    // No need to check if meal exists in menu. This should be done in Parse class -> Only sent to add2Order if its valid.
    public void add2Order(Meal m, Dishes d) {
    	if(m != null) {
    		this.addMeal(m);
    	}
    	if(d != null) {
    		this.addDish(d);
    	}
    	return;
    }
    public void finalizeOrder (String date, SystemState s) {
    	this.r.setCompletedOrders(1);
    	this.setDate(date);
    	// 
    	this.applyPolicies(s);
    	// How do I make them pay???
    	
    	// Change the price of the order based on the Fidelity card
    	if(c.getFidelity().equalsIgnoreCase("Lottery")) {
    		if(c.isFreeOrder()) {
    			this.profit = 0.0;
    		}
    	}
    	else if(c.getFidelity().equalsIgnoreCase("Points")) {
    		if(c.getPoints()>=100) {
    			this.profit *= 0.90;
    			c.setPoints(-100);
    			if(this.profit > 10) {
    				c.setPoints((int)(this.profit/10));
    			}
    		}
    	}
    	
    	// Don't need anything for Basic or not registered in Fidelity program
    	
        @SuppressWarnings("resource")
        Scanner inputLine = new Scanner(System.in);
        String payment = null;
        String[] e = {"empty", "empty"};
        if(!s.getAuto()) {
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
        }
        // Additional section for processing when systemState.getAuto() is true
        else {
            // System.out.println("Auto mode is enabled. Reading the next line...");
            String nextLine = null;
            // Read the next line from the startup.txt file
            BufferedReader br = s.getBr();
            try {
				nextLine = br.readLine();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
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
        if(e[0].equalsIgnoreCase("N")) {
        	System.out.println("Order canceled.");
        	this.c.setActiveOrder(null);
        	return;
        }
    	s.addActiveOrder(this);
    	// Allocate a driver
    	ArrayList<Courier> c = s.getAvailableCourier();
    	if(c == null || c.size() == 0) {
    		System.out.println("Courier not available right now. Manager must manually find courier\n");
    		return;
    	}
    	// System.out.println(c.size()); -> debugging purposes
    	Courier active = c.get(0);
    	active.setOrder(this);
		System.out.printf("Courier %s is on their way with your order!%n", active.getName());
    	return;
    }
    public void setCustomer (Customer c) {
    	this.c = c;
    }
    public String [] getDate() {
    	String[] orderDate = this.date.split("/");
    	return orderDate;
    }
    
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
