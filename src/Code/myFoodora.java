package Code;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class myFoodora {
	public static void main (String [] args) throws IOException {
		boolean loop = true;
		// Enter data using BufferReader
		while (loop) {
	        BufferedReader reader = new BufferedReader(
	            new InputStreamReader(System.in));
	
	        // Reading data using readLine
	        String name = reader.readLine();
	        if(name.equals("Exit")||name.equals("exit")){
	        	loop = false;
	        }
	        else {
	        	// Printing the read line
	        	System.out.println(name);
	        }
		}
	}
}
