package Commands;
import food.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import Code.*;
import user.*;
public class CommandExecutor implements CommandVisitor {
	private SystemState systemState;

    public CommandExecutor(SystemState systemState) {
        this.systemState = systemState;
    }

	
    @Override
    public void visitManagerCommand(String [] command) {	
        // Execute manager commands
        // System.out.println("Executing manager command: " + command);
        
    	// registerManager <name> <lastname> <username> <password>
        if (command[0].equalsIgnoreCase("registermanager")) {
            if (systemState.getUserLoggedIn() != 1  && !systemState.getAuto()) {
                System.out.println("User cannot access this command");
                return;
            }

            if (command.length != 5) {
                System.out.println("Invalid Command. Use following format: registerManager <name> <lastname> <username> <password>");
                return;
            }
            try {
                Manager m = new Manager(command[3], command[4], "Manager", command[1], command[2]);
                System.out.println("Successfully added manager");
                systemState.getActiveMembers().add(m);
            } catch (Exception e) {
                System.out.println("An error occurred while registering the manager.");
                e.printStackTrace();
            }
        }
      
      // registerRestaurant <name> <Latitude> <Longitude> <username> <password>
        if (command[0].equalsIgnoreCase("registerrestaurant")) {
            if (systemState.getUserLoggedIn() != 1  && !systemState.getAuto()) {
                System.out.println("User cannot access this command");
                return;
            }

            if (command.length != 6) {
                System.out.println("Invalid Command. Use following format: registerRestaurant <name> <Latitude> <Longitude> <username> <password>");
                return;
            }

            try {
            	Location loc = new Location(Double.parseDouble(command[2]), Double.parseDouble(command[3]));
            	loc = new Location(Double.parseDouble(command[2]), Double.parseDouble(command[3]));
                Restaurant r = new Restaurant(command[4], command[5], "Restaurant", command[1], loc);
                System.out.println("Successfully added restaurant");
                systemState.getActiveMembers().add(r);
            } catch (NumberFormatException e) {
                System.out.println("Invalid latitude or longitude format.");
            } catch (Exception e) {
                System.out.println("An error occurred while registering the restaurant.");
                e.printStackTrace();
            }
        }

        // registerCustomer <firstName> <lastName> <username> <Latitude> <Longitude> <password>
        else if (command[0].equalsIgnoreCase("registercustomer")) {
            if (systemState.getUserLoggedIn() != 1  && !systemState.getAuto()) {
                System.out.println("User cannot access this command");
                return;
            }

            if (command.length != 7) {
                System.out.println("Invalid Command. Use following format: registerCustomer <firstName> <lastName> <username> <Latitude> <Longitude> <password>");
                return;
            }

            try {
                Location loc = new Location(Double.parseDouble(command[4]), Double.parseDouble(command[5]));
                Customer c = new Customer(command[3], command[6], "Customer", command[1], command[2], "blank@gmail.com", "***-***-****", loc);
                try {
                    @SuppressWarnings("resource")
                    Scanner inputLine = new Scanner(System.in);
                    String s;
                    String[] e = {"empty", "empty"};
                    if(!systemState.getAuto()) {
	                    while (true) {
	                    	System.out.println("Please input your Email and Phone Number in the following format: <Email> <Phone_Number>");
	                        s = inputLine.nextLine().toString();
	                        e = s.split(" ");
	                        if (e.length != 2) {
	                            System.out.println("Incorrect format. Enter in the following format: <Email> <Phone_Number>");
	                        } else {
	                            break;
	                        }
	                    }
                    }
                    // Additional section for processing when systemState.getAuto() is true
                    else {
                        // System.out.println("Auto mode is enabled. Reading the next line...");
                        String nextLine = null;
                        // Read the next line from the startup.txt file
                        BufferedReader br = systemState.getBr();
                        nextLine = br.readLine();
                        if (nextLine != null) {
                            e = nextLine.split(" ");
                            if (e.length != 2) {
                                System.out.println("Incorrect format. Enter in the following format: <Email> <Phone_Number>");
                            }
                        } else {
                            System.out.println("No more lines found in the file.");
                        }
                    }
                    c.setEmail(e[0].trim());
                    c.setCellNumber(e[1].trim());
                } catch (Exception e) {
                    System.err.println("An error occurred: " + e.getMessage());
                }               
                System.out.println("Successfully added customer");
                systemState.getActiveMembers().add(c);
            } catch (NumberFormatException e) {
                System.out.println("Invalid latitude or longitude format.");
            } catch (Exception e) {
                System.out.println("An error occurred while registering the customer.");
                e.printStackTrace();
            }
        }
        // registerCourier <firstName> <lastName> <username> <position> <password>
        else if (command[0].equalsIgnoreCase("registercourier")) {
            if (systemState.getUserLoggedIn() != 1  && !systemState.getAuto()) {
                System.out.println("User cannot access this command");
                return;
            }

            if (command.length != 6) {
                System.out.println("Invalid Command. Use following format: registerCourier <firstName> <lastName> <username> <position> <password>");
                return;
            }

            Courier d = new Courier(command[3], command[5], "Courier", command[1], command[2], "***-***-****", 0, false, new Location(0, 0));
            try {
                @SuppressWarnings("resource")
				Scanner inputLine = new Scanner(System.in);
                String s;
                String[] e = {null, null, null};
                if(!systemState.getAuto()) {
                	System.out.println("Please input your Phone Number and Location in the following format: <phone number> <Latitude> <Longitude>");
	                while (true) {
	                    s = inputLine.nextLine().toString();
	                    e = s.split(" ");
	                    if (e.length != 3) {
	                        System.out.println("Incorrect format. Enter in the following format: <phone number> <Latitude> <Longitude>");
	                    } else {
	                        break;
	                    }
	                }
                }
                else {
                    // System.out.println("Auto mode is enabled. Reading the next line...");
                    String nextLine = null;
                    // Read the next line from the startup.txt file
                    BufferedReader br = systemState.getBr();
                    nextLine = br.readLine();
                    if (nextLine != null) {
                        e = nextLine.split(" ");
                        if (e.length != 3) {
                            System.out.println("Incorrect format. Enter in the following format: <phone number> <Latitude> <Longitude>");
                        }
                    } else {
                        System.out.println("No more lines found in the file.");
                    }
                }
                d.setPhoneNumber(e[0].trim());
                d.getLoc().setLocation(Double.parseDouble(e[1].trim()), Double.parseDouble(e[2].trim()));
                System.out.println("Successfully added courier");
                systemState.getActiveMembers().add(d);
            } catch (NumberFormatException e) {
                System.out.println("Invalid latitude or longitude format.");
            } catch (Exception e) {
                System.out.println("An error occurred while registering the courier.");
                e.printStackTrace();
            }
        }
        

		
		
		// setDeliveryPolicy <delPolicyName>
		else if (command[0].equalsIgnoreCase("setdeliverypolicy")) {
			if (systemState.getUserLoggedIn() != 1  && !systemState.getAuto()) {
		        System.out.println("User does not have access to this command.");
		        return;
		    }
		    // Validate the command format
		    if (command.length != 2) {
		        System.out.println("Invalid Command. Use the following format: findDeliverer <orderName>");
		        return;
		    }
		    if(command[1].equalsIgnoreCase("Fast")) {
		    	systemState.setDeliveryPolicy("Fast");
		    	System.out.println("Delivery policy set.");
		    	return;
		    }
		    else if(command[1].equalsIgnoreCase("Fair")) {
		    	systemState.setDeliveryPolicy("Fair");
			    System.out.println("Delivery policy set.");
		    	return;
		    }
		    System.out.println("Unable to find policy name. Your two options are:\n1. Fast\n2. Fair");
		    return;
		}
		
		// setProfitPolicy <ProfitPolicyName> 
		else if (command[0].equalsIgnoreCase("setprofitpolicy")) {
			if (systemState.getUserLoggedIn() != 1  && !systemState.getAuto()) {
		        System.out.println("User does not have access to this command.");
		        return;
		    }
		    // Validate the command format
		    if (command.length != 2) {
		        System.out.println("Invalid Command. Use the following format:  setProfitPolicy <ProfitPolicyName>");
		        return;
		    }
		    if(command[1].equalsIgnoreCase("targetProfit_DeliveryCost")) {
		    	systemState.setPricingPolicy("targetProfit_DeliveryCost");
		    	System.out.println("Pricing policy set.");
		    	return;
		    }
		    else if(command[1].equalsIgnoreCase("targetProfit_ServiceFee")) {
		    	systemState.setPricingPolicy("targetProfit_ServiceFee");
		    	System.out.println("Pricing policy set.");
		    	return;
		    }
		    else if(command[1].equalsIgnoreCase("targetProfit_Markup")) {
		    	systemState.setPricingPolicy("targetProfit_Markup");
		    	System.out.println("Pricing policy set.");
		    	return;
		    }
		    System.out.println("Unable to find policy name. Your three options are: \n1. targetProfit_DeliveryCost\n2. targetProfit_ServiceFee\n3. targetProfit_Markup");
		    return;
		}
		
		// associateCard <userName> <cardType>
		else if (command[0].equalsIgnoreCase("associatecard")) {
			if (systemState.getUserLoggedIn() != 1  && !systemState.getAuto()) {
		        System.out.println("User does not have access to this command.");
		        return;
		    }
		    // Validate the command format
		    if (command.length != 3) {
		        System.out.println("Invalid Command. Use the following format:  associateCard <userName> <cardType>");
		        return;
		    }
		    Customer c = null;
		    for (User u : systemState.getActiveMembers()) {
		    	if(u.getUsername().equals((command[1]).trim())) {
		    		c = (Customer) u;
		    	}
		    }
		    if(c == null) {
		    	System.out.println("Could not find user. Try again with correct username (CASE SENSITIVE).");
		    	return;
		    }
		    if(c.setFidelity(command[2])) System.out.println("Successfully updated user's fidelity agreement.");
		    return;
		}
		
		// showCourierDeliveries <>
		else if (command[0].equalsIgnoreCase("showcourierdeliveries")) {
			if (systemState.getUserLoggedIn() != 1  && !systemState.getAuto()) {
		        System.out.println("User does not have access to this command.");
		        return;
		    }
		    // Validate the command format
		    if (command.length != 1) {
		        System.out.println("Invalid Command. Use the following format: showCustomers <>");
		        return;
		    }
		    ArrayList<Courier> best = new ArrayList<>();
		    for (User u : systemState.getActiveMembers()) {
		    	if(u.getUserType().equalsIgnoreCase("Courier")) {
		    		best.add((Courier) u);
		    	}
		    }
		    systemState.bubbleSortCouriersByDeliveries(best);
		    for (Courier courier : best) {
	            System.out.println(courier);
	        }
		}
		
		// showRestaurantTop <>
		else if (command[0].equalsIgnoreCase("showrestauranttop")) {
			if (systemState.getUserLoggedIn() != 1  && !systemState.getAuto()) {
		        System.out.println("User does not have access to this command.");
		        return;
		    }
		    // Validate the command format
		    if (command.length != 1) {
		        System.out.println("Invalid Command. Use the following format: showRestaurantTop <>");
		        return;
		    }
		    ArrayList<Restaurant> best = new ArrayList<>();
		    for (User u : systemState.getActiveMembers()) {
		    	if(u.getUserType().equalsIgnoreCase("Restaurant")) {
		    		best.add((Restaurant) u);
		    	}
		    }
		    systemState.bubbleSortByCompletedDeliveries(best);
		    for (Restaurant r : best) {
	            System.out.println(r);
	        }
		}
		
		// showCustomers <>
		else if (command[0].equalsIgnoreCase("showcustomers")) {
			if (systemState.getUserLoggedIn() != 1  && !systemState.getAuto()) {
		        System.out.println("User does not have access to this command.");
		        return;
		    }
		    // Validate the command format
		    if (command.length != 1) {
		        System.out.println("Invalid Command. Use the following format: showCustomers <>");
		        return;
		    }
		    for (User u : systemState.getActiveMembers()) {
		    	if(u.getUserType().equalsIgnoreCase("Customer")) {
		    		Customer a = (Customer) u;
		    		System.out.printf("** %s%n",a.getFullName());
		    	}
		    }
		}
		
		// showMenuItem <restaurant-name>
		else if (command[0].equalsIgnoreCase("showmenuitem")) {
			if (systemState.getUserLoggedIn() != 1  && !systemState.getAuto()) {
		        System.out.println("User does not have access to this command.");
		        return;
		    }
		    // Validate the command format
		    if (command.length != 2) {
		        System.out.println("Invalid Command. Use the following format: showMenuItem <restaurant-name>");
		        return;
		    }
		    Restaurant r = null;
		    for (User u : systemState.getActiveMembers()) {
		    	if(u.getName().equalsIgnoreCase(command[1])) {
		    		r = (Restaurant)u;
		    		break;
		    	}
		    }
		    if(r == null) {
		    	System.out.println("Unable to find restaurant. Try again with the correct name.");
		    	return;
		    }
		    System.out.println(r.getMenu());
		}
		
		// showTotalProfit <>
		else if (command[0].equalsIgnoreCase("showtotalprofit")) {
			if (systemState.getUserLoggedIn() != 1  && !systemState.getAuto()) {
		        System.out.println("User does not have access to this command.");
		        return;
		    }
		    // Validate the command format
		    if (command.length != 1) {
		        System.out.println("Invalid Command. Use the following format: showTotalProfit <>");
		        return;
		    }
			double totProfit = 0.0;
			for (Order o : systemState.getActiveOrders()) {
		    	totProfit += o.getProfit();
		    	
		    }
			for (Order o : systemState.getCompletedOrders()) {
		    	totProfit += o.getProfit();
		    	
		    }
		    System.out.printf("The total profit to date is: $%.2f%n", totProfit);
		    return;
		}
		
		// showTotalProfit <startDate> <endDate>
		else if (command[0].equalsIgnoreCase("showTotalProfit")) {
			if (systemState.getUserLoggedIn() != 1  && !systemState.getAuto()) {
		        System.out.println("User does not have access to this command.");
		        return;
		    }
		    // Validate the command format
		    if (command.length != 3) {
		        System.out.println("Invalid Command. Use the following format: showTotalProfit <startDate> <endDate>");
		        return;
		    }
		    String [] startDate = command[1].split("/");
		    String [] endDate = command[2].split("/");
			double totProfit = 0.0;
		    for (Order o : systemState.getActiveOrders()) {
		    	// First check the years
		    	int start = Integer.parseInt(startDate[2]);
		    	int order = Integer.parseInt(o.getDate()[2]);
		    	int end = Integer.parseInt(endDate[2]);
		    	if((start <= order )&& (order <= end)) {
		    		start = Integer.parseInt(startDate[0]);
			    	order = Integer.parseInt(o.getDate()[0]);
			    	end = Integer.parseInt(endDate[0]);
			    	// Next check the months
			    	if(start <= order && order <= end) {
			    		// Lastly check the day
			    		start = Integer.parseInt(startDate[1]);
				    	order = Integer.parseInt(o.getDate()[1]);
				    	end = Integer.parseInt(endDate[1]);
				    	if(start <= order && order <= end) {
				    		totProfit += o.getProfit();
				    	}
			    	}
		    	}
		    	
		    }
		    for (Order o : systemState.getCompletedOrders()) {
		    	// First check the years
		    	int start = Integer.parseInt(startDate[2]);
		    	int order = Integer.parseInt(o.getDate()[2]);
		    	int end = Integer.parseInt(endDate[2]);
		    	if(start < order && order < end) {
		    		start = Integer.parseInt(startDate[0]);
			    	order = Integer.parseInt(o.getDate()[0]);
			    	end = Integer.parseInt(endDate[0]);
			    	// Next check the months
			    	if(start < order && order < end) {
			    		// Lastly check the day
			    		start = Integer.parseInt(startDate[1]);
				    	order = Integer.parseInt(o.getDate()[1]);
				    	end = Integer.parseInt(endDate[1]);
				    	if(start < order && order < end) {
				    		totProfit += o.getProfit();
				    	}
			    	}
		    	}
		    	
		    }
		    System.out.printf("The total profit from %s to %s is: $%.2f%n", command[1], command[2], totProfit);
		    return;
		}
		
     // deactivateUser <username> <userType>
 		else if (command[0].equalsIgnoreCase("deactivateUser")) {
 			if (systemState.getUserLoggedIn() != 1  && !systemState.getAuto()) {
 		        System.out.println("User does not have access to this command.");
 		        return;
 		    }
 		    // Validate the command format
 		    if (command.length != 3) {
 		        System.out.println("Invalid Command. Use the following format: deactivateUser <name> <userType>");
 		        return;
 		    }
 			for(User u : systemState.getActiveMembers()) {
 				if(u.getUsername().equalsIgnoreCase(command[1].trim()) && (u.getUserType().equalsIgnoreCase(command[2].trim()))) {
 					systemState.removeActiveMember(u);
 					System.out.println("Succesfully deactived user.");
 					return;
 				}
 			}
 			System.out.println("Unable to find user. Type the correct name and usertype");
 		}
        
     // activateUser <username> <userType>
  		else if (command[0].equalsIgnoreCase("activateUser")) {
  			if (systemState.getUserLoggedIn() != 1  && !systemState.getAuto()) {
  		        System.out.println("User does not have access to this command.");
  		        return;
  		    }
  		    // Validate the command format
  		    if (command.length != 3) {
  		        System.out.println("Invalid Command. Use the following format: activateUser <name> <userType>");
  		        return;
  		    }
  			for(User u : systemState.getDeactiveMembers()) {
  				if(u.getUsername().equalsIgnoreCase(command[1].trim()) && (u.getUserType().equalsIgnoreCase(command[2].trim()))) {
  					systemState.reactivateMember(u);
  					System.out.println("Succesfully reactivated user.");
  					return;
  				}
  			}
  			System.out.println("Unable to find user. Type the correct name and usertype");
  		}
        // removeUser <username> <userType> 
  		else if (command[0].equalsIgnoreCase("removeUser")) {
  			if (systemState.getUserLoggedIn() != 1  && !systemState.getAuto()) {
  		        System.out.println("User does not have access to this command.");
  		        return;
  		    }
  		    // Validate the command format
  		    if (command.length != 3) {
  		        System.out.println("Invalid Command. Use the following format: removeUser <username> <userType> ");
  		        return;
  		    }
  			systemState.removeUser(command[1].trim(), command[2].trim());
  		}
    }

    @Override
    public void visitCustomerCommand(String [] command) {
        // Execute customer commands
        // System.out.println("Executing customer command: " + command);
        

		// createOrder <restaurantName> <orderName> ADD IN CHECK IF THERE IS A CURRENT ORDER ALREADY OPEN
        //Should print out menu available for customer
		if (command[0].equalsIgnoreCase("createorder")) {
			if (systemState.getUserLoggedIn() != 3  && !systemState.getAuto()) {
		        System.out.println("User does not have access to this command.");
		        return;
		    }
		    // Validate the command format
		    if (command.length != 3) {
		        System.out.println("Invalid Command. Use the following format: createOrder <restaurantName> <orderName>");
		        return;
		    }
		    for(User u : systemState.getActiveMembers()) {
		    	if(u.getUserType().equalsIgnoreCase("Restaurant")) {
		    		Restaurant r = (Restaurant) u;
		    		if(r.getName().equalsIgnoreCase(command[1].trim())) {
		    			System.out.println(r.getName());
			    		Order o = new Order(command[2].trim(), r);
			    		o.setCustomer(systemState.getC());
			    		systemState.getC().setActiveOrder(o); 
				        System.out.println("Order Successfully created. Add items (see below) to your order and save it to confirm.");
				        r.getMenu(o.getCustomer());
			    		return;
		    		}
		    	}
		    }
		    System.out.println("Unable to find Restaurant. Try again using correct name.");
		    
		}
		
		// addItem2Order <orderName> <itemName>
		else if (command[0].equalsIgnoreCase("additem2order")) {
			if (systemState.getUserLoggedIn() != 3  && !systemState.getAuto()) {
		        System.out.println("User does not have access to this command.");
		        return;
		    }
		    // Validate the command format
		    if (command.length != 3) {
		        System.out.println("Invalid Command. Use the following format: addItem2Order <orderName> <itemName>");
		        return;
		    }
		    if(systemState.getC().getActiveOrder() == null) {
		    	System.out.println("Invalid command. You must create an order first before adding items. See help <> for more information");
		    	return;
		    }
		    Restaurant r = systemState.getC().getActiveOrder().getRestaurant();
		    Meal mealItem = null;
		    Dishes dishItem = null;
		    for (Meal m : r.getMenu().getAvailMeals()) {
		    	if(command[2].trim().equalsIgnoreCase(m.getMealName())) {
		    		mealItem = m;
		    	}
		    }
		    for (Dishes d : r.getMenu().getAvailDishes()) {
		    	if(command[2].trim().equalsIgnoreCase(d.getDishName())) {
		    		dishItem = d;
		    	}
		    }
		    
		    if(mealItem != null || dishItem != null) {
		    	systemState.getC().getActiveOrder().add2Order(mealItem, dishItem);
		    	System.out.println("Added item to order.");
		    	return;
		    }
		    System.out.println("Unable to find food item. Try again using correct name");
		    return;
		}
		
		// endOrder <orderName> <date> //HOW DO WE PAY?! ALLOCATE DRIVER, etc.
		else if (command[0].equalsIgnoreCase("endorder")) {
			if (systemState.getUserLoggedIn() != 3  && !systemState.getAuto()) {
		        System.out.println("User does not have access to this command.");
		        return;
		    }
		    // Validate the command format
		    if (command.length != 3) {
		        System.out.println("Invalid Command. Use the following format: endOrder <orderName> <date>");
		        return;
		    }
		    
		    if(systemState.getC().getActiveOrder().getOrderName().equalsIgnoreCase(command[1].trim())) {
		    	systemState.getC().getActiveOrder().finalizeOrder(command[2].trim(), systemState);
		    	// System.out.println("Order successfully ended. Sending to Restaurant for processing.");
		    	return;
		    }
		    System.out.println("Unable to find order name. Try again using correct order name");
		}
		
		// registerFidelity <option> 
		else if (command[0].equalsIgnoreCase("registerfidelity")) {
			if (systemState.getUserLoggedIn() != 3  && !systemState.getAuto()) {
		        System.out.println("User does not have access to this command.");
		        return;
		    }
		    // Validate the command format
		    if (command.length != 2) {
		        System.out.println("Invalid Command. Use the following format: registerFidelity <option> \nOption = Basic, Points, Lottery or Unregister");
		        return;
		    }
		    if(command[1].equalsIgnoreCase("Basic")) {
		    	systemState.getC().setFidelity("Basic");
		    }
		    else if(command[1].equalsIgnoreCase("Points")) {
		    	systemState.getC().setFidelity("Point");
		    }
		    else if(command[1].equalsIgnoreCase("Lottery")) {
		    	systemState.getC().setFidelity("Lottery");
		    }
		    else if(command[1].equalsIgnoreCase("Unregister")) {
		    	systemState.getC().setFidelity(null);
		    }
		    else {
		        System.out.println("Invalid option. \nOption = Basic, Points, Lottery or Unregister");
		        return;
		    }
		    System.out.println("Successfully updated fidelity agreement.");
		}
		
		// accountInformation <> 
		// Should give user information regarding order history, fidelity card, etc.
		else if (command[0].equalsIgnoreCase("accountinformation")) {
			if (systemState.getUserLoggedIn() != 3  && !systemState.getAuto()) {
		        System.out.println("User does not have access to this command.");
		        return;
		    }
		    System.out.println("** Full name: " + systemState.getC().getFullName());
		    System.out.println("** Username: " + systemState.getC().getUsername());
		    System.out.println("** Fidelity Card: " + systemState.getC().getFidelity());
		    System.out.println("** Orders:");
		    int count = 0;
		    for (Order o : systemState.getActiveOrders()) {
		    	if(o.getCustomer().getName().equals(systemState.getC().getName())){
		    		System.out.println(o);
		    		count++;
		    	}
		    }
		    if(count == 0) {
		    	System.out.println("- No orders have been placed\n");
		    }
		}
		
		// setNotifcation <option> -> register or unregister
		else if (command[0].equalsIgnoreCase("setNotification")) {
			if (systemState.getUserLoggedIn() != 3  && !systemState.getAuto()) {
		        System.out.println("User does not have access to this command.");
		        return;
		    }
		    // Validate the command format
		    if (command.length != 2) {
		        System.out.println("Invalid Command. Use the following format: setNotifcation <option> \noption = register or unregister");
		        return;
		    }
		    
		    
		}
		// showRestaurants <>
		else if (command[0].equalsIgnoreCase("showRestaurants")) {
			if (systemState.getUserLoggedIn() != 3  && !systemState.getAuto()) {
		        System.out.println("User does not have access to this command.");
		        return;
		    }
		    systemState.showRestaurants();
		}
    }

    @Override
    public void visitCourierCommand(String [] command) {
        // Execute courier commands
        // System.out.println("Executing courier command: " + command);
        

		// onDuty <username>
		if (command[0].equalsIgnoreCase("onduty")) {
			if (systemState.getUserLoggedIn() != 4  && !systemState.getAuto()) {
		        System.out.println("User does not have access to this command.");
		        return;
		    }
		    // Validate the command format
		    if (command.length != 2) {
		        System.out.println("Invalid Command. Use the following format: onDuty <username>");
		        return;
		    }
		    if(systemState.getD().getUsername().equals(command[1].trim())) {
			    if(systemState.getD().onDuty()) {
			    	System.out.printf("%s is already on duty%n", systemState.getD().getName());
			    	return;
			    }
		    	System.out.printf("%s is now on duty%n", systemState.getD().getName());
		    	systemState.getD().setOnDuty(true);
			    return;
		    }
		    System.out.println("Incorrect username. Try again using correct username (CASE SENSITIVE)");
		}
		
		// offDuty <username>
		else if (command[0].equalsIgnoreCase("offduty")) {
			if (systemState.getUserLoggedIn() != 4  && !systemState.getAuto()) {
		        System.out.println("User does not have access to this command.");
		        return;
		    }
		    // Validate the command format
		    if (command.length != 2) {
		        System.out.println("Invalid Command. Use the following format: offDuty <username>");
		        return;
		    }
		    if(systemState.getD().getUsername().equals(command[1].trim())) {
		    	if(!systemState.getD().onDuty()) {
			    	System.out.printf("%s is already off duty%n", systemState.getD().getName());
			    	return;
			    }
			    System.out.printf("%s is now off duty%n", systemState.getD().getName());
			    systemState.getD().setOnDuty(false);
			    return;
		    }
		    System.out.println("Incorrect username. Try again using correct username (CASE SENSITIVE)");
		}
		
		// finishDelivery <order_name>
		else if (command[0].equalsIgnoreCase("finishDelivery")) {
			if (systemState.getUserLoggedIn() != 4  && !systemState.getAuto()) {
		        System.out.println("User does not have access to this command.");
		        return;
		    }
		    // Validate the command format
		    if (command.length != 2) {
		        System.out.println("Invalid Command. Use the following format: finishDelivery <order_name>");
		        return;
		    }
		    for(Order o : systemState.getActiveOrders()) {
		    	if(o.getOrderName().equalsIgnoreCase(command[1])) {
		    		systemState.getActiveOrders().remove(o);
		    		systemState.getCompletedOrders().add(o);
		    		o.setCompleted(true);
		    		System.out.println("Order has successfully been completed. Good job");
		    		systemState.getD().finishJob(o);
		    		systemState.getD().setLoc(o.getCustomer().getLoc());
		    		return;
		    	}
		    }
		    System.out.println("Unable to find order name");
		}
		
		// updatePos <latitude> <longitude>
		else if (command[0].equalsIgnoreCase("updatePos")) {
			if (systemState.getUserLoggedIn() != 4  && !systemState.getAuto()) {
		        System.out.println("User does not have access to this command.");
		        return;
		    }
		    // Validate the command format
		    if (command.length != 3) {
		        System.out.println("Invalid Command. Use the following format: updatePos <latitude> <longitude>");
		        return;
		    }
		    try {
		    systemState.getD().setLoc(Double.parseDouble(command[1]), Double.parseDouble(command[2]));
		    System.out.println("Location successfully updated");
		
		    }catch (NumberFormatException e) {
                System.out.println("Invalid latitude or longitude format.");
            } 
		}
    }

    @Override
    public void visitRestaurantCommand(String [] command) {
        // Execute restaurant commands
        // System.out.println("Executing restaurant command: " + command);
        
     // addDishRestauarantMenu <dishName> <dishCategory> <foodCategory> <unitPrice>
        if (command[0].equalsIgnoreCase("adddishrestaurantmenu")) {
            if (systemState.getUserLoggedIn() != 2  && !systemState.getAuto()) {
                System.out.println("User does not have access to this command.");
                return;
            }

            if (command.length != 5) {
                System.out.println("Invalid Command. Use following format: addDishrestaurantmenu <dishName> <dishCategory> <foodCategory> <unitPrice>");
                return;
            }

            // Valid options for dishCategory and foodCategory
            @SuppressWarnings("unused")
			String[] validDishCategories = {"starter", "main", "dessert"};
            String[] validFoodCategories = {"standard", "vegetarian", "gluten-free"};

            String dishName = command[1];
            String dishCategory = command[2].toLowerCase();
            String foodCategory = command[3].toLowerCase();
            double unitPrice;

            // Check if dishCategory is valid
            if (!dishCategory.equals("starter") && !dishCategory.equals("main") && !dishCategory.equals("dessert")) {
                System.out.println("Invalid dish category. -> starter, main, or dessert");
                return;
            }

            // Check if foodCategory is valid
            if (!foodCategory.equals("standard") && !foodCategory.equals("vegetarian") && !foodCategory.equals("gluten-free")) {
                System.out.println("Invalid food category. -> standard, vegetarian or gluten-free");
                return;
            }

            try {
                unitPrice = Double.parseDouble(command[4]);

                // Create the dish object
                Dishes dish = new Dishes(dishName, dishCategory, foodCategory, unitPrice);

                // Add the dish to the restaurant's menu
                systemState.getR().getMenu().addDish(dish);

                System.out.println("Dish added successfully to the restaurant's menu.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid unit price format.");
            } catch (Exception e) {
                System.out.println("An error occurred while adding the dish to the restaurant's menu.");
                e.printStackTrace();
            }
        }

		// createMeal <mealName>
		else if (command[0].equalsIgnoreCase("createmeal")) {
			if (systemState.getUserLoggedIn() != 2  && !systemState.getAuto()) {
                System.out.println("User does not have access to this command.");
                return;
            }

            if (command.length != 2) {
                System.out.println("Invalid Command. Use following format: createMeal <mealName>");
                return;
            }
            Meal m = new Meal(command[1]);
            try {
                //getRest().addMeal(m);
            	systemState.setNewMeal(m, true);
                System.out.println("Successfully created");
            } catch (Exception e) {
                System.out.println("An error occurred while creating the meal.");
                e.printStackTrace();
                return;
            }
		}
		
		// addDish2Meal <dishName> <mealName>
		else if (command[0].equalsIgnoreCase("adddish2meal")) {
			// Check if the user has the necessary access level (2)
		    if (systemState.getUserLoggedIn() != 2  && !systemState.getAuto()) {
		        System.out.println("User does not have access to this command.");
		        return;
		    }
		    // Validate the command format
		    if (command.length != 3) {
		        System.out.println("Invalid Command. Use the following format: addDish2Meal <dishName> <mealName>");
		        return;
		    }
		    // Extract dish name and meal name from the command
		    String dishName = command[1].trim();
		    String mealName = command[2].trim();
		    // Find the target meal in the available meals
		    Meal targetMeal = null;
		    for (Meal meal : systemState.getR().getMenu().getAvailMeals()) {
		        if (meal.getMealName().equalsIgnoreCase(mealName)) {
		            targetMeal = meal;
		            break;
		        }
		    }
		    // Check to see if meal has not been saved.
		    if(systemState.getNewMeal()!= null && mealName.equalsIgnoreCase(systemState.getNewMeal().getMealName())) {
		    	targetMeal = systemState.getNewMeal();
		    }
		    // If target meal not found, inform user and return
		    if (targetMeal == null) {
		        System.out.println("Unable to find listed meal. Try again with the correct name.");
		        return;
		    }
		    // Find the target dish in the available dishes
		    Dishes targetDish = null;
		    for (Dishes dish : systemState.getR().getMenu().getAvailDishes()) {
		        if (dish.getDishName().equalsIgnoreCase(dishName)) {
		            targetDish = dish;
		            break;
		        }
		    }
		    // If target dish not found, inform user and return
		    if (targetDish == null) {
		        System.out.println("Unable to find listed dish. Try again with the correct name.");
		        return;
		    }
		    // Add dish to meal
		    targetMeal.addDishes(targetDish);
		    if(targetMeal.equals(systemState.getNewMeal())) {
		    	System.out.println("Successfully added to new meal. Save Meal to add to menu");
		    	return;
		    }
		    else {
		    	System.out.println("Successfully added to meal. Menu has been updated");
		    }
		}

		
		// showMeal <mealName> EDIT THIS TO INCLUDE ALL ASPECTS
		else if (command[0].equalsIgnoreCase("showmeal")) {
			// Check if the user has the necessary access level (2)
		    if (systemState.getUserLoggedIn() != 2  && !systemState.getAuto()) {
		        System.out.println("User does not have access to this command.");
		        return;
		    }
		    // Validate the command format
		    if (command.length != 2) {
		        System.out.println("Invalid Command. Use the following format: showMeal <mealName>");
		        return;
		    }
		    systemState.getR().showMeal(command[1].trim());
		    return;
		}
		
		// saveMeal <mealName>
		else if (command[0].equalsIgnoreCase("savemeal")) {
			// Check if the user has the necessary access level (2)
		    if (systemState.getUserLoggedIn() != 2  && !systemState.getAuto()) {
		        System.out.println("User does not have access to this command.");
		        return;
		    }
		    // Validate the command format
		    if (command.length != 2) {
		        System.out.println("Invalid Command. Use the following format: saveMeal <mealName>");
		        return;
		    }
		    if(command[1].trim().equalsIgnoreCase(systemState.getNewMeal().getMealName())) {
		    	systemState.getR().getMenu().addMeal(systemState.getNewMeal());
		    	System.out.println("Successfully added meal to menu.");
		    	systemState.setNewMeal(null, false);
		    	return;
		    }
		    else {
		    	System.out.println("Unable to find correct meal. Please try again using the name used earlier in createMeal command");
		    }
		}
		
		// setSpecialOffer <mealName> WORK ON PRICING FUNCTIONS!!
		else if (command[0].equalsIgnoreCase("setspecialoffer")) {
			// Check if the user has the necessary access level (2)
		    if (systemState.getUserLoggedIn() != 2  && !systemState.getAuto()) {
		        System.out.println("User does not have access to this command.");
		        return;
		    }
		    // Validate the command format
		    if (command.length != 2) {
		        System.out.println("Invalid Command. Use the following format: setSpecialOffer <mealName>");
		        return;
		    }
		    for (Meal m : systemState.getR().getMenu().getAvailMeals()) {
		    	if(m.getMealName().equalsIgnoreCase(command[1].trim())) {
		    		systemState.getR().getMenu().addSpecial(m, systemState.getR().getDiscountFactor());
		    		System.out.printf("Successfully added %s to Weekly Specials%n", m.getMealName());
			        return;
		    	}
		    }
		    System.out.println("Unable to find meal in listed menu. Try again with correct meal name.");
	        return;
		}
		
		// removeFromSpecialOffer <mealName>
		else if (command[0].equalsIgnoreCase("removefromspecialoffer")) {
			// Check if the user has the necessary access level (2)
		    if (systemState.getUserLoggedIn() != 2  && !systemState.getAuto()) {
		        System.out.println("User does not have access to this command.");
		        return;
		    }
		    // Validate the command format
		    if (command.length != 2) {
		        System.out.println("Invalid Command. Use the following format: removeFromSpecialOffer <mealName>");
		        return;
		    }
		    for (Meal m : systemState.getR().getMenu().getAvailMeals()) {
		    	if(m.getMealName().equalsIgnoreCase(command[1].trim())) {
		    		systemState.getR().getMenu().removeSpecial(m, systemState.getR().getDiscountFactor());
		    		System.out.printf("Successfully removed %s from Weekly Specials%n", m.getMealName());
			        return;
		    	}
		    }
		    System.out.println("Unable to find meal in listed menu. Try again with correct meal name.");
	        return;
		}
		
        // setDiscountPercentage <discount-Factor>
 		else if (command[0].equalsIgnoreCase("setDiscountPercentage")) {
 		// Check if the user has the necessary access level (2)
		    if (systemState.getUserLoggedIn() != 2  && systemState.getUserLoggedIn() != 1  && !systemState.getAuto()) {
		        System.out.println("User does not have access to this command.");
		        return;
		    }
		    // Validate the command format
		    if (command.length != 2) {
		        System.out.println("Invalid Command. Use the following format: setDiscountPercentage <discount-Factor>");
		        return;
		    }
		    systemState.getR().setDiscountFactor(Double.parseDouble(command[1]));
		    System.out.println("Successfully changed Discount-Factor");
		    return;
 		}
        
     // findDeliverer <orderName>
     		else if (command[0].equalsIgnoreCase("finddeliverer")) {
     			if (systemState.getUserLoggedIn() != 2 && !systemState.getAuto()) {
     		        System.out.println("User does not have access to this command.");
     		        return;
     		    }
     		    // Validate the command format
     		    if (command.length != 2) {
     		        System.out.println("Invalid Command. Use the following format: findDeliverer <orderName>");
     		        return;
     		    }
     		    Courier c = (systemState.getAvailableCourier()).get(0); // .getAvailableCourier creates a list of available Couriers based on delivery policy
     		    if(c == null) {
     		    	System.out.println("No couriers available at this time. Try again later.");
     		    	return;
     		    }
     		    Order order = null;
     		    for (Order o : systemState.getActiveOrders()) {
     		    	if(o.getOrderName().equalsIgnoreCase(command[1].trim())) {
     		    		order = o; 
     		    		break;
     		    	}
     		    }
     		    c.setOrder(order);
     		    
     		}
     // removemeal <mealName>
		else if (command[0].equalsIgnoreCase("removemeal")) {
			// Check if the user has the necessary access level (2)
		    if (systemState.getUserLoggedIn() != 2  && !systemState.getAuto()) {
		        System.out.println("User does not have access to this command.");
		        return;
		    }
		    // Validate the command format
		    if (command.length != 2) {
		        System.out.println("Invalid Command. Use the following format: removemeal <mealName>");
		        return;
		    }
		    for (Meal m : systemState.getR().getMenu().getAvailMeals()) {
		    	if(m.getMealName().equalsIgnoreCase(command[1].trim())) {
		    		systemState.getR().getMenu().removeMeal(m);
		    		System.out.printf("Successfully removed %s from Menu%n", m.getMealName());
			        return;
		    	}
		    }
		    System.out.println("Unable to find meal in listed menu. Try again with correct meal name.");
	        return;
		}
        
     // removedish <dishName>
 		else if (command[0].equalsIgnoreCase("removemeal")) {
 			// Check if the user has the necessary access level (2)
 		    if ((systemState.getUserLoggedIn() != 2 || systemState.getUserLoggedIn() != 1)  && !systemState.getAuto()) {
 		        System.out.println("User does not have access to this command.");
 		        return;
 		    }
 		    // Validate the command format
 		    if (command.length != 2) {
 		        System.out.println("Invalid Command. Use the following format: removemeal <mealName>");
 		        return;
 		    }
 		    for (Dishes d : systemState.getR().getMenu().getAvailDishes()) {
 		    	if(d.getDishName().equalsIgnoreCase(command[1].trim())) {
 		    		systemState.getR().getMenu().removeDish(d);
 		    		System.out.printf("Successfully removed %s from Menu%n", d.getDishName());
 			        return;
 		    	}
 		    }
 		    System.out.println("Unable to find dish in listed menu. Try again with correct dish name.");
 	        return;
 		}
     // sortOrders <menu_type>
     // sortOrders <menuType> <Restaurantname>
 		else if (command[0].equalsIgnoreCase("sortorders")) {
 		    // Check if the user has the necessary access level (2 or 1)
 		    if ((systemState.getUserLoggedIn() != 2 && systemState.getUserLoggedIn() != 1) && !systemState.getAuto()) {
 		        System.out.println("User does not have access to this command.");
 		        return;
 		    }
 		    // Validate the command format
 		    if (command.length != 2 && systemState.getUserLoggedIn() == 2) {
 		        System.out.println("Invalid Command. Use the following format: sortOrders <menuType> \n menu_type = halfMeal or dish");
 		        return;
 		    }
 		    else if(command.length != 3 && systemState.getUserLoggedIn() == 2) {
 		    	System.out.println("Invalid Command. Use the following format: sortOrders <menuType> <Restaurantname> \n menu_type = halfMeal or dish");
 		        return;
 		    }

 		    String menuType = command[1].trim().toLowerCase();
 		    ArrayList<Order> allOrders = new ArrayList<>();
 		    allOrders.addAll(systemState.getActiveOrders());
 		    allOrders.addAll(systemState.getCompletedOrders());
 		    Restaurant currentRestaurant = null;
 		    if(systemState.getUserLoggedIn()== 1) {
 		    	for(User u: systemState.getActiveMembers()) {
 		    		if(u.getName().equalsIgnoreCase(command[2])) {
 		    			currentRestaurant = (Restaurant)u;
 		    			break;
 		    		}
 		    	}
 		    }
 		    else {
 		    	currentRestaurant = systemState.getR();
 		    }

 		    ArrayList<Order> restaurantOrders = new ArrayList<>();

 		    // Collect orders for the current restaurant
 		    for (Order order : allOrders) {
 		        if (order.getRestaurant().equals(currentRestaurant)) {
 		            restaurantOrders.add(order);
 		        }
 		    }

 		    // Initialize counters for dishes or half meals
 		    HashMap<String, Integer> itemCountMap = new HashMap<>();

 		    switch (menuType) {
 		        case "halfmeal":
 		            // Count the number of halfMeal items
 		            for (Order order : restaurantOrders) {
 		                for (Meal meal : order.getMeals()) {
 		                	System.out.println(meal.isHalfMeal());
 		                    if (meal.isHalfMeal()) {
 		                        String mealName = meal.getMealName();
 		                        itemCountMap.put(mealName, itemCountMap.getOrDefault(mealName, 0) + 1);
 		                    }
 		                }
 		            }
 		            break;

 		        case "dish":
 		            // Count the occurrences of each dish in the total order history
 		            for (Order order : restaurantOrders) {
 		                for (Dishes dish : order.getDishes()) {
 		                    String dishName = dish.getDishName();
 		                    itemCountMap.put(dishName, itemCountMap.getOrDefault(dishName, 0) + 1);
 		                }
 		            }
 		            break;

 		        default:
 		            System.out.println("Invalid menu type. Use 'halfMeal' or 'dish'.");
 		            return;
 		    }

 		    // Create a list from the itemCountMap and sort it based on the count in descending order
 		    List<Map.Entry<String, Integer>> sortedItems = new ArrayList<>(itemCountMap.entrySet());
 		    sortedItems.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

 		    // Display the sorted items
 		    System.out.printf("%s sorted by number of occurrences:%n", menuType.equals("halfmeal") ? "HalfMeals" : "Dishes");
 		    for (Map.Entry<String, Integer> entry : sortedItems) {
 		        System.out.println(entry.getKey() + ": " + entry.getValue());
 		    }
 		}

    }

    @Override
    public void visitAnyoneCommand(String [] command) {
        // Execute commands for anyone
    	// Skip Enter key press (no commands)
        if (command.length == 0 || command[0].trim().isEmpty()) {
            return;
        }
        
        // Check if User wants to end terminal
        else if (command[0].equalsIgnoreCase("end")) {
        	systemState.exitLoop();
            return;
        }
        
        // login <username> <password>
        else if (command[0].equalsIgnoreCase("login")) {
            if (systemState.getUserLoggedIn() != 0) {
                System.out.println("User already logged in. Logout first");
                return;
            }
            if (command.length != 3) {
                System.out.println("Invalid Login Format -> login <username> <password>");
                return;
            }
            
            // Login for managers
            for (int i = 0; i < systemState.getActiveMembers().size(); i++) {
                String username = command[1].trim();
                String password = command[2].trim();
                
                if (username.equals(systemState.getActiveMembers().get(i).getUsername()) && 
                    password.equals(systemState.getActiveMembers().get(i).getPassword())) {
                    
                    String userType = systemState.getActiveMembers().get(i).getUserType().toLowerCase();
                    
                    System.out.printf("Login Successful. Welcome, %s%n", systemState.getActiveMembers().get(i).getName());
                    
                    switch (userType) {
                        case "manager":
                        	systemState.setM((Manager) systemState.getActiveMembers().get(i));
                        	systemState.setUserLoggedIn(1);
                            break;
                        case "restaurant":
                        	systemState.setR((Restaurant) systemState.getActiveMembers().get(i));
                        	systemState.setUserLoggedIn(2);
                            break;
                        case "customer":
                        	systemState.setC((Customer) systemState.getActiveMembers().get(i));
                        	// SHow restaurants for users
                        	System.out.printf("%n%n***** Available restaurants: *****%n%n");
                        	systemState.showRestaurants();
                        	systemState.setUserLoggedIn(3);
                            break;
                        case "courier":
                        	systemState.setD((Courier) systemState.getActiveMembers().get(i));
                        	systemState.setUserLoggedIn(4);
                            break;
                        default:
                            break;
                    }
                    return;
                }
            }
            
            System.out.println("User not found or invalid credentials. Try again with correct username and password.");
        }
        
        // logout
        else if (command[0].equalsIgnoreCase("logout")) {
            if (systemState.getUserLoggedIn() == 0) {
                System.out.println("No Users Logged In");
            } else {           
            	int temp = systemState.getUserLoggedIn();
            	switch (temp) {
                case 1:
                	systemState.setM(null);
                case 2:
                	systemState.setR(null);
                case 3:
                	systemState.setC(null);
                case 4:
                	systemState.setD(null);
                systemState.setUserLoggedIn(0);
                System.out.println("Logout Successful");
                return;
            	}
            }
        }
	
        // help
        else if (command[0].equalsIgnoreCase("help")) {
            //System.out.println("Here are your available commands:");
        	systemState.printCommands();
        }
        

		// runTest <testScenario-file>
		else if (command[0].equalsIgnoreCase("runtest")) {
	            if (command.length != 2) {
	                System.out.println("Invalid Command. Use the following format: runtest <testScenario-file>");
	                return;
	            }
	            runTest(command[1].trim());
	        }
		
		// Invalid command -> Try again
		else {
			System.out.println("\033[0;31mInvalid Command\033[0m");
			System.out.println("error");
		}
    	
        // System.out.println("Executing command for anyone: " + command);
    }
    public void runTest(String filename) {
        // Check if the filename ends with .txt and append it if not
        if (!filename.endsWith(".txt")) {
            filename += ".txt";
        }

        // Construct the file path dynamically based on the provided filename
        String filePath = "eval/" + filename;
        systemState.setAuto(true);
        BufferedReader r;
        try {
            r = new BufferedReader(new FileReader(filePath));
            systemState.setBr(r);
            String line;
            while ((line = systemState.getBr().readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    systemState.getFoodSys().acceptCommandVisitor(line, systemState.getCommandExecutor());
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("End of file.");
        systemState.setAuto(false); // Set auto reading off again.
    }

}
    

