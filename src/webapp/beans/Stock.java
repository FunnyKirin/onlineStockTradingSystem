package webapp.beans;

public class Stock {

	private String symbol;
	private String company;
	private String type;
	private double pps; // price per share
	
	public Stock() {
		this (null, null, null, 0);
	}

	public Stock(String symbol, String company, String type, double pps) {
		this.symbol = symbol;
		this.company = company;
		this.type = type;
		this.pps = pps;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getPPS() {
		return pps;
	}

	public void setPPS(double pps) {
		this.pps = pps;
	}

}