package webapp.beans;

public class StockWithRevenue extends Stock {
	private double revenue;
	
	public StockWithRevenue() {
	}

	public StockWithRevenue(String symbol, String company, String type, double pps, double revenue) {
		super(symbol, company, type, pps);
		this.setRevenue(revenue);
	}

	public double getRevenue() {
		return revenue;
	}

	public void setRevenue(double revenue) {
		this.revenue = revenue;
	}
}
