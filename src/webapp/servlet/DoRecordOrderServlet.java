package webapp.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
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
import webapp.beans.Employee;
import webapp.beans.Location;
import webapp.beans.Order;
import webapp.beans.Stock;
import webapp.beans.Trade;
import webapp.beans.Transaction;
import webapp.utils.DBUtils;
import webapp.utils.ManagerUtils;
import webapp.utils.MyUtils;

@WebServlet(urlPatterns = { "/doRecordOrder" })
public class DoRecordOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DoRecordOrderServlet() {
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

		String symbol = request.getParameter("symbol");
		String buySell = request.getParameter("buy_sell");
		String type = request.getParameter("order_type");
		String feeStr = request.getParameter("fee");
		String orderIdStr = request.getParameter("order_id");
		String numSharesStr = request.getParameter("num_shares");
		String accountIdStr = request.getParameter("account_id");

		String errorString = null;

		List<Stock> stocks = null;
		try {
			stocks = ManagerUtils.getStocks(conn);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Stock stock = null;
		Account account = null;
		Employee employee = null;
		Order order = null;

		try {
			//int numShares = Integer.parseInt(numSharesStr);
			int accountId = Integer.parseInt(accountIdStr);
			int orderId = Integer.parseInt(orderIdStr);
			int eid = MyUtils.getLoginedEmployee(request.getSession()).getId();
			double fee = Double.parseDouble(feeStr);

			stock = DBUtils.findStock(conn, symbol);
			account = DBUtils.findAccount(conn, accountId);
			employee = DBUtils.findEmployeeById(conn, eid);
			order = DBUtils.findOrder(conn, orderId);

			Transaction t = new Transaction();
			t.setFee(fee);
			t.setPps(stock.getPps());

			t = DBUtils.insertTransaction(conn, t);

			Trade trade = new Trade(account, employee, order, stock, t);
			DBUtils.insertTrade(conn, trade, "buy".equals(buySell));

		} catch (SQLException e) {
			errorString = e.getMessage();
			e.printStackTrace();
		} catch (NumberFormatException e2) {
			errorString = e2.getMessage();
			e2.printStackTrace();
		}

		// If no error.
		// The product does not exist to edit.
		// Redirect to productList page.
		if (errorString != null) {
			response.sendRedirect("recordOrder");
			return;
		} else {
			errorString = "Success";
		}

		// Store errorString in request attribute, before forward to views.
		request.setAttribute("errorString", errorString);
		request.setAttribute("stocks", stocks);

		RequestDispatcher dispatcher = request.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/RecordOrderView.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}