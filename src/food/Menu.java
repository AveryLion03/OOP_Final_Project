package food;

import java.util.ArrayList;

public class Menu {
    // Variables
	protected ArrayList<Dishes> dish = new ArrayList<>();
	protected ArrayList<Meal> meals = new ArrayList<>();

    // Constructor with parameters
    public Menu(ArrayList<Dishes> dish, ArrayList<Meal> meal) {
        this.dish = dish;
        this.meals = meal;
    }

    // Empty constructor
    public Menu() {
    	this.dish = null;
    	this.meals = null;
    }

	public void addDish(Dishes dish) {
		// TODO Auto-generated method stub
		this.dish.add(dish);
	}

    // Getters and setters for the member variables can be added if needed
}
