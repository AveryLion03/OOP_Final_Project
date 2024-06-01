package Commands;

import java.util.ArrayList;
import java.util.Scanner;

import Code.Location;
import user.*;
import food.*;

public class Parse {
	protected ArrayList<User> activeMembers;
	protected ArrayList<User> deactiveMembers;
    private static Boolean run = true;
    private Meal newMeal;
    protected int userLoggedIn = 0;
    private Restaurant r;
    private Customer c;
    private Courier d;
    private Manager m;
    
    public Parse(ArrayList<User> mem) {
		super();
		this.activeMembers = mem;
		this.deactiveMembers = new ArrayList<>();
		this.newMeal = null;
		this.r = null;
		this.c = null;
		this.d = null;
		this.m = null;
	}

	public int getUserLoggedIn() {
		return userLoggedIn;
	}
	// Private variables -> What user is logged in right now?
	protected void setCust(Customer cus) {
		this.c = cus;
	}
	protected Meal getNewMeal() {
		return this.newMeal;
	}
	protected void setNewMeal(Meal m, boolean set) {
		if(set) this.newMeal = m;
		else this.newMeal = null;
	}
	
	protected Customer getCust() {
		return this.c;
	}
	protected void setRest(Restaurant rest) {
		this.r = rest;
	}
	protected Restaurant getRest() {
		return this.r;
	}
	protected void setMan(Manager man) {
		this.m = man;
	}
	protected Manager getMan() {
		return this.m;
	}
	protected void setCour(Courier cour) {
		this.d = cour;
	}
	protected Courier getCour() {
		return this.d;
	}
	
	// Should we continue CLUI?
	public void exitLoop() {
		run = false;
	}
	public Boolean getLoop() {
		return run;
	}
	
	// Set what type of user is logged in
	protected void setUserLoggedIn(int val) {
		userLoggedIn = val;
	}
	
	//Only available for managers -> Prints out list of Restaurants
	protected void showRestaurants() {
		//print out restaurants available
		for(int i = 0; i < activeMembers.size(); i++) {
			if(activeMembers.get(i).getUserType().equalsIgnoreCase("Restaurant")) {
				System.out.println(activeMembers.get(i).getName());
			}
		}
	}
	//Only available for managers -> Prints out list of customers
	protected void showCustomers() {
		//return list;
		for(int i = 0; i < activeMembers.size(); i++) {
			if(activeMembers.get(i).getUserType().equalsIgnoreCase("Customer")) {
				System.out.println(activeMembers.get(i).getName());
			}
		}
	}
	//Only available for managers -> Prints out list of Couriers
	protected void showCouriers() {
		//return list;
		for(int i = 0; i < activeMembers.size(); i++) {
			if(activeMembers.get(i).getUserType().equalsIgnoreCase("Courier")) {
				System.out.println(activeMembers.get(i).getName());
			}
		}
	}
	
	public static void bubbleSortCouriersByDeliveries(ArrayList<Courier> couriers) {
        int n = couriers.size();
        boolean swapped;

        // Traverse through all elements in the list
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            // Last i elements are already sorted
            for (int j = 0; j < n - i - 1; j++) {
                // Swap if the element found is less than the next element
                if (couriers.get(j).getOrderCount() < couriers.get(j + 1).getOrderCount()) {
                    Courier temp = couriers.get(j);
                    couriers.set(j, couriers.get(j + 1));
                    couriers.set(j + 1, temp);
                    swapped = true;
                }
            }
            // If no two elements were swapped by inner loop, then break
            if (!swapped) break;
        }
    }
	
	// Shows available commands for current user of CLUI
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
			// Commands Available for Anyone
			System.out.println("Commands Available for Anyone:");
			System.out.println("1. Login <username> <password>");
			System.out.println("2. logout <>");
	        System.out.println("3. runTest <testScenario-file>");
	        System.out.println("4. help <>");
	        System.out.println("5. end <>");
	        
		}
		//Manager Commands
		else if (userLoggedIn == 1) {
			// Commands Available for Managers
	        System.out.println("Commands Available for Managers:");
	        System.out.println("1. setDeliveryPolicy <delPolicyName>");
	        System.out.println("2. setProfitPolicy <ProfitPolicyName>");
	        System.out.println("3. associateCard <userName> <cardType>");
	        System.out.println("4. showCourierDeliveries <>");
	        System.out.println("5. showRestaurantTop <>");
	        System.out.println("6. showCustomers <>");
	        System.out.println("7. showMenuItem <restaurant-name>");
	        System.out.println("8. showTotalProfit <>");
	        System.out.println("9. showTotalProfit <startDate> <endDate>");
	        System.out.println("10. registerRestaurant <name> <address> <username> <password>");
	        System.out.println("11. registerCustomer <firstName> <lastName> <username> <address> <password>");
	        System.out.println("12. registerCourier <firstName> <lastName> <username> <position> <password>");
		}
		//Restaurant Commands
		else if (userLoggedIn == 2) {
			// Commands Available for Restaurants
	        System.out.println("Commands Available for Restaurants:");
	        System.out.println("1. findDeliverer <orderName>");
	        System.out.println("2. addDishRestauarantMenu <dishName> <dishCategory> <foodCategory> <unitPrice>");
	        System.out.println("3. createMeal <mealName>");
	        System.out.println("4. addDish2Meal <dishName> <mealName>");
	        System.out.println("5. showMeal <mealName>");
	        System.out.println("6. saveMeal <mealName>");
	        System.out.println("7. setSpecialOffer <mealName>");
	        System.out.println("8. removeFromSpecialOffer <mealName>");

		}
		//Customer Commands
		else if (userLoggedIn == 3) {
			 // Commands Available for Customers
	        System.out.println("Commands Available for Customers:");
	        System.out.println("1. createOrder <restaurantName> <orderName>");
	        System.out.println("2. addItem2Order <orderName> <itemName>");
	        System.out.println("3. endOrder <orderName> <date>");
		}
		//Courier Commands
		else {
			// Commands Available for Couriers
	        System.out.println("Commands Available for Couriers:");
	        System.out.println("1. onDuty <username>");
	        System.out.println("2. offDuty <username>");
		}
	}

	
	public void acceptCommandVisitor(String command, CommandVisitor visitor) {
        if (command.startsWith("setDeliveryPolicy") || command.startsWith("setProfitPolicy") ||
            command.startsWith("associateCard") || command.startsWith("showCourierDeliveries") ||
            command.startsWith("showRestaurantTop") || command.startsWith("showCustomers") ||
            command.startsWith("showMenuItem") || command.startsWith("showTotalProfit") ||
            command.startsWith("registerRestaurant") || command.startsWith("registerCustomer") ||
            command.startsWith("registerCourier")) {
            visitor.visitManagerCommand(command);
        } else if (command.startsWith("createOrder") || command.startsWith("addItem2Order") ||
                   command.startsWith("endOrder")) {
            visitor.visitCustomerCommand(command);
        } else if (command.startsWith("onDuty") || command.startsWith("offDuty")) {
            visitor.visitCourierCommand(command);
        } else if (command.startsWith("findDeliverer") || command.startsWith("addDishRestauarantMenu") ||
                   command.startsWith("createMeal") || command.startsWith("addDish2Meal") ||
                   command.startsWith("showMeal") || command.startsWith("saveMeal") ||
                   command.startsWith("setSpecialOffer") || command.startsWith("removeFromSpecialOffer")) {
            visitor.visitRestaurantCommand(command);
        } else {
            visitor.visitAnyoneCommand(command);
        }
    } 
	
	//Process Commands
	
    @SuppressWarnings("unused")
	public void processCommands(Boolean init, String lines) {
        String[] elements;
        if (init) {
            elements = lines.split(",");
        } else {
            elements = lines.split(" ");
        }

        for (int i = 0; i < elements.length; i++) {
            elements[i] = elements[i].trim();
        }

        /*
         *  Initialization Methods (startUp.txt) -> Customers, Managers, Restaurants, Couriers
         *  
         *  ADD IN MENUS -> Meals, dishes, etc.
         *  ADD IN PAST ORDERS -> order name, food, restaurant, etc.
         *  ADD IN PAST DRIVERS, etc.
         */
        if (init) {
            if (elements.length >= 3) {
                String elementType = elements[2].toLowerCase(); // Ensure elements[2] exists before calling toLowerCase()

                try {
                    if (elementType.equals("customer") && elements.length >= 9) {
                        Location loc = new Location(Double.parseDouble(elements[7]), Double.parseDouble(elements[8]));
                        Customer n = new Customer(elements[0], elements[1], elements[2], elements[3], elements[4], elements[5], elements[6], loc);
                        activeMembers.add(n);
                        //System.out.println("Customer Added");
                    } else if (elementType.equals("manager") && elements.length >= 5) {
                        Manager m = new Manager(elements[0], elements[1], elements[2], elements[3], elements[4]);
                        activeMembers.add(m);
                        //System.out.println("Manager Added");
                    } else if (elementType.equals("restaurant") && elements.length >= 6) {
                        Location loc = new Location(Double.parseDouble(elements[4]), Double.parseDouble(elements[5]));
                        Restaurant r = new Restaurant(elements[0], elements[1], elements[2], elements[3], loc);
                        activeMembers.add(r);
                        //System.out.println("Restaurant Added");
                    } else if (elementType.equals("courier") && elements.length >= 10) {
                        Location loc = new Location(Double.parseDouble(elements[8]), Double.parseDouble(elements[9]));
                        Courier c = new Courier(elements[0], elements[1], elements[2], elements[3], elements[4], elements[5], Integer.parseInt(elements[6]), Boolean.parseBoolean(elements[7]), loc);
                        activeMembers.add(c);
                        //System.out.println("Courier Added");
                    } else {
                        System.out.println("Insufficient elements for member creation.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid numeric format in input data.");
                }
            } else {
                System.out.println("Insufficient elements for processing.");
            }
        }


	/*
	 * User Interface commands
	 */
        else {
            // Skip Enter key press (no commands)
            if (elements.length == 0 || elements[0].trim().isEmpty()) {
                return;
            }
            
            // Check if User wants to end terminal
            else if (elements[0].equalsIgnoreCase("end")) {
                exitLoop();
                return;
            }
            
            // login <username> <password>
            else if (elements[0].equalsIgnoreCase("login")) {
                if (getUserLoggedIn() != 0) {
                    System.out.println("User already logged in. Logout first");
                    return;
                }
                if (elements.length != 3) {
                    System.out.println("Invalid Login Format -> login <username> <password>");
                    return;
                }
                
                // Login for managers
                for (int i = 0; i < activeMembers.size(); i++) {
                    String username = elements[1].trim();
                    String password = elements[2].trim();
                    
                    if (username.equals(activeMembers.get(i).getUsername()) && 
                        password.equals(activeMembers.get(i).getPassword())) {
                        
                        String userType = activeMembers.get(i).getUserType().toLowerCase();
                        
                        System.out.printf("Login Successful. Welcome, %s%n", activeMembers.get(i).getName());
                        
                        switch (userType) {
                            case "manager":
                                setMan((Manager) activeMembers.get(i));
                                setUserLoggedIn(1);
                                break;
                            case "restaurant":
                                setRest((Restaurant) activeMembers.get(i));
                                setUserLoggedIn(2);
                                break;
                            case "customer":
                                setCust((Customer) activeMembers.get(i));
                                setUserLoggedIn(3);
                                break;
                            case "courier":
                                setCour((Courier) activeMembers.get(i));
                                setUserLoggedIn(4);
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
            else if (elements[0].equalsIgnoreCase("logout")) {
                if (getUserLoggedIn() == 0) {
                    System.out.println("No Users Logged In");
                } else {           
                	int temp = getUserLoggedIn();
                	switch (temp) {
                    case 1:
                        setMan(null);
                    case 2:
                        setRest(null);
                    case 3:
                        setCust(null);
                    case 4:
                        setCour(null);
                    setUserLoggedIn(0);
                    System.out.println("Logout Successful");
                    return;
                	}
                }
            }
		
         // help
            else if (elements[0].equalsIgnoreCase("help")) {
                //System.out.println("Here are your available commands:");
                printCommands();
            }

            // Check if user is logged in
            else if (getUserLoggedIn() == 0) {
                System.out.println("Login first before using commands. Type 'help' for more information");
            }

            // registerRestaurant <name> <Latitude> <Longitude> <username> <password>
            else if (elements[0].equalsIgnoreCase("registerrestaurant")) {
                if (getUserLoggedIn() != 1) {
                    System.out.println("User cannot access this command");
                    return;
                }

                if (elements.length != 6) {
                    System.out.println("Invalid Command. Use following format: registerRestaurant <name> <Latitude> <Longitude> <username> <password>");
                    return;
                }

                try {
                    Location loc = new Location(Double.parseDouble(elements[2]), Double.parseDouble(elements[3]));
                    Restaurant r = new Restaurant(elements[4], elements[5], "Restaurant", elements[1], loc);
                    System.out.println("Successfully added");
                    activeMembers.add(r);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid latitude or longitude format.");
                } catch (Exception e) {
                    System.out.println("An error occurred while registering the restaurant.");
                    e.printStackTrace();
                }
            }

            // registerCustomer <firstName> <lastName> <username> <Latitude> <Longitude> <password>
            else if (elements[0].equalsIgnoreCase("registercustomer")) {
                if (getUserLoggedIn() != 1) {
                    System.out.println("User cannot access this command");
                    return;
                }

                if (elements.length != 7) {
                    System.out.println("Invalid Command. Use following format: registerCustomer <firstName> <lastName> <username> <Latitude> <Longitude> <password>");
                    return;
                }

                try {
                    Location loc = new Location(Double.parseDouble(elements[4]), Double.parseDouble(elements[5]));
                    Customer c = new Customer(elements[3], elements[6], "Customer", elements[1], elements[2], "blank@gmail.com", "***-***-****", loc);
                    try {
                        @SuppressWarnings("resource")
    					Scanner inputLine = new Scanner(System.in);
                        System.out.println("Please input your Email and Phone Number in the following format: <Email> <Phone_Number>");
                        //System.out.println("For Fidelity Card, there are three options: Basic, Point and Lottery");
                        String s;
                        String[] e;
                        while (true) {
                            s = inputLine.nextLine().toString();
                            e = s.split(" ");
                            if (e.length != 2) {
                                System.out.println("Incorrect format. Enter in the following format: <Email> <Phone_Number>");
                            } else {
                                break;
                            }
                        }
                        for (int i = 0; i < e.length; i++) {
                            e[i] = e[i].trim();
                        }
                        c.setCellNumber(e[1].trim());
                        c.setEmail(e[0].trim().toUpperCase());
                    } catch (Exception e) {
                        System.out.println("An error occurred while registering the Customer.");
                        e.printStackTrace();
                    }
                    System.out.println("Successfully added");
                    activeMembers.add(c);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid latitude or longitude format.");
                } catch (Exception e) {
                    System.out.println("An error occurred while registering the customer.");
                    e.printStackTrace();
                }
            }

            // registerCourier <firstName> <lastName> <username> <position> <password>
            else if (elements[0].equalsIgnoreCase("registercourier")) {
                if (getUserLoggedIn() != 1) {
                    System.out.println("User cannot access this command");
                    return;
                }

                if (elements.length != 6) {
                    System.out.println("Invalid Command. Use following format: registerCourier <firstName> <lastName> <username> <position> <password>");
                    return;
                }

                Courier d = new Courier(elements[3], elements[5], "Courier", elements[1], elements[2], "***-***-****", 0, false, new Location(0, 0));
                try {
                    @SuppressWarnings("resource")
					Scanner inputLine = new Scanner(System.in);
                    System.out.println("Please input your Phone Number and Location in the following format: <phone number> <Latitude> <Longitude>");
                    String s;
                    String[] e;
                    while (true) {
                        s = inputLine.nextLine().toString();
                        e = s.split(" ");
                        if (e.length != 3) {
                            System.out.println("Incorrect format. Enter in the following format: <phone number> <Latitude> <Longitude>");
                        } else {
                            break;
                        }
                    }
                    for (int i = 0; i < e.length; i++) {
                        e[i] = e[i].trim();
                    }
                    d.setPhoneNumber(e[0].trim());
                    d.getLoc().setLocation(Double.parseDouble(e[1].trim()), Double.parseDouble(e[2].trim()));
                    System.out.println("Successfully added");
                    activeMembers.add(d);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid latitude or longitude format.");
                } catch (Exception e) {
                    System.out.println("An error occurred while registering the courier.");
                    e.printStackTrace();
                }
            }

            // addDishRestauarantMenu <dishName> <dishCategory> <foodCategory> <unitPrice>
            else if (elements[0].equalsIgnoreCase("adddishrestaurantmenu")) {
                if (getUserLoggedIn() != 2) {
                    System.out.println("User does not have access to this command.");
                    return;
                }

                if (elements.length != 5) {
                    System.out.println("Invalid Command. Use following format: addDishrestaurantmenu <dishName> <dishCategory> <foodCategory> <unitPrice>");
                    return;
                }

                // Valid options for dishCategory and foodCategory
                @SuppressWarnings("unused")
				String[] validDishCategories = {"starter", "main", "dessert"};
                String[] validFoodCategories = {"standard", "vegetarian", "gluten-free"};

                String dishName = elements[1];
                String dishCategory = elements[2].toLowerCase();
                String foodCategory = elements[3].toLowerCase();
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
                    unitPrice = Double.parseDouble(elements[4]);

                    // Create the dish object
                    Dishes dish = new Dishes(dishName, dishCategory, foodCategory, unitPrice);

                    // Add the dish to the restaurant's menu
                    getRest().getMenu().addDish(dish);

                    System.out.println("Dish added successfully to the restaurant's menu.");
                } catch (NumberFormatException e) {
                    System.out.println("Invalid unit price format.");
                } catch (Exception e) {
                    System.out.println("An error occurred while adding the dish to the restaurant's menu.");
                    e.printStackTrace();
                }
            }

			// createMeal <mealName>
			else if (elements[0].equalsIgnoreCase("createmeal")) {
				if (getUserLoggedIn() != 2) {
                    System.out.println("User does not have access to this command.");
                    return;
                }

                if (elements.length != 2) {
                    System.out.println("Invalid Command. Use following format: createMeal <mealName>");
                    return;
                }
                Meal m = new Meal(elements[1]);
                try {
                    //getRest().addMeal(m);
                    setNewMeal(m, true);
                    System.out.println("Successfully created");
                } catch (Exception e) {
                    System.out.println("An error occurred while creating the meal.");
                    e.printStackTrace();
                    return;
                }
			}
			
			// addDish2Meal <dishName> <mealName>
			else if (elements[0].equalsIgnoreCase("adddish2meal")) {
				// Check if the user has the necessary access level (2)
			    if (getUserLoggedIn() != 2) {
			        System.out.println("User does not have access to this command.");
			        return;
			    }
			    // Validate the command format
			    if (elements.length != 3) {
			        System.out.println("Invalid Command. Use the following format: addDish2Meal <dishName> <mealName>");
			        return;
			    }
			    // Extract dish name and meal name from the command
			    String dishName = elements[1].trim();
			    String mealName = elements[2].trim();
			    // Find the target meal in the available meals
			    Meal targetMeal = null;
			    for (Meal meal : getRest().getMenu().getAvailMeals()) {
			        if (meal.getMealName().equalsIgnoreCase(mealName)) {
			            targetMeal = meal;
			            break;
			        }
			    }
			    // Check to see if meal has not been saved.
			    if(getNewMeal()!= null && mealName.equalsIgnoreCase(getNewMeal().getMealName())) {
			    	targetMeal = getNewMeal();
			    }
			    // If target meal not found, inform user and return
			    if (targetMeal == null) {
			        System.out.println("Unable to find listed meal. Try again with the correct name.");
			        return;
			    }
			    // Find the target dish in the available dishes
			    Dishes targetDish = null;
			    for (Dishes dish : getRest().getMenu().getAvailDishes()) {
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
			    targetMeal.addDish(targetDish);
			    if(targetMeal.equals(getNewMeal())) {
			    	System.out.println("Successfully added to new meal. Save Meal to add to menu");
			    	return;
			    }
			    else {
			    	System.out.println("Successfully added to meal. Menu has been updated");
			    }
			}

			
			// showMeal <mealName> EDIT THIS TO INCLUDE ALL ASPECTS
			else if (elements[0].equalsIgnoreCase("showmeal")) {
				// Check if the user has the necessary access level (2)
			    if (getUserLoggedIn() != 2) {
			        System.out.println("User does not have access to this command.");
			        return;
			    }
			    // Validate the command format
			    if (elements.length != 2) {
			        System.out.println("Invalid Command. Use the following format: showMeal <mealName>");
			        return;
			    }
			    getRest().showMeal(elements[1].trim());
			    return;
			}
			
			// saveMeal <mealName>
			else if (elements[0].equalsIgnoreCase("savemeal")) {
				// Check if the user has the necessary access level (2)
			    if (getUserLoggedIn() != 2) {
			        System.out.println("User does not have access to this command.");
			        return;
			    }
			    // Validate the command format
			    if (elements.length != 2) {
			        System.out.println("Invalid Command. Use the following format: saveMeal <mealName>");
			        return;
			    }
			    if(elements[1].trim().equalsIgnoreCase(getNewMeal().getMealName())) {
			    	getRest().getMenu().addMeal(getNewMeal());
			    	System.out.println("Successfully added meal to menu.");
			    	setNewMeal(null, false);
			    	return;
			    }
			    else {
			    	System.out.println("Unable to find correct meal. Please try again using the name used earlier in createMeal command");
			    }
			}
			
			// setSpecialOffer <mealName> WORK ON PRICING FUNCTIONS!!
			else if (elements[0].equalsIgnoreCase("setspecialoffer")) {
				// Check if the user has the necessary access level (2)
			    if (getUserLoggedIn() != 2) {
			        System.out.println("User does not have access to this command.");
			        return;
			    }
			    // Validate the command format
			    if (elements.length != 2) {
			        System.out.println("Invalid Command. Use the following format: setSpecialOffer <mealName>");
			        return;
			    }
			    for (Meal m : getRest().getMenu().getAvailMeals()) {
			    	if(m.getMealName().equalsIgnoreCase(elements[1].trim())) {
			    		getRest().getMenu().addSpecial(m, getRest().getDiscountFactor());
			    		System.out.printf("Successfully added %s to Weekly Specials%n", m.getMealName());
				        return;
			    	}
			    }
			    System.out.println("Unable to find meal in listed menu. Try again with correct meal name.");
		        return;
			}
			
			// removeFromSpecialOffer <mealName>
			else if (elements[0].equalsIgnoreCase("removefromspecialoffer")) {
				// Check if the user has the necessary access level (2)
			    if (getUserLoggedIn() != 2) {
			        System.out.println("User does not have access to this command.");
			        return;
			    }
			    // Validate the command format
			    if (elements.length != 2) {
			        System.out.println("Invalid Command. Use the following format: removeFromSpecialOffer <mealName>");
			        return;
			    }
			    for (Meal m : getRest().getMenu().getAvailMeals()) {
			    	if(m.getMealName().equalsIgnoreCase(elements[1].trim())) {
			    		getRest().getMenu().removeSpecial(m, getRest().getDiscountFactor());
			    		System.out.printf("Successfully removed %s from Weekly Specials%n", m.getMealName());
				        return;
			    	}
			    }
			    System.out.println("Unable to find meal in listed menu. Try again with correct meal name.");
		        return;
			}
			
            // setDiscountPercentage <discount-Factor>
     		else if (elements[0].equalsIgnoreCase("setDiscountPercentage")) {
     		// Check if the user has the necessary access level (2)
			    if (getUserLoggedIn() != 2) {
			        System.out.println("User does not have access to this command.");
			        return;
			    }
			    // Validate the command format
			    if (elements.length != 2) {
			        System.out.println("Invalid Command. Use the following format: setDiscountPercentage <discount-Factor>");
			        return;
			    }
			    getRest().setDiscountFactor(Double.parseDouble(elements[1]));
			    System.out.println("Successfully changed Discount-Factor");
			    return;
     		}
			// createOrder <restaurantName> <orderName> ADD IN CHECK IF THERE IS A CURRENT ORDER ALREADY OPEN
            //Should print out menu available for customer
			else if (elements[0].equalsIgnoreCase("createorder")) {
				if (getUserLoggedIn() != 3) {
			        System.out.println("User does not have access to this command.");
			        return;
			    }
			    // Validate the command format
			    if (elements.length != 3) {
			        System.out.println("Invalid Command. Use the following format: createOrder <restaurantName> <orderName>");
			        return;
			    }
			    for(User u : this.activeMembers) {
			    	if(u.getUserType().equalsIgnoreCase("Restaurant")) {
			    		Restaurant r = (Restaurant) u;
			    		getCust().createOrder(elements[2].trim(), r);
				        System.out.println("Order Successfully created. Add items to your order and save it to confirm.");
			    		return;
			    	}
			    }
			    System.out.println("Unable to find Restaurant. Try again using correct name.");
			    
			}
			
			// addItem2Order <orderName> <itemName>
			else if (elements[0].equalsIgnoreCase("additem2order")) {
				if (getUserLoggedIn() != 3) {
			        System.out.println("User does not have access to this command.");
			        return;
			    }
			    // Validate the command format
			    if (elements.length != 3) {
			        System.out.println("Invalid Command. Use the following format: addItem2Order <orderName> <itemName>");
			        return;
			    }
			    Restaurant r = getCust().getRestaurant(elements[1].trim());
			    Meal mealItem = null;
			    Dishes dishItem = null;
			    for (Meal m : r.getMenu().getAvailMeals()) {
			    	if(elements[2].trim().equalsIgnoreCase(m.getMealName())) {
			    		mealItem = m;
			    	}
			    }
			    for (Dishes d : r.getMenu().getAvailDishes()) {
			    	if(elements[2].trim().equalsIgnoreCase(d.getDishName())) {
			    		dishItem = d;
			    	}
			    }
			    
			    if(mealItem != null || dishItem != null) {
			    	getCust().add2Order(mealItem, dishItem);
			    	return;
			    }
			    System.out.println("Unable to find food item. Try again using correct name");
			    return;
			}
			
			// endOrder <orderName> <date> //HOW DO WE PAY?! ALLOCATE DRIVER, etc.
			else if (elements[0].equalsIgnoreCase("endorder")) {
				if (getUserLoggedIn() != 3) {
			        System.out.println("User does not have access to this command.");
			        return;
			    }
			    // Validate the command format
			    if (elements.length != 3) {
			        System.out.println("Invalid Command. Use the following format: endOrder <orderName> <date>");
			        return;
			    }
			    
			    if(getCust().checkOrder(elements[1].trim())) {
			    	getCust().finalizeOrder(elements[2].trim());
			    	System.out.println("Order successfully ended. Sending to myFoodora manager and Restaurant for processing.");
			    	return;
			    }
			    System.out.println("Unable to find order name. Try again using correct order name");
			}
			
			// onDuty <username>
			else if (elements[0].equalsIgnoreCase("onduty")) {
				if (getUserLoggedIn() != 4) {
			        System.out.println("User does not have access to this command.");
			        return;
			    }
			    // Validate the command format
			    if (elements.length != 2) {
			        System.out.println("Invalid Command. Use the following format: onDuty <username>");
			        return;
			    }
			    if(getCour().getUsername().equals(elements[1].trim())) {
				    if(getCour().onDuty()) {
				    	System.out.printf("%s is already on duty%n", getCour().getName());
				    	return;
				    }
			    	System.out.printf("%s is now on duty%n", getCour().getName());
				    getCour().setOnDuty(true);
				    return;
			    }
			    System.out.println("Incorrect username. Try again using correct username (CASE SENSITIVE)");
			}
			
			// offDuty <username>
			else if (elements[0].equalsIgnoreCase("offduty")) {
				if (getUserLoggedIn() != 4) {
			        System.out.println("User does not have access to this command.");
			        return;
			    }
			    // Validate the command format
			    if (elements.length != 2) {
			        System.out.println("Invalid Command. Use the following format: offDuty <username>");
			        return;
			    }
			    if(getCour().getUsername().equals(elements[1].trim())) {
			    	if(!getCour().onDuty()) {
				    	System.out.printf("%s is already off duty%n", getCour().getName());
				    	return;
				    }
				    System.out.printf("%s is now off duty%n", getCour().getName());
				    getCour().setOnDuty(false);
				    return;
			    }
			    System.out.println("Incorrect username. Try again using correct username (CASE SENSITIVE)");
			}
			
			// findDeliverer <orderName>
			else if (elements[0].equalsIgnoreCase("finddeliverer")) {
				if (getUserLoggedIn() != 1) {
			        System.out.println("User does not have access to this command.");
			        return;
			    }
			    // Validate the command format
			    if (elements.length != 2) {
			        System.out.println("Invalid Command. Use the following format: findDeliverer <orderName>");
			        return;
			    }
			    ArrayList<Customer> c = new ArrayList<>();
			    ArrayList<Courier> d = new ArrayList<>();
			    for ( User u : this.activeMembers) {
			    	if(u.getUserType().equalsIgnoreCase("Customer")) {
			    		c.add((Customer)u);
			    	}
			    }
			    /*
			    for ( User u : this.activeMembers) {
			    	if(u.getUserType().equalsIgnoreCase("Courier")) {
			    		d.add((Courier)u);
			    	}
			    }
			    */
			    for(Customer cust : c) {
			    	if(cust.findOrder(elements[1].trim())) {
			    		// Find deliverer based on delivery policy... Implement this
			    	}
			    }
			    
			}
			
			// setDeliveryPolicy <delPolicyName>
			else if (elements[0].equalsIgnoreCase("setdeliverypolicy")) {
			   
			}
			
			// setProfitPolicy <ProfitPolicyName>
			else if (elements[0].equalsIgnoreCase("setprofitpolicy")) {
			    
			}
			
			// associateCard <userName> <cardType>
			else if (elements[0].equalsIgnoreCase("associatecard")) {
			    
			}
			
			// showCourierDeliveries <>
			else if (elements[0].equalsIgnoreCase("showcourierdeliveries")) {
				if (getUserLoggedIn() != 1) {
			        System.out.println("User does not have access to this command.");
			        return;
			    }
			    // Validate the command format
			    if (elements.length != 1) {
			        System.out.println("Invalid Command. Use the following format: showCustomers <>");
			        return;
			    }
			    ArrayList<Courier> best = new ArrayList<>();
			    for (User u : this.activeMembers) {
			    	if(u.getUserType().equalsIgnoreCase("Courier")) {
			    		best.add((Courier) u);
			    	}
			    }
			    bubbleSortCouriersByDeliveries(best);
			    for (Courier courier : best) {
		            System.out.println(courier);
		        }
			}
			
			// showRestaurantTop <>
			else if (elements[0].equalsIgnoreCase("showrestauranttop")) {
				if (getUserLoggedIn() != 1) {
			        System.out.println("User does not have access to this command.");
			        return;
			    }
			    // Validate the command format
			    if (elements.length != 1) {
			        System.out.println("Invalid Command. Use the following format: showCustomers <>");
			        return;
			    }
			    ArrayList<Courier> best = new ArrayList<>();
			    for (User u : this.activeMembers) {
			    	if(u.getUserType().equalsIgnoreCase("Courier")) {
			    		best.add((Courier) u);
			    	}
			    }
			    bubbleSortCouriersByDeliveries(best);
			    for (Courier courier : best) {
		            System.out.println(courier);
		        }
			}
			
			// showCustomers <>
			else if (elements[0].equalsIgnoreCase("showcustomers")) {
				if (getUserLoggedIn() != 1) {
			        System.out.println("User does not have access to this command.");
			        return;
			    }
			    // Validate the command format
			    if (elements.length != 1) {
			        System.out.println("Invalid Command. Use the following format: showCustomers <>");
			        return;
			    }
			    for (User u : this.activeMembers) {
			    	if(u.getUserType().equalsIgnoreCase("Customer")) {
			    		Customer a = (Customer) u;
			    		System.out.printf("** %s%n",a.getFullName());
			    	}
			    }
			}
			
			// showMenuItem <restaurant-name>
			else if (elements[0].equalsIgnoreCase("showmenuitem")) {
			    
			}
			
			// showTotalProfit <>
			else if (elements[0].equalsIgnoreCase("showtotalprofit")) {
			    
			}
			
			// showTotalProfit <startDate> <endDate>
			else if (elements[0].equalsIgnoreCase("showtotalprofitperiod")) {
			    
			}
			
			// runTest <testScenario-file>
			else if (elements[0].equalsIgnoreCase("runtest")) {
			    
			}
			
			// Invalid command -> Try again
			else {
				System.out.println("\033[0;31mInvalid Command\033[0m");
			}
		}
	}
}
