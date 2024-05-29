package user;

import Code.Location;

public class Customer extends Person {
    // Variables specific to Customer
    private String email;
    private String cellNumber;
    private Location loc;
    private String fidelity;
    private int points;

    // Constructor
    public Customer(String username, String password, String userType, String name, String surname, String email, String cellNumber, Location loc) {
        super(username, password, userType, name, surname); // Call to the superclass constructor
        this.email = email;
        this.cellNumber = cellNumber;
        this.loc = loc;
        this.fidelity = "none"; // Default fidelity
        this.points = 0; // Default points
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

    public void setFidelity(String fidelity) {
        if (isValidFidelity(fidelity)) {
            this.fidelity = fidelity;
        } else {
            throw new IllegalArgumentException("Invalid fidelity value. Options are none, basic, point, or lottery.");
        }
    }

    private boolean isValidFidelity(String fidelity) {
        return fidelity.equals("none") || fidelity.equals("basic") || fidelity.equals("point") || fidelity.equals("lottery");
    }

    public int getPoints() {
        return points;
    }

    // Setter for points
    // Depending on your system design, you may choose to set points directly or through some other mechanism.
    // If points are managed internally by the system, you might not need this setter.
    public void setPoints(int points) {
        this.points = points;
    }
}
