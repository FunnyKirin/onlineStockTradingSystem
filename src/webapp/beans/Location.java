package webapp.beans;

public class Location {
	private int zipcode;
	private String city;
	private String state;
	
	public Location(int zipcode, String city, String state) {
		super();
		this.zipcode = zipcode;
		this.city = city;
		this.state = state;
	}

	public int getZipcode() {
		return zipcode;
	}

	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
