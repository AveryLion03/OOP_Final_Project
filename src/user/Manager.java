package user;

/**
 * Represents a manager who oversees operations.
 */
public class Manager extends Person {
    /**
     * Constructs a Manager object with the specified attributes.
     * 
     * @param username the username of the manager
     * @param password the password of the manager
     * @param userType the type of user (e.g., manager)
     * @param name     the name of the manager
     * @param surname  the surname of the manager
     */
    public Manager(String username, String password, String userType, String name, String surname) {
        super(username, password, userType, name, surname);
    }
}
