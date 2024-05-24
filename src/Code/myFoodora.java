package Code;
import java.util.ArrayList;
import java.util.Scanner;
import user.*;
import food.*;

public class myFoodora {
	protected Restaurant avail [];
	protected Courier drivers [];
	protected Customer list [];
	protected static int userLoggedIn;
	
	@SuppressWarnings("static-access")
	public myFoodora(Restaurant[] avail, Courier [] driv, Customer[] list, int userLog) {
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
	protected Restaurant[] showRestaurants() {
		return avail;
	}

	protected void setRestaurants(Restaurant[] avail) {
		this.avail = avail;
	}
	//Only available for managers -> Prints out list of customers
	protected void showCustomers() {
		//return list;
	}

	protected void setCustomerList(Customer[] list) {
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
		
	}
	public static void main (String [] args) {
		 // check if length of args array is
		
		//Run initialization program:
		Scanner scanner = new Scanner("startUp.txt");
	    ArrayList<String> lines = new ArrayList<>(); // just something to hold the lines of the text file seperately
	    while (scanner.hasNextLine()){
	        lines.add(scanner.nextLine());          // read all lines of the text file
	    }
	    for (int i = 0; i< lines.size(); i++) {
	    	
	    }

        // greater than 0
		boolean exit = false;
		try (Scanner inputLine = new Scanner(System.in)) {
			while(!exit) {
				//Read first command -> decide what to do from here
				String s = (inputLine.next()).toString();
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
			}
		}
	}
}
