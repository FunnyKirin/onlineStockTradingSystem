package webapp.beans;

public class UserAccount {

	public static final String GENDER_MALE = "M";
	public static final String GENDER_FEMALE = "F";

	private String firstname;
	private String lastname;
	private String gender;
	private String username;
	private String password;
	
	public UserAccount() {
		this(null, null, null, null, null);
	}
	
	// firstname, lastname, gender, username, password
	public UserAccount(String f, String l, String g, String u, String p) {
		firstname = f;
		lastname = l;
		gender = g;
		username = u;
		password = p;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}