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
    public void finalizeOrder (String date) {
    	this.setDate(date);
    	// Send it to restaurant -> Now they get to deal with finding a driver.
    }
    public void setCustomer (Customer c) {
    	this.c = c;
    }
}
