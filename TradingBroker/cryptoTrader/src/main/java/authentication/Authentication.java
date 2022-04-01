package authentication;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Authentication {

	/*
	 * Verifies if the username and password are correct. Returns True if they match
	 * and false if there is no user or the password is incorrect Arguments:
	 * username: a string to represent the user password: a string to represent the
	 * password
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
			writer.append('\n');
			writer.append(username);
			writer.append('\n');
			writer.append(password);
			writer.close();
			return true;
		}
	}

	/*
	 * a helper method to GetUser takes a string usern
	 * 
	 */
	private String getUser(String username) {

		// TODO
	}

	private boolean setUser(String username, String password) {
		return false;
	}
}
