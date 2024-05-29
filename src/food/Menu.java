package food;

import java.util.ArrayList;

public class Menu {
    // Variables
	protected ArrayList<Dishes> dish;
	protected ArrayList<Meal> meals;

    // Constructor with parameters

    // Empty constructor
    public Menu() {
    	this.dish = new ArrayList<>();
    	this.meals = new ArrayList<>();
    }
    public ArrayList<Meal> getAvailMeals(){
    	return this.meals;
    }
    public ArrayList<Dishes> getAvailDishes(){
    	return this.dish;
    }
	public void addDish(Dishes dish) {
		// TODO Auto-generated method stub
		this.dish.add(dish);
	}
	public void addDishToMeal(Meal m, Dishes dish) {
		for (Meal meals : this.meals) {
			if(meals.equals(m)) {
				meals.addDishToMeal(dish);
			}
		}
	}
	public void addMeal(Meal m) {
		meals.add(m);
	}
	
	public void showMeal(String m) {
		for (Meal meals : this.meals) {
			if(meals.getMealName().equalsIgnoreCase(m)) {
				meals.print();
				return;
			}
		}
		System.out.println("Meal not found. Try again with correct name");
		return;
	}
    // Getters and setters for the member variables can be added if needed
}
