package food;

public class Meal {
    // Protected member variables
    protected String mealName;
    protected Dishes[] dishes;
    protected double price;
    protected boolean dealOfDay;

    // Constructor
    public Meal(String mealName, Dishes[] dishes, double price, boolean dealOfDay) {
        this.mealName = mealName;
        this.dishes = dishes;
        this.price = price;
        this.dealOfDay = dealOfDay;
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

    public Dishes[] getDishes() {
        return dishes;
    }

    public void setDishes(Dishes[] dishes) {
        this.dishes = dishes;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDealOfDay(boolean dealOfDay) {
        this.dealOfDay = dealOfDay;
    }
}

