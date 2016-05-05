package webapp.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webapp.beans.Employee;
import webapp.beans.Stock;
import webapp.utils.DBUtils;
import webapp.utils.MyUtils;

@WebServlet(urlPatterns = { "/editStock" })
public class EditStockServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public EditStockServlet() {
        super();
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	// Check User has logged on
        Employee loginedUser = MyUtils.getLoginedEmployee(request.getSession());
  
        // Not logged in
        if (loginedUser == null) {
            // Redirect to login page.
            response.sendRedirect(request.getContextPath() + "/employeeLogin");
            return;
        }
    	
        Connection conn = MyUtils.getStoredConnection(request);
 
        Stock stock = null;
        String errorString = null;
        
        String symbol = request.getParameter("symbol");
 
        try {
            stock = DBUtils.findStock(conn, symbol);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        } catch (Exception e2) {
        	e2.printStackTrace();
            errorString = e2.getMessage();
        }
 
         
        // If no error.
        // The product does not exist to edit.
        // Redirect to productList page.
        if (errorString != null && stock == null) {
            response.sendRedirect("stockList");
            return;
        }
 
        // Store errorString in request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("stock", stock);
 
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/EditStockView.jsp");
        dispatcher.forward(request, response);
 
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
 
}