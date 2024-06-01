package food;

public class Dishes {
    // Member variables
    protected String dishName;
    protected String dishType;
    protected String dishCategory;
    protected double unitPrice;

    // Constructor
    public Dishes(String dishName, String dishCategory, String dishType, double unitPrice) {
        this.dishName = dishName;
        this.dishType = dishType;
        this.dishCategory = dishCategory;
        this.unitPrice = unitPrice;
    }

    public Dishes(Dishes d) {
        this.dishName = d.getDishName();
        this.dishType = d.getDishType();
        this.dishCategory = d.getDishCategory();
        this.unitPrice = d.getUnitPrice();
    }

	// Method to check if the dish is gluten-free
    public boolean isGlutenFree() {
        return this.dishCategory.equalsIgnoreCase("gluten-free");
    }
    public String getDishCategory() {
    	return this.dishCategory;
    }
    // Method to check if the dish is vegetarian
    public boolean isVeg() {
        return this.dishCategory.equalsIgnoreCase("vegetarian");
    }

    // Getters and setters ??
    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
    public String getDishType() {
    	return this.dishType;
    }
    
    @Override
    public String toString() {
        return "Dish Name: " + dishName + "\n" +
                "Dish Type: " + dishType + "\n" +
                "Dish Category: " + dishCategory + "\n" +
                "Unit Price: " + unitPrice + "\n";
    }
}

