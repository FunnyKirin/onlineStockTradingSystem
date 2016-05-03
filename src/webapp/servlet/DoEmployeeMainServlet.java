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
import javax.servlet.http.HttpSession;

import webapp.beans.Client;
import webapp.utils.DBUtils;
import webapp.utils.ManagerUtils;
import webapp.utils.MyUtils;

@WebServlet(urlPatterns = { "/doEmployeeMain" })
public class DoEmployeeMainServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public DoEmployeeMainServlet() {
        super();
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	Connection conn = MyUtils.getStoredConnection(request);
		
		String handle = request.getParameter("handle");
		String content = "<h3>";
		switch (handle) {
		case "mailing_list":
			try {
				ArrayList<String> emails = ManagerUtils.getMailingList(conn);
				if (emails.isEmpty()) {
					content += "We don't have any client.</h3>";
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			content = "Invalid Operation</h3>";
		}
		
		request.setAttribute("mainPanel", content);
		// Not a valid one, forward
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/MainEmployee.jsp");
        dispatcher.forward(request, response);
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}