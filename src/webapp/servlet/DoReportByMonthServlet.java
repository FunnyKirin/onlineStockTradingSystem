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

import webapp.beans.Trade;
import webapp.utils.ManagerUtils;
import webapp.utils.MyUtils;

@WebServlet(urlPatterns = { "/doReportByMonth" })
public class DoReportByMonthServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public DoReportByMonthServlet() {
        super();
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
        
        String monthStr = request.getParameter("month");
 
        ArrayList<Trade> trades = null;
        String errorString = null;
 
        try {
        	int month = Integer.parseInt(monthStr);
            trades = ManagerUtils.getSalesReportByMonth(conn, month);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        } catch(NumberFormatException ee) {
        	errorString = "Invalid input";
        }
 
         
        // If no error.
        // The product does not exist to edit.
        // Redirect to productList page.
        if (errorString != null && trades == null) {
            response.sendRedirect("reportByMonth");
            return;
        }
 
        // Store errorString in request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("trades", trades);
 
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/SalesByMonthView.jsp");
        dispatcher.forward(request, response);
 
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
 
}