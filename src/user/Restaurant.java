package user;

import java.util.ArrayList;

import Code.Location;


public class Restaurant extends User {
	
    // variables specific to Restaurant
    protected Location loc;
    protected ArrayList<Customer> visited = new ArrayList<>() ;
    protected ArrayList<Courier> available = new ArrayList<>();

    // Constructor
    public Restaurant(String username, String password, String userType, String name, Location loc) {
        super(username, password, userType, name); // Call to the superclass constructor
        this.loc = loc;
        /*
        this.visited = visited;
        this.available = available;
        */
    }
    @Override
	public String getName() {
		return super.getName();
	}
	@Override
	public void setName(String name) {
		super.Name = name;
	}

	public ArrayList<Customer> getVisited() {
		return visited;
	}

	public void setVisited(ArrayList<Customer> visited) {
		this.visited = visited;
	}

	public ArrayList<Courier> getAvailable() {
		return available;
	}

	public void setAvailable(ArrayList<Courier> available) {
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
