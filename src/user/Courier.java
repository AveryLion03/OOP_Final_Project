package user;

import Code.Location;

public class Courier extends Person {
    // Protected member variables specific to Courier
    protected int phoneNumber;
    protected int orderCount;
    protected boolean onDuty;
    protected Location loc;

    // Constructor
    public Courier(int ID, String username, String password, String userType, String name, String surname, int phoneNumber, int orderCount, boolean onDuty, Location loc) {
        super(ID, username, password, userType, name, surname); // Call to the superclass constructor
        this.phoneNumber = phoneNumber;
        this.orderCount = orderCount;
        this.onDuty = onDuty;
        this.loc = loc;
    }
    
    /*
     * getters and setters
     * void setDuty()
     * void updatePos()
     * boolean acceptDelivery()
     */
}
