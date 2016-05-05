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

import webapp.beans.Stock;
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

		String content = "";
		switch (handle) {
		case "mailing_list":
			try {
				ArrayList<String> emails = ManagerUtils.getMailingList(conn);
				if (emails.isEmpty()) {
					content += "<h3>We don't have any client</h3>";
				} else {
					content += "<table border=\"1\" cellpadding=\"5\" cellspacing=\"1\">";
					content += "<tr><th>Client Email</th></tr>";
					for (String email : emails) {
						content += "<tr><td>" + email + "</td></tr>";
					}
					content += "</table>";
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
/*
		case "give_suggestion":
			content = "<form action=\"doEmployeeMain\">";
			content += "<input name=\"id\" type=\"text\" />";
			content += "<input onclick=\"setHandle(\"do_give_suggestion\")\" type=\"submit\" value=\"Submit\" />";
			content += "<input type=\"hidden\" id=\"handle\" name=\"handle\" value=\"mailing_list\" />";
			content += "</form>";
		case "do_give_suggestion":
			try {
				String str = request.getParameter("id");
				if (!(str.length() == 9 && str.matches("[0-9]+"))) {
					int clientId = Integer.parseInt(str);
					stocks = ManagerUtils.giveSuggestions(conn, clientId);
					if (stocks == null) {
					} else if (stocks.isEmpty()) {
						content = "<h3>You don't got no stock babe</h3>";
					} else {
						content = "<table><tr><th>Symbol</th>" + "<th>Company Name</th>" + "<th>Type</th>"
								+ "<th>Price per Share</th>";
						for (Stock s : stocks) {
							content += "<tr><td>" + s.getSymbol() + "</td><td>" + s.getCompany() + "</td><td>"
									+ s.getType() + "</td><td>" + s.getPPS() + "</td></tr>";
						}
						content += "</table>";
					}
				} else {
					content = "<h3>No such ID</h3>";
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e2) {
				e2.printStackTrace();
				System.out.println("Damn it");
			}
			break;
*/
		default:
			content += "Invalid Operation";
		}

		content += "";
		request.setAttribute("mainPanel", content);
		// forward
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/MainEmployee.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}