package authentication;

import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

/* Name: Authentication
 * Date: April 3
 * Authors: Josh (and Courtney)
 * Description: LoginUI uses the Authentication class to addusers and verify credentials
 *		and verify usernames and passwords. AddUser will store 
 *		The provided username and password into the Accounts.txt.
 *		Login will verify from the Accounts.txt file a pair of username and password
 */

public class LoginUI {

	/*
	 * Login is called to prompt the user
	 * 
	 */
	public static boolean login() {
		Authentication aut = new Authentication();

		JTextField username = new JTextField();
		JTextField password = new JTextField();

		Object[] message = { "Username:", username, "Password:", password };

		// Prompts the user if they want to create a new account
		int opt = JOptionPane.showConfirmDialog(null, "Do you have an account?", "Login", JOptionPane.YES_NO_OPTION);
		if (opt == JOptionPane.NO_OPTION) {
			opt = JOptionPane.showConfirmDialog(null, message, "Create New User", JOptionPane.CANCEL_OPTION);
			if (opt == JOptionPane.OK_OPTION) {
				try {
					if (!aut.addUser(username.getText(), password.getText()))
						return false;
				} catch (IOException e) {
					return false;
				}
			} else
				return false;
		}

		username.setText("");
		password.setText("");

		// This is the Login

		opt = JOptionPane.showConfirmDialog(null, message, "Login", JOptionPane.CANCEL_OPTION);
		if (opt == JOptionPane.OK_OPTION) {
			// Determine if the Login was correct
			if (aut.login(username.getText(), password.getText())) {
				return true;
			} else
				return false;
		} else {
			// The user Canceled
			return false;
		}

	}
}
