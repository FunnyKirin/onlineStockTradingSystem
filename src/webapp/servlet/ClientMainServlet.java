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

import webapp.beans.*;
import webapp.utils.ClientUtils;
import webapp.utils.MyUtils;
 
@WebServlet(urlPatterns = { "/clientMain"})
public class ClientMainServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public ClientMainServlet() {
        super();
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
/**
		Connection conn = MyUtils.getStoredConnection(request);
		HttpSession session= request.getSession();
		Client thisClient = (Client)MyUtils.getLoginedUser(session);
		int thisClientID= thisClient.getSSN();

		try {
			ClientUtils.getCurrentStocks(conn, thisClientID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		**/
    	HttpSession session = request.getSession();
    	Account loginedUser = (Account) MyUtils.getLoginedUser(session);
    	
    	if (loginedUser == null) {
    		// Redirect to login page.
            response.sendRedirect(request.getContextPath() + "/clientLogin");
            return;
    	}
         
        // Forward to /WEB-INF/views/loginView.jsp
        // (Users can not access directly into JSP pages placed in WEB-INF)        
        RequestDispatcher dispatcher = this.getServletContext()
        		.getRequestDispatcher("/WEB-INF/views/MainClient.jsp");
        request.setAttribute("user", loginedUser.getUsername());
         
        dispatcher.forward(request, response);
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
 
}