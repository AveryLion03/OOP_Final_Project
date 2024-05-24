package user;

public class User {
    // Protected member variables
    protected int ID;
    protected String username;
    protected String password;
    protected String userType;
    
    
    //Adjust to number of users that are automatically loaded. if three users are automatically loaded, counter should start at 4
    private static int counter=0;

    // Constructor
    public User(int ID, String username, String password, String userType) {
        User.counter ++;
    	this.ID = ID;
        this.username = username;
        this.password = password;
        this.userType = userType;
    }
    
    //getters and setters for everything EXCEPT:
    //no get password because noone should have access to it
    //no set ID because it is assigned automatically through the counter

	public String getUsername() {
		return username;
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
    
    
/*
 * *getters and setter?
 *login()

*register()

*updateInfo()

*runTest()

*help()

 */
    

}
