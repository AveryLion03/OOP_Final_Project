package user;

import java.util.ArrayList;
import java.util.Objects;
import food.*;

/**
 * The Courier class represents a courier who delivers orders.
 * It extends the Person class and adds specific properties and methods for a courier.
 */
public class Courier extends Person {

    /** The phone number of the courier. */
    protected String phoneNumber;
    
    /** The number of orders the courier has delivered. */
    protected int orderCount;
    
    /** Flag indicating whether the courier is currently on duty. */
    protected boolean onDuty;
    
    /** The current location of the courier. */
    protected Location loc;
    
    /** The list of orders assigned to the courier for delivery. */
    protected ArrayList<Order> currentJobs;

    /**
     * Constructs a new Courier object with the specified details.
     *
     * @param username the username of the courier
     * @param password the password of the courier
     * @param userType the type of user (e.g., courier)
     * @param name the name of the courier
     * @param surname the surname of the courier
     * @param phoneNumber the phone number of the courier
     * @param orderCount the number of orders delivered by the courier
     * @param onDuty the availability status of the courier
     * @param loc the current location of the courier
     */
    public Courier(String username, String password, String userType, String name, String surname, String phoneNumber, int orderCount, boolean onDuty, Location loc) {
        super(username, password, userType, name, surname); // Call to the superclass constructor
        this.phoneNumber = phoneNumber;
        this.orderCount = orderCount;
        this.onDuty = onDuty;
        this.loc = loc;
        this.currentJobs = new ArrayList<>();
    }

    /**
     * Retrieves the phone number of the courier.
     *
     * @return the phone number of the courier
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    /**
     * Retrieves the list of orders assigned to the courier.
     *
     * @return the list of orders assigned to the courier
     */
    public ArrayList<Order> getJobs(){
        return this.currentJobs;
    }
    
    /**
     * Checks if the courier is available for new deliveries.
     *
     * @return true if the courier is available; false otherwise
     */
    public boolean available() {
        return this.onDuty;
    }
    
    /**
     * Assigns an order to the courier for delivery.
     *
     * @param o the order to be assigned to the courier
     */
    public void setOrder(Order o) {
        this.currentJobs.add(o);
        this.orderCount++;
    }
    /**
     * Marks the specified order as finished by setting its delivery status to true.
     *
     * @param o the order to be marked as finished
     */
    public void finishJob(Order o) {
        for (Order order : this.currentJobs) {
            if (order.getOrderName().equals(o.getOrderName())) {
                order.setDelivery(true);
            }
        }
    }

    /**
     * Sets the phone number of the courier.
     *
     * @param phoneNumber the phone number to be set for the courier
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Checks if the courier is currently on duty.
     *
     * @return true if the courier is on duty; false otherwise
     */
    public boolean isOnDuty() {
        return onDuty;
    }

    /**
     * Sets the duty status of the courier.
     *
     * @param onDuty true if the courier is on duty; false otherwise
     */
    public void setOnDuty(boolean onDuty) {
        this.onDuty = onDuty;
    }

    /**
     * Retrieves the current location of the courier.
     *
     * @return the current location of the courier
     */
    public Location getLoc() {
        return loc;
    }

    /**
     * Sets the location of the courier.
     *
     * @param l the new location to be set for the courier
     */
    public void setLoc(Location l) {
        this.loc.setLocation(l);
    }

    /**
     * Sets the location of the courier using latitude and longitude coordinates.
     *
     * @param lat the latitude coordinate of the new location
     * @param longitude the longitude coordinate of the new location
     */
    public void setLoc(Double lat, Double longitude) {
        this.loc.setLocation(lat, longitude);
    }

    /**
     * Retrieves the total number of orders delivered by the courier.
     *
     * @return the total number of orders delivered by the courier
     */
    public int getOrderCount() {
        return orderCount;
    }

    /**
     * Checks if the courier is currently on duty.
     *
     * @return true if the courier is on duty; false otherwise
     */
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
        return "Courier " + name 
        		+ " " + surname 
        		+ ": " + orderCount;
    }

    // Other methods, such as acceptDelivery()
}
