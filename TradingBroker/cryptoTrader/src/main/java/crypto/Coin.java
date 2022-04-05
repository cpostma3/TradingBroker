package crypto;
import tradingApplication.Product;
/**
 * @Name: Coin
 * @Date: April 3
 * @Authors: Patrick
 * @Description: The Coin Class holds the information of a single coin. The attributes
 * 				being the name, price, market cap and volume. These attributes can
 * 				be accessed via the getter methods 
 */
public class Coin implements Product {

	//attributes
	private String name;
	private double price;
	private double cap;
	private double volume;

	/**
	 * Constructor that holds the coin's name
	 * @param name: coin's name
	 */
	protected Coin(String name) {
		this.name = name;
	}
	/**
	 * Constructor that holds the coin's name, price, cap and volume
	 * @param name: coin's name
	 * @param price: current price of the coin
	 * @param cap: current cap value of the coin
	 * @param volume: amount of the coin
	 */
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

	/**
	 * getter for the name
	 * @return: the name of the coin as a String
	 */
	public String getName() {
		return name;
	}

	/**
	 * getter for the price
	 * @return: the price of the coin as a double
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * getter for the cap value
	 * @return: the cap of the coin as a double
	 */
	public double getCap() {
		return cap;
	}

	/**
	 * getter for the volume
	 * @return: the volume of the coin as a double
	 */
	public double getVolume() {
		return volume;
	}	
	
}
