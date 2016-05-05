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
import webapp.beans.Stock;
import webapp.beans.Trade;
import webapp.utils.ClientUtils;
import webapp.utils.ManagerUtils;
import webapp.utils.MyUtils;

@WebServlet(urlPatterns = { "/doManagerMain" })
public class DoManagerMainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DoManagerMainServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Connection conn = MyUtils.getStoredConnection(request);
		ArrayList<Trade> trades = null;
		ArrayList<Stock> stocks = null;
		Employee employee = null;

		String handle = request.getParameter("handle");
		String content = "";
		switch (handle) {
		case "best_employee":
			try {
				employee = ManagerUtils.getCoolestEmployee(conn);
				if (employee != null) {
					content = "<h3>Employee who Makes the Most: ";
					content += employee.getFirstname() + " "
							+ employee.getLastname() + "</h3>";
				} else {
					content = "Something's weird";
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "list_stocks":
			try {
				stocks = ManagerUtils.getStocks(conn);
				if (stocks == null) {
				} else if (stocks.isEmpty()) {
					content = "<h3>You don't got no stock babe</h3>";
				} else {
					content = "<table><tr><th>Symbol</th>" + "<th>Company Name</th>" + "<th>Type</th>"
							+ "<th>Price per Share</th>";
					for (Stock s : stocks) {
						content += "<tr><td>" + s.getSymbol() + "</td><td>" + s.getCompany() + "</td><td>" + s.getType()
								+ "</td><td>" + s.getPps() + "</td></tr>";
					}
					content += "</table>";
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "stocks_by_symbol":
			try {
				trades = ClientUtils.getStocksBySymbol(conn);
				if (trades == null) {
				} else if (trades.isEmpty()) {
					content = "<h3>You don't got no stock babe</h3>";
				} else {
					content = "<table><tr><th>Symbol</th>" + "<th>Order ID</th>" + "<th>Price per Share</th>"
							+ "<th>Order Type</th>" + "<th># of Shares" + "</th></tr>";
					for (Trade t : trades) {
						content += "<tr><td>" + t.getStock().getSymbol() + "</td><td>" + t.getOrder().getId()
								+ "</td><td>" + t.getOrder().getPps() + "</td><td>" + t.getOrder().getType()
								+ "</td><td>" + t.getOrder().getNumShares() + "</td></tr>";
					}
					content += "</table>";
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "stocks_by_name":
			try {
				trades = ClientUtils.getStocksByName(conn);
				if (trades == null) {
				} else if (trades.isEmpty()) {
					content = "<h3>You don't got no stock babe</h3>";
				} else {
					content = "<table><tr><th>Last Name</th>" + "<th>First Name</th>" + "<th>Order ID</th>"
							+ "<th>Price per Share</th>" + "<th>Order Type</th>" + "<th># of Shares" + "</th></tr>";
					for (Trade t : trades) {
						content += "<tr><td>" + t.getAccount().getLastname() + "</td><td>"
								+ t.getAccount().getFirstname() + "</td><td>" + t.getOrder().getId() + "</td><td>"
								+ t.getOrder().getPps() + "</td><td>" + t.getOrder().getType() + "</td><td>"
								+ t.getOrder().getNumShares() + "</td></tr>";
					}
					content += "</table>";
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		}

		request.setAttribute("mainPanel", content);
		// forward
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/MainManager.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}