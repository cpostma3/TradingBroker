package tradingApplication;

/**
 * @Name: Broker
 * @Date: April 2
 * @Authors: Josh
 * @Description: This class is representative of a single broker.
 * 				This object holds the Name of the Broker, 
 * 				the Coins the broker is interested in and
 * 				the strategy the broker uses
 *
 */
public class Broker implements Product{

	// Attributes of the Broker
	private String name;
	private String[] coinNames;
	private String strategy;

	/**
	 * Constructor
	 * @param name: broker name
	 * @param coins: what coins the broker has available
	 * @param strategy: broker's trading strategy
	 */
	public Broker(String name, String[] coins, String strategy) {
		this.name = name;
		this.coinNames = coins;
		this.strategy = strategy;
	}

	// GETTERS
	/**
	 * Get name
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get coins
	 * @return coin names
	 */
	public String[] getCoins() {
		return coinNames;
	}

	/**
	 * Get trading strategy
	 * @return strategy
	 */
	public String getStrategy() {
		return strategy;
	}

}
