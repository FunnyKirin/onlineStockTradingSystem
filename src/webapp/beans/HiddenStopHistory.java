package webapp.beans;

import java.sql.Date;

public class HiddenStopHistory {
	private Date date;
	private int numShares;
	private Stock stock;
	private int price;
	private Trade trade;
	private Transaction transaction;

	public Date getDate() {
		return date;
	}
	public int getNumShares() {
		return numShares;
	}
	public Stock getStock() {
		return stock;
	}
}
