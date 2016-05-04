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
	
	public static ArrayList<Stock> getStockSuggestions(Connection conn) throws SQLException {
		ArrayList<Stock> stockSuggestions = new ArrayList<Stock>();
		return stockSuggestions;
	}
	
	public static ArrayList<Stock> getStocksByName(Connection conn) throws SQLException {
		ArrayList<Stock> stocks = new ArrayList<Stock>();
		
		String sql = "call stockListingByName();";
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
	
	public static ArrayList<Stock> getStocksBySymbol(Connection conn) throws SQLException {
		ArrayList<Stock> stocks = new ArrayList<Stock>();
		
		String sql = "call stockListingBySymbol();";
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
}