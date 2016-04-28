package webapp.beans;

import java.sql.Date;

public class Employee extends Person {

	private Person person;
	private int id;
	private Date dateStarted;
	private double hourlyRate;

	private String username;
	private String password;

	public Employee() {

	}

	public Employee(String firstname, String lastname, String address, int SSN, String telephone, Location location,
			int id, Date dateStarted, double hourlyRate) {
		super(firstname, lastname, address, SSN, telephone, location);
		this.id = id;
		this.dateStarted = dateStarted;
		this.hourlyRate = hourlyRate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateStarted() {
		return dateStarted;
	}

	public void setDateStarted(Date dateStarted) {
		this.dateStarted = dateStarted;
	}

	public double getHourlyRate() {
		return hourlyRate;
	}

	public void setHourlyRate(double hourlyRate) {
		this.hourlyRate = hourlyRate;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
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
