package webapp.beans;

public class Client extends Person{
	private String email;
	private int rating;
	private int creditCardNumber;
	private int clientId;
	public Client(int initClientId, String lastName, String firstName) {
		super(lastName, firstName);
		clientId = initClientId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String newEmail) {
		email = newEmail;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int newRating) {
		rating = newRating;
	}
	public int getCreditCardNumber() {
		return creditCardNumber;
	}
	public void setCreditCardNumber(int newNumber) {
		creditCardNumber = newNumber;
	}
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int newClientId) {
		clientId = newClientId;
	}
}
