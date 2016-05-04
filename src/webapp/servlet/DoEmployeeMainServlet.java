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
import webapp.beans.History;

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
					content += "We don't have any client";
				} else {
					for (String email : emails) {
						content += email + "<br />";
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "order_history":
			try {
				ArrayList<History> history_list = ManagerUtils.getOrderHistory(conn);
				if (history_list == null) {
					content += "You don't have a history";
				} else {
					content += "Order ID | Stock Symbol | # of Shares | Price Type | Order Type<br />";
					for (History h : history_list) {
						content += h.getId() + " ";
						content += h.getSymbol() + " ";
						content += h.getNumShares() + " ";
						content += h.getPriceType() + " ";
						content += h.getOrderType() + "<br />";
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;
		default:
			content += "Invalid Operation";
		}

		content += "</h3>";
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