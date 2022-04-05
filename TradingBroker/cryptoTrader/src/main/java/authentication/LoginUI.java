package authentication;

import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * @Name: Authentication
 * @Date: April 3
 * @Authors: Josh (and Courtney)
 * @Description: LoginUI's primary method is Login which creates a popup to allow the
 * 		user to login or create a new account. This class passes off the parameters to 
 * 		the Authentication class to add users and verify credentials. 
 *
 */
public class LoginUI {

	/**
	 * Login is called to prompt the user to add an account or login. Returns true
	 * if login successful. Returns false otherwise.
	 * 
	 * Using JOptionPane to create a popup and retrieve information. The method uses
	 * the Authentication class to Login and verify the input. If there is an
	 * attempt at adding a user with the same name or prvoviding credentials that
	 * are incorrect an error popup will appear and the method will return false.
	 * 
	 * @return true if successful log in, false if failed login
	 */
	public static boolean login() {
		Authentication aut = new Authentication();

		JTextField username = new JTextField();
		JTextField password = new JTextField();
		
		Object[] message = { "Username:", username, "Password:", password };

		// Prompt the user if they want to create a new account
		int opt = JOptionPane.showConfirmDialog(null, "Do you have an account?", "Login", JOptionPane.YES_NO_OPTION);
		if (opt == JOptionPane.NO_OPTION) {
			
			//Create new user if a field is empty AND they wish to add an account reprompt
			do {
				opt = JOptionPane.showConfirmDialog(null, message, "Create New User", JOptionPane.CANCEL_OPTION);
			}while((username.getText().isEmpty() || password.getText().isEmpty()) && opt == JOptionPane.OK_OPTION);
			
			if (opt == JOptionPane.OK_OPTION) {
				try {
					if (!aut.addUser(username.getText(), password.getText())) {
						JOptionPane.showMessageDialog(null, "User already exists");
						return false;
					}
				} catch (IOException e) {
					return false;
				}
			} else // Cancel
				return false;
		}

		//wipe the text
		username.setText("");
		password.setText("");

		// Prompt for the Login if a text field is empty AND they want to login reprompt
		do {
			opt = JOptionPane.showConfirmDialog(null, message, "Login", JOptionPane.CANCEL_OPTION);
		}while((username.getText().isEmpty() || password.getText().isEmpty()) && opt == JOptionPane.OK_OPTION);
	
		if (opt == JOptionPane.OK_OPTION)
			// Determine if the Login was correct
			if (aut.login(username.getText(), password.getText()))
				return true;
			else
				JOptionPane.showMessageDialog(null, "Login Failed");

		// The user canceled or failed login
		return false;
	}
}
