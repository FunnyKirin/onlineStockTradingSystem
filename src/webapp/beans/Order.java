package webapp.beans;

import java.sql.Date;
import java.sql.Time;

public class Order {
	private int id;
	private Date date;
	private int numShares;

	private String type;
	private String orderType;
	private double pps;
	private double percent;

	public Order() {
	}

	public Order(int id, Date date, int numShares, double pps, String type) {
		super();
		this.id = id;
		this.date = date;
		this.numShares = numShares;
		this.pps = pps;
		this.type = type;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getPps() {
		return pps;
	}

	public void setPps(double pps) {
		this.pps = pps;
	}

	public double getPercent() {
		return percent;
	}

	public void setPercent(double percent) {
		this.percent = percent;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
}
