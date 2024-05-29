package food;

import java.util.ArrayList;

public class Meal {
    // Protected member variables
    protected String mealName;
    protected ArrayList<Dishes> dishes;
    protected double price;
    protected boolean dealOfDay;

    // Constructor
    public Meal(String mealName, ArrayList<Dishes> dishes, double price, boolean dealOfDay) {
        this.mealName = mealName;
        this.dishes = dishes;
        this.price = price;
        this.dealOfDay = dealOfDay;
    }
    
    // 1 arg Constructor
    public Meal(String mealName) {
    	this.mealName = mealName;
    	this.dishes = new ArrayList<>();
    	this.price = 0.0;
    	this.dealOfDay = false;
    }
    
    // Method to get the price of the meal
    public double getPrice() {
        return price;
    }

    // Method to check if the meal is the deal of the day
    public boolean isDealOfDay() {
        return dealOfDay;
    }

    // Method to make the meal (for demonstration purposes, this example just prints the meal details)
    public void makeMeal(String type) {
        if ("half".equalsIgnoreCase(type) || "full".equalsIgnoreCase(type)) {
            System.out.println("Preparing a " + type + " meal: " + mealName);
        } else {
            System.out.println("Invalid meal type. Please choose 'half' or 'full'.");
        }
    }

    // Getters and setters 
    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public void getDishes() {
        for (Dishes dish : this.dishes) {
        	System.out.println(dish);
        }
    }

    public void setDishes(Dishes dish) {
        this.dishes.add(dish);
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDealOfDay(boolean dealOfDay) {
        this.dealOfDay = dealOfDay;
    }
    public void addDishToMeal(Dishes dish) {
    	this.dishes.add(dish);
    }
    
    @Override
    public boolean equals(Object obj) {
    	// Check if the object is compared with itself
        if (this == obj) {
            return true;
        }
        // Check if the object is an instance of Meal
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        // Typecast obj to Meal so that we can compare data members
        Meal meal = (Meal) obj;
        // Compare the data members and return accordingly
        if (Double.compare(meal.price, price) != 0) {
            return false;
        }
        if (dealOfDay != meal.dealOfDay) {
            return false;
        }
        if (mealName != null ? !mealName.equals(meal.mealName) : meal.mealName != null) {
            return false;
        }
        if (dishes != null ? !dishes.equals(meal.dishes) : meal.dishes != null) {
            return false;
        }

        return true;
    }
    
    public void print() {
    	System.out.printf("%s: $%.2f%n", this.mealName, this.price);
    	System.out.println("Starters: ");
    	for(Dishes d : this.dishes) {
    		if(d.getDishType().equalsIgnoreCase("Starter")) {
    			System.out.printf("** %s **%n", d.getDishName());
    		}
    	}
    	System.out.println("Main Courses: ");
    	for(Dishes d : this.dishes) {
    		if(d.getDishType().equalsIgnoreCase("Main")) {
    			System.out.printf("** %s **%n", d.getDishName());
    		}
    	}
    	System.out.println("Desserts: ");
    	for(Dishes d : this.dishes) {
    		if(d.getDishType().equalsIgnoreCase("Dessert")) {
    			System.out.printf("** %s **%n", d.getDishName());
    		}
    	}
    }
}

