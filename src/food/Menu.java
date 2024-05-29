package food;

public class Menu {
    // Variables
    protected Dishes[] starters;
    protected Dishes[] mainCourse;
    protected Dishes[] desserts;
    protected Meal[] standHalf;
    protected Meal[] standFull;
    protected Meal[] vegHalf;
    protected Meal[] vegFull;
    protected Meal[] glutHalf;
    protected Meal[] glutFull;

    // Constructor with parameters
    public Menu(Dishes[] starters, Dishes[] mainCourse, Dishes[] desserts, Meal[] standHalf, Meal[] standFull, Meal[] vegHalf, Meal[] vegFull, Meal[] glutHalf, Meal[] glutFull) {
        this.starters = starters;
        this.mainCourse = mainCourse;
        this.desserts = desserts;
        this.standHalf = standHalf;
        this.standFull = standFull;
        this.vegHalf = vegHalf;
        this.vegFull = vegFull;
        this.glutHalf = glutHalf;
        this.glutFull = glutFull;
    }

    // Empty constructor
    public Menu() {
        this.starters = null;
        this.mainCourse = null;
        this.desserts = null;
        this.standHalf = null;
        this.standFull = null;
        this.vegHalf = null;
        this.vegFull = null;
        this.glutHalf = null;
        this.glutFull = null;
    }

    // Getters and setters for the member variables can be added if needed
}
