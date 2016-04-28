package webapp.beans;

public class Client extends Person {
	/*
	 * Person() private String firstname; private String lastname; private
	 * String address; private String SSN; private String telephone; private
	 * Location location;
	 */

	private String username;
	private String password;

	private String email;
	private double rating;
	private Account account;

	public Client() {}

	public Client(String firstname, String lastname, String address, 
			int SSN, String telephone, Location location,
			String email, double rating, Account account) {
		super(firstname, lastname, address, SSN, telephone, location);
		this.email = email;
		this.rating = rating;
		this.setAccount(account);
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}
