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

import webapp.beans.Employee;
import webapp.beans.Location;
import webapp.beans.Person;
import webapp.beans.Stock;
import webapp.utils.DBUtils;
import webapp.utils.ManagerUtils;
import webapp.utils.MyUtils;

@WebServlet(urlPatterns = { "/doEditStock" })
public class DoEditStockServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DoEditStockServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn = MyUtils.getStoredConnection(request);

		// Person
		String ppsStr = request.getParameter("pps");
		String symbol = request.getParameter("symbol");

		String errorString = null;

		try {
			double pps = Double.parseDouble(ppsStr);
			DBUtils.fluxStock(conn, symbol, pps);
		} catch (SQLException e) {
			e.printStackTrace();
			errorString = e.getMessage();
		} catch (Exception e2) {
			e2.printStackTrace();
			errorString = e2.getMessage();
		}

		// Store infomation to request attribute, before forward to views.
		request.setAttribute("errorString", errorString);

		// If error, forward to Edit page.
		if (errorString != null) {
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/EditStockView.jsp");
			dispatcher.forward(request, response);
		}

		// If everything nice.
		// Redirect to the listing page.
		else {
			response.sendRedirect(request.getContextPath() + "/stockList");
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}