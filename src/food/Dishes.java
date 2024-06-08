package food;

/**
 * The Dishes class represents a dish with its details such as name, category, type, and unit price.
 */
public class Dishes {
    
    /** The name of the dish. */
    protected String dishName;
    
    /** The type of the dish. */
    protected String dishType;
    
    /** The category of the dish. */
    protected String dishCategory;
    
    /** The unit price of the dish. */
    protected double unitPrice;

    /**
     * Constructs a new Dishes object with the specified details.
     *
     * @param dishName the name of the dish
     * @param dishCategory the category of the dish
     * @param dishType the type of the dish
     * @param unitPrice the unit price of the dish
     */
    public Dishes(String dishName, String dishCategory, String dishType, double unitPrice) {
        this.dishName = dishName;
        this.dishType = dishType;
        this.dishCategory = dishCategory;
        this.unitPrice = unitPrice;
    }

    /**
     * Constructs a new Dishes object by copying the details from another Dishes object.
     *
     * @param d the Dishes object to be copied
     */
    public Dishes(Dishes d) {
        this.dishName = d.getDishName();
        this.dishType = d.getDishType();
        this.dishCategory = d.getDishCategory();
        this.unitPrice = d.getUnitPrice();
    }

    /**
     * Checks if the dish is gluten-free.
     *
     * @return true if the dish is gluten-free; false otherwise
     */
    public boolean isGlutenFree() {
        return this.dishCategory.equalsIgnoreCase("gluten-free");
    }

    /**
     * Retrieves the category of the dish.
     *
     * @return the category of the dish
     */
    public String getDishCategory() {
        return this.dishCategory;
    }

    /**
     * Checks if the dish is vegetarian.
     *
     * @return true if the dish is vegetarian; false otherwise
     */
    public boolean isVeg() {
        return this.dishCategory.equalsIgnoreCase("vegetarian");
    }

    /**
     * Retrieves the name of the dish.
     *
     * @return the name of the dish
     */
    public String getDishName() {
        return dishName;
    }

    /**
     * Sets the name of the dish.
     *
     * @param dishName the name to be set for the dish
     */
    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    /**
     * Retrieves the unit price of the dish.
     *
     * @return the unit price of the dish
     */
    public double getUnitPrice() {
        return unitPrice;
    }

    /**
     * Sets the unit price of the dish.
     *
     * @param unitPrice the unit price to be set for the dish
     */
    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * Retrieves the type of the dish.
     *
     * @return the type of the dish
     */
    public String getDishType() {
        return this.dishType;
    }

    /**
     * Returns a string representation of the dish.
     *
     * @return a string containing the details of the dish
     */
    @Override
    public String toString() {
        return "Dish Name: " + dishName + "\n" +
                "Dish Type: " + dishType + "\n" +
                "Dish Category: " + dishCategory + "\n" +
                "Unit Price: " + unitPrice + "\n";
    }
}
