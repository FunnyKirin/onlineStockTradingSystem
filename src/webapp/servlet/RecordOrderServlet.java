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

import webapp.beans.Employee;
import webapp.beans.Stock;
import webapp.utils.DBUtils;
import webapp.utils.ManagerUtils;
import webapp.utils.MyUtils;

@WebServlet(urlPatterns = { "/recordOrder" })
public class RecordOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RecordOrderServlet() {
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
		String errorString = null;
		
		List<Stock> stocks = null;
		Employee employee = null;

		try {
			int eid = loginedUser.getId();

			stocks = ManagerUtils.getStocks(conn);
			employee = DBUtils.findEmployeeById(conn, eid);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NumberFormatException e2) {
			e2.printStackTrace();
		}

		// Store errorString in request attribute, before forward to views.
		request.setAttribute("errorString", errorString);
		request.setAttribute("stocks", stocks);
		request.setAttribute("emp", employee);

		// Forward to /WEB-INF/views/RegisterView.jsp
		// (Users can not access directly into JSP pages placed in WEB-INF)
		RequestDispatcher dispatcher = this.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/RecordOrderView.jsp");

		dispatcher.forward(request, response);
	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}