package Code;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import user.*;
import food.*;

public class myFoodora {
    protected static ArrayList<Restaurant> avail = new ArrayList<>();
    protected static ArrayList<Courier> drivers = new ArrayList<>();
    protected static ArrayList<Customer> list = new ArrayList<>();
    protected static int userLoggedIn;
	
	@SuppressWarnings("static-access")
	public myFoodora(ArrayList<Restaurant> avail, ArrayList<Courier> driv, ArrayList<Customer> list, int userLog) {
		super();
		this.avail = avail;
		this.list = list;
		this.drivers = driv;
		this.userLoggedIn = userLog;
	}

	public static double getUserLoggedIn() {
		return userLoggedIn;
	}
	protected static void setUserLoggedIn(int val) {
		userLoggedIn = val;
	}
	//Only available for managers -> Prints out list of customers
	protected ArrayList<Restaurant> showRestaurants() {
		return avail;
	}

	protected void setRestaurants(ArrayList<Restaurant> avail) {
		this.avail = avail;
	}
	//Only available for managers -> Prints out list of customers
	protected void showCustomers() {
		//return list;
	}

	protected void setCustomerList(ArrayList<Customer> list) {
		this.list = list;
	}
	public static void printCommands () {
		/*This will print out all available commands for each type of customer
		 * Not Logged in = 0
		 * Manager = 1
		 * Restaurant = 2
		 * Customer = 3
		 * Courier = 4
		 */
		if(userLoggedIn == 0) {
			System.out.println("Login Required");
		}
		//Manager Commands
		else if (userLoggedIn == 1) {
			System.out.println("Commands Available for Managers:");
			//Print out table of commands
		}
		//Restaurant Commands
		else if (userLoggedIn == 2) {
			System.out.println("Commands Available for Managers:");
			//Print out table of commands
		}
		//Customer Commands
		else if (userLoggedIn == 3) {
			System.out.println("Commands Available for Managers:");
			//Print out table of commands
		}
		//Courier Commands
		else {
			System.out.println("Commands Available for Managers:");
			//Print out table of commands
		}
	}

	
	//Process Commands?
	public static void processCommands(String lines) {
		String[] elements = lines.split(",");
		for(int i = 0; i < elements.length; i++) {
			elements[i] = elements[i].trim();
		}
		if(elements[0].toLowerCase().equals("end")) {
			return;
		}
		else if(elements[2].toLowerCase().equals("customer")) {
			//String username, String password, String userType, String name, String surname, String email, String cellNumber, Location loc
			Location loc = new Location (Double.parseDouble(elements[7]), Double.parseDouble(elements[8]));
			Customer n = new Customer (elements[0], elements [1], elements [2], elements [3], elements [4], elements [5], elements[6], loc);
			list.add(n);
			System.out.println("Customer Added");
		}
		else if(elements[2].toLowerCase().equals("manager")){
			Manager m = new Manager (elements[0], elements[1], elements [2], elements[3], elements[4]);
			System.out.println("Manager Added");
		}
		else {
			System.out.println("No One Added");
		}
	}

	public static void main (String [] args) {
		 // check if length of args array is
		
		//*********** Run initialization program: ***********
		File start = new File("Code/startUp.txt");
		//System.out.println("Absolute path: " + start.getAbsolutePath());
	    ArrayList<String> lines = new ArrayList<>(); // just something to hold the lines of the text file seperately
	    try (Scanner scanner = new Scanner(start)){
		    while (scanner.hasNextLine()){
		        lines.add(scanner.nextLine());          // read all lines of the text file
		    }
		    for (int i = 0; i< lines.size(); i++) {
		    	myFoodora.processCommands(lines.get(i));
		    }
	    } catch (FileNotFoundException e) {
	    	System.err.println("File not found: " + e.getMessage());
	    }
	    //***************** Begin CLUI **********************
        // greater than 0
		boolean exit = false;
		try (Scanner inputLine = new Scanner(System.in)) {
			while(!exit) {
				//Read first command -> decide what to do from here
				String s = (inputLine.next()).toString();
				s.trim();
				s = s.toLowerCase();
				if(s.equals("help")) {
					printCommands();
				}
				else if(s.equals("login")) {
					//Compare to user list -> username and password
					setUserLoggedIn(inputLine.nextInt());
					System.out.println(getUserLoggedIn());
				}
				else if (s.equals("logout")){
					setUserLoggedIn(0);
					//System.out.println(getUserLoggedIn());
					//printCommands();
				}
				else if (s.equals("getuserphone")){
					int a = inputLine.nextInt();
					
					System.out.println(list.get(a).getFullName() + ": " + list.get(a).getCellNumber());
					//System.out.println(getUserLoggedIn());
					//printCommands();
				}
				else if (s.equals("printusers")) {
					System.out.printf("There are %d Users: %n", list.size());
					for(Customer elem : list) {
						//System.out.println(elem); //Make a print statement for each user
						System.out.println(elem.getFullName());
					}
				}
				else if(s.equals("end")) {
					exit = true;
				}
			}
		}
	}
}
