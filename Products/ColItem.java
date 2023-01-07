package Products;


import Users.Guest;

/**
 * @author HlibKokin
 * @version 13/05/2022
 * abstract class that provides common methods and fields
 * 
 */

public abstract class ColItem {
	
	/**
	 * field that contains winner of the auction
	 */
	private Guest winner = null;
	protected String collection;
	/**
	 * field that contains starting price of the auction
	 */
	private int fprice;
	
	/**
	 * field that contains current price of the auction
	 */
	private int price;
	private String time;
	private String AccessTo;
	
	public void SetTime(String time) {
		this.time = time;
	}
	public String GetTime() {
		return this.time;
	}
	
	public void SetPrice(int price) {
		this.price = price;
	}
	public int GetPrice() {
		return this.price;
	}
	
	public void SetFPrice(int price) {
		fprice = price;
	}
	public int GetFprice() {
		return this.fprice;
	}
	public void SetAccessTo(String AccessTo) {
		this.AccessTo = AccessTo;
	}
	public String GetAccessTo() {
		return this.AccessTo;
	}
	
	public abstract void SetName(String Name);

	public String getWinnerName() {
		return winner.GetLogin();
	}
	public Guest getWinner() {
		return winner;
	}

	public void setWinner(Guest winner, boolean host, String name) {
		if(host == true)this.winner = winner;
		else {
			this.winner = new Guest(name,name);
		}
	};


	public abstract String GetCollection() ;
	
	public abstract int GetSchedule();
	public abstract void SetSchedule();
	public abstract void StartTimer();
	public abstract boolean IsStarted();
	
}
