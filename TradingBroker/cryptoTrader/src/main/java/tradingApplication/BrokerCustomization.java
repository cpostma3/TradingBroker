package tradingApplication;

/**
 * @Name: BrokerCustomization
 * @Date: April 2
 * @Authors: Josh
 * @Description: This class holds a list of Broker objects.
 * 				This object holds the Name of the Broker, 
 * 				the Coins the broker is interested in and
 * 				the strategy the broker uses
 *
 */
public class BrokerCustomization {

	//Attributes
	private Broker brokers [];
	private int cur = 0;
	
	/**
	 * Constructor for BrokerCustomization
	 * Sets the total number of brokers held
	 * @param size: total number of brokers
	 */
	public BrokerCustomization(int size) {
		brokers = new Broker [size];
	}
	
	

	/**
	 * This method will add a new broker to the array returning true.
	 * If the name of the new broker is already included false will be returned
	 * and the broker will not be added
	 * @param name: the name of the broker
	 * @param coins: an array of coins the broker is interested in
	 * @param strategy: the strategy the broker wants to use
	 * @return true if broker is added to array, false otherwise
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
	
	/**
	 * This predicate method cycles through the brokers
	 * and returns the true if a broker of the same name exists
	 * it will return false otherwise.
	 * @param name: the name of the broker
	 * @return: true if broker of the same name exists, otherwise false
	 */
	private boolean duplicateBroker(String name) {
		for (int i = 0; i < cur; i++)
			if(brokers[i].getName().equals(name))
				return true;
		return false;
	}
	
	
	/**
	 * getter to return list of brokers
	 * @return list of brokers
	 */
	protected Broker[] getBrokers() {
		return brokers;
	}
	
}
