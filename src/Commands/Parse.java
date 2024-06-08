package Commands;

import java.util.ArrayList;
import java.util.Scanner;

import Code.SystemState;
import user.*;
import food.*;
/**
 * The Parse class is responsible for parsing commands and delegating them to the appropriate command visitors.
 */
public class Parse {
    
    /** The system state containing information about the current system state. */
    private SystemState systemState;
    
    /** The executor for executing parsed commands. */
    private CommandExecutor commandExecutor;

    /**
     * Constructs a Parse object with the given list of users.
     *
     * @param users the list of users in the system
     */
    public Parse(ArrayList<User> users) {
        this.setSystemState(new SystemState(this));
        this.getSystemState().setActiveMembers(users);
        this.commandExecutor = new CommandExecutor(this.getSystemState());
        this.systemState.setCommandExecutor(commandExecutor);
    }

    /**
     * Retrieves the loop status of the system.
     *
     * @return true if the system loop should continue; false otherwise
     */
    public boolean getLoop() {
        return getSystemState().getRun();
    }

    /**
     * Accepts a command visitor to process the given command.
     *
     * @param command the command to be processed
     * @param visitor the command visitor to handle the command
     */
    public void acceptCommandVisitor(String command, CommandVisitor visitor) {
        command = command.toLowerCase(); // Convert the command to lowercase
        
        String[] elements;
        // Separate the commands into their arguments
        elements = command.split(" ");
        
        for (int i = 0; i < elements.length; i++) {
            elements[i] = elements[i].trim();
        }

        // Process the command based on its type and user role
        if (elements[0].startsWith("setdeliverypolicy") || elements[0].startsWith("setprofitpolicy") ||
            elements[0].startsWith("associatecard") || elements[0].startsWith("showcourierdeliveries") ||
            elements[0].startsWith("showrestauranttop") || elements[0].startsWith("showcustomers") ||
            elements[0].startsWith("showmenuitem") || elements[0].startsWith("showtotalprofit") ||
            elements[0].startsWith("registerrestaurant") || elements[0].startsWith("registercustomer") ||
            elements[0].startsWith("registercourier") || elements[0].startsWith("registermanager") ||
            elements[0].startsWith("activateuser") || elements[0].startsWith("deactivateuser") ||
            elements[0].startsWith("removeuser")) {
            // Manager commands
            visitor.visitManagerCommand(elements);
        } else if (elements[0].startsWith("createorder") || elements[0].startsWith("additem2order") ||
                   elements[0].startsWith("endorder") || elements[0].startsWith("registerfidelity") ||
                   elements[0].startsWith("accountinformation") || elements[0].startsWith("setnotification") ||
                   elements[0].startsWith("showrestaurants")) {
            // Customer commands
            visitor.visitCustomerCommand(elements);
        } else if (elements[0].startsWith("onduty") || elements[0].startsWith("offduty") ||
                   elements[0].startsWith("finishdelivery")) {
            // Courier commands
            visitor.visitCourierCommand(elements);
        } else if (elements[0].startsWith("finddeliverer") || elements[0].startsWith("adddishrestaurantmenu") ||
                   elements[0].startsWith("createmeal") || elements[0].startsWith("adddish2meal") ||
                   elements[0].startsWith("showmeal") || elements[0].startsWith("savemeal") ||
                   elements[0].startsWith("setspecialoffer") || elements[0].startsWith("removefromspecialoffer") ||
                   elements[0].startsWith("setdiscountpercentage") || elements[0].startsWith("removemeal") ||
                   elements[0].startsWith("removedish") || elements[0].startsWith("sortorders")) {
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

    /**
     * Retrieves the system state.
     *
     * @return the system state
     */
    public SystemState getSystemState() {
        return systemState;
    }

    /**
     * Sets the system state.
     *
     * @param systemState the system state to be set
     */
    public void setSystemState(SystemState systemState) {
        this.systemState = systemState;
    }
}
