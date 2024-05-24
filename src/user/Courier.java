package user;

import Code.Location;

public class Courier extends Person {
    // Protected member variables specific to Courier
    protected String phoneNumber;
    protected int orderCount;
    protected boolean onDuty;
    protected Location loc;
    //Order ID or somethign to assign orert to courier so they can accept

    // Constructor
    public Courier(String username, String password, String userType, String name, String surname, String phoneNumber, int orderCount, boolean onDuty, Location loc) {
        super(username, password, userType, name, surname); // Call to the superclass constructor
        this.phoneNumber = phoneNumber;
        this.orderCount = orderCount;
        this.onDuty = onDuty;
        this.loc = loc;
    }
    
    
    //All gestters and setters except set order count?

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public boolean isOnDuty() {
		return onDuty;
	}

	public void setOnDuty(boolean onDuty) {
		this.onDuty = onDuty;
	}

	public Location getLoc() {
		return loc;
	}

	//set Loc or updatePos?
	public void setLoc(Location loc) {
		this.loc = loc;
	}

	public int getOrderCount() {
		return orderCount;
	}
    
	
 
     //boolean acceptDelivery()
     
}
