package food;

public class Menu {
    // variables
    protected Dishes[] starters;
    protected Dishes[] mainCourse;
    protected Dishes[] desserts;
    protected Meal[] standHalf;
    protected Meal[] standFull;
    protected Meal[] vegHalf;
    protected Meal[] vegFull;
    protected Meal[] glutHalf;
    protected Meal[] glutFull;

    // Constructor
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

    // Getters and setters for the member variables?
    
}

