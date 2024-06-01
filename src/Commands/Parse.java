package Commands;

import java.util.ArrayList;
import java.util.Scanner;

import Code.Location;
import user.*;
import food.*;

public class Parse {
	private SystemState systemState;
    private CommandExecutor commandExecutor;

    public Parse(ArrayList<User> users) {
        this.setSystemState(new SystemState());
        this.getSystemState().activeMembers = users;
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

	    if (elements[0].startsWith("setdeliverypolicy") || elements[0].startsWith("setprofitpolicy") ||
	        elements[0].startsWith("associatecard") || elements[0].startsWith("showcourierdeliveries") ||
	        elements[0].startsWith("showrestauranttop") || elements[0].startsWith("showcustomers") ||
	        elements[0].startsWith("showmenuitem") || elements[0].startsWith("showtotalprofit") ||
	        elements[0].startsWith("registerrestaurant") || elements[0].startsWith("registercustomer") ||
	        elements[0].startsWith("registercourier")) {
	        // Manager commands
	        visitor.visitManagerCommand(elements);
	    } else if (elements[0].startsWith("createorder") || elements[0].startsWith("additem2order") ||
	               elements[0].startsWith("endorder")) {
	        // Customer commands
	        visitor.visitCustomerCommand(elements);
	    } else if (elements[0].startsWith("onduty") || elements[0].startsWith("offduty")) {
	        // Courier commands
	        visitor.visitCourierCommand(elements);
	    } else if (elements[0].startsWith("finddeliverer") || elements[0].startsWith("adddishrestauarantmenu") ||
	               elements[0].startsWith("createmeal") || elements[0].startsWith("adddish2meal") ||
	               elements[0].startsWith("showmeal") || elements[0].startsWith("savemeal") ||
	               elements[0].startsWith("setspecialoffer") || elements[0].startsWith("removefromspecialoffer")) {
	        // Restaurant commands
	        visitor.visitRestaurantCommand(elements);
	    } else {
	        // Not logged in user
	        visitor.visitAnyoneCommand(elements);
	    }
	}


	
	//Process Commands
	
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

	public SystemState getSystemState() {
		return systemState;
	}

	public void setSystemState(SystemState systemState) {
		this.systemState = systemState;
	}
}
