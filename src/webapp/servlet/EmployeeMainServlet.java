package webapp.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webapp.beans.Account;
import webapp.beans.Employee;
import webapp.utils.ClientUtils;
import webapp.utils.MyUtils;
 
@WebServlet(urlPatterns = { "/employeeMain"})
public class EmployeeMainServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public EmployeeMainServlet() {
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

        // Forward to /WEB-INF/views/loginView.jsp
        // (Users can not access directly into JSP pages placed in WEB-INF)        
        RequestDispatcher dispatcher = this.getServletContext()
        		.getRequestDispatcher("/WEB-INF/views/MainEmployee.jsp");
         
        dispatcher.forward(request, response);
         
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
 
}