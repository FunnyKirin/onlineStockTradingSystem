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

public class ManagerUtils {
	
	public static ArrayList<String> getMailingList(Connection conn) throws SQLException {
		ArrayList<String> emails = new ArrayList<String>();
		String sql = "call getMailingList()";

		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		
		while (rs.next()) {
			String email = rs.getString("Email");
			emails.add(email);
			System.out.println(email);
		}
		
		return emails;
	}
	
	public static void setStockSharePrice(Stock stock, double pps) {
		stock.setPPS(pps);
	}
}
