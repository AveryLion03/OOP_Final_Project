package food;

import java.util.ArrayList;

import user.*;
import food.*;
import Code.*;

public class Order {
	// Order Information
	String orderName;
	String date;
	Double profit;
	boolean completedDelivery;
	ArrayList<Meal> meals;
	ArrayList<Dishes> dish;
	// Users involved in Order
	Restaurant r;
	Courier driver;
	Customer c;
	
	public Order(String orderName) {
		this.orderName = orderName;
		this.date = null;
		this.r = null;
		this.meals = new ArrayList<>();
		this.dish = new ArrayList<>();
		this.profit = 0.0;
		this.driver = null;
		this.completedDelivery = false;
		// this.delivery = null;
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
	public void createOrder(String orderName, Restaurant r) {
    	Order o = new Order(orderName);
    	o.setRestaurant(r);
    	//this.activeOrder = o;
    }
    
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
    	this.setDate(date);
    	// 
    	this.applyPolicies(s);
    	// How do I make them pay???
    	s.addActiveOrder(this);
    	// Allocate a driver
    	Courier c = s.getAvailableCourier().get(0);
    	if(c == null) {
    		System.out.println("Courier not available right now. Manager must manually find courier");
    		return;
    	}
    	c.setOrder(this);
		System.out.printf("Courier %s is on their way with your order!%n", c.getName());
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

		String targetPolicy = systemState.getPricingPolicy(); // Assuming index 1 stores the target policy string
		double deliveryCost = systemState.getDeliveryCost();
		double serviceFee = systemState.getServiceFee();
		double markupPercentage = systemState.getMarkupPercent();
		double targetProfit;
		// Determine the previous completed orders. Optimize based on this value
		if(systemState.getCompletedOrders().size() != 0) {
			targetProfit = systemState.getCompletedOrders().size()*systemState.getTargetProfit();
		}
		else {
			targetProfit = systemState.getTargetProfit()*5;
		}
		
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
}
