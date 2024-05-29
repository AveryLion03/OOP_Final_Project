package Code;

import java.util.ArrayList;
import java.util.Scanner;
import user.*;
import food.*;

public class Parse {
	protected ArrayList<User> members;
    private static Boolean run = true;
    protected int userLoggedIn = 0;
    private Restaurant r;
    private Customer c;
    private Courier d;
    private Manager m;
    
    public Parse(ArrayList<User> mem) {
		super();
		this.members = mem;
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
         */
        if (init) {
            if (elements.length >= 3) {
                String elementType = elements[2].toLowerCase(); // Ensure elements[2] exists before calling toLowerCase()

                try {
                    if (elementType.equals("customer") && elements.length >= 9) {
                        Location loc = new Location(Double.parseDouble(elements[7]), Double.parseDouble(elements[8]));
                        Customer n = new Customer(elements[0], elements[1], elements[2], elements[3], elements[4], elements[5], elements[6], loc);
                        members.add(n);
                        //System.out.println("Customer Added");
                    } else if (elementType.equals("manager") && elements.length >= 5) {
                        Manager m = new Manager(elements[0], elements[1], elements[2], elements[3], elements[4]);
                        members.add(m);
                        //System.out.println("Manager Added");
                    } else if (elementType.equals("restaurant") && elements.length >= 6) {
                        Location loc = new Location(Double.parseDouble(elements[4]), Double.parseDouble(elements[5]));
                        Restaurant r = new Restaurant(elements[0], elements[1], elements[2], elements[3], loc);
                        members.add(r);
                        //System.out.println("Restaurant Added");
                    } else if (elementType.equals("courier") && elements.length >= 10) {
                        Location loc = new Location(Double.parseDouble(elements[8]), Double.parseDouble(elements[9]));
                        Courier c = new Courier(elements[0], elements[1], elements[2], elements[3], elements[4], elements[5], Integer.parseInt(elements[6]), Boolean.parseBoolean(elements[7]), loc);
                        members.add(c);
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
                for (int i = 0; i < members.size(); i++) {
                    String username = elements[1].trim();
                    String password = elements[2].trim();
                    
                    if (username.equals(members.get(i).getUsername()) && 
                        password.equals(members.get(i).getPassword())) {
                        
                        String userType = members.get(i).getUserType().toLowerCase();
                        
                        System.out.printf("Login Successful. Welcome, %s%n", members.get(i).getName());
                        
                        switch (userType) {
                            case "manager":
                                setMan((Manager) members.get(i));
                                setUserLoggedIn(1);
                                break;
                            case "restaurant":
                                setRest((Restaurant) members.get(i));
                                setUserLoggedIn(2);
                                break;
                            case "customer":
                                setCust((Customer) members.get(i));
                                setUserLoggedIn(3);
                                break;
                            case "courier":
                                setCour((Courier) members.get(i));
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
                System.out.println("Here are your available commands:");
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
                    members.add(r);
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
                    System.out.println("Successfully added");
                    members.add(c);
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
                    members.add(d);
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
                    @SuppressWarnings("resource")
					Scanner inputLine = new Scanner(System.in);
                    System.out.println("Please input price and note if this meal is the meal of the day: <price> <mealofDay?>");
                    String s;
                    String[] e;
                    while (true) {
                        s = inputLine.nextLine().toString();
                        e = s.split(" ");
                        if (e.length != 2) {
                            System.out.println("Incorrect format. Enter in the following format: <price> <mealofDay?>");
                        } else {
                        	if(e[1].equalsIgnoreCase("true")|| e[1].equalsIgnoreCase("false")) break;
                        	else {
                        		System.out.println("Incorrect format for <mealOfDay?>. Enter in the following format: true or false");
                        	}
                        }
                    }
                    for (int i = 0; i < e.length; i++) {
                        e[i] = e[i].trim();
                    }
                    m.setPrice(Double.parseDouble(e[0]));
                    m.setDealOfDay(Boolean.parseBoolean(e[1]));
                    getRest().addMeal(m);
                    System.out.println("Successfully added");
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
			    targetMeal.addDishToMeal(targetDish);
			    System.out.println("Successfully added");
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
			    
			}
			
			// setSpecialOffer <mealName>
			else if (elements[0].equalsIgnoreCase("setspecialoffer")) {
			    
			}
			
			// removeFromSpecialOffer <mealName>
			else if (elements[0].equalsIgnoreCase("removefromspecialoffer")) {
			   
			}
			
			// createOrder <restaurantName> <orderName>
			else if (elements[0].equalsIgnoreCase("createorder")) {
			    
			}
			
			// addItem2Order <orderName> <itemName>
			else if (elements[0].equalsIgnoreCase("additem2order")) {
			    
			}
			
			// endOrder <orderName> <date>
			else if (elements[0].equalsIgnoreCase("endorder")) {
			    
			}
			
			// onDuty <username>
			else if (elements[0].equalsIgnoreCase("onduty")) {
			    
			}
			
			// offDuty <username>
			else if (elements[0].equalsIgnoreCase("offduty")) {
			    
			}
			
			// findDeliverer <orderName>
			else if (elements[0].equalsIgnoreCase("finddeliverer")) {
			    
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
			    
			}
			
			// showRestaurantTop <>
			else if (elements[0].equalsIgnoreCase("showrestauranttop")) {
			    
			}
			
			// showCustomers <>
			else if (elements[0].equalsIgnoreCase("showcustomers")) {
			    
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
