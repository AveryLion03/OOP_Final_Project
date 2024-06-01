package food;

import java.util.ArrayList;

import user.*;
import food.*;
import Code.*;

public class Order {
	String orderName;
	String date;
	Restaurant r;
	ArrayList<Meal> meals;
	ArrayList<Dishes> dish;
	Double profit;
	Courier driver;
	boolean completedDelivery;
	Location delivery;
	
	public Order(String orderName) {
		this.orderName = orderName;
		this.date = null;
		this.r = null;
		this.meals = new ArrayList<>();
		this.dish = new ArrayList<>();
		this.profit = 0.0;
		this.driver = null;
		this.completedDelivery = false;
		this.delivery = null;
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
	
	public Restaurant getRestaurant() {
		return this.r;
	}
	public String getOrderName() {
		return this.orderName;
	}
}
