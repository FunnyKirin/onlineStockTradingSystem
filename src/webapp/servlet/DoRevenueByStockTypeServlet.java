package webapp.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webapp.beans.ClientWithRevenue;
import webapp.beans.Employee;
import webapp.beans.Stock;
import webapp.beans.StockWithRevenue;
import webapp.utils.ManagerUtils;
import webapp.utils.MyUtils;

@WebServlet(urlPatterns = { "/doRevenueByStockType" })
public class DoRevenueByStockTypeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public DoRevenueByStockTypeServlet() {
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
        
        StockWithRevenue swr = null;
        String type = request.getParameter("type");
        String errorString = null;
        
        List<Stock> stocks = null;
 
        try {
        	stocks = ManagerUtils.getStocks(conn);
            swr = ManagerUtils.getRevenueByStockType(conn, type);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        } catch(NumberFormatException ee) {
        	errorString = "Invalid input";
        }
 
         
        // If no error.
        // The product does not exist to edit.
        // Redirect to productList page.
        if (errorString != null && swr == null) {
            response.sendRedirect("revenueByStockSymbol");
            return;
        }
 
        // Store errorString in request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("stocks", stocks);
        request.setAttribute("cust", swr);
 
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/RevenueViewByStockType.jsp");
        dispatcher.forward(request, response);
 
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}