package user;

public class Manager extends Person {
	
	//Question: In the UML theres no specific variables. Shouldnt a Manager at least have a list of locations that he manges tho?
	
	

	//Constructor
	public Manager(int ID, String username, String password, String userType, String name, String surname) {
		super(ID, username, password, userType, name, surname);
		
	}

}
