package Code;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import user.*;
import food.*;

public class myFoodora {
	
	
	public static void main (String [] args) {
		//Have User be one giant arraylist?
		ArrayList<User> u = new ArrayList<>();
		Parse foodSys = new Parse(u);
		
		//*********** Run initialization program: ***********
		File start = new File("Code/startUp.txt");
		//System.out.println("Absolute path: " + start.getAbsolutePath());
	    ArrayList<String> lines = new ArrayList<>(); // just something to hold the lines of the text file seperately
	    try (Scanner scanner = new Scanner(start)){
		    while (scanner.hasNextLine()){
		        lines.add(scanner.nextLine());          // read all lines of the text file
		    }
		    for (int i = 0; i< lines.size(); i++) {
		    	foodSys.processCommands(true, lines.get(i));
		    }
	    } catch (FileNotFoundException e) {
	    	System.err.println("File not found: " + e.getMessage());
	    }
	    
	    //***************** Begin CLUI **********************
        // greater than 0
		try (Scanner inputLine = new Scanner(System.in)) {
			while(foodSys.getLoop()) {
				//Read first command -> decide what to do from here
				String s = (inputLine.nextLine()).toString();
				//System.out.println(s);
				foodSys.processCommands(false, s);
				
			}
		}
	}
}
