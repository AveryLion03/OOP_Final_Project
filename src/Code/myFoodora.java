package Code;
import java.util.Scanner;
import user.*;
import food.*;

public class myFoodora {
	protected Restaurant avail [];
	private User list [];
	private static int userLoggedIn;
	
	public myFoodora(Restaurant[] avail, User[] list, int userLog) {
		super();
		this.avail = avail;
		this.list = list;
		this.userLoggedIn = userLog;
	}

	public static double getUserLoggedIn() {
		return userLoggedIn;
	}
	protected static void setUserLoggedIn(int val) {
		userLoggedIn = val;
	}

	public Restaurant[] getAvail() {
		return avail;
	}

	protected void setAvail(Restaurant[] avail) {
		this.avail = avail;
	}

	public User[] getList() {
		return list;
	}

	protected void setList(User[] list) {
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

	public static void main (String [] args) {
		 // check if length of args array is
        // greater than 0
		String use;
		try (Scanner inputLine = new Scanner(System.in)) {
			while(true) {
				use = inputLine.nextLine();
				use = use.toLowerCase();
				if(use.equals("help")) {
					printCommands();
				}
				else {
					setUserLoggedIn(Integer.valueOf(use));
					System.out.print(getUserLoggedIn());
				}
			}
		}
	}
}
