package user;

import Code.Location;

public class Customer extends Person {
    //variables specific to Customer
    protected String email;
    protected String cellNumber;
    protected Location loc;

    // Constructor
    public Customer(int ID, String username, String password, String userType, String name, String surname, String email, String cellNumber, Location loc) {
        super(ID, username, password, userType, name, surname); // Call to the superclass constructor
        this.email = email;
        this.cellNumber = cellNumber;
        this.loc = loc;
    }
    
    /*
     * sooo many methods :(
     */
}
