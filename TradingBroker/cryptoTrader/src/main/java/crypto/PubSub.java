package crypto;

import cryptoTrader.utils.AvailableCryptoList;
import cryptoTrader.utils.DataFetcher;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
import tradingApplication.Creator;
/**
 * @Name: PubSub
 * @Date: April 3
 * @Authors: Josh
 * @Description: The PubSub Class is used to fetch coin values and
 * 	be accessed via the getter methods 
 */
public class PubSub extends Creator{
	
	private static PubSub instance;
	
	private String [] cryptoNames;
	private DataFetcher fetch;
	private String date;
	
	/**
	 * get instance of itself and return it
	 * @return instance of itself
	 */
	public static PubSub getInstance() {
		if (instance == null)
			instance = new PubSub();

		return instance;
	}
	
	/**
	 * fetch data and refresh
	 */
	private PubSub() {
		cryptoNames = AvailableCryptoList.getInstance().getAvailableCryptos();
		//subscribers = new ArrayList<List<Broker>>(cryptoNames.length);
		fetch = new DataFetcher();
		refresh();
	}
	/**
	 * factory method to build a coin object, and return the object
	 * @param name of coin
	 * @param price of coin
	 * @param market cap for coin
	 * @param trade volume for coin
	 * @return the created coin object
	 */
	public Coin build(String name, double price, double cap, double volume) {
		return new Coin(name,price,cap,volume);
	}
	/**
	 * Obtains the list of crypto names from AvailableCryptoList
	 * and obtains the date
	 */
	public void refresh() {
		SimpleDateFormat form = new SimpleDateFormat("dd-MM-yyyy");
		date = form.format(new Date());
	}

	/**
	 * This method returns the current market price, market cap and volume
	 * of each coin in the coinName array. 
	 * @param coinNames: list of names of coins
	 * @return: the current market price, market cap and volume
	 */
	public Coin[] getCoinInfo(String coinNames[]){
		List<Coin> want = new ArrayList<Coin>();
		for(int i = 0; i < coinNames.length; i++) {
			for(int j = 0; j < cryptoNames.length; j++) {
				//Get the coin id and see if they match if so add information
				String id = AvailableCryptoList.getInstance().getCryptoID(cryptoNames[j]);
//				System.out.println(id + " is called " + cryptoNames[j] + " The price " + fetch.getPriceForCoin(id, date) + " the cap is " + fetch.getMarketCapForCoin(id, date));   // This is to see the list of cryptos and their names
				if(cryptoNames[j].equals(coinNames[i]) || id.equals(coinNames[i])) {
					want.add(build(id, fetch.getPriceForCoin(id, date), fetch.getMarketCapForCoin(id, date), fetch.getVolumeForCoin(id, date)));
					break;
				}
			}
//			System.out.println(coinNames[i] + " price: " + want[i].getPrice() + " cap " + want[i].getCap() + " volume " + want[i].getVolume());
		}
		
		if(want.isEmpty())
			return null;
		
		return want.toArray(new Coin[0]);
	}
	
	/**
	 * Creates a poppup of all listed crypto currencies and their ids
	 */
	public void displayCryptoNames() {
		String message = "";
		for (int i = 0; i < cryptoNames.length; i++)
			message+= cryptoNames[i] + ": with id " + AvailableCryptoList.getInstance().getCryptoID(cryptoNames[i]) + "\n";
		
		JOptionPane.showMessageDialog(null, message);
	}
	
	/**
	 * getter for the date
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	
	/**
	 * main method
	 * @param args
	 */
	public static void main(String[] args) {
		PubSub pub = new PubSub();
		String [] t = {"EOS"};
		pub.getCoinInfo(t);
		
	}
}
