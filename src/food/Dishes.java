package food;

public class Dishes {
    // Member variables
    protected String dishName;
    protected boolean isGlutenFree;
    protected boolean isVeg;
    protected double unitPrice;

    // Constructor
    public Dishes(String dishName, boolean isGlutenFree, boolean isVeg, double unitPrice) {
        this.dishName = dishName;
        this.isGlutenFree = isGlutenFree;
        this.isVeg = isVeg;
        this.unitPrice = unitPrice;
    }

    // Method to check if the dish is gluten-free
    public boolean isGlutenFree() {
        return isGlutenFree;
    }

    // Method to check if the dish is vegetarian
    public boolean isVeg() {
        return isVeg;
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

    public void setGlutenFree(boolean isGlutenFree) {
        this.isGlutenFree = isGlutenFree;
    }

    public void setVeg(boolean isVeg) {
        this.isVeg = isVeg;
    }
}

