package user;

import Code.Location;


public class Restaurant extends User {
	
    // variables specific to Restaurant
    protected String name;
    protected Location loc;
    protected Customer[] visited;
    protected Courier[] available;

    // Constructor
    public Restaurant(int ID, String username, String password, String userType, String name, Location loc, Customer[] visited, Courier[] available) {
        super(ID, username, password, userType); // Call to the superclass constructor
        this.name = name;
        this.loc = loc;
        this.visited = visited;
        this.available = available;
    }
   
    
    /*
     * manageDish()

	manageMeal()

	manageMenu()

	setSpecial() -> can set 5%, 10% and remove discount
	
	getters and setters
     */
}
