package authentication;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/* Name: Authentication
 * Date: April 1
 * Authors:
 * Description:
 *
 */
public class Authentication {

	/* Verifies if the username and password are correct. 
	 * Returns True if they match and false if there is no user or the password is incorrect
	 * Arguments: 
	 * 	username: a string to represent the user 
	 * 	password: a string to represent the password
	 */
	public boolean login(String username, String password) {
		if (password.equals(getUser(username)))
			return true;
		return false;
	}
	
	/*
	 * a helper method to write a new user to the document returns true if it works
	 * false if user already exists
	 */
	public boolean addUser(String username, String password) throws IOException {
		if (!getUser(username).equals(null))
			return false;
		else {
			BufferedWriter writer = new BufferedWriter(new FileWriter("Accounts.txt", true));
			writer.newLine();
			writer.append(username);
			writer.newLine();
			writer.append(password);
			writer.close();
			return true;
		}
	}
	
	
	/* getUser is a helper method to GetUser a password of a user in the 
	 * database. If no user exists it will return null
	 * TODO
	 */
	public static String getUser(String username) {
		//Instantiate Scanner
		Scanner scan;
		try {
			scan = new Scanner(new File("Accounts.txt"));
		//Remove blank line at start
		String next = scan.nextLine();
		
		//Comb through document for the correct username 
		while(scan.hasNextLine()) {
			//Get the next username
			next = scan.nextLine();

			if(next.equals(username))
				return scan.nextLine();
			//Brush past next password
			next = scan.nextLine();			
		}
		
		return null;		 
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	private boolean setUser(String username, String password) {
		return false;
	}

}
