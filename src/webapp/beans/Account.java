package webapp.beans;

import java.sql.Date;

public class Account {
	private String ID;
	private Date dateOpened;
	private int clientId;
	
	public Account() {}
	
	public Account(String ID, Date dateOpened, int clientId) {
		super();
		this.ID = ID;
		this.dateOpened = dateOpened;
		this.clientId = clientId;
	}
	


	public String getID() {
		return ID;
	}
	public void setID(String ID) {
		this.ID = ID;
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
	
}