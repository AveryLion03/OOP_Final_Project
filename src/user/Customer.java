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

    /**
     * Constructs a new Customer object with the specified attributes.
     * 
     * @param username   the username of the customer
     * @param password   the password of the customer
     * @param userType   the type of user (e.g., customer)
     * @param name       the name of the customer
     * @param surname    the surname of the customer
     * @param email      the email address of the customer
     * @param cellNumber the cell phone number of the customer
     * @param loc        the location of the customer
     */
    public Customer(String username, String password, String userType, String name, String surname, String email,
            String cellNumber, Location loc) {
        super(username, password, userType, name, surname); // Call to the superclass constructor
        this.email = email;
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

    /**
     * Sets the fidelity status of the customer.
     * 
     * @param fidelity the fidelity status to set (e.g., Basic, Point, Lottery)
     * @return true if fidelity status is successfully set, false otherwise
     */
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
        return fidelity == null || fidelity.equalsIgnoreCase("basic") || fidelity.equalsIgnoreCase("point")
                || fidelity.equalsIgnoreCase("lottery");
    }

    public int getPoints() {
        return points;
    }

    /**
     * Adds points to the customer's fidelity points.
     * 
     * @param p the points to add
     */
    public void setPoints(double p) {
        this.points += p;
    }

    /**
     * Checks if the customer's fidelity points qualify for a discount.
     * 
     * @param points the points needed for a discount
     * @return true if the customer qualifies for a discount, false otherwise
     */
    public boolean pointsDiscount(int points) {
        if (this.fidelity.equalsIgnoreCase("Point")) {
            this.points += points;
            if (this.points >= 100) { // If the customer has accumulated over 100 points, they receive a discount
                return true;
            }
        }
        return false;
    }

    /**
     * Retrieves the active order associated with the customer.
     * 
     * @return the active order associated with the customer
     */
    public Order getActiveOrder() {
        return this.activeOrder;
    }

    /**
     * Sets the active order associated with the customer.
     * 
     * @param o the order to set as active
     */
    public void setActiveOrder(Order o) {
        this.activeOrder = o;
    }

    /**
     * Checks if the current order of the customer is free.
     * 
     * @return true if the current order is free, false otherwise
     */
    public boolean isFreeOrder() {
        return freeOrder;
    }

    /**
     * Sets whether the current order of the customer is free.
     * 
     * @param freeOrder true if the current order is free, false otherwise
     */
    public void setFreeOrder(boolean freeOrder) {
        this.freeOrder = freeOrder;
    }
}
