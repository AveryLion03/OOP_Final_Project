package user;

import Code.Location;

public class Customer extends Person {
    //variables specific to Customer
    protected String email;
    protected String cellNumber;
    protected Location loc;
    protected String fidelity;
    protected int points;

    // Constructor
    public Customer(String username, String password, String userType, String name, String surname, String email, String cellNumber, Location loc) {
        super(username, password, userType, name, surname); // Call to the superclass constructor
        this.email = email;
        this.cellNumber = cellNumber;
        this.loc = loc;
    }
    
    //getters and setters for everything except set points because system should do that? What about fidelity

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCellNumber() {
		return cellNumber;
	}

	public void setCellNumber(String cellNumber) {
		this.cellNumber = cellNumber;
	}

	public Location getLoc() {
		return loc;
	}

	public void setLoc(Location loc) {
		this.loc = loc;
	}

	public String getFidelity() {
		return fidelity;
	}

	//make sure that user only enters one of the valid options-- this feels like bad coding
	public void setFidelity(String fidelity) {
	    try {
	        if (fidelity.equals("none") || fidelity.equals("basic") || fidelity.equals("point") || fidelity.equals("lottery")) {
	            this.fidelity = fidelity;
	        } else {
	            throw new IllegalArgumentException("Invalid fidelity value. Options are none,basic, point or lottery ");
	        }
	    } catch (IllegalArgumentException e) {
	        System.out.println(e.getMessage());
	        
	    }
	}


	public int getPoints() {
		return points;
	}
    
    
    
    
    /*
     * sooo many methods :(
     */
}
