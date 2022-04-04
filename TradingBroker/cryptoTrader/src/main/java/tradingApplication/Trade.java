package tradingApplication;

public class Trade {
	
	public String[][] executeStrategy(Broker b) {
		//Get the value of the applicable coins
		Coin[] coinInfo = PubSub.getInstance().getCoinInfo(b.getCoins());
		//Execute the strategy associated with the current broker
		if (b.getStrategy().equals("Strategy-A")) {
			return strategyA(b, coinInfo);
		} else if (b.getStrategy().equals("Strategy-B")) {
			return strategyB(b, coinInfo);
		} else if (b.getStrategy().equals("Strategy-C")) {
			return strategyC(b, coinInfo);
		} else if (b.getStrategy().equals("Strategy-D")) {
			return strategyD(b, coinInfo);
		} else return null;
	}

	// Perform a trade if the prices of all coins we're interested in is <$500
	private String[][] strategyA(Broker b, Coin[] coinInfo) {
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
		String[][] transactions;
		if (performTrade) {
			transactions = new String[coinInfo.length][7];
			for (int i = 0; i < coinInfo.length; i++) {
				transactions[i][0] = b.getName();
				transactions[i][1] = b.getStrategy();
				transactions[i][2] = coinInfo[i].getName();
				transactions[i][3] = "Buy";
				if (coinInfo[i].getVolume() > 10) {
					transactions[i][4] = "10";
				} else {
					transactions[i][4] = "" + coinInfo[i].getVolume();
				}
				transactions[i][5] = "" + coinInfo[i].getPrice();
				transactions[i][6] = PubSub.getInstance().getDate();
			}
		}
		// Otherwise, return an empty string
		else {
			transactions = new String[0][0];
		}
		return transactions;
	}

	// Perform a trade if the price of bitcoin is >$50, then buy bitcoin
	private String[][] strategyB(Broker b, Coin[] coinInfo) {
		boolean bitcoin = false;
		int order = 0;
		// Check that bitcoin is one of the coins the broker is interested in
		for (int i = 0; i < coinInfo.length; i++) {
			if (coinInfo[i].equals("bitcoin")) {
				bitcoin = true;
				order = i;
			}
		}

		// Check whether a trade can be performed and should be performed and return the
		// appropriate value
		String[][] transactions;
		if (bitcoin) {
			if (coinInfo[order].getPrice() > 50) {
				transactions = new String[1][7];
				transactions[0][0] = b.getName();
				transactions[0][1] = b.getStrategy();
				transactions[0][2] = coinInfo[order].getName();
				transactions[0][3] = "Buy";
				transactions[0][4] = "1";
				transactions[0][5] = "" + coinInfo[order].getPrice();
				transactions[0][6] = PubSub.getInstance().getDate();
			} else {
				transactions = new String[0][0];
			}
		} else {
			transactions = new String[1][7];
			transactions[0][0] = b.getName();
			transactions[0][1] = b.getStrategy();
			transactions[0][2] = "bitcoin";
			transactions[0][3] = "Fail";
			transactions[0][4] = null;
			transactions[0][5] = null;
			transactions[0][6] = PubSub.getInstance().getDate();
		}
		return transactions;
	}

	// Perform a trade if the value of the first coin is greater than the value of
	// the second coin
	private String[][] strategyC(Broker b, Coin[] coinInfo) {
		String[][] transactions;
		if (coinInfo.length > 1) {
			if (coinInfo[0].getPrice() > coinInfo[1].getPrice()) {
				transactions = new String[1][7];
				transactions[0][0] = b.getName();
				transactions[0][1] = b.getStrategy();
				transactions[0][2] = coinInfo[0].getName();
				transactions[0][3] = "Sell";
				transactions[0][4] = "1000";
				transactions[0][5] = "" + coinInfo[0].getPrice();
				transactions[0][6] = PubSub.getInstance().getDate();
			} else {
				transactions = new String[0][0];
			}
		} else {
			transactions = new String[0][0];
		}
		return transactions;
	}

	// Buy or sell each coin based on the cap and the price
	private String[][] strategyD(Broker b, Coin[] coinInfo) {
		String[][] tempTransactions = new String[coinInfo.length][7];
		String[][] transactions;
		int increment = 0;
		// Check if a trade should be performed and record the information
		for (int i = 0; i < coinInfo.length; i++) {
			// If the cap is high and the price is high, then sell
			if (coinInfo[i].getCap() > 5 && coinInfo[i].getPrice() > 500) {
				tempTransactions[increment][0] = b.getName();
				tempTransactions[increment][1] = b.getStrategy();
				tempTransactions[increment][2] = coinInfo[i].getName();
				tempTransactions[increment][3] = "Sell";
				tempTransactions[increment][4] = "" + coinInfo[i].getVolume() * 0.2;
				tempTransactions[increment][5] = "" + coinInfo[i].getPrice();
				tempTransactions[increment][6] = PubSub.getInstance().getDate();
				increment++;
			} 
			// If the cap is high and the price is low, then buy
			else if (coinInfo[i].getCap() > 5 && coinInfo[i].getPrice() < 500) {
				tempTransactions[increment][0] = b.getName();
				tempTransactions[increment][1] = b.getStrategy();
				tempTransactions[increment][2] = coinInfo[i].getName();
				tempTransactions[increment][3] = "Buy";
				tempTransactions[increment][4] = "" + coinInfo[i].getVolume() * 0.1;
				tempTransactions[increment][5] = "" + coinInfo[i].getPrice();
				tempTransactions[increment][6] = PubSub.getInstance().getDate();
				increment++;
			} 
			// If the cap is low and the price is high, then sell
			else if (coinInfo[i].getCap() < 5 && coinInfo[i].getPrice() > 500) {
				tempTransactions[increment][0] = b.getName();
				tempTransactions[increment][1] = b.getStrategy();
				tempTransactions[increment][2] = coinInfo[i].getName();
				tempTransactions[increment][3] = "Sell";
				tempTransactions[increment][4] = "" + coinInfo[i].getVolume() * 0.15;
				tempTransactions[increment][5] = "" + coinInfo[i].getPrice();
				tempTransactions[increment][6] = PubSub.getInstance().getDate();
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
}
