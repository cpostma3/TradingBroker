package tradingApplication;

/* Name: Coin
 * Date: April 3
 * Authors: Patrick
 * Description: The Transaction Class holds the information of a single broker transaction. 
 * 				The attributes being the name, strategy, coin, action(buy or sell), 
 * 				the amount bought or sold, price and date.
 */
public class Transaction {
	private String[] tData = new String[7];
	
	public String[] getAll() {
		return this.tData;
	}
	
	public String getName() {
		return this.tData[0];
	}
	
	public void setName(String name) {
		this.tData[0] = name;
	}
	
	public String getStrategy() {
		return this.tData[1];
	}
	
	public void setStrategy(String strategy) {
		this.tData[1] = strategy;
	}
	
	public String getCoin() {
		return this.tData[2];
	}
	
	public void setCoin(String coin) {
		this.tData[2] = coin;
	}
	
	public String getAction() {
		return this.tData[3];
	}
	
	public void setAction(String action) {
		this.tData[3] = action;
	}
	
	public String getAmount() {
		return this.tData[4];
	}
	
	public void setAmount(String amount) {
		this.tData[4] = amount;
	}
	
	public String getPrice() {
		return this.tData[5];
	}
	
	public void setPrice(String price) {
		this.tData[5] = price;
	}
	
	public String getDate() {
		return this.tData[6];
	}
	
	public void setDate(String date) {
		this.tData[6] = date;
	}
}
