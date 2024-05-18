package user;

public class Person extends User {
    // Protected member variables specific to Person
    protected String name;
    protected String surname;

    // Constructor
    public Person(int ID, String username, String password, String userType, String name, String surname) {
        super(ID, username, password, userType); // Call to the superclass constructor
        this.name = name;
        this.surname = surname;
    }

    // get  full name
    public String getName() {
        return name + " " + surname;
    }
    
    /*
     * other methods- getters, setters?
     */
}
