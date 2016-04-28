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
 
import webapp.beans.UserAccount;
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
 
    	String firstname = request.getParameter("firstname");
    	String lastname = request.getParameter("lastname");
    	String gender = request.getParameter("gender");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
 
         
        UserAccount user = null;
        boolean hasError = false;
        String errorString = null;
 
        if (username == null || password == null
                 || username.length() == 0 || password.length() == 0
                 || firstname == null || lastname == null) {
            hasError = true;
            errorString = "Fill out everything before you proceed";
        } else {
            Connection conn = MyUtils.getStoredConnection(request);
            try {
              
                user = DBUtils.insertAccount(conn, account);
                 
                if (user == null) {
                    hasError = true;
                    errorString = "User name or password invalid";
                }
            } catch (SQLException e) {
                e.printStackTrace();
                hasError = true;
                errorString = e.getMessage();
            }
        }
        
        // If error, forward to /WEB-INF/views/RegisterView.jsp
        if (hasError) {
            user = new UserAccount();
            user.setUsername(username);
            user.setPassword(password);
             
        
            // Store information in request attribute, before forward.
            request.setAttribute("errorString", errorString);
            request.setAttribute("user", user);
 
       
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
            MyUtils.storeLoginedUser(session, user);
      
            // Redirect to userInfo page.
            response.sendRedirect(request.getContextPath() + "/userInfo");
        }
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
 
}