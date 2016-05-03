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
				Date dateStarted = rs.getDate("dateStarted");
				double hourlyRate = rs.getDouble("hourlyRate");
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

	public static Client loginAsClient(Connection conn, String username, String password) throws SQLException {
		String sql = "Select username, password from Client where username = ? and password = ?";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, username);
		pstm.setString(2, password);
		ResultSet rs = pstm.executeQuery();

		if (rs.next()) {
			String getClientSql = "Select * from Client where username = ?";

			pstm = conn.prepareStatement(getClientSql);
			pstm.setString(1, username);
			rs = pstm.executeQuery();

			if (rs.next()) {
				// Client field
				String email = rs.getString("Email");
				double rating = rs.getDouble("Rating");
				Person person = findPerson(conn, rs.getInt("ID"));

				String firstname = person.getFirstname();
				String lastname = person.getLastname();
				String address = person.getAddress();
				String telephone = person.getTelephone();
				int ssn = person.getSSN();
				Location location = person.getLocation();
				Account account = findAccount(conn, ssn);

				return new Client(firstname, lastname, address, ssn, telephone, location, email, rating, account);
			}
		}

		return null;
	}

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

	public static Client findClient(Connection conn, String username) throws SQLException {
		String sql = "Select * from Client where username = ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, username);
		ResultSet rs = pstm.executeQuery();

		Client client = null;
		if (rs.next()) {
			String email = rs.getString("email");
			double rating = rs.getDouble("Rating");
			int id = rs.getInt("ID");
			String password = rs.getString("Password");

			Person person = findPerson(conn, id);
			Account account = findAccount(conn, id);
			if (person == null || account == null)
				return null;

			String firstname = person.getFirstname();
			String lastname = person.getLastname();
			String address = person.getAddress();
			String telephone = person.getTelephone();
			Location location = person.getLocation();

			client = new Client(firstname, lastname, address, id, telephone, location, email, rating, account);
			client.setUsername(username);
			client.setPassword(password);

			return client;
		}

		return null;
	}

	public static void insertClient(Connection conn, Client client) throws SQLException {
		// Location
		insertLocation(conn, client.getLocation());

		// Account
		insertAccount(conn, client.getAccount());

		// Person
		String sql = "Insert ignore into Person(Firstname, Lastname, Address, Telephone, SSN, Zipcode)"
				+ "values (?, ?, ?, ?, ?, ?)";
		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setString(1, client.getFirstname());
		pstm.setString(2, client.getLastname());
		pstm.setString(3, client.getAddress());
		pstm.setString(4, client.getTelephone());
		pstm.setInt(5, client.getSSN());
		pstm.setInt(6, client.getLocation().getZipcode());
		pstm.executeUpdate();

		// Client
		sql = "Insert ignore into Client(Id, Email, Rating, Username, Password, CreditCardNumber)"
				+ "values (?, ?, ?, ?, ?, ?)";
		pstm = conn.prepareStatement(sql);

		pstm.setInt(1, client.getSSN());
		pstm.setString(2, client.getEmail());
		pstm.setDouble(3, client.getRating());
		pstm.setString(4, client.getUsername());
		pstm.setString(5, client.getPassword());
		pstm.setString(6, client.getCreditCardNum());
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