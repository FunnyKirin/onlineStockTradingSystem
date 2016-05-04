package webapp.beans;

import webapp.beans.*;

public class hasStock {
	
	private int numOfShares;
	private String stockSymbol;
	private String companyName;
	private String type;
	private double pricePerShare;
	public hasStock(){
		
	}
	public hasStock(int numOfShares, String stockSymbol, String companyName, String type, double pricePerShare) {
		this.numOfShares = numOfShares;
		this.stockSymbol = stockSymbol;
		this.companyName = companyName;
		this.type = type;
		this.pricePerShare = pricePerShare;
	}
	
	public double getPricePerShare(){
		return pricePerShare;
	}
	public String getCompanyName(){
		return companyName;
	}
	
	public String getType(){
		return type;
	}
	
	public void setNumOfShares(int nos){
		numOfShares = nos;
	}
	public int getNumOfShares(){
		return numOfShares;
	}
	
	public void setStockSymbol(String sym){
		stockSymbol = sym;
	}
	public String getStockSymbol(){
		return stockSymbol;
	}
}
