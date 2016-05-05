package webapp.utils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import webapp.beans.Stock;
import webapp.beans.StockWithRevenue;
import webapp.beans.Trade;
import webapp.beans.Transaction;
import webapp.beans.Account;
import webapp.beans.Client;
import webapp.beans.Employee;
import webapp.beans.History;
import webapp.beans.Location;
import webapp.beans.Order;
import webapp.beans.Person;
import webapp.beans.ClientWithRevenue;

public class ManagerUtils {
	public static ArrayList<Trade> getSalesReportByMonth(Connection conn, int month) throws SQLException {
		ArrayList<Trade> trades = new ArrayList<Trade>();
		
		String sql = "call monthlySalesReport(?)";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, month);
		ResultSet rs = pstm.executeQuery();
		
		while (rs.next()) {
			Account account = new Account();
			Employee employee = new Employee();
			Order order = new Order();
			Stock stock = new Stock();
			Transaction transaction = new Transaction();
			
			account.setId(rs.getInt("AccountId"));
			employee.setId(rs.getInt("BrokerId"));
			order.setId(rs.getInt("OrderId"));
			stock.setSymbol(rs.getString("StockId"));
			transaction.setId(rs.getInt("TransactionId"));
			
			trades.add(new Trade(account, employee, order, stock, transaction));
		}
		
		return trades;
	}
	
	public static Client getCoolestClient(Connection conn) throws SQLException {
		String sql = "call customer_mostRevenue()";
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		
		if (rs.next()) {
			int id = rs.getInt("Client ID");
			Client client = DBUtils.findClient(conn, id);
			return client;
		}
		
		return null;
	}
	
	public static Employee getCoolestEmployee(Connection conn) throws SQLException {
		String sql = "call mostRevenue_CustomerRepresentative()";
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		
		if (rs.next()) {
			int ssn = rs.getInt("Employee SSN");
			int id = rs.getInt("Employee ID");
			Employee employee = DBUtils.findEmployee(conn, ssn);
			employee.setId(id);
			return employee;
		}
		
		return null;
	}

	public static ArrayList<String> getMailingList(Connection conn) throws SQLException {
		ArrayList<String> emails = new ArrayList<String>();
		String sql = "call getMailingList()";

		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();

		while (rs.next()) {
			String email = rs.getString("Email");
			emails.add(email);
			// System.out.println(email);
		}

		return emails;
	}

	public static ClientWithRevenue getRevenueByCustID(Connection conn, int id) throws SQLException {
		String sql = "call revenueByCustID(?)";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, id);
		ResultSet rs = pstm.executeQuery();

		if (rs.next()) {
			String email = rs.getString("Email");
			double rating = rs.getDouble("Rating");
			String credit = rs.getString("CreditCardNumber");
			
			double revenue = rs.getDouble("Revenue");
			
			ClientWithRevenue c = new ClientWithRevenue();
			c.setSSN(id);
			c.setEmail(email);
			c.setRating(rating);
			c.setCreditCardNum(credit);
			c.setRevenue(revenue);
			
			return c;
		}

		return null;
	}
	
	public static StockWithRevenue getRevenueByStockType(Connection conn, String str) throws SQLException {
		String sql = "call revenueByStockType(?)";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, str);
		ResultSet rs = pstm.executeQuery();

		if (rs.next()) {
			String symbol = rs.getString("StockSymbol");
			String company = rs.getString("CompanyName");
			String type = rs.getString("Type");
			double pps = rs.getDouble("PricePerShare");
			double revenue = rs.getDouble("Revenue");
			
			return new StockWithRevenue(symbol, company, type, pps, revenue);
		}

		return null;
	}
	
	public static StockWithRevenue getRevenueByStockSymbol(Connection conn, String str) throws SQLException {
		String sql = "call revenueByStockSymbol(?)";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, str);
		ResultSet rs = pstm.executeQuery();

		if (rs.next()) {
			String symbol = rs.getString("StockSymbol");
			String company = rs.getString("CompanyName");
			String type = rs.getString("Type");
			double pps = rs.getDouble("PricePerShare");
			double revenue = rs.getDouble("Revenue");
			
			return new StockWithRevenue(symbol, company, type, pps, revenue);
		}

		return null;
	}
	
	public static ArrayList<Stock> getStocks(Connection conn) throws SQLException {
		ArrayList<Stock> stocks = new ArrayList<Stock>();
		String sql = "select * from Stock";

		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();

		while (rs.next()) {
			String symbol = rs.getString("StockSymbol");
			String company = rs.getString("CompanyName");
			String type = rs.getString("Type");
			double pps = rs.getDouble("PricePerShare");

			stocks.add(new Stock(symbol, company, type, pps));
		}

		return stocks;
	}

	public static ArrayList<Stock> getActiveStocks(Connection conn) throws SQLException {
		ArrayList<Stock> stocks = new ArrayList<Stock>();
		String sql = "call activeStocks()";
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();

		while (rs.next()) {
			String symbol = rs.getString("Active Stocks");
			stocks.add(DBUtils.findStock(conn, symbol));
		}

		return stocks;
	}
	
	public static ArrayList<Client> getClients(Connection conn) throws SQLException {
		ArrayList<Client> clients = new ArrayList<Client>();

		String sql = "select ID from Client";

		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();

		while (rs.next()) {
			int clientId = rs.getInt("ID");
			clients.add(DBUtils.findClient(conn, clientId));
		}

		return clients;
	}

	public static ArrayList<Account> getAccounts(Connection conn) throws SQLException {
		ArrayList<Account> accounts = new ArrayList<Account>();

		String sql = "select Username, ClientID from Account";

		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();

		while (rs.next()) {
			String username = rs.getString("username");
			accounts.add(DBUtils.findAccount(conn, username));
		}

		return accounts;
	}

	public static ArrayList<Employee> getEmployees(Connection conn) throws SQLException {
		ArrayList<Employee> employees = new ArrayList<Employee>();

		String sql = "select SSN from Employee";

		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();

		while (rs.next()) {
			int id = rs.getInt("SSN");
			employees.add(DBUtils.findEmployee(conn, id));
		}

		return employees;
	}

	public static ArrayList<String> getBestSellers(Connection conn) throws SQLException {
		ArrayList<String> best = new ArrayList<String>();
		String sql = "call bestSellerStocks()";

		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();

		while (rs.next()) {
			String id = rs.getString("StockId");

			best.add(id);
		}

		return best;
	}
	
	public static ArrayList<Stock> giveSuggestions(Connection conn, int clientId) throws SQLException {
		ArrayList<Stock> stocks = new ArrayList<Stock>();
		String sql = "SELECT S.StockSymbol, S.CompanyName, S.PricePerShare, S.Type "
				+ "FROM Account A, Stock S, StockOrder O, Trade T "
				+ "WHERE A.ClientID = ? AND S.StockSymbol = T.StockId AND "
				+ "T.AccountId = A.ID AND T.OrderId= O.ID";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, clientId);
		ResultSet rs = pstm.executeQuery();

		while (rs.next()) {
			String symbol = rs.getString("StockSymbol");
			String company = rs.getString("CompanyName");
			String type = rs.getString("Type");
			double pps = rs.getDouble("PricePerShare");

			System.out.println(symbol);

			stocks.add(new Stock(symbol, company, type, pps));
		}

		return stocks;
	}
}
