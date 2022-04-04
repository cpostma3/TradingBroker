package tradingApplication;

public class Coin {

	//attributes
	String name;
	double price;
	double cap;
	double volume;

	/* Constructor that holds the coin's name, price, cap and volume
	 * 
	 */
	protected Coin(String name, double price, double cap, double volume) {
		this.name = name;
		this.price = price;
		this.cap = cap;
		this.volume = volume;
	}
	
	
	//GETTERS
	
	public String getName() {
		return name;
	}
	
	public double getPrice() {
		return price;
	}
	
	public double getCap() {
		return cap;
	}
	
	public double getVolume() {
		return volume;
	}	
	
}
