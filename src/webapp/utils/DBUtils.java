package webapp.utils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import webapp.beans.Stock;
import webapp.beans.Account;
import webapp.beans.Client;
import webapp.beans.Employee;
import webapp.beans.Location;
import webapp.beans.Person;

public class DBUtils {

	public static Employee loginAsEmployee(Connection conn, String username, String password) throws SQLException {

		String sql = "Select username, password from Employee where username = ? and password = ?";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, username);
		pstm.setString(2, password);
		ResultSet rs = pstm.executeQuery();

		if (rs.next()) {
			String getEmployeeSql = "Select * from Employee where username = ?";

			pstm = conn.prepareStatement(getEmployeeSql);
			pstm.setString(1, username);
			rs = pstm.executeQuery();

			if (rs.next()) {

				// Employee field
				Date dateStarted = rs.getDate("StartDate");
				double hourlyRate = rs.getDouble("HourlyRate");
				int id = rs.getInt("id");
				Person person = findPerson(conn, rs.getInt("SSN"));

				String firstname = person.getFirstname();
				String lastname = person.getLastname();
				String address = person.getAddress();
				String telephone = person.getTelephone();
				int ssn = person.getSSN();
				Location location = person.getLocation();

				return new Employee(firstname, lastname, address, ssn, telephone, location, id, dateStarted,
						hourlyRate);
			}
		}
		return null;
	}

	public static Account loginAsClient(Connection conn, String username, String password) throws SQLException {
		String sql = "Select username, password from Account where username = ? and password = ?";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, username);
		pstm.setString(2, password);
		ResultSet rs = pstm.executeQuery();

		if (rs.next()) {
			String getAccountSql = "Select * from Account where username = ?";

			pstm = conn.prepareStatement(getAccountSql);
			pstm.setString(1, username);
			rs = pstm.executeQuery();

			if (rs.next()) {
				// Client field
				int Id = rs.getInt("ID");
				Date dateOpened = rs.getDate("DateOpened");
				int clientId = rs.getInt("clientID");
				
				Client client = findClient(conn, clientId);
				String email = client.getEmail();
				double rating = client.getRating();
				int SSN = client.getSSN();
				String creditCardNum = client.getCreditCardNum();
				
				Person person = findPerson(conn, SSN);
				String lastName = person.getLastname();
				String firstName = person.getFirstname();
				String address = person.getAddress();
				String telephone = person.getTelephone();
				Location location = person.getLocation();
				return new Account(firstName, lastName, address, SSN, telephone, location, email, rating, creditCardNum, dateOpened, clientId);
			}
		}

		return null;
	}
/**
	public static Account findAccount(Connection conn, int clientId) throws SQLException {
		String sql = "Select * from Account where ClientID = ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, clientId);
		ResultSet rs = pstm.executeQuery();

		if (rs.next()) {
			Date dateOpened = rs.getDate("DateOpened");
			return new Account(dateOpened, clientId);
		}

		return null;
	}
**/
	public static Stock findStock(Connection conn, String symbol) throws SQLException {
		String sql = "Select * from Stock where symbol = ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, symbol);
		ResultSet rs = pstm.executeQuery();

		if (rs.next()) {
			String company = rs.getString("CompanyName");
			String type = rs.getString("type");
			double pps = rs.getDouble("pps");

			return new Stock(symbol, company, type, pps);

		}
		return null;
	}

	public static Person findPerson(Connection conn, int SSN) throws SQLException {
		String sql = "Select * from Person where SSN = ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, SSN);
		ResultSet rs = pstm.executeQuery();

		if (rs.next()) {

			String firstname = rs.getString("Firstname");
			String lastname = rs.getString("Lastname");
			String address = rs.getString("Address");
			String telephone = rs.getString("Telephone");
			Location location = findLocation(conn, rs.getInt("Zipcode"));

			return new Person(firstname, lastname, address, SSN, telephone, location);
		}

		return null;
	}

	public static Location findLocation(Connection conn, int zipcode) throws SQLException {
		String sql = "Select * from Location where Zipcode = ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, zipcode);
		ResultSet rs = pstm.executeQuery();

		if (rs.next()) {
			String state = rs.getString("State");
			String city = rs.getString("City");
			
			return new Location(zipcode, city, state);
		}

		return null;
	}

	public static Client findClient(Connection conn, int clientID) throws SQLException {
		String sql = "Select * from Client where ID = ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, clientID);
		ResultSet rs = pstm.executeQuery();

		Client client = null;
		if (rs.next()) {
			String email = rs.getString("email");
			double rating = rs.getDouble("Rating");
			String creditCardNum = rs.getString("CreditCardNumber");
			int id = rs.getInt("ID");

			Person person = findPerson(conn, id);
			//Account account = findAccount(conn, id);
			if (person == null)
				return null;

			String firstname = person.getFirstname();
			String lastname = person.getLastname();
			String address = person.getAddress();
			String telephone = person.getTelephone();
			Location location = person.getLocation();

			client = new Client(firstname, lastname, address, id, telephone, location, email, rating, creditCardNum);


			return client;
		}

		return null;
	}

	public static void insertClient(Connection conn, Account account) throws SQLException {
		// Location
		insertLocation(conn, account.getLocation());

		// Account
		//insertAccount(conn, client.getAccount());

		// Person
		String sql = "Insert ignore into Person(Firstname, Lastname, Address, Telephone, SSN, Zipcode)"
				+ "values (?, ?, ?, ?, ?, ?)";
		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setString(1, account.getFirstname());
		pstm.setString(2, account.getLastname());
		pstm.setString(3, account.getAddress());
		pstm.setString(4, account.getTelephone());
		pstm.setInt(5, account.getSSN());
		pstm.setInt(6, account.getLocation().getZipcode());
		pstm.executeUpdate();

		// Client
		sql = "Insert ignore into Client(Id, Email, Rating, Username, Password, CreditCardNumber)"
				+ "values (?, ?, ?, ?, ?, ?)";
		pstm = conn.prepareStatement(sql);

		pstm.setInt(1, account.getSSN());
		pstm.setString(2, account.getEmail());
		pstm.setDouble(3, account.getRating());
		pstm.setString(4, account.getUsername());
		pstm.setString(5, account.getPassword());
		pstm.setString(6, account.getCreditCardNum());
		pstm.executeUpdate();
	}

	public static void insertEmployee(Connection conn, Employee employee) throws SQLException {
		// Location
		insertLocation(conn, employee.getLocation());

		String sql = "Insert ignore into Person(Firstname, Lastname, Address, Telephone, SSN, Zipcode)"
				+ "values (?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setString(1, employee.getFirstname());
		pstm.setString(2, employee.getLastname());
		pstm.setString(3, employee.getAddress());
		pstm.setString(4, employee.getTelephone());
		pstm.setInt(5, employee.getSSN());
		pstm.setInt(6, employee.getLocation().getZipcode());
		pstm.executeUpdate();

		// Employee
		sql = "Insert ignore into Employee(SSN, Id, StartDate, Username, Password, HourlyRate)"
				+ "values (?, ?, ?, ?, ?)";
		pstm = conn.prepareStatement(sql);

		pstm.setInt(1, employee.getSSN());
		pstm.setInt(2, employee.getId());
		pstm.setDate(3, employee.getDateStarted());
		pstm.setString(4, employee.getUsername());
		pstm.setString(5, employee.getPassword());
		pstm.setDouble(6, employee.getHourlyRate());
		pstm.executeUpdate();
	}

	public static void insertLocation(Connection conn, Location location) throws SQLException {
		// Location
		String sql = "Insert ignore into Location(Zipcode, City, State) values(?, ?, ?)";
		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setInt(1, location.getZipcode());
		pstm.setString(2, location.getCity());
		pstm.setString(3, location.getState());
		pstm.executeUpdate();
	}

	public static void insertAccount(Connection conn, Account account) throws SQLException {
		// Account
		String sql = "Insert ignore into Account(DateOpened, ClientID) values (?, ?)";
		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setDate(1, account.getDateOpened());
		pstm.setInt(2, account.getClientId());
		pstm.executeUpdate();
	}

	public static void insertStock(Connection conn, Stock stock) throws SQLException {
		// Stock
		String sql = "Insert ignore into Stock(symbol, CompanyName, Type, PricePerShare)" + "values (?, ?, ?)";
		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setString(1, stock.getSymbol());
		pstm.setString(2, stock.getCompany());
		pstm.setString(3, stock.getType());
		pstm.setDouble(4, stock.getPPS());
		pstm.executeUpdate();
	}
}