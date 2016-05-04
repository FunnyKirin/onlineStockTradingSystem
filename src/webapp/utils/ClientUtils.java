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
		String sql = "SELECT * from hasStock where AccountId = ?;";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, AccountID);
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {

			hasStock thisStock = new hasStock();
			String StockSymbol = rs.getString("StockSymbol");
			int numOfShares = rs.getInt("NumShares");
			thisStock.setNumberOfShares(numOfShares);
			thisStock.setStockSymbol(StockSymbol);
			System.out.println(thisStock.getStockSymbol()+" "+thisStock.getNumberOfShares());
			currentStocks.add(thisStock);
		}
		return currentStocks;
	}
	
	public static ArrayList<Stock> getBestSellers(Connection conn) throws SQLException {
		ArrayList<Stock> bestSellers = new ArrayList<Stock>();
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