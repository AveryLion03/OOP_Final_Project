package Code;

import java.util.ArrayList;
import java.util.Scanner;
import user.*;

public class Parse {
	protected ArrayList<User> members;
    private static Boolean run = true;
    protected int userLoggedIn = 0;
    
    public Parse(ArrayList<User> mem) {
		super();
		this.members = mem;
	}

	public double getUserLoggedIn() {
		return userLoggedIn;
	}
	public void exitLoop() {
		run = false;
	}
	public Boolean getLoop() {
		return run;
	}
	protected void setUserLoggedIn(int val) {
		userLoggedIn = val;
	}
	//Only available for managers -> Prints out list of Restaurants
	protected void showRestaurants() {
		//print out restaurants available
		for(int i = 0; i < members.size(); i++) {
			if(members.get(i).getUserType().equalsIgnoreCase("Restaurant")) {
				System.out.println(members.get(i).getName());
			}
		}
	}
	//Only available for managers -> Prints out list of customers
	protected void showCustomers() {
		//return list;
		for(int i = 0; i < members.size(); i++) {
			if(members.get(i).getUserType().equalsIgnoreCase("Customer")) {
				System.out.println(members.get(i).getName());
			}
		}
	}
	//Only available for managers -> Prints out list of Couriers
	protected void showCouriers() {
		//return list;
		for(int i = 0; i < members.size(); i++) {
			if(members.get(i).getUserType().equalsIgnoreCase("Courier")) {
				System.out.println(members.get(i).getName());
			}
		}
	}
	public void printCommands () {
		/*This will print out all available commands for each type of customer
		 * SHOULD I MAKE A .txt DOCUMENT TO READ OFF COMMANDS?
		 * Not Logged in = 0
		 * Manager = 1
		 * Restaurant = 2
		 * Customer = 3
		 * Courier = 4
		 */
		if(userLoggedIn == 0) {
			System.out.println("Login <username> <password>");
			System.out.println("End <>");
			System.out.println("runTest <testScenario-file>");
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
	public void processCommands(Boolean init, String lines){
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
				members.add(n);
				System.out.println("Customer Added");
			}
			else if(elements[2].toLowerCase().equals("manager")){
				Manager m = new Manager (elements[0], elements[1], elements [2], elements[3], elements[4]);
				members.add(m);
				System.out.println("Manager Added");
			}
			else if(elements[2].toLowerCase().equals("restaurant")) {
				Location loc = new Location (Double.parseDouble(elements[4]), Double.parseDouble(elements[5]));
				Restaurant r = new Restaurant (elements[0], elements[1], elements [2], elements[3], loc);
				members.add(r);
				System.out.println("Restaurant Added");
			}
			else if(elements[2].toLowerCase().equals("courier")) {
				Location loc = new Location (Double.parseDouble(elements[8]), Double.parseDouble(elements[9]));
				Courier c = new Courier(elements[0], elements[1], elements [2], elements[3], elements[4], elements[5], Integer.parseInt(elements[6]), Boolean.parseBoolean(elements[7]), loc);
				members.add(c);
				System.out.println("Courier Added");
			}
		}
		/*
		 * User Interface commands
		 */
		else {
			// Skip Enter key press (no commands)
			if(elements[0].trim().length() == 0) return;
			// Check if User wants to end terminal
			else if(elements[0].toLowerCase().equals("end")) {
				exitLoop();
				return;
			}
			// login <username> <password>
			else if (elements[0].toLowerCase().equals("login")) {
				if(getUserLoggedIn() != 0) {
					System.out.println("User already logged in. Logout first");
					return;
				}
				if(elements.length != 3) {
					System.out.println("Invalid Login Format -> login <username> <password>");
					return;
				}
				// Login for managers
				for(int i = 0; i < members.size(); i++) {
					if((elements[1].trim()).equals(members.get(i).getUsername()) && (elements[2].trim()).equals(members.get(i).getPassword())) {
						System.out.printf("Login Successful. Welcome, %s%n", members.get(i).getName());
						if(members.get(i).getUserType().equalsIgnoreCase("Manager"))setUserLoggedIn(1);
						else if(members.get(i).getUserType().equalsIgnoreCase("Restaurant"))setUserLoggedIn(2);
						else if(members.get(i).getUserType().equalsIgnoreCase("Customer"))setUserLoggedIn(3);
						else setUserLoggedIn(4);
						return;
					}
				}
				
				System.out.println("User not found. Try again using correct Username or Password");
				
			}
			
			// logout <>
			else if (elements[0].toLowerCase().equals("logout")) {
				if(getUserLoggedIn() == 0) System.out.println("No Users Logged In");
				else {
					setUserLoggedIn(0);
					System.out.println("Logout Successful");
				}
			}
			// help <>
			else if (elements[0].toLowerCase().equals("help")) {
				System.out.println("Here are your available commands:");
				printCommands();
			}
			// Check to ensure we are logged in first
			else if(getUserLoggedIn() == 0) System.out.println("Login first before using commands. Type 'help' for more information");
			// registerRestaurant <name> <address> <username> <password> 
			else if (elements[0].toLowerCase().equals("registerrestaurant")) {
				if(getUserLoggedIn() != 1) {
					System.out.println("User cannot access this command");
					return;
				}
				//Invalid command
				if(elements.length != 6) {
					System.out.println("Invalid Command. Use following format: registerRestaurant <name> <address> <username> <password>");
					return;
				}
				//ADD TRY AND CATCH FOR INVALID INPUT LOCATION?
				Location loc = new Location (Double.parseDouble(elements[2]), Double.parseDouble(elements[3]));
				Restaurant r = new Restaurant(elements[4], elements[5], "Restaurant", elements[1], loc);
				System.out.println("Successfully added");
				members.add(r);
			}
			// registerCustomer <firstName> <lastName> <username> <address> <password>
			else if (elements[0].toLowerCase().equals("registercustomer")) {
				if(getUserLoggedIn() != 1) {
					System.out.println("User cannot access this command");
					return;
				}
				//Invalid command
				if(elements.length != 7) {
					System.out.println("Invalid Command. Use following format: registerCustomer <firstName> <lastName> <username> <address> <password>");
					return;
				}
				//ADD TRY AND CATCH FOR INVALID INPUT LOCATION?
				//String username, String password, String userType, String name, String surname, String email, String cellNumber, Location loc
				Location loc = new Location (Double.parseDouble(elements[4]), Double.parseDouble(elements[5]));
				Customer c = new Customer(elements[3], elements[6], "Customer", elements[1], elements[2], "blank@gmail.com", "***-***-****", loc);
				Scanner inputLine = new Scanner(System.in);
				System.out.println("Please input your email and phone number in the following format: <email> <phone number>");
				String s = (inputLine.nextLine()).toString();
				String[] e = s.split(" ");
				for(int i = 0; i < e.length; i++) {
					e[i] = e[i].trim();
				}
				c.setCellNumber(e[1]);
				c.setEmail(e[0]);
				System.out.println("Successfully added");
				members.add(c);
			}
			// registerCourier <firstName> <lastName> <username> <position> <password>
			else if (elements[0].toLowerCase().equals("registercourier")) {
				if(getUserLoggedIn() != 1) {
					System.out.println("User cannot access this command");
					return;
				}
				//Invalid command
				if(elements.length != 6) {
					System.out.println("Invalid Command. Use following format: registerCourier <firstName> <lastName> <username> <position> <password>");
					return;
				}
				//ADD TRY AND CATCH FOR INVALID INPUT LOCATION?
				//String username, String password, String userType, String name, String surname, String phoneNumber, int orderCount, boolean onDuty, Location loc
				Location loc = new Location(0, 0);
				Courier d = new Courier(elements[3], elements[5], "Courier", elements[1], elements[2], "***-***-****", 0, false, loc);
				System.out.println("Successfully added");
				members.add(d);
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
			else {
				System.out.println("\033[0;31mInvalid Command\033[0m");
			}
		}
	}
}
