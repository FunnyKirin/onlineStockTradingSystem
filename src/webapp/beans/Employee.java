package webapp.beans;

import java.sql.Date;

public class Employee extends Person {
	private int empId;
	private int SSN;
	private Date startDate;
	private int hourlyRate;
	
	public Employee(int initEmpId, String lastName, String firstName) {
		super(lastName, firstName);
		
		empId = initEmpId;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int newEmpId) {
		empId = newEmpId;
	}
	public int getSSN() {
		return SSN;
	}
	public void setSSN(int newSSN) {
		SSN = newSSN;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date newStartDate) {
		startDate = newStartDate;
	}
	public int getHourlyRate() {
		return hourlyRate;
	}
	public void setHourlyRate(int newHourlyRate) {
		hourlyRate = newHourlyRate;
	}
}
