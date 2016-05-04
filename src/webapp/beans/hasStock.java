package webapp.beans;

import webapp.beans.*;

public class hasStock {
	
	private int numOfShares;
	private String stockSymbol;
	public hasStock(){
		
	}
	public hasStock(int numOfShares, String stockSymbol) {
		this.numOfShares = numOfShares;
		this.stockSymbol = stockSymbol;
	}
	
	public void setNumberOfShares(int nos){
		numOfShares = nos;
	}
	public int getNumberOfShares(){
		return numOfShares;
	}
	
	public void setStockSymbol(String sym){
		stockSymbol = sym;
	}
	public String getStockSymbol(){
		return stockSymbol;
	}
}
