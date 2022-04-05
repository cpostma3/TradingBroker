package tradingApplication;

/**
 * @Name: Transaction
 * @Date: April 3
 * @Authors: Patrick
 * @Description: The Transaction Class holds the information of a single broker transaction. 
 * 				The attributes being the name, strategy, coin, action(buy or sell), 
 * 				the amount bought or sold, price and date.
 */
public class Transaction {
	private String[] tData = new String[7];
	
	/**
	 * getter for all transaction data
	 * @return all the transaction data
	 */
	protected String[] getAll() {
		return this.tData;
	}
	
	/**
	 * setter for name of currency
	 * @param name: currency name
	 */
	public void setName(String name) {
		this.tData[0] = name;
	}

	/**
	 * setter for trading strategy
	 * @param strategy: desired trading strategy
	 */
	public void setStrategy(String strategy) {
		this.tData[1] = strategy;
	}
	
	/**
	 * setter for coin type
	 * @param coin: desired coin type
	 */
	public void setCoin(String coin) {
		this.tData[2] = coin;
	}
	
	/**
	 * setter for action
	 * @param action
	 */
	public void setAction(String action) {
		this.tData[3] = action;
	}
	
	/**
	 * setter for amount of currency
	 * @param amount: amount of currency available
	 */
	public void setAmount(String amount) {
		this.tData[4] = amount;
	}
	
	/**
	 * setter for price of currency
	 * @param price: current price of currency
	 */
	public void setPrice(String price) {
		this.tData[5] = price;
	}
	
	/**
	 * setter for date of transaction
	 * @param date: date of transaction
	 */
	public void setDate(String date) {
		this.tData[6] = date;
	}
}
