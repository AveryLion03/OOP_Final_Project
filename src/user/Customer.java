package user;

import java.util.ArrayList;

import Code.*;
import food.*;

public class Customer extends Person {
    // Variables specific to Customer
    private String email;
    private String cellNumber;
    private Location loc;
    private String fidelity;
    private int points;
    private boolean freeOrder;
    protected Order activeOrder;

    // Constructor
    public Customer(String username, String password, String userType, String name, String surname, String email, String cellNumber, Location loc) {
        super(username, password, userType, name, surname); // Call to the superclass constructor
        this.email = email;
        // this.pastOrders = new ArrayList<>(); //Past orders!
        this.activeOrder = null;
        this.cellNumber = cellNumber;
        this.loc = loc;
        this.fidelity = "Basic"; // Default fidelity
        this.points = 0; // Default points
        this.setFreeOrder(false);
    }

    // Getters and setters
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

    public Boolean setFidelity(String fidelity) {
        if (isValidFidelity(fidelity)) {
            this.fidelity = fidelity;
        } else {
            System.out.println("Invalid fidelity value. Options are none, basic, point, or lottery.");
            return false;
        }
        return true;
    }

    private boolean isValidFidelity(String fidelity) {
        return fidelity == null || fidelity.equalsIgnoreCase("basic") || fidelity.equalsIgnoreCase("point") || fidelity.equalsIgnoreCase("lottery");
    }

    public int getPoints() {
        return points;
    }
    public void setPoints(double p) {
    	this.points += p;
    }

    // Setter for points
    // Depending on your system design, you may choose to set points directly or through some other mechanism.
    // If points are managed internally by the system, you might not need this setter.
    public boolean pointsDiscount(int points) {
        if(this.fidelity.equalsIgnoreCase("Point"))	{
        	this.points += points;
        	if(this.points >= 100) { //If We have reached over 100 points, we receive a discount!
        		return true;
        	}
        }
        return false;
    }
    
    public Order getActiveOrder() {
    	return this.activeOrder;
    }
    public void setActiveOrder(Order o) {
    	this.activeOrder = o;
    }

	public boolean isFreeOrder() {
		return freeOrder;
	}

	public void setFreeOrder(boolean freeOrder) {
		this.freeOrder = freeOrder;
	}
}
