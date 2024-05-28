package user;

public class Person extends User {
    // Protected member variables specific to Person
    protected String name;
    protected String surname;

    // Constructor
    public Person(String username, String password, String userType, String name, String surname) {
        super(username, password, userType, name); // Call to the superclass constructor
        this.name = name;
        this.surname = surname;
    }
    
    

    public String getSurname() {
		return surname;
	}



	public void setSurname(String surname) {
		this.surname = surname;
	}


	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}


	// get  full name
    public String getFullName() {
        return name + " " + surname;
    }
    
    //no other methods?
}
