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

import webapp.beans.Stock;
import webapp.utils.ManagerUtils;
import webapp.utils.MyUtils;

@WebServlet(urlPatterns = { "/doGiveSuggestion" })
public class DoGiveSuggestionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public DoGiveSuggestionServlet() {
        super();
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
        
        String SSNstr = request.getParameter("client_id");
 
        ArrayList<Stock> stocks = null;
        String errorString = null;
 
        try {
        	int SSN = Integer.parseInt(SSNstr);
            stocks = ManagerUtils.giveSuggestions(conn, SSN);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        } catch(NumberFormatException ee) {
        	errorString = "Invalid input";
        }
 
         
        // If no error.
        // The product does not exist to edit.
        // Redirect to productList page.
        if (errorString != null && stocks == null) {
            response.sendRedirect("giveSuggestion");
            return;
        }
 
        // Store errorString in request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("stocks", stocks);
 
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/GiveSuggestionView.jsp");
        dispatcher.forward(request, response);
 
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
 
}