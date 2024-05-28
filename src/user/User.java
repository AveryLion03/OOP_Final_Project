package user;

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

    // Constructor
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

	public String getUsername() {
		return username;
	}

	public String getName() {
		return Name;
	}
	public void setName(String name) {
		this.Name = name;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public int getID() {
		return ID;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return password;
	}
    
    
/*
 * 
 *login()

*register()

*updateInfo()

*runTest()

*help()

 */
    

}
