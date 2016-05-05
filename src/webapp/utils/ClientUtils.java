package webapp.utils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import webapp.beans.*;

public class ClientUtils {
	//@todo
	public static ArrayList<hasStock> getCurrentStocks(Connection conn, int AccountID) throws SQLException {
		ArrayList<hasStock> currentStocks = new ArrayList<hasStock>();
		String sql = "SELECT * from hasStock H, Stock S where H.AccountId = ? AND H.StockSymbol = S.StockSymbol";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, AccountID);
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {

			String StockSymbol = rs.getString("StockSymbol");
			String StockName = rs.getString("CompanyName");
			int numOfShares = rs.getInt("NumShares");
			String type = rs.getString("Type");
			double pricePerShare = rs.getDouble("PricePerShare");

			hasStock thisStock = new hasStock(numOfShares, StockSymbol, StockName, type, pricePerShare);
			System.out.println(numOfShares+ StockSymbol+ StockName+ type+ pricePerShare);

			currentStocks.add(thisStock);
		}
		return currentStocks;
	}
	public static ArrayList<TrailingStopHistory> getTrailingHis(Connection conn, int orderID) throws SQLException {
		ArrayList<TrailingStopHistory> trailingHisList = new ArrayList<TrailingStopHistory>();
		String sql = "call trailingHistory(?)";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, orderID);

		ResultSet rs = pstm.executeQuery();
		while(rs.next()){
			
		}
		return trailingHisList;
	}
	public static ArrayList<History> getOrderHistory(Connection conn, int AccountID)throws SQLException {
		ArrayList<History> orderHistory = new ArrayList<History>();
		String sql = "call orderHistory(?)";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, AccountID);

		ResultSet rs = pstm.executeQuery();
		while(rs.next()){
			String id = rs.getString("ID");
			String symbol = rs.getString("StockSymbol");
			String numShares = rs.getString("numShares");
			String priceType = rs.getString("priceType");
			String orderType = rs.getString("orderType");
			Date date = rs.getDate("DateTime");
			//System.out.println("history:"+id+symbol+numShares+priceType+orderType);

			orderHistory.add(new History(id, symbol, numShares, priceType, orderType, date));

		}
		return orderHistory;
	}
	
	public static ArrayList<Stock> getBestSellers(Connection conn) throws SQLException {
		ArrayList<Stock> bestSellers = new ArrayList<Stock>();
		
		String sql = "call bestSellerStocks();";
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		
		while (rs.next()) {
			String symbol = rs.getString("StockId");
			String company = rs.getString("CompanyName");
			String type = rs.getString("Type");
			double pps = rs.getDouble("PricePerShare");
			
			bestSellers.add(new Stock(symbol, company, type, pps));
		}
		
		return bestSellers;
	}
	
	public static ArrayList<Stock> getStockSuggestions(Connection conn, int AccountID) throws SQLException {
		ArrayList<Stock> stockSuggestions = new ArrayList<Stock>();
		String sql = "call giveSuggestion(?)";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, AccountID);
		ResultSet rs = pstm.executeQuery();
		
		while (rs.next()) {
			String symbol = rs.getString("StockSymbol");
			String company = rs.getString("CompanyName");
			String type = rs.getString("Type");
			double pps = rs.getDouble("PricePerShare");
			
			stockSuggestions.add(new Stock(symbol, company, type, pps));
		}
		return stockSuggestions;
	}
	
	public static ArrayList<Stock> searchStocksByName(Connection conn, String input) throws SQLException {
		ArrayList<Stock> stocks = new ArrayList<Stock>();
		
		String sql = "call searchAvailStockByName(?);";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, input);

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
	public static ArrayList<Stock> searchStocksByType(Connection conn, String input) throws SQLException {
		ArrayList<Stock> stocks = new ArrayList<Stock>();
		
		String sql = "call searchAvailStockByType(?);";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, input);

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
	
	public static ArrayList<Trade> getStocksByName(Connection conn) throws SQLException {
		ArrayList<Trade> trades = new ArrayList<Trade>();
		
		String sql = "Select P.lastname, P.firstname, O.* from Person P, Client C, Account A"
				+ ", Trade T, StockOrder O where T.AccountId = A.ID and O.ID = T.OrderID and "
				+ "P.SSN = C.ID and C.ID = A.ClientID Order By P.lastname;";
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		
		while (rs.next()) {
			String lastname = rs.getString("P.lastname");
			String firstname = rs.getString("P.firstname");
			int id = rs.getInt("O.ID");
			int numShares = rs.getInt("NumShares");
			String type = rs.getString("OrderType");
			double pps = rs.getDouble("PricePerShare");
			
			Account account = new Account();
			account.setFirstname(firstname);
			account.setLastname(lastname);
			Order order = new Order();
			order.setId(id);
			order.setNumShares(numShares);
			order.setPps(pps);
			order.setType(type);
			
			Trade t = new Trade();
			t.setAccount(account);
			t.setOrder(order);
			
			trades.add(t);
		}
		
		return trades;
	}
	
	public static ArrayList<Trade> getStocksBySymbol(Connection conn) throws SQLException {
		ArrayList<Trade> trades = new ArrayList<Trade>();
		
		String sql = "Select S.StockSymbol , O.* from Trade T, StockOrder O, Stock S "
				+ "where O.ID = T.OrderID and S.StockSymbol = T.StockId Order By S.StockSymbol";
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		
		while (rs.next()) {
			String symbol = rs.getString("S.StockSymbol");
			int id = rs.getInt("O.ID");
			int numShares = rs.getInt("NumShares");
			String type = rs.getString("OrderType");
			double pps = rs.getDouble("PricePerShare");
			
			Stock s = new Stock();
			s.setSymbol(symbol);
			Order order = new Order();
			order.setId(id);
			order.setNumShares(numShares);
			order.setPps(pps);
			order.setType(type);
			
			Trade t = new Trade();
			t.setStock(s);
			t.setOrder(order);
			
			trades.add(t);
		}
		
		return trades;
	}
}