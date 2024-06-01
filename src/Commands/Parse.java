package Commands;

import java.util.ArrayList;
import java.util.Scanner;

import Code.SystemState;
import user.*;
import food.*;

public class Parse {
	private SystemState systemState;
    private CommandExecutor commandExecutor;

    public Parse(ArrayList<User> users) {
        this.setSystemState(new SystemState());
        this.getSystemState().setActiveMembers(users);
        this.commandExecutor = new CommandExecutor(this.getSystemState());
    }

    public boolean getLoop() {
        return getSystemState().getRun();
    }

    public void acceptCommandVisitor(String command, CommandVisitor visitor) {
        command = command.toLowerCase(); // Convert the command to lowercase
        
        String[] elements;
        // Separate the commands into their arguments
        elements = command.split(" ");
        
        for (int i = 0; i < elements.length; i++) {
            elements[i] = elements[i].trim();
        }

        // Process the command based on the user type and the command itself
        if (elements[0].startsWith("setdeliverypolicy") || elements[0].startsWith("setprofitpolicy") ||
            elements[0].startsWith("associatecard") || elements[0].startsWith("showcourierdeliveries") ||
            elements[0].startsWith("showrestauranttop") || elements[0].startsWith("showcustomers") ||
            elements[0].startsWith("showmenuitem") || elements[0].startsWith("showtotalprofit") ||
            elements[0].startsWith("registerrestaurant") || elements[0].startsWith("registercustomer") ||
            elements[0].startsWith("registercourier")) {
            // Manager commands
            visitor.visitManagerCommand(elements);
        } else if (elements[0].startsWith("createorder") || elements[0].startsWith("additem2order") ||
                   elements[0].startsWith("endorder")|| elements[0].startsWith("showrestaurants")) {
            // Customer commands
            visitor.visitCustomerCommand(elements);
        } else if (elements[0].startsWith("onduty") || elements[0].startsWith("offduty") ||
                   elements[0].startsWith("acceptdelivery") || elements[0].startsWith("finishdelivery") ||
                   elements[0].startsWith("getorderpos") || elements[0].startsWith("updateposition")) {
            // Courier commands
            visitor.visitCourierCommand(elements);
        } else if (elements[0].startsWith("finddeliverer") || elements[0].startsWith("adddishrestauarantmenu") ||
                   elements[0].startsWith("createmeal") || elements[0].startsWith("adddish2meal") ||
                   elements[0].startsWith("showmeal") || elements[0].startsWith("savemeal") ||
                   elements[0].startsWith("setspecialoffer") || elements[0].startsWith("removefromspecialoffer") ||
                   elements[0].startsWith("setdiscountpercentage")) {
            // Restaurant commands
            visitor.visitRestaurantCommand(elements);
        } else if (elements[0].startsWith("login") || elements[0].startsWith("logout") ||
                   elements[0].startsWith("runtest") || elements[0].startsWith("help") ||
                   elements[0].startsWith("end")) {
            // Anyone commands
            visitor.visitAnyoneCommand(elements);
        } else {
            // Invalid command
            System.out.println("Invalid command.");
        }
    }



	
	//Process Commands for initialization purposes!!
	
    @SuppressWarnings("unused")
    public void processCommands(String lines) {
        String[] elements;
        elements = lines.split(",");

        for (int i = 0; i < elements.length; i++) {
            elements[i] = elements[i].trim();
        }

        if (elements.length >= 3) {
            String elementType = elements[2].toLowerCase();

            try {
                if (elementType.equals("customer") && elements.length >= 9) {
                    Location loc = new Location(Double.parseDouble(elements[7]), Double.parseDouble(elements[8]));
                    Customer n = new Customer(elements[0], elements[1], elements[2], elements[3], elements[4], elements[5], elements[6], loc);
                    getSystemState().getActiveMembers().add(n);
                } else if (elementType.equals("manager") && elements.length >= 5) {
                    Manager m = new Manager(elements[0], elements[1], elements[2], elements[3], elements[4]);
                    getSystemState().getActiveMembers().add(m);
                } else if (elementType.equals("restaurant") && elements.length >= 6) {
                    Location loc = new Location(Double.parseDouble(elements[4]), Double.parseDouble(elements[5]));
                    Restaurant r = new Restaurant(elements[0], elements[1], elements[2], elements[3], loc);
                    getSystemState().getActiveMembers().add(r);
                } else if (elementType.equals("courier") && elements.length >= 10) {
                    Location loc = new Location(Double.parseDouble(elements[8]), Double.parseDouble(elements[9]));
                    Courier c = new Courier(elements[0], elements[1], elements[2], elements[3], elements[4], elements[5], Integer.parseInt(elements[6]), Boolean.parseBoolean(elements[7]), loc);
                    getSystemState().getActiveMembers().add(c);
                } else if (elements.length >= 2) {
                    String command = elements[0].toLowerCase();
                    String restaurantName = elements[1];
                    Restaurant restaurant = findRestaurantByName(restaurantName);

                    if (restaurant != null) {
                        switch (command) {
                            case "adddishrestaurantmenu":
                                if (elements.length >= 5) {
                                    String dishName = elements[2];
                                    String dishCategory = elements[3];
                                    String foodCategory = elements[4];
                                    double unitPrice = Double.parseDouble(elements[5]);
                                    Dishes dish = new Dishes(dishName, dishCategory, foodCategory, unitPrice);
                                    restaurant.addToMenu(dish);
                                    // System.out.println("Dish added to restaurant menu.");
                                } else {
                                    System.out.println("Insufficient elements for adding dish to restaurant menu.");
                                }
                                break;

                            case "createmeal":
                                if (elements.length >= 3) {
                                    String mealName = elements[2];
                                    Meal meal = new Meal(mealName);
                                    restaurant.addMeal(meal);
                                    // System.out.println("Meal created.");
                                } else {
                                    System.out.println("Insufficient elements for creating meal.");
                                }
                                break;

                            case "adddish2meal":
                                if (elements.length >= 4) {
                                    String dishName = elements[2];
                                    String mealName = elements[3];
                                    Meal meal = null;
                                    Dishes dish = null;
                                    for(Meal m : restaurant.getMenu().getAvailMeals()) {
                                    	if(mealName.equalsIgnoreCase(m.getMealName())) {
                                    		meal = m;
                                    	}
                                    }
                                    for(Dishes d : restaurant.getMenu().getAvailDishes()) {
                                    	if(dishName.equalsIgnoreCase(d.getDishName())) {
                                    		dish = d;
                                    	}
                                    }
                                    if (meal != null && dish != null) {
                                        meal.addDishes(dish);
                                        // System.out.println("Dish added to meal.");
                                    } else {
                                        System.out.println("Dish or meal not found.");
                                    }
                                } else {
                                    System.out.println("Insufficient elements for adding dish to meal.");
                                }
                                break;

                            case "showmeal":
                                if (elements.length >= 3) {
                                    String mealName = elements[2];
                                    Meal meal = null;
                                    for(Meal m : restaurant.getMenu().getAvailMeals()) {
                                    	if(mealName.equalsIgnoreCase(m.getMealName())) {
                                    		meal = m;
                                    	}
                                    }
                                    if (meal != null) {
                                        System.out.println(meal);
                                    } else {
                                        System.out.println("Meal not found.");
                                    }
                                } else {
                                    System.out.println("Insufficient elements for showing meal.");
                                }
                                break;

                            case "savemeal":
                                if (elements.length >= 3) {
                                    String mealName = elements[2];
                                    Meal meal = null;
                                    for(Meal m : restaurant.getMenu().getAvailMeals()) {
                                    	if(mealName.equalsIgnoreCase(m.getMealName())) {
                                    		meal = m;
                                    	}
                                    }
                                    if (meal != null) {
                                    	restaurant.getMenu().addMeal(meal);
                                        // System.out.println("Meal saved.");
                                    } else {
                                        System.out.println("Meal not found.");
                                    }
                                } else {
                                    System.out.println("Insufficient elements for saving meal.");
                                }
                                break;

                            case "removefromspecialoffer":
                                if (elements.length >= 3) {
                                    String mealName = elements[2];
                                    Meal meal = null;
                                    for(Meal m : restaurant.getMenu().getAvailMeals()) {
                                    	if(mealName.equalsIgnoreCase(m.getMealName())) {
                                    		meal = m;
                                    	}
                                    }
                                    if (meal != null) {
                                    	restaurant.getMenu().removeSpecial(meal, restaurant.getDiscountFactor());
                                        // System.out.println("Meal removed from special offer.");
                                    } else {
                                        System.out.println("Meal not found.");
                                    }
                                } else {
                                    System.out.println("Insufficient elements for removing meal from special offer.");
                                }
                                break;

                            case "setspecialoffer":
                                if (elements.length >= 3) {
                                    String mealName = elements[2];
                                    Meal meal = null;
                                    for(Meal m : restaurant.getMenu().getAvailMeals()) {
                                    	if(mealName.equalsIgnoreCase(m.getMealName())) {
                                    		meal = m;
                                    	}
                                    }
                                    if (meal != null) {
                                    	restaurant.getMenu().addSpecial(meal, restaurant.getDiscountFactor());
                                        // System.out.println("Meal set as special offer.");
                                    } else {
                                        System.out.println("Meal not found.");
                                    }
                                } else {
                                    System.out.println("Insufficient elements for setting special offer.");
                                }
                                break;

                            case "setdiscountpercentage":
                                if (elements.length >= 3) {
                                    double discountFactor = Double.parseDouble(elements[2]);
                                    restaurant.setDiscountFactor(discountFactor);
                                    // System.out.printf("Discount percentage of %.2f percent set.%n", discountFactor);
                                } else {
                                    System.out.println("Insufficient elements for setting discount percentage.");
                                }
                                break;

                            default:
                                System.out.println("Unknown restaurant command.");
                                break;
                        }
                    } else {
                        System.out.println("Restaurant not found.");
                    }
                } else {
                	System.out.println(elements.length);
                    System.out.println("Insufficient elements for member creation.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid numeric format in input data.");
            }
        } else {
            System.out.println("Insufficient elements for processing.");
        }
    }

    private Restaurant findRestaurantByName(String restaurantName) {
        for (User user : getSystemState().getActiveMembers()) {
            if (user instanceof Restaurant && user.getName().equalsIgnoreCase(restaurantName)) {
                return (Restaurant) user;
            }
        }
        return null;
    }


	public SystemState getSystemState() {
		return systemState;
	}

	public void setSystemState(SystemState systemState) {
		this.systemState = systemState;
	}
}
