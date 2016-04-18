package webapp.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
 
import webapp.beans.Stock;
import webapp.beans.UserAccount;
 
public class DBUtils {
 
  public static UserAccount findUser(Connection conn, String username, String password) throws SQLException {
 
      String sql = "Select a.User_Name, a.Password, a.Gender from User_Account a "
              + " where a.User_Name = ? and a.password= ?";
 
      PreparedStatement pstm = conn.prepareStatement(sql);
      pstm.setString(1, username);
      pstm.setString(2, password);
      ResultSet rs = pstm.executeQuery();
 
      if (rs.next()) {
          String gender = rs.getString("Gender");
          UserAccount user = new UserAccount();
          user.setUsername(username);
          user.setPassword(password);
          user.setGender(gender);
          return user;
      }
      return null;
  }
 
  public static UserAccount findUser(Connection conn, String username) throws SQLException {
 
      String sql = "Select a.User_Name, a.Password, a.Gender from User_Account a " + " where a.User_Name = ? ";
 
      PreparedStatement pstm = conn.prepareStatement(sql);
      pstm.setString(1, username);
 
      ResultSet rs = pstm.executeQuery();
 
      if (rs.next()) {
          String password = rs.getString("Password");
          String gender = rs.getString("Gender");
          UserAccount user = new UserAccount();
          user.setUsername(username);
          user.setPassword(password);
          user.setGender(gender);
          return user;
      }
      return null;
  }

  public static void insertUser(Connection conn, UserAccount user) throws SQLException {
	  String sql = "Insert into UserAccount(FirstName, LastName, Gender, Username, Password) "
			  + "values (?, ?, ?, ?, ?)";
	  PreparedStatement pstm = conn.prepareStatement(sql);
	  
	  pstm.setString(1, user.getFirstname());
      pstm.setString(2, user.getLastname());
      pstm.setString(3, user.getGender());
      pstm.setString(2, user.getUsername());
      pstm.setString(3, user.getPassword());
  }
  
  public static List<Stock> queryProduct(Connection conn) throws SQLException {
      String sql = "Select a.Code, a.Name, a.Price from Product a ";
 
      PreparedStatement pstm = conn.prepareStatement(sql);
 
      ResultSet rs = pstm.executeQuery();
      List<Stock> list = new ArrayList<Stock>();
      while (rs.next()) {
          String code = rs.getString("Code");
          String name = rs.getString("Name");
          float price = rs.getFloat("Price");
          Stock stock = new Stock();
          stock.setCode(code);
          stock.setName(name);
          stock.setPrice(price);
          list.add(stock);
      }
      return list;
  }
 
  public static Stock findProduct(Connection conn, String code) throws SQLException {
      String sql = "Select a.Code, a.Name, a.Price from Product a where a.Code=?";
 
      PreparedStatement pstm = conn.prepareStatement(sql);
      pstm.setString(1, code);
 
      ResultSet rs = pstm.executeQuery();
 
      while (rs.next()) {
          String name = rs.getString("Name");
          float price = rs.getFloat("Price");
          Stock stock = new Stock(code, name, price);
          return stock;
      }
      return null;
  }
 
  public static void updateProduct(Connection conn, Stock stock) throws SQLException {
      String sql = "Update Product set Name =?, Price=? where Code=? ";
 
      PreparedStatement pstm = conn.prepareStatement(sql);
 
      pstm.setString(1, stock.getName());
      pstm.setFloat(2, stock.getPrice());
      pstm.setString(3, stock.getCode());
      pstm.executeUpdate();
  }
 
  public static void insertProduct(Connection conn, Stock stock) throws SQLException {
      String sql = "Insert into Product(Code, Name,Price) values (?,?,?)";
 
      PreparedStatement pstm = conn.prepareStatement(sql);
 
      pstm.setString(1, stock.getCode());
      pstm.setString(2, stock.getName());
      pstm.setFloat(3, stock.getPrice());
 
      pstm.executeUpdate();
  }
 
  public static void deleteProduct(Connection conn, String code) throws SQLException {
      String sql = "Delete from Product where Code= ?";
 
      PreparedStatement pstm = conn.prepareStatement(sql);
 
      pstm.setString(1, code);
 
      pstm.executeUpdate();
  }
 
}