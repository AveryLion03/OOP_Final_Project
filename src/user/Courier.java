package user;

import Code.Location;

public class Courier extends Person {
    // Protected member variables specific to Courier
    protected int phoneNumber;
    protected int orderCount;
    protected boolean onDuty;
    protected Location loc;
    //Order ID or somethign to assign orert to courier so they can accept

    // Constructor
    public Courier(int ID, String username, String password, String userType, String name, String surname, int phoneNumber, int orderCount, boolean onDuty, Location loc) {
        super(ID, username, password, userType, name, surname); // Call to the superclass constructor
        this.phoneNumber = phoneNumber;
        this.orderCount = orderCount;
        this.onDuty = onDuty;
        this.loc = loc;
    }
    
    
    //All gestters and setters except set order count?

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
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
