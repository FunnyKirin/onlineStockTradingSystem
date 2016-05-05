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
import webapp.utils.ManagerUtils;
import webapp.utils.MyUtils;

@WebServlet(urlPatterns = { "/revenueByStockType" })
public class RevenueByStockTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RevenueByStockTypeServlet() {
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
		List<Stock> stocks = null;
		try {
			stocks = ManagerUtils.getStocks(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.setAttribute("stocks", stocks);

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