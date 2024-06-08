package food;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import user.Courier;

public class Meal {
    // Member variables

    /** The name of the meal. */
    protected String mealName;

    /** The dessert dish in the meal. */
    protected Dishes dessert;

    /** The main dish in the meal. */
    protected Dishes main;

    /** The starter dish in the meal. */
    protected Dishes starter;

    /** The price of the meal. */
    protected double price;

    /** Flag indicating if the meal is a special deal. */
    protected boolean deal;

    /** The discount rate applied to the meal. */
    protected double discount_rate;

    /** Flag indicating if the meal is a half meal. */
    protected boolean halfMeal;

    // Constructor

    /**
     * Constructs a new Meal object with the specified name, price, and deal status.
     *
     * @param mealName the name of the meal
     * @param price the price of the meal
     * @param deal the deal status of the meal
     */
    public Meal(String mealName, double price, boolean deal) {
        this.mealName = mealName.toUpperCase();
        this.dessert = null;
        this.starter = null;
        this.main = null;
        this.price = price;
        this.deal = deal;
        this.discount_rate = 10.0;
        this.halfMeal = false;
    }

    /**
     * Constructs a new Meal object with the specified name.
     *
     * @param mealName the name of the meal
     */
    public Meal(String mealName) {
        this(mealName, 0.0, false);
    }

    /**
     * Checks if the meal is a half meal.
     *
     * @return true if the meal is a half meal; false otherwise
     */
    public boolean isHalfMeal() {
        return this.halfMeal;
    }

    // Getters

    /**
     * Retrieves the name of the meal.
     *
     * @return the name of the meal
     */
    public String getMealName() {
        return mealName;
    }

    /**
     * Retrieves the deal status of the meal.
     *
     * @return true if the meal is a special deal; false otherwise
     */
    public boolean getDeal() {
        return this.deal;
    }

    /**
     * Retrieves the discount rate applied to the meal.
     *
     * @return the discount rate of the meal
     */
    public double getDiscount() {
        return this.discount_rate;
    }

    // Setters

    /**
     * Sets the deal status of the meal.
     *
     * @param deal the deal status to be set for the meal
     */
    protected void setDeal(boolean deal) {
        this.deal = deal;
    }

    /**
     * Retrieves the price of the meal.
     *
     * @return the price of the meal
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the name of the meal.
     *
     * @param mealName the name to be set for the meal
     */
    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    /**
     * Sets the discount rate of the meal.
     *
     * @param newDiscount the discount rate to be set for the meal
     */
    public void set_Discount(double newDiscount) {
        this.discount_rate = newDiscount;
    }

    /**
     * Adds a dish to the meal.
     *
     * @param dish the dish to be added to the meal
     */
    public void addDishes(Dishes dish) {
        if (dish.dishCategory.equalsIgnoreCase("Starter")) {
            handleDishAddition(dish, starter, "Starter");
        } else if (dish.dishCategory.equalsIgnoreCase("Main")) {
            handleDishAddition(dish, main, "Main");
        } else if (dish.dishCategory.equalsIgnoreCase("Dessert")) {
            handleDishAddition(dish, dessert, "Dessert");
        }
    }

    /**
     * Handles the addition of a dish to the meal.
     *
     * @param dish the dish to be added
     * @param existingDish the existing dish of the same category
     * @param category the category of the dish
     */
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

    /**
     * Assigns a dish to the meal.
     *
     * @param dish the dish to be assigned
     * @param category the category of the dish
     */
    private void assignDish(Dishes dish, String category) {
        switch (category.toLowerCase()) {
            case "starter":
                this.starter = new Dishes(dish);
                if (this.main != null && this.dessert == null) {
                    this.halfMeal = true;
                } else if (this.main != null && this.dessert != null) {
                    this.halfMeal = false;
                }
                break;
            case "main":
                this.main = new Dishes(dish);
                break;
            case "dessert":
                this.dessert = new Dishes(dish);
                if (this.main != null && this.starter == null) {
                    this.halfMeal = true;
                } else if (this.main != null && this.starter != null) {
                    this.halfMeal = false;
                }
                break;
        }
        if (deal) {
            price += dish.getUnitPrice() * (1 / discount_rate); // Add in the discount factor of meal of week
        } else {
            price += dish.getUnitPrice() * 0.95; // Add in the discount factor of 5%
        }
    }

    /**
     * Sets the price of the meal.
     *
     * @param price the price to be set for the meal
     */
    public void setPrice(double price) {
        if (price >= 0) {
            this.price = price;
        }
    }

    // Method to apply a discount to the meal price for Meal of Week

    /**
     * Applies a discount to the meal price for Meal of Week.
     *
     * @param discount the discount to be applied
     */
    public void applyDiscount(double discount) {
        setDeal(true);
        this.discount_rate = discount;
        if (discount >= 5) {
            this.price /= 0.95; // Counteract previous 5% discount applied
            this.price *= (1.0 - (1.0 / (discount))); // Apply new discount
        }
    }

    // Method to remove a discount from the meal price for Meal of Week

    /**
     * Removes a discount from the
     * meal price for Meal of Week.
     *
     * @param discount the discount to be removed
     */
    public void removeDiscount(double discount) {
        setDeal(false);
        if (discount >= 5) {
            this.price /= (1.0 - (1 / discount)); // Remove Meal of Week discount
            this.price *= 0.95; // Apply normal 5% discount
        }
    }
    /**
     * Makes the meal.
     * 
     * @param type the type of meal to be made (half or full)
     */
    public void makeMeal(String type) {
        if ("half".equalsIgnoreCase(type) || "full".equalsIgnoreCase(type)) {
            System.out.println("Preparing a " + type + " meal: " + mealName);
        } else {
            System.out.println("Invalid meal type. Please choose 'half' or 'full'.");
        }
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * 
     * @param obj the reference object with which to compare
     * @return true if this object is the same as the obj argument; false otherwise
     */
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

    /**
     * Returns a string representation of the object.
     * 
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        sb.append("Meal Name: ").append(mealName).append("\n");
        // Check if it's a half or full meal
        if (this.halfMeal) {
            sb.append("HALF_MEAL\n");
        } else {
            sb.append("FULL_MEAL\n");
        }
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
        sb.append("Special: ").append(deal ? "Yes" : "No").append("\n");
        return sb.toString();
    }
}

