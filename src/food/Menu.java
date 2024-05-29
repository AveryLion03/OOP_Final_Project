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

	public void addDish(Dishes dish) {
		// TODO Auto-generated method stub
		this.dish.add(dish);
	}

    // Getters and setters for the member variables can be added if needed
}
