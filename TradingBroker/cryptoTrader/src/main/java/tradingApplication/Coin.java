package tradingApplication;

/* Name: Coin
 * Date: April 3
 * Authors: Patrick
 * Description: The Coin Class holds the information of a single coin. The attributes
 * 				being the name, price, market cap and volume. These attributes can
 * 				be accessed via the getter methods 
 */
public class Coin {

	//attributes
	String name;
	double price;
	double cap;
	double volume;

	/* Constructor that holds the coin's name, price, cap and volume
	 */
	protected Coin(String name) {
		this.name = name;
	}
	
	protected Coin(String name, double price, double cap, double volume) {
		this.name = name;
		this.price = price;
		this.cap = cap;
		this.volume = volume;
	}
	
	//Setters
	
//	protected void setPrice(double price) {
//		this.price = price;
//	}
//	
//	protected void setCap(double cap) {
//		this.cap = cap;
//	}
//	
//	protected void setVolume(double volume) {
//		this.volume = volume;
//	}
	
	
	
	//GETTERS

	//Returns the name of the coin as a String
	public String getName() {
		return name;
	}

	//returns the price of the coin as a double
	public double getPrice() {
		return price;
	}

	//returns the Market Cap of the coin as a double
	public double getCap() {
		return cap;
	}

	//returns the volume of the coin as a double
	public double getVolume() {
		return volume;
	}	
	
}
