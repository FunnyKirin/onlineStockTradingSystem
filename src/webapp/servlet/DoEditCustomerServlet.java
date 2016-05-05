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

import webapp.beans.Account;
import webapp.beans.Client;
import webapp.beans.Employee;
import webapp.beans.Location;
import webapp.beans.Person;
import webapp.utils.DBUtils;
import webapp.utils.ManagerUtils;
import webapp.utils.MyUtils;

@WebServlet(urlPatterns = { "/doEditCustomer" })
public class DoEditCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DoEditCustomerServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn = MyUtils.getStoredConnection(request);

		// Person
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String address = request.getParameter("address");
		String telephone = request.getParameter("telephone");

		// Account
		String ratingStr = request.getParameter("rating");
		String creditCard = request.getParameter("creditcard");
		//String dateStr = request.getParameter("dateCreated");

		// Location
		String zipcodeStr = request.getParameter("zipcode");
		String city = request.getParameter("city");
		String state = request.getParameter("state");

		// Client
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		Account account = null;
		Location location = null;
		String errorString = null;

		try {
			int zipcode = Integer.parseInt(zipcodeStr);
			double rating = Double.parseDouble(ratingStr);
			
			Account old = DBUtils.findAccount(conn, username);
			
			location = new Location(zipcode, city, state);
			account = new Account(firstname, lastname, address, old.getSSN(), telephone,
					location, email, rating, creditCard, old.getDateOpened(), old.getSSN());
			account.setUsername(username);
			account.setPassword(password);
			
			DBUtils.updateLocation(conn, location);
			DBUtils.updatePerson(conn, (Person)account);
			DBUtils.updateClient(conn, (Client)account);
			DBUtils.updateAccount(conn, account);
		} catch (SQLException e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}

		// Store infomation to request attribute, before forward to views.
		request.setAttribute("errorString", errorString);
		request.setAttribute("cust", account);

		// If error, forward to Edit page.
		if (errorString != null) {
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/EditCustomerView.jsp");
			dispatcher.forward(request, response);
		}

		// If everything nice.
		// Redirect to the customer list page.
		else {
			response.sendRedirect(request.getContextPath() + "/customerList");
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}