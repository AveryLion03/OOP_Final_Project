package user;

import java.util.ArrayList;
import java.util.Objects;
import food.*;

public class Courier extends Person {
    // Protected member variables specific to Courier
    protected String phoneNumber;
    protected int orderCount;
    protected boolean onDuty;
    protected Location loc;
    protected Order currentJob;
    // Constructor
    public Courier(String username, String password, String userType, String name, String surname, String phoneNumber, int orderCount, boolean onDuty, Location loc) {
        super(username, password, userType, name, surname); // Call to the superclass constructor
        this.phoneNumber = phoneNumber;
        this.orderCount = orderCount;
        this.onDuty = onDuty;
        this.loc = loc;
        this.currentJob = null;
    }

    // Getters and setters
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public boolean available() {
    	if(currentJob == null) {
    		return true;
    	}
    	return false;
    }
    public void setOrder(Order o) {
    	this.currentJob = o;
    }

    public void finishJob() {
    	this.currentJob = null;
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

    public void setLoc(Location loc) {
        this.loc = loc;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public boolean onDuty() {
        return this.onDuty;
    }

    // Override equals and hashCode methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Courier courier = (Courier) o;
        return orderCount == courier.orderCount &&
                onDuty == courier.onDuty &&
                Objects.equals(phoneNumber, courier.phoneNumber) &&
                Objects.equals(loc, courier.loc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), phoneNumber, orderCount, onDuty, loc);
    }

    // Override toString method
    @Override
    public String toString() {
        return "Courier " + name +" " + surname + ": " + orderCount;
        		
        		/*
	        		"Courier{" +
	                "username='" + username + '\'' +
	                ", name='" + name + '\'' +
	                ", surname='" + surname + '\'' +
	                ", phoneNumber='" + phoneNumber + '\'' +
	                ", orderCount=" + orderCount +
	                ", onDuty=" + onDuty +
	                ", location=" + loc +
	                '}';
         		*/
    }

    // Other methods, such as acceptDelivery()
}
