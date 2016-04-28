package webapp.beans;

public class Person {

	private String firstname;
	private String lastname;
	private String address;
	private int SSN;
	private String telephone;
	private Location location;
	
	public Person() {
		this(null, null, null, 0, null, null);
	}
	
	// firstname, lastname, SSN, address, telephone, location
	public Person(String firstname, String lastname, String address, int SSN, String telephone,
			Location location) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = address;
		this.SSN = SSN;
		this.telephone = telephone;
		this.location = location;
	}
	
	
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getSSN() {
		return SSN;
	}

	public void setSSN(int SSN) {
		this.SSN = SSN;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

}