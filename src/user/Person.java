package user;

/**
 * Represents a person, inheriting attributes from the User class.
 */
public class Person extends User {
    // Protected member variables specific to Person
    protected String name;
    protected String surname;

    /**
     * Constructs a Person object with the specified attributes.
     * 
     * @param username the username of the person
     * @param password the password of the person
     * @param userType the type of user (e.g., person)
     * @param name     the name of the person
     * @param surname  the surname of the person
     */
    public Person(String username, String password, String userType, String name, String surname) {
        super(username, password, userType, name); // Call to the superclass constructor
        this.name = name;
        this.surname = surname;
    }

    /**
     * Retrieves the surname of the person.
     * 
     * @return the surname of the person
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets the surname of the person.
     * 
     * @param surname the surname to be set
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Retrieves the name of the person.
     * 
     * @return the name of the person
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the person.
     * 
     * @param name the name to be set
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the full name of the person.
     * 
     * @return the full name of the person
     */
    public String getFullName() {
        return name + " " + surname;
    }
}
