package food;

import java.util.ArrayList;

import user.*;
import food.*;
import Code.*;

public class Order {
	String orderName;
	String date;
	ArrayList<Meal> meals;
	ArrayList<Dishes> dish;
	Double profit;
	Courier driver;
	
	public Order(String orderName) {
		this.orderName = orderName;
		this.date = null;
		this.meals = new ArrayList<>();
		this.dish = new ArrayList<>();
		this.profit = 0.0;
		this.driver = null;
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
}
