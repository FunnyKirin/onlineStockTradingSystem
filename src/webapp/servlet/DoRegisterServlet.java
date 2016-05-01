package webapp.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webapp.beans.Account;
import webapp.beans.Client;
import webapp.beans.Location;
import webapp.utils.DBUtils;
import webapp.utils.MyUtils;
 
@WebServlet(urlPatterns = { "/doRegister" })
public class DoRegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public DoRegisterServlet() {
        super();
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
    	// Person
    	String firstname = request.getParameter("firstname");
    	String lastname = request.getParameter("lastname");
    	String address = request.getParameter("address");
    	String SSNstr = request.getParameter("SSN");
    	String email = request.getParameter("email");
    	String telephone = request.getParameter("telephone");
        
        // Account
        String creditCardNum = request.getParameter("creditcard");
        String dateStr = request.getParameter("date"); // yyyy-mm-dd
        
        		
        // Location
        String zipcodeStr = request.getParameter("zipcode");
        String city = request.getParameter("city");
    	String state = request.getParameter("state");
    	
    	
    	// Client
    	String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        
        Client client = null;
        Location location = null;
        Account account = null;
        boolean hasError = false;
        String errorString = null;
 
        if (username == null || password == null
                 || firstname == null || lastname == null
                 || SSNstr == null || city == null || dateStr == null
                 || state == null || zipcodeStr == null
                 || creditCardNum == null || address == null
                 || email == null || telephone == null) {
            hasError = true;
            errorString = "Fill out everything before you proceed";
        } else if (username.length() == 0 || password.length() == 0
        		|| firstname.length() == 0 || lastname.length() == 0
        		|| SSNstr.length() == 0 || city.length() == 0
        		|| dateStr.length() == 0 || state.length() == 0
        		|| zipcodeStr.length() == 0 || creditCardNum.length() == 0
        		|| address.length() == 0 || email.length() == 0
        		|| telephone.length() == 0) {
        	hasError = true;
            errorString = "Fill out everything before you proceed";
        } else {
            Connection conn = MyUtils.getStoredConnection(request);
            try {
            	int zipcode = Integer.parseInt(zipcodeStr);
            	int SSN = Integer.parseInt(SSNstr);
            	Date date = Date.valueOf(dateStr);
            	location = new Location(zipcode, city, state);
            	account = new Account(creditCardNum, date, SSN);
            	client = new Client(firstname, lastname, address, SSN, telephone, location, email, 0, account);
                DBUtils.insertClient(conn, client);
            	
            } catch (SQLException e) {
                e.printStackTrace();
                hasError = true;
                errorString = e.getMessage();
            }
        }
        
        // If error, forward to /WEB-INF/views/RegisterView.jsp
        if (hasError) {
            client = new Client();
            client.setUsername(username);
            client.setPassword(password);
             
        
            // Store information in request attribute, before forward.
            request.setAttribute("errorString", errorString);
            request.setAttribute("user", client);
 
       
            // Forward to /WEB-INF/views/RegisterView.jsp
            RequestDispatcher dispatcher //
            = this.getServletContext().getRequestDispatcher("/WEB-INF/views/RegisterView.jsp");
 
            dispatcher.forward(request, response);
        }
     
        // If no error
        // Store user information in Session
        // And redirect to userInfo page.
        else {
            HttpSession session = request.getSession();
            MyUtils.storeLoginedUser(session, client);
      
            // Redirect to userInfo page.
            response.sendRedirect(request.getContextPath() + "/mainClient");
        }
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
 
}