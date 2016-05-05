package webapp.beans;

import java.sql.Date;

public class Account extends Client{
	private int Id;
	private Date dateOpened;
	private int clientId;
	private String username;
	private String password;
	
	public Account() {}
	
	public Account(String firstname, String lastname, String address, 
			int SSN, String telephone, Location location,
			String email, double rating, String creditCardNum, Date dateOpened, int clientId) {
		super(firstname, lastname, address, SSN, telephone, location, email, rating, creditCardNum);
		//this.Id=Id;
		this.dateOpened = dateOpened;
		this.clientId = clientId;
	}
	
	public void setId(int Id){
		this.Id=Id;
	}
	public int getId(){
		return Id;
	}
	
	public Date getDateOpened() {
		return dateOpened;
	}
	public void setDateOpened(Date dateOpened) {
		this.dateOpened = dateOpened;
	}
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
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
	
}