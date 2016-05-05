package webapp.beans;

import java.sql.Date;

public class OrderHistory {
	private String StockSymbol;
	private Date date;
	private int numShares;
	private double price;
	private String type;
	private double value;
	private double sellPrice;
	private Transaction transaction;

	public OrderHistory(String StockSymbol, double price, String type, double value, double sellPrice, int numShares, Date date){
		this.StockSymbol = StockSymbol;
		this.date = date;
		this.numShares = numShares;
		this.price = price;
		this.value = value;
		this.type=type;
		this.sellPrice = sellPrice;
	}
	
	public String getStockSymbol(){
		return StockSymbol;
	}
	
	public Date getDate() {
		return date;
	}
	public int getNumShares() {
		return numShares;
	}
	
	public double getPrice(){
		return price;
	}
	
	public String getType(){
		return type;
	}
	
	public double getValue(){
		return value;
	}
}
