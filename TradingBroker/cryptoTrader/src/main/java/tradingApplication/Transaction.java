package tradingApplication;

public class Transaction {
	private String[] t = new String[7];
	
	public Transaction(String[] trn){
		this.t = trn;
	}
	
	public String[] getAll() {
		return this.t;
	}
	
	public void setAll(String[] trn) {
		this.t = trn;
	}
	
	public String getName() {
		return this.t[0];
	}
	
	public void setName(String n) {
		this.t[0] = n;
	}
	
	public String getStrat() {
		return this.t[1];
	}
	
	public void setStrat(String s) {
		this.t[1] = s;
	}
	
	public String getCoin() {
		return this.t[2];
	}
	
	public void setCoin(String c) {
		this.t[2] = c;
	}
	
	public String getAction() {
		return this.t[3];
	}
	
	public void setAction(String a) {
		this.t[3] = a;
	}
	
	public String getAmount() {
		return this.t[4];
	}
	
	public void setAmount(String a) {
		this.t[4] = a;
	}
	
	public String getPrice() {
		return this.t[5];
	}
	
	public void setPrice(String p) {
		this.t[5] = p;
	}
	
	public String getDate() {
		return this.t[6];
	}
	
	public void setDate(String d) {
		this.t[6] = d;
	}
}
