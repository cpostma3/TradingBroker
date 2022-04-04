package tradingApplication;

import cryptoTrader.gui.MainUI;
import cryptoTrader.utils.AvailableCryptoList;
import cryptoTrader.utils.DataFetcher;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	 * of each coin in the coinName array. and is
	 * 
	 */
	public Coin[] getCoinInfo(String coinNames[]){
		Coin[] want = new Coin[coinNames.length];
		for(int i = 0; i < coinNames.length; i++) {
			for(int j = 0; j < cryptoNames.length; j++) {
				//Get the coin id and see if they match if so add information
				String id = AvailableCryptoList.getInstance().getCryptoID(cryptoNames[j]);
//				System.out.println(id + " is called " + cryptoNames[j]);    This is to see the list of cryptos and their names
				if(cryptoNames[j].equals(coinNames[i]) || id.equals(coinNames[i])) {
					want[i] = new Coin(id, fetch.getPriceForCoin(id, date), fetch.getMarketCapForCoin(id, date), fetch.getVolumeForCoin(id, date));
					break;
				}
			}
			System.out.println(coinNames[i] + " price: " + want[i].getPrice() + " cap " + want[i].getCap() + " volume " + want[i].getVolume());
//			System.out.println();
		}
		
		return want;
	}
	
	
	
	/* Helper predicate method that returns true if the string
	 * is the name of a crypto currency returns false otherwise
	 */
	private boolean isCrypto(String coin) {
		for(int i = 0; i < cryptoNames.length; i++)
			if(cryptoNames[i].equals(coin))
				return true;
		
		return false;
	}

	/* Method that returns the date
	 */
	public String getDate() {
		return date;
	}
	
	public static void main(String[] args) {
		PubSub pub = new PubSub();
		String [] t = {"EOS", "bitcoin", "flow"};
		pub.getCoinInfo(t);
		
	}
}
