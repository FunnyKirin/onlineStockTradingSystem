package webapp.beans;

import java.sql.Date;

public class HiddenStopHistory {
	private String StockSymbol;
	private Date date;
	private int numShares;
	private int price;
	private String type;
	private int value;
	private int sellPrice;
	private Transaction transaction;

	
	public String getStockSymbol(){
		return StockSymbol;
	}
	
	public Date getDate() {
		return date;
	}
	public int getNumShares() {
		return numShares;
	}
	
	public int getPrice(){
		return price;
	}
	
	public String getType(){
		return type;
	}
	
	public int getValue(){
		return value;
	}
}
