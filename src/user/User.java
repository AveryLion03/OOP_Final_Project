package user;

/**
 * Represents a user in the system.
 */
public class User {
    // Protected member variables
    protected int ID;
    protected String username;
    protected String password;
    protected String userType;
    protected String Name;
    
    
    //Adjust to number of users that are automatically loaded. if three users are automatically loaded, counter should start at 4
    //theoretically we´d have a database with users, count the entries and then add 1
    private static int counter = 0;

    /**
     * Constructs a User object with the specified attributes.
     * 
     * @param username the username of the user
     * @param password the password of the user
     * @param userType the type of the user (e.g., customer, restaurant)
     * @param name     the name of the user
     */
    public User(String username, String password, String userType, String name) {
        //User.counter ++;
        this.ID = ++User.counter;
        this.Name = name;
        this.username = username;
        this.password = password;
        this.userType = userType;
    }
    
    //getters and setters for everything EXCEPT:
    //no get password because no one should have access to it?
    //no set ID because it is assigned automatically through the counter

    /**
     * Retrieves the username of the user.
     * 
     * @return the username of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Retrieves the name of the user.
     * 
     * @return the name of the user
     */
    public String getName() {
        return Name;
    }
    
    /**
     * Sets the name of the user.
     * 
     * @param name the name to set
     */
    public void setName(String name) {
        this.Name = name;
    }

    /**
     * Sets the username of the user.
     * 
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Retrieves the user type of the user.
     * 
     * @return the user type of the user
     */
    public String getUserType() {
        return userType;
    }

    /**
     * Sets the user type of the user.
     * 
     * @param userType the user type to set
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     * Retrieves the ID of the user.
     * 
     * @return the ID of the user
     */
    public int getID() {
        return ID;
    }

    /**
     * Sets the password of the user.
     * 
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * Retrieves the password of the user.
     * 
     * @return the password of the user
     */
    public String getPassword() {
        return password;
    } 
}
