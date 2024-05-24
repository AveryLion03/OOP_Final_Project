package Code;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import user.*;
import food.*;

public class myFoodora {
    protected static ArrayList<Restaurant> rest = new ArrayList<>();
    protected static ArrayList<Courier> drivers = new ArrayList<>();
    protected static ArrayList<Customer> cust = new ArrayList<>();
    protected static ArrayList<Manager> manager = new ArrayList<>();
    private static Boolean run = true;
    protected static int userLoggedIn = 0;
	
	@SuppressWarnings("static-access")
	public myFoodora(ArrayList<Restaurant> restaurants, ArrayList<Courier> driv, ArrayList<Customer> list, ArrayList<Manager> manag, int userLog) {
		super();
		this.rest = restaurants;
		this.cust = list;
		this.manager = manag;
		this.drivers = driv;
		this.userLoggedIn = userLog;
	}

	public static double getUserLoggedIn() {
		return userLoggedIn;
	}
	public static void exitLoop() {
		run = false;
	}
	public static Boolean getLoop() {
		return run;
	}
	protected static void setUserLoggedIn(int val) {
		userLoggedIn = val;
	}
	//Only available for managers -> Prints out list of customers
	protected ArrayList<Restaurant> showRestaurants() {
		return rest;
	}

	protected void setRestaurants(ArrayList<Restaurant> avail) {
		this.rest = avail;
	}
	//Only available for managers -> Prints out list of customers
	protected void showCustomers() {
		//return list;
	}

	protected void setCustomerList(ArrayList<Customer> list) {
		this.cust = list;
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
			System.out.println("Login <username> <password>");
		}
		//Manager Commands
		else if (userLoggedIn == 1) {
			System.out.println("Commands Available for Managers:");
			//Print out table of commands
		}
		//Restaurant Commands
		else if (userLoggedIn == 2) {
			System.out.println("Commands Available for Restaurants:");
			//Print out table of commands
		}
		//Customer Commands
		else if (userLoggedIn == 3) {
			System.out.println("Commands Available for Customers:");
			//Print out table of commands
		}
		//Courier Commands
		else {
			System.out.println("Commands Available for Couriers:");
			//Print out table of commands
		}
	}

	
	//Process Commands?
	public static void processCommands(Boolean init, String lines){
		String[] elements;
		if(init) {
			elements = lines.split(",");
			for(int i = 0; i < elements.length; i++) {
				elements[i] = elements[i].trim();
			}
		}
		else {
			elements = lines.split(" ");
			for(int i = 0; i < elements.length; i++) {
				elements[i] = elements[i].trim();
			}
		}		
		/*
		 *  Initialization Methods (startUp.txt) -> Customers, Managers, Restaurants, Couriers
		 */
		if(init) {
			if(elements[2].toLowerCase().equals("customer")) {
				//String username, String password, String userType, String name, String surname, String email, String cellNumber, Location loc
				Location loc = new Location (Double.parseDouble(elements[7]), Double.parseDouble(elements[8]));
				Customer n = new Customer (elements[0], elements [1], elements [2], elements [3], elements [4], elements [5], elements[6], loc);
				cust.add(n);
				System.out.println("Customer Added");
			}
			else if(elements[2].toLowerCase().equals("manager")){
				Manager m = new Manager (elements[0], elements[1], elements [2], elements[3], elements[4]);
				manager.add(m);
				System.out.println("Manager Added");
			}
			else if(elements[2].toLowerCase().equals("restaurant")) {
				Location loc = new Location (Double.parseDouble(elements[4]), Double.parseDouble(elements[5]));
				Restaurant r = new Restaurant (elements[0], elements[1], elements [2], elements[3], loc);
				rest.add(r);
				System.out.println("Restaurant Added");
			}
			else if(elements[2].toLowerCase().equals("courier")) {
				Location loc = new Location (Double.parseDouble(elements[8]), Double.parseDouble(elements[9]));
				Courier c = new Courier(elements[0], elements[1], elements [2], elements[3], elements[4], elements[5], Integer.parseInt(elements[6]), Boolean.parseBoolean(elements[7]), loc);
				drivers.add(c);
				System.out.println("Courier Added");
			}
		}
		/*
		 * User Interface commands
		 */
		else {
			/*
			 * First Check to see if user wants to end terminal
			 */
			if(elements[0].toLowerCase().equals("end")) {
				exitLoop();
				return;
			}
			// login <username> <password>
			else if (elements[0].toLowerCase().equals("login")) {
				if(getUserLoggedIn() != 0) {
					System.out.println("User already logged in. Logout first");
					return;
				}
				if(elements.length < 3 || elements.length > 3) {
					System.out.println("Invalid Login Format -> login <username> <password>");
					return;
				}
				for(int i = 0; i < myFoodora.manager.size(); i++) {
					if((elements[1].trim()).equals(myFoodora.manager.get(i).getUsername()) && (elements[2].trim()).equals(myFoodora.manager.get(i).getPassword())) {
						System.out.printf("Login Successful. Welcome, %s%n", myFoodora.manager.get(i).getFullName());
						setUserLoggedIn(1);
						break;
					}
				}
				
			}
			// logout <>
			else if (elements[0].toLowerCase().equals("logout")) {
				if(getUserLoggedIn() == 0) System.out.println("No Users Logged In");
				else {
					setUserLoggedIn(0);
					System.out.println("Logout Successful");
				}
			}
			// registerRestaurant <name> <address> <username> <password>
			else if (elements[0].toLowerCase().equals("registerrestaurant")) {
				
			}
			else if (elements[0].toLowerCase().equals("registercustomer")) {
			    // registerCustomer <firstName> <lastName> <username> <address> <password>
			}
			else if (elements[0].toLowerCase().equals("registercourier")) {
			    // registerCourier <firstName> <lastName> <username> <position> <password>
			}
			else if (elements[0].toLowerCase().equals("adddishrestaurantmenu")) {
			    // addDishRestauarantMenu <dishName> <dishCategory> <foodCategory> <unitPrice>
			}
			else if (elements[0].toLowerCase().equals("createmeal")) {
			    // createMeal <mealName>
			}
			else if (elements[0].toLowerCase().equals("adddish2meal")) {
			    // addDish2Meal <dishName> <mealName>
			}
			else if (elements[0].toLowerCase().equals("showmeal")) {
			    // showMeal <mealName>
			}
			else if (elements[0].toLowerCase().equals("savemeal")) {
			    // saveMeal <mealName>
			}
			else if (elements[0].toLowerCase().equals("setspecialoffer")) {
			    // setSpecialOffer <mealName>
			}
			else if (elements[0].toLowerCase().equals("removefromspecialoffer")) {
			    // removeFromSpecialOffer <mealName>
			}
			else if (elements[0].toLowerCase().equals("createorder")) {
			    // createOrder <restaurantName> <orderName>
			}
			else if (elements[0].toLowerCase().equals("additem2order")) {
			    // addItem2Order <orderName> <itemName>
			}
			else if (elements[0].toLowerCase().equals("endorder")) {
			    // endOrder <orderName> <date>
			}
			else if (elements[0].toLowerCase().equals("onduty")) {
			    // onDuty <username>
			}
			else if (elements[0].toLowerCase().equals("offduty")) {
			    // offDuty <username>
			}
			else if (elements[0].toLowerCase().equals("finddeliverer")) {
			    // findDeliverer <orderName>
			}
			else if (elements[0].toLowerCase().equals("setdeliverypolicy")) {
			    // setDeliveryPolicy <delPolicyName>
			}
			else if (elements[0].toLowerCase().equals("setprofitpolicy")) {
			    // setProfitPolicy <ProfitPolicyName>
			}
			else if (elements[0].toLowerCase().equals("associatecard")) {
			    // associateCard <userName> <cardType>
			}
			else if (elements[0].toLowerCase().equals("showcourierdeliveries")) {
			    // showCourierDeliveries <>
			}
			else if (elements[0].toLowerCase().equals("showrestauranttop")) {
			    // showRestaurantTop <>
			}
			else if (elements[0].toLowerCase().equals("showcustomers")) {
			    // showCustomers <>
			}
			else if (elements[0].toLowerCase().equals("showmenuitem")) {
			    // showMenuItem <restaurant-name>
			}
			else if (elements[0].toLowerCase().equals("showtotalprofit")) {
			    // showTotalProfit <>
			}
			else if (elements[0].toLowerCase().equals("showtotalprofitperiod")) {
			    // showTotalProfit <startDate> <endDate>
			}
			else if (elements[0].toLowerCase().equals("runtest")) {
			    // runTest <testScenario-file>
			}
			else if (elements[0].toLowerCase().equals("help")) {
			    // help <>
			}
			else {
				System.out.println("Invalid Command");
			}
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
		    	myFoodora.processCommands(true, lines.get(i));
		    }
	    } catch (FileNotFoundException e) {
	    	System.err.println("File not found: " + e.getMessage());
	    }
	    
	    //***************** Begin CLUI **********************
        // greater than 0
		try (Scanner inputLine = new Scanner(System.in)) {
			while(getLoop()) {
				//Read first command -> decide what to do from here
				String s = (inputLine.nextLine()).toString();
				//System.out.println(s);
				myFoodora.processCommands(false, s);
				
			}
		}
	}
}
