package food;

import java.util.ArrayList;

/**
 * Represents a menu consisting of dishes, meals, and specials.
 */
public class Menu {
    /** The list of available dishes. */
    protected ArrayList<Dishes> dish;
    
    /** The list of available meals. */
    protected ArrayList<Meal> meals;
    
    /** The list of special meals. */
    private ArrayList<Meal> specials;

    /**
     * Constructs an empty menu.
     */
    public Menu() {
        this.dish = new ArrayList<>();
        this.meals = new ArrayList<>();
        this.setSpecials(new ArrayList<>());
    }

    /**
     * Retrieves the list of available meals.
     * 
     * @return the list of available meals
     */
    public ArrayList<Meal> getAvailMeals() {
        return this.meals;
    }

    /**
     * Retrieves the list of available dishes.
     * 
     * @return the list of available dishes
     */
    public ArrayList<Dishes> getAvailDishes() {
        return this.dish;
    }

    /**
     * Removes a dish from the menu.
     * 
     * @param d the dish to be removed
     */
    public void removeDish(Dishes d) {
        this.dish.remove(d);
    }

    /**
     * Removes a meal from the menu.
     * 
     * @param m the meal to be removed
     */
    public void removeMeal(Meal m) {
        this.meals.remove(m);
    }

    /**
     * Adds a dish to the menu.
     * 
     * @param dish the dish to be added
     */
    public void addDish(Dishes dish) {
        this.dish.add(dish);
    }

    /**
     * Adds a dish to a specific meal.
     * 
     * @param m the meal to which the dish is to be added
     * @param dish the dish to be added
     */
    public void addDishToMeal(Meal m, Dishes dish) {
        for (Meal meals : this.meals) {
            if (meals.equals(m)) {
                meals.addDishes(dish);
            }
        }
    }

    /**
     * Adds a special meal to the menu with a discount.
     * 
     * @param m the special meal to be added
     * @param d the discount rate to be applied
     */
    public void addSpecial(Meal m, Double d) {
        m.applyDiscount(d);
        this.getSpecials().add(m);
        this.meals.remove(m);
    }

    /**
     * Removes a special meal from the menu.
     * 
     * @param m the special meal to be removed
     * @param d the discount rate of the special meal
     */
    public void removeSpecial(Meal m, Double d) {
        if (this.getSpecials() != null) {
            m.removeDiscount(d);
            this.meals.add(m);
            boolean removed = this.getSpecials().remove(m);

            if (!removed) {
                System.out.println("Item not found in the list.");
            }
        } else {
            System.out.println("Specials list is null.");
        }
    }

    /**
     * Updates the discount rate of all special meals.
     * 
     * @param newDiscount the new discount rate to be applied
     * @param oldDiscount the old discount rate to be removed
     */
    public void updateSpecialPrice(Double newDiscount, Double oldDiscount) {
        for (Meal m : getSpecials()) {
            m.removeDiscount(oldDiscount);
            m.applyDiscount(newDiscount);
        }
    }

    /**
     * Adds a meal to the menu.
     * 
     * @param m the meal to be added
     */
    public void addMeal(Meal m) {
        meals.add(m);
    }

    /**
     * Displays details of a specific meal.
     * 
     * @param m the name of the meal to be displayed
     */
    public void showMeal(String m) {
        for (Meal meals : this.meals) {
            if (meals.getMealName().equalsIgnoreCase(m)) {
                System.out.println(meals);
                return;
            }
        }
        System.out.println("Meal not found. Try again with correct name");
        return;
    }

    /**
     * Returns a string representation of the menu.
     * 
     * @return a string representation of the menu
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("***** Menu: *****\n");
        sb.append("*     Dishes     *\n");
        for (Dishes d : dish) {
            sb.append(d.toString()).append("\n");
        }
        sb.append("*     Meals     *\n");
        for (Meal m : meals) {
            sb.append(m.toString()).append("\n");
        }
        sb.append("*    Specials     *\n");
        for (Meal s : getSpecials()) {
            sb.append(s.toString()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Retrieves the list of special meals.
     * 
     * @return the list of special meals
     */
    public ArrayList<Meal> getSpecials() {
        return specials;
    }

    /**
     * Sets the list of special meals.
     * 
     * @param specials the list of special meals to be set
     */
    public void setSpecials(ArrayList<Meal> specials) {
        this.specials = specials;
    }
}
