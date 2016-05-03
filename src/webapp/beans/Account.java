package webapp.beans;

import java.sql.Date;
import java.util.ArrayList;

public class Account {
	private Date dateOpened;
	private int clientId;
	private ArrayList<Stock> stocks;
	private String username;
	private String password;
	
	public Account() {}
	
	public Account(Date dateOpened, int clientId) {
		this.dateOpened = dateOpened;
		this.clientId = clientId;
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