package Code;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import Commands.*;
import user.*;
import food.*;

/**
 * The {@code myFoodora} class serves as the entry point to the MyFoodora application.
 * It initializes the system, processes user commands via the Command Line User Interface (CLUI),
 * and executes the corresponding operations.
 * 
 * <p>This class sets up the initial list of users, parses and validates commands, and manages
 * the interaction between the user input and the core system state through the {@link CommandExecutor}.</p>
 * 
 * @version 1.0, 2024-06-07
 * @since 1.0
 */
public class myFoodora {
    /**
     * The main method initializes the MyFoodora system, sets up the command parser and executor,
     * and enters a loop to process commands from the user via the CLUI.
     * 
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Initialize an ArrayList of User
        ArrayList<User> users = new ArrayList<>();
        
        // Create a Parse instance with the users list
        Parse foodSys = new Parse(users);
        CommandExecutor commandExecutor = new CommandExecutor(foodSys.getSystemState());
        String init = "runTest startUp";
        // Begin Command Line User Interface (CLUI)
        try (Scanner inputLine = new Scanner(System.in)) {
        	// Run the initialization program
        	foodSys.acceptCommandVisitor(init, commandExecutor);
        	System.out.println("Initialization File Complete! CLUI Now Active\n\n\n");
        	foodSys.getSystemState().setAuto(false);
            while (foodSys.getLoop()) {
                System.out.print(">> ");
                System.out.flush();
                String command = inputLine.nextLine().trim();
                if (!command.isEmpty()) { // Check if the command is empty
                    foodSys.acceptCommandVisitor(command, commandExecutor);
                }
            }
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace(); // Print the stack trace for debugging purposes
        }
    }
}