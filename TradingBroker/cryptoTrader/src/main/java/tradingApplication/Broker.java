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

	public String[][] executeStrategy() {
		//Get the value of the applicable coins
		Coin[] coinInfo = PubSub.getInstance.getCoinInfo(coinNames);
		//Execute the strategy associated with the current broker
		if (strategy.equals("Strategy-A")) {
			return strategyA(coinInfo);
		} else if (strategy.equals("Strategy-B")) {
			return strategyB(coinInfo);
		} else if (strategy.equals("Strategy-C")) {
			return strategyC(coinInfo);
		} else if (strategy.equals("Strategy-D")) {
			return strategyD(coinInfo);
		} 
	}

	// Perform a trade if the prices of all coins we're interested in is <$500
	private String[][] strategyA(Coin[] coinInfo) {
		// Create a variable to store the transactions, as well as action, quantity, and
		// price variables
		boolean performTrade = true;

		// For every coin in the interest list, if the coin is cheaper than $500, buy 10
		// (or the maximum amount) of them
		for (int i = 0; i < coinNames.length; i++) {
			if (coinInfo[i].getPrice() > 500) {
				performTrade = false;
			}
		}

		// Perform the trade if the prices of all coins are cheaper than $500
		String[][] transactions;
		if (performTrade) {
			transactions = new String[coinNames.length][7];
			for (int i = 0; i < coinNames.length; i++) {
				transactions[i][0] = name;
				transactions[i][1] = strategy;
				transactions[i][2] = coinNames[i];
				transactions[i][3] = "Buy";
				if (coinInfo[i].getVolume() > 10) {
					transactions[i][4] = "10";
				} else {
					transactions[i][4] = "" + coinInfo[i].getVolume();
				}
				transactions[i][5] = "" + coinInfo[i].getPrice();
				transactions[i][6] = PubSub.getDate();
			}
		}
		// Otherwise, return an empty string
		else {
			transactions = new String[0][0];
		}
		return transactions;
	}

	// Perform a trade if the price of bitcoin is >$50, then buy bitcoin
	private String[][] strategyB(Coin[] coinInfo) {
		boolean bitcoin = false;
		boolean performTrade = false;
		int order = 0;
		// Check that bitcoin is one of the coins the broker is interested in
		for (int i = 0; i < coinNames.length; i++) {
			if (coinNames[i].equals("bitcoin")) {
				performTrade = true;
				order = i;
			}
		}

		// Check whether a trade can be performed and should be performed and return the
		// appropriate value
		String[][] transactions;
		if (bitcoin) {
			if (coinInfo[order].getPrice() > 50) {
				transactions = new String[1][7];
				transactions[0][0] = name;
				transactions[0][1] = strategy;
				transactions[0][2] = coinNames[order];
				transactions[0][3] = "Buy";
				transactions[0][4] = "1";
				transactions[0][5] = "" + coinInfo[order].getPrice();
				transactions[0][6] = PubSub.getDate();
			} else {
				transactions = new String[0][0];
			}
		} else {
			transactions = new String[1][7];
			transactions[0][0] = name;
			transactions[0][1] = strategy;
			transactions[0][2] = "bitcoin";
			transactions[0][3] = "Fail";
			transactions[0][4] = null;
			transactions[0][5] = null;
			transactions[0][6] = PubSub.getDate();
		}
		return transactions;
	}

	// Perform a trade if the value of the first coin is greater than the value of
	// the second coin
	private String[][] strategyC(Coin[] coinInfo) {
		String[][] transactions;
		if (coinNames.length > 1) {
			if (coinInfo[0].getPrice() > coinInfo[1].getPrice()) {
				transactions = new String[1][7];
				transactions[0][0] = name;
				transactions[0][1] = strategy;
				transactions[0][2] = coinNames[0];
				transactions[0][3] = "Sell";
				transactions[0][4] = "1000";
				transactions[0][5] = "" + coinInfo[0].getPrice();
				transactions[0][6] = PubSub.getDate();
			} else {
				transactions = new String[0][0];
			}
		} else {
			transactions = new String[0][0];
		}
		return transactions;
	}

	// Buy or sell each coin based on the cap and the price
	private String[][] strategyD(Coin[] coinInfo) {
		String[][] tempTransactions = new String[coinNames.length][7];
		String[][] transactions;
		int increment = 0;
		// Check if a trade should be performed and record the information
		for (int i = 0; i < coinNames.length; i++) {
			// If the cap is high and the price is high, then sell
			if (coinInfo[i].getCap() > 5 && coinInfo[i].getPrice() > 500) {
				tempTransactions[increment][0] = name;
				tempTransactions[increment][1] = strategy;
				tempTransactions[increment][2] = coinNames[i];
				tempTransactions[increment][3] = "Sell";
				tempTransactions[increment][4] = "" + coinInfo[i].getVolume() * 0.2;
				tempTransactions[increment][5] = "" + coinInfo[i].getPrice();
				tempTransactions[increment][6] = PubSub.getDate();
				increment++;
			} 
			// If the cap is high and the price is low, then buy
			else if (coinInfo[i].getCap() > 5 && coinInfo[i].getPrice() < 500) {
				transactions[increment][0] = name;
				transactions[increment][1] = strategy;
				transactions[increment][2] = coinNames[i];
				transactions[increment][3] = "Buy";
				transactions[increment][4] = "" + coinInfo[i].getVolume() * 0.1;
				transactions[increment][5] = "" + coinInfo[i].getPrice();
				transactions[increment][6] = PubSub.getDate();
				increment++;
			} 
			// If the cap is low and the price is high, then sell
			else if (coinInfo[i].getCap() < 5 && coinInfo[i].getPrice() > 500) {
				transactions[increment][0] = name;
				transactions[increment][1] = strategy;
				transactions[increment][2] = coinNames[i];
				transactions[increment][3] = "Sell";
				transactions[increment][4] = "" + coinInfo[i].getVolume() * 0.15;
				transactions[increment][5] = "" + coinInfo[i].getPrice();
				transactions[increment][6] = PubSub.getDate();
				increment++;
			}
		}

		// Check if there are items to buy and sell
		if (increment == 0) {
			transactions = new String[0][0];
		} else {
			transactions = new String[increment][7];
		}
		
		// Copy over the information of the items to buy and sell
		for (int i = 0; i < increment; i++) {
			transactions[i] = tempTransactions[i];
		}
		
		// Return the 2D array
		return transactions;
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
