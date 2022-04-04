package authentication;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @Name: Authentication
 * @Date: April 3
 * @Authors: Courtney and Josh
 * @Description: Authentication is used to store usernames and passwords
 *		and verify usernames and passwords. AddUser will store 
 *		The provided username and password into the Accounts.txt.
 *		Login will verify from the Accounts.txt file a pair of username and password
 */

public class Authentication {

	/**
	 * Verifies if the username and password are correct. 
	 * Returns True if they match and false if there is no user or the password is incorrect
	 * @param username: a string to represent the user 
	 * @param password: a string to represent the password
	 * @return true if valid username and password match, false otherwise
	 * @throws IOException
	 */
	protected boolean login(String username, String password) {
		if (password.equals(getUser(username)))
			return true;
		
		return false;
	}
	
	/**
	 * a helper method to write a new user to the document returns true if it works
	 * false if user already exists
	 * @param username: a string to represent the user 
	 * @param password: a string to represent the password
	 * @return successful addition returns true, false if unsuccessful
	 */
	protected boolean addUser(String username, String password) throws IOException {
		if (getUser(username) != null)
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
	
	/**
	 * getUser is a helper method to GetUser a password of a user in the 
	 * database. If no user of that username exists it will return null.
	 * @param username: a string to represent the user 
	 * @return return the password, or if no user exists it returns null
	 */
	private String getUser(String username) {
		//Instantiate Scanner
		Scanner scan;
		try {
			scan = new Scanner(new File("Accounts.txt"));
			
			//If the document is empty
			if(!scan.hasNextLine())
				return null;
			
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
			//There is no text file at all create one and continue
			File accounts = new File("Accounts.txt");
			try {
				accounts.createNewFile();
			} catch (IOException e1) {e1.printStackTrace();}
			return null;
		}
	}
}