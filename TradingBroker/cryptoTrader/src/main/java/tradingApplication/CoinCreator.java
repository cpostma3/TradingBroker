package tradingApplication;

public class CoinCreator extends Creator{

	@Override
	public Coin build(String name) {
		return new Coin(name);
	}
	
	public Coin build(String name, double price, double cap, double volume) {
		return new Coin(name, price, cap, volume);
	}

}
