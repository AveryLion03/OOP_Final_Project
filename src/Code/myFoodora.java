package Code;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import Commands.*;
import user.*;
import food.*;

public class myFoodora {
    
    public static void main(String[] args) {
        // Have User be one giant ArrayList?
        ArrayList<User> users = new ArrayList<>();
        Parse foodSys = new Parse(users);

        // Run initialization program
        File start = new File("Code/startUp.txt");
        ArrayList<String> lines = new ArrayList<>(); // To hold the lines of the text file separately
        try (Scanner scanner = new Scanner(start)) {
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
            for (String line : lines) {
                foodSys.processCommands(true, line);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found: " + e.getMessage());
            return; // Terminate the program if initialization file is missing
        }

        System.out.println("Initialization File Complete! CLUI Now Active");

        // Create a command visitor
        CommandVisitor commandExecutor = new CommandExecutor();

        // Begin Command Line User Interface (CLUI)
        try (Scanner inputLine = new Scanner(System.in)) {
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
