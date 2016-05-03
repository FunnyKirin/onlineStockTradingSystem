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
import webapp.beans.Location;
import webapp.beans.Person;

public class ClientUtils {
	//@todo
	public static ArrayList<Stock> getCurrentStocks(Connection conn, int clientId) throws SQLException {
		ArrayList<Stock> currentStocks = new ArrayList<Stock>();
		String sql = "Select ClientID from Account where ClientID = ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, clientId);
		ResultSet rs = pstm.executeQuery();

		if (rs.next()) {
			sql = "	SELECT S.* from NumShares FROM Stock S, Account A, hasStcok H WHERE A.ClientID = H.AccountId and S.StockSymbol = H.StockSymbol;";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, clientId);
			rs = pstm.executeQuery();

			return currentStocks;
		}

		return null;
	}
	
	public static ArrayList<Stock> getBestSellers(Connection conn) throws SQLException {
		ArrayList<Stock> bestSellers = new ArrayList<Stock>();
		return bestSellers;
	}
	
	public static ArrayList<Stock> getStockSuggestions(Connection conn) throws SQLException {
		ArrayList<Stock> stockSuggestions = new ArrayList<Stock>();
		return stockSuggestions;
	}
	
	
}