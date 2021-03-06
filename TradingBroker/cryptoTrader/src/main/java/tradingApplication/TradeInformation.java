package tradingApplication;

import java.util.ArrayList;

/**
 * @Name TradeInformation
 * @Date April 4
 * @author Owen 
 * @Description: Populate brokers and execute their trading strategies and allow for getting of transaction lists.
 */
public class TradeInformation {
	
	private static TradeInformation instance;
	private ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	private BrokerCustomization brokers;
	/**
	 * Create instance of itself it doesn't already exist
	 * @return instance of itself
	 */
	public static TradeInformation getInstance() {
		if(instance == null) {
			instance = new TradeInformation();
		}
		return instance;
	}
	
	/**
	 * Constructor for instance calling.
	 */
	private TradeInformation() {
		brokers = new BrokerCustomization();
	}

	/**
	 * Constructor for instance calling.
	 */
	public boolean addBroker(String name, String[] coins, String strategy) {
		return brokers.addBroker(name, coins, strategy);
	}
	
	/**
	 * Clears the list of Brokers in BrokerCustomization
	 */
	public void clear() {
		brokers.clear();
	}
	
	/**
	 * Execute broker's trading strategy from broker list
	 * 
	 */
	public void executeBrokers() {
		Broker[] brokerList = brokers.getBrokers();
		for(int i = 0; i < brokerList.length; i++) {
			Transaction[] transactionList = Trade.executeStrategy(brokerList[i]);
			for(int j = 0; j < transactionList.length; j++) {
				transactions.add(transactionList[j]);
			}
		}
	}
	
	/**
	 * getter to return transaction list
	 * @return String[][] list of transactions
	 */
	public String[][] getTransactionsList(){
		String[][] transactionList = new String[transactions.size()][7];
		int counter = 0;
		for(Transaction currentTransaction : transactions) {
			transactionList[counter] = currentTransaction.getAll();
			counter++;
		}
		return transactionList;
	}
}
