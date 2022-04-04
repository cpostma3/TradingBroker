package tradingApplication;

import cryptoTrader.gui.MainUI;
import cryptoTrader.utils.AvailableCryptoList;
import cryptoTrader.utils.DataFetcher;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* Name: Coin
 * Date: April 3
 * Authors: Josh
 * Description: The PubSub Class is used to fetch coin values and
 * 				
 * 				be accessed via the getter methods 
 */
public class PubSub {
	
	private static PubSub instance;
	
	private String [] cryptoNames;
	private DataFetcher fetch;
	//private List<List<Broker>> subscribers;
	private String date;
	
	
	public static PubSub getInstance() {
		if (instance == null)
			instance = new PubSub();

		return instance;
	}
	
	/* Constructor 
	 */
	private PubSub() {
		cryptoNames = AvailableCryptoList.getInstance().getAvailableCryptos();
		//subscribers = new ArrayList<List<Broker>>(cryptoNames.length);
		fetch = new DataFetcher();
		refresh();
	}
	
	/* Obtains the list of crypto names from AvailableCryptoList
	 * and obtains the date
	 */
	public void refresh() {
		SimpleDateFormat form = new SimpleDateFormat("dd-MM-yyyy");
		date = form.format(new Date());
	}

	/* This method returns the current market price, market cap and volume
	 * of each coin in the coinName array. 
	 * 
	 */
	public Coin[] getCoinInfo(String coinNames[]){
		Coin[] want = new Coin[coinNames.length];
		for(int i = 0; i < coinNames.length; i++) {
			for(int j = 0; j < cryptoNames.length; j++) {
				//Get the coin id and see if they match if so add information
				String id = AvailableCryptoList.getInstance().getCryptoID(cryptoNames[j]);
//				System.out.println(id + " is called " + cryptoNames[j] + " The price " + fetch.getPriceForCoin(id, date) + " the cap is " + fetch.getMarketCapForCoin(id, date));   // This is to see the list of cryptos and their names
				if(cryptoNames[j].equals(coinNames[i]) || id.equals(coinNames[i])) {
					want[i] = new Coin(id, fetch.getPriceForCoin(id, date), fetch.getMarketCapForCoin(id, date), fetch.getVolumeForCoin(id, date));
					break;
				}
			}
//			System.out.println(coinNames[i] + " price: " + want[i].getPrice() + " cap " + want[i].getCap() + " volume " + want[i].getVolume());
		}
		
		return want;
	}
	


	/* Method that returns the date
	 */
	public String getDate() {
		return date;
	}
	
	public static void main(String[] args) {
		PubSub pub = new PubSub();
		String [] t = {"EOS"};
		pub.getCoinInfo(t);
		
	}
}