package food;

import java.util.ArrayList;
import java.util.List;

public class Meal {
    // Member variables
    protected String mealName;
    protected List<Dishes> dishes;
    protected double price;
    protected boolean deal;
    protected double discount_rate;

    // Constructor
    public Meal(String mealName, List<Dishes> dishes, double price, boolean deal) {
        this.mealName = mealName.toUpperCase();
        this.dishes = dishes != null ? dishes : new ArrayList<>();
        this.price = price;
        this.deal = deal;
        this.discount_rate = 10.0;
    }
        // Single argument constructor
    public Meal(String mealName) {
        this(mealName, new ArrayList<>(), 0.0, false);
    }

    // Getters
    public String getMealName() {
        return mealName;
    }
    protected boolean getDeal() {
    	return this.deal;
    }
    protected void setDeal(boolean deal) {
    	this.deal = deal;
    }
    public List<Dishes> getDishes() {
        return new ArrayList<>(dishes); // Return a copy to prevent external modification
    }

    public double getPrice() {
        return price;
    }

    // Setters
    public void setMealName(String mealName) {
        this.mealName = mealName;
    }
    
    public void set_Discount (double newDiscount) {
    	this.discount_rate = newDiscount;
    }

    public void setDishes(List<Dishes> dishes) {
        if (dishes != null) {
            this.dishes = new ArrayList<>(dishes); // Create a new list to prevent external modifications
        }
    }

    public void addDish(Dishes dish) {
        if (dish != null) {
            this.dishes.add(dish);
            if(this.deal) this.price += dish.getUnitPrice() * (1 / this.discount_rate); // Add in the discount factor of meal of week
            else this.price += dish.getUnitPrice() * 0.95; // Add in the discount factor of 5%
            
        }
    }

    public void setPrice(double price) {
        if (price >= 0) {
            this.price = price;
        }
    }

    // Method to apply a discount to the meal price for Meal of Week
    public void applyDiscount(double discount) {
    	setDeal(true);
    	this.discount_rate = discount;
        if (discount >= 5) {
        	this.price /= 0.95; // Counteract previous 5% discount applied
            this.price *= (1.0 - (1.0 / (discount ))); // Apply new discount
        }
    }

    // Method to remove a discount from the meal price for Meal of Week
    public void removeDiscount(double discount) {
    	setDeal(false);
        if (discount >= 5) {
            this.price /= (1.0 - (1 / discount)); // Remove Meal of Week discount
            this.price *= 0.95; // Apply normal 5% discount
        }
    }

    // Method to make the meal (for demonstration purposes, this example just prints the meal details)
    public void makeMeal(String type) {
        if ("half".equalsIgnoreCase(type) || "full".equalsIgnoreCase(type)) {
            System.out.println("Preparing a " + type + " meal: " + mealName);
        } else {
            System.out.println("Invalid meal type. Please choose 'half' or 'full'.");
        }
    }

    // Method to print all dishes in the meal
    public void printDishes() {
        for (Dishes dish : dishes) {
            System.out.println(dish);
        }
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

