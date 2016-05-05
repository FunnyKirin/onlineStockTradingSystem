package webapp.beans;

public class ClientWithRevenue extends Client {
	private double revenue;
	
	public ClientWithRevenue() {
		
	}

	public ClientWithRevenue(String firstname, String lastname, String address, int SSN, String telephone,
			Location location, String email, double rating, String creditCardNum, double revenue) {
		super(firstname, lastname, address, SSN, telephone, location,
				email, rating, creditCardNum);
		this.revenue = revenue;
	}

	public double getRevenue() {
		return revenue;
	}

	public void setRevenue(double revenue) {
		this.revenue = revenue;
	}
	
}
