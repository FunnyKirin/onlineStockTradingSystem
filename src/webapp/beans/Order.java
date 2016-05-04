package webapp.beans;

import java.sql.Date;
import java.sql.Time;

public class Order {
	private int id;
	private Date date;
	private int numShares;
	
	public Order() {
		this(0,null,0);
	}
	
	public Order(int id, Date date, int numShares) {
		super();
		this.id = id;
		this.date = date;
		this.numShares = numShares;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getNumShares() {
		return numShares;
	}
	public void setNumShares(int numShares) {
		this.numShares = numShares;
	}
}
