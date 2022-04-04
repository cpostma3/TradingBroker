package tradingApplication;

/* Name: Broker
 * Date: April 2
 * Authors: Josh and Courtney (and Patrick [and Owen])
 * Description: This class is representative of a single broker.
 * 				This object holds the Name of the Broker, 
 * 				the Coins the broker is interested in and
 * 				the strategy the broker uses
 */

public class Broker {

	// Attributes of the Broker
	private String name;
	private String[] coinNames;
	private String strategy;

	// Constructor
	public Broker(String name, String[] coins, String strategy) {
		this.name = name;
		this.coinNames = coins;
		this.strategy = strategy;
	}

	// GETTERS
	public String getName() {
		return name;
	}

	public String[] getCoins() {
		return coinNames;
	}

	public String getStrategy() {
		return strategy;
	}

}
