package food;

import java.util.ArrayList;

public class Menu {
    // Variables
	protected ArrayList<Dishes> dish;
	protected ArrayList<Meal> meals;
	protected ArrayList<Meal> specials;

    // Constructor with parameters

    // Empty constructor
    public Menu() {
    	this.dish = new ArrayList<>();
    	this.meals = new ArrayList<>();
    	this.specials = new ArrayList<>();
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
				meals.addDish(dish);
			}
		}
	}
	public void addSpecial (Meal m, Double d) {
		m.applyDiscount(d);
		this.specials.add(m);
	}
	public void removeSpecial(Meal m, Double d) {
	    if (this.specials != null) {
	    	m.removeDiscount(d);
	        boolean removed = this.specials.remove(m);
	        
	        if (!removed) {
	            // Handle the case where the item was not found in the list
	            System.out.println("Item not found in the list.");
	        }
	    } else {
	        // Handle the case where the list is null
	        System.out.println("Specials list is null.");
	    }
	}
	
	public void updateSpecialPrice(Double newDiscount, Double oldDiscount) {
		for (Meal m : specials) {
			m.removeDiscount(oldDiscount);
			m.applyDiscount(newDiscount);
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
