package webapp.beans;

import java.sql.Date;

public class Transaction {
	private int id;
	private double fee;
	private Date date;
	private double pps;
	
	public Transaction() {
		this(0, 0, null, 0);
	}
	
	public Transaction(int id, double fee, Date date, double pps) {
		super();
		this.id = id;
		this.fee = fee;
		this.date = date;
		this.pps = pps;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getFee() {
		return fee;
	}
	public void setFee(double fee) {
		this.fee = fee;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getPPS() {
		return pps;
	}
	public void setPPS(double pps) {
		this.pps = pps;
	}
	
	
}
