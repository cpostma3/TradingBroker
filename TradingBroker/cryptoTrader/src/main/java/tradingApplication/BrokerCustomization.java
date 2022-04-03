package tradingApplication;

/* Name: BrokerCustomization
 * Date: April 2
 * Authors: Josh
 * Description: This class holds a list of Broker objects.
 * 				This object holds the Name of the Broker, 
 * 				the Coins the broker is interested in and
 * 				the strategy the broker uses
 */

public class BrokerCustomization {

	//Attributes
	private Broker brokers [];
	private int cur = 0;
	

	/* Constructor for BrokerCustomization
	 *  Sets the total number of brokers held
	 *  Arguments: int size: the total number of brokers
	 */
	public BrokerCustomization(int size) {
		brokers = new Broker [size];
	}
	
	
	/* This method will add a new broker to the array returning true.
	 * If the name of the new broker is already included false will be returned
	 * and the broker will not be added
	 * Arguments:	String name: the name of the broker
	 * 				String [] coins: an array of coins the broker is interested in
	 * 				String strategy: the strategy the broker wants to use
	 */
	public boolean addBroker(String name, String[] coins, String strategy) {
		//If there is another broker of that name return false
		if (duplicateBroker(name))
			return false;

		//Create Broker and increment
		brokers[cur] = new Broker(name, coins, strategy);
		cur++;
		return true;
	}
	
	
	/* This predicate method cycles through the brokers
	 * and returns the true if a broker of the same name exists
	 * it will return false otherwise.
	 */
	private boolean duplicateBroker(String name) {
		for (int i = 0; i < cur; i++)
			if(brokers[i].getName().equals(name))
				return true;
		return false;
	}
	
	
	/* TODO Some form of Getter or calculus
	 * 
	 */
	
	
}
