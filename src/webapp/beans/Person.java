package webapp.beans;

public class Person {
	private int SSN;
	private String lastName;
	private String firstName;
	private String address;
	private int zipCode;
	private int telephone;
	
	public Person(String initLastName, String initFirstName) {
		lastName = initLastName;
		firstName = initFirstName;
	}
	
	public int getSSN() {
		return SSN;
	}
	public void setSSN(int newSSN) {
		SSN = newSSN;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String newLastName) {
		lastName = newLastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String newFirstName) {
		firstName = newFirstName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String newAddress) {
		address = newAddress;
	}
	public int getZipCode() {
		return zipCode;
	}
	public void setZipCode(int newZipCode) {
		zipCode = newZipCode;
	}
	public int getTelephone() {
		return telephone;
	}
	public void setTelephone(int newTelephone) {
		telephone = newTelephone;
	}
}
