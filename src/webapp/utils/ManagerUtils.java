package webapp.utils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import webapp.beans.Stock;
import webapp.beans.Account;
import webapp.beans.Client;
import webapp.beans.Employee;
import webapp.beans.History;
import webapp.beans.Location;
import webapp.beans.Person;

public class ManagerUtils {

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

	public static ArrayList<History> getOrderHistory(Connection conn, int accountId) throws SQLException {
		ArrayList<History> history_list = new ArrayList<History>();
		String sql = "call orderHistory(?)";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, accountId);
		ResultSet rs = pstm.executeQuery();

		while (rs.next()) {
			String id = rs.getString("ID");
			String symbol = rs.getString("StockSymbol");
			String numShares = rs.getString("numShares");
			String priceType = rs.getString("priceType");
			String orderType = rs.getString("orderType");

			history_list.add(new History(id, symbol, numShares, priceType, orderType));
		}

		return history_list;
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

	public static void setStockSharePrice(Stock stock, double pps) {
		stock.setPPS(pps);
	}
}
