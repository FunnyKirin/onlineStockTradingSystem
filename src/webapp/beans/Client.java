package webapp.beans;

public class Client extends Person {
	/*
	 * Person() private String firstname; private String lastname; private
	 * String address; private String SSN; private String telephone; private
	 * Location location;
	 */

	private String email;
	private double rating;
	private String creditCardNum;

	public Client() {}

	public Client(String firstname, String lastname, String address, 
			int SSN, String telephone, Location location,
			String email, double rating, String creditCardNum) {
		super(firstname, lastname, address, SSN, telephone, location);
		this.email = email;
		this.rating = rating;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}


	public String getCreditCardNum() {
		return creditCardNum;
	}

	public void setCreditCardNum(String creditCardNum) {
		this.creditCardNum = creditCardNum;
	}

}
