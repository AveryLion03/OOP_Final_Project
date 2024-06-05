package food;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Meal {
    // Member variables
    protected String mealName;
    protected Dishes dessert;
    protected Dishes main;
    protected Dishes starter;
    protected double price;
    protected boolean deal;
    protected double discount_rate;

    // Constructor
    public Meal(String mealName, double price, boolean deal) {
        this.mealName = mealName.toUpperCase();
        this.dessert = null;
        this.starter = null;
        this.main = null;
        this.price = price;
        this.deal = deal;
        this.discount_rate = 10.0;
    }
        // Single argument constructor
    public Meal(String mealName) {
        this(mealName, 0.0, false);
    }

    // Getters
    public String getMealName() {
        return mealName;
    }
    public boolean getDeal() {
    	return this.deal;
    }
    protected void setDeal(boolean deal) {
    	this.deal = deal;
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

    public void addDishes(Dishes dish) {
    	// System.out.println(dish.dishCategory);
        if (dish.dishCategory.equalsIgnoreCase("Starter")) {
            handleDishAddition(dish, starter, "Starter");
        } else if (dish.dishCategory.equalsIgnoreCase("Main")) {
            handleDishAddition(dish, main, "Main");
        } else if (dish.dishCategory.equalsIgnoreCase("Dessert")) {
            handleDishAddition(dish, dessert, "Dessert");
        }
    }

    private void handleDishAddition(Dishes dish, Dishes existingDish, String category) {
        if (existingDish != null) {
            System.out.printf("Warning: You are replacing the current %s in this meal: %s. Do you want to continue? Y/N%n", category, existingDish.getDishName());
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("y")) {
                assignDish(dish, category);
            } else {
                System.out.println("Change to menu has been canceled");
            }
        } else {
            assignDish(dish, category);
        }
    }

    private void assignDish(Dishes dish, String category) {
        switch (category.toLowerCase()) {
            case "starter":
                this.starter = new Dishes(dish);
                break;
            case "main":
                this.main = new Dishes(dish);
                break;
            case "dessert":
                this.dessert = new Dishes(dish);
                break;
        }
        if (deal) {
            price += dish.getUnitPrice() * (1 / discount_rate); // Add in the discount factor of meal of week
        } else {
            price += dish.getUnitPrice() * 0.95; // Add in the discount factor of 5%
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

    @Override
    public boolean equals(Object obj) {
        // Check if the object is compared with itself
        if (this == obj) {
            return true;
        }
        // Check if the object is null or of a different class
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        // Typecast obj to Meal so that we can compare data members
        Meal meal = (Meal) obj;
        // Compare the data members and return accordingly
        return Double.compare(meal.price, price) == 0 &&
                deal == meal.deal &&
                Double.compare(meal.discount_rate, discount_rate) == 0 &&
                Objects.equals(mealName, meal.mealName) &&
                Objects.equals(dessert, meal.dessert) &&
                Objects.equals(main, meal.main) &&
                Objects.equals(starter, meal.starter);
    }

    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        sb.append("Meal Name: ").append(mealName).append("\n");
        if (starter != null) {
            sb.append("Starter: ").append(starter.getDishName()).append("\n");
            count++;
        }
        if (main != null) {
            sb.append("Main Course: ").append(main.getDishName()).append("\n");
            count++;
        }
        if (dessert != null) {
            sb.append("Dessert: ").append(dessert.getDishName()).append("\n");
            count++;
        }
        sb.append("Price: $").append(price).append("\n");
        sb.append("Deal: ").append(deal ? "Yes" : "No").append("\n");
        return sb.toString();
    }

}

