package webapp.beans;

import java.sql.Date;

public class Account {
	private Date dateOpened;
	private int clientId;
	
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
	
}