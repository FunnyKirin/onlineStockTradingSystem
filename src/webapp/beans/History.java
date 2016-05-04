package webapp.beans;

import java.sql.Date;

public class History {
	private String id;
	private String symbol;
	private String numShares;
	private String priceType;
	private String orderType;
	private Date date;
	public History(String id, String symbol, String numShares, String priceType, String orderType,Date date) {
		super();
		this.id = id;
		this.symbol = symbol;
		this.numShares = numShares;
		this.priceType = priceType;
		this.orderType = orderType;
		this.date=date;
	}
	
	public Date getDate(){
		return date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getNumShares() {
		return numShares;
	}

	public void setNumShares(String numShares) {
		this.numShares = numShares;
	}

	public String getPriceType() {
		return priceType;
	}

	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	
}