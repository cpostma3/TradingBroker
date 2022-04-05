package tradingApplication;

/**
 * @Name: Trade
 * @Date: April 4
 * @Authors: Courtney, Patrick, Josh and Owen
 * @Description: This class's purpose is to execute a single broker's strategy
 * 				returning the transaction(s) it performed in a Transaction[]
 */
public class Trade {
	
	/**
	 * Find which strategy the broker uses and execute it
	 * @param broker: broker desired to use strategy
	 * @return corresponding strategy to the broker, or null if none selected
	 */
	protected static Transaction[] executeStrategy(Broker broker) {
		//Get the value of the applicable coins
		Coin[] coinInfo = PubSub.getInstance().getCoinInfo(broker.getCoins());
		if(coinInfo == null)
			return new Transaction[0];
		//Execute the strategy associated with the current broker
		if (broker.getStrategy().equals("Strategy-A")) {
			return strategyA(broker, coinInfo);
		} else if (broker.getStrategy().equals("Strategy-B")) {
			return strategyB(broker, coinInfo);
		} else if (broker.getStrategy().equals("Strategy-C")) {
			return strategyC(broker, coinInfo);
		} else if (broker.getStrategy().equals("Strategy-D")) {
			return strategyD(broker, coinInfo);
		} else return null;
	}

	/**
	 * Strategy A: Perform a trade if the prices of all coins we're interested in is <$500
	 * @param broker: broker executing strategy
	 * @param coinInfo: info on the coin used for trading
	 * @return: list of transactions after strategy has been executed
	 */
	private static Transaction[] strategyA(Broker broker, Coin[] coinInfo) {
		// Create a variable to store the transactions, as well as action, quantity, and
		// price variables
		boolean performTrade = true;

		// For every coin in the interest list, if the coin is cheaper than $500, buy 10
		// (or the maximum amount) of them
		for (int i = 0; i < coinInfo.length; i++) {
			if (coinInfo[i].getPrice() > 500) {
				performTrade = false;
			}
		}

		// Perform the trade if the prices of all coins are cheaper than $500
		Transaction[] transactions;
		if (performTrade) {
			transactions = new Transaction[coinInfo.length];
			for (int i = 0; i < coinInfo.length; i++) {
				transactions[i] = new Transaction();
				transactions[i].setName(broker.getName());
				transactions[i].setStrategy(broker.getStrategy());
				transactions[i].setCoin(coinInfo[i].getName());
				transactions[i].setAction("Buy");
				if (coinInfo[i].getVolume() > 10) {
					transactions[i].setAmount("10");
				} else {
					transactions[i].setAmount("" + coinInfo[i].getVolume());
				}
				transactions[i].setPrice("" + coinInfo[i].getPrice());
				transactions[i].setDate(PubSub.getInstance().getDate());
			}
		}
		// Otherwise, return an empty string
		else {
			transactions =  new Transaction[0];
		}
		
		return transactions;
	}

	/**
	 * Strategy B: Perform a trade if the price of bitcoin is >$50, then buy bitcoin
	 * @param broker: broker executing strategy
	 * @param coinInfo: info on the coin used for trading
	 * @return: list of transactions after strategy has been executed
	 */
	private static Transaction[] strategyB(Broker broker, Coin[] coinInfo) {
		boolean bitcoin = false;
		int order = 0;
		// Check that bitcoin is one of the coins the broker is interested in
		for (int i = 0; i < coinInfo.length; i++) {
			if (coinInfo[i].getName().toLowerCase().equals("bitcoin")) {
				bitcoin = true;
				order = i;
			}
		}

		// Check whether a trade can be performed and should be performed and return the
		// appropriate value
		Transaction[] transactions;
		if (bitcoin) {
			if (coinInfo[order].getPrice() > 50) {
				transactions = new Transaction[1];
				transactions[0] = new Transaction();
				transactions[0].setName(broker.getName());
				transactions[0].setStrategy(broker.getStrategy());
				transactions[0].setCoin(coinInfo[order].getName());
				transactions[0].setAction("Buy");
				transactions[0].setAmount("1");
				transactions[0].setPrice("" + coinInfo[order].getPrice());
				transactions[0].setDate(PubSub.getInstance().getDate());
			} else {
				transactions = new Transaction[0];
			}
		} else {
			transactions = new Transaction[1];
			transactions[0] = new Transaction();
			transactions[0].setName(broker.getName());
			transactions[0].setStrategy(broker.getStrategy());
			transactions[0].setCoin("bitcoin");
			transactions[0].setAction("Fail");
			transactions[0].setAmount("Null");
			transactions[0].setPrice("Null");
			transactions[0].setDate(PubSub.getInstance().getDate());
		}
		return transactions;
	}

	/**
	 * Strategy C: Perform a trade if the value of the first coin is greater than the value of
	 * the second coin
	 * @param broker: broker executing strategy
	 * @param coinInfo: info on the coin used for trading
	 * @return: list of transactions after strategy has been executed
	 */
	private static Transaction[] strategyC(Broker broker, Coin[] coinInfo) {
		Transaction[] transactions;
		if (coinInfo.length > 1) {
			if (coinInfo[0].getPrice() > coinInfo[1].getPrice()) {
				transactions = new Transaction[1];
				transactions[0] = new Transaction();
				transactions[0].setName(broker.getName());
				transactions[0].setStrategy(broker.getStrategy());
				transactions[0].setCoin(coinInfo[0].getName());
				transactions[0].setAction("Sell");
				transactions[0].setAmount("1000");
				transactions[0].setPrice("" + coinInfo[0].getPrice());
				transactions[0].setDate(PubSub.getInstance().getDate());
			} else {
				transactions = new Transaction[0];
				
			}
		} else {
			transactions = new Transaction[0];
		}
		return transactions;
	}

	/**
	 * Strategy D: Buy or sell each coin based on the cap and the price
	 * @param broker: broker executing strategy
	 * @param coinInfo: info on the coin used for trading
	 * @return: list of transactions after strategy has been executed
	 */
	private static Transaction[] strategyD(Broker broker, Coin[] coinInfo) {
		Transaction[] tempTransactions = new Transaction[coinInfo.length];
		Transaction[] transactions;
		int increment = 0;
		// Check if a trade should be performed and record the information
		for (int i = 0; i < coinInfo.length; i++) {
			// If the cap is high and the price is high, then sell
			if (coinInfo[i].getCap() > 5 && coinInfo[i].getPrice() > 500) {
				tempTransactions[i] = new Transaction();
				tempTransactions[increment].setName(broker.getName());
				tempTransactions[increment].setStrategy(broker.getStrategy());
				tempTransactions[increment].setCoin(coinInfo[i].getName());
				tempTransactions[increment].setAction("Sell");
				tempTransactions[increment].setAmount("" + coinInfo[i].getVolume() * 0.2);
				tempTransactions[increment].setPrice("" + coinInfo[i].getPrice());
				tempTransactions[increment].setDate(PubSub.getInstance().getDate());
				increment++;
			} 
			// If the cap is high and the price is low, then buy
			else if (coinInfo[i].getCap() > 5 && coinInfo[i].getPrice() < 500) {
				tempTransactions[i] = new Transaction();
				tempTransactions[increment].setName(broker.getName());
				tempTransactions[increment].setStrategy(broker.getStrategy());
				tempTransactions[increment].setCoin(coinInfo[i].getName());
				tempTransactions[increment].setAction("Buy");
				tempTransactions[increment].setAmount("" + coinInfo[i].getVolume() * 0.1);
				tempTransactions[increment].setPrice("" + coinInfo[i].getPrice());
				tempTransactions[increment].setDate(PubSub.getInstance().getDate());
				increment++;
			} 
			// If the cap is low and the price is high, then sell
			else if (coinInfo[i].getCap() < 5 && coinInfo[i].getPrice() > 500) {
				tempTransactions[i] = new Transaction();
				tempTransactions[increment].setName(broker.getName());
				tempTransactions[increment].setStrategy(broker.getStrategy());
				tempTransactions[increment].setName(coinInfo[i].getName());
				tempTransactions[increment].setAction("Sell");
				tempTransactions[increment].setAmount("" + coinInfo[i].getVolume() * 0.15);
				tempTransactions[increment].setPrice("" + coinInfo[i].getPrice());
				tempTransactions[increment].setDate(PubSub.getInstance().getDate());
				increment++;
			}
		}

		// Check if there are items to buy and sell
		if (increment == 0) {
			transactions = new Transaction[0];
		} else {
			transactions = new Transaction[increment];
		}
		
		// Copy over the information of the items to buy and sell
		for (int i = 0; i < increment; i++) {
			transactions[i] = tempTransactions[i];
		}
		
		// Return the 2D array
		return transactions;
	}
}
