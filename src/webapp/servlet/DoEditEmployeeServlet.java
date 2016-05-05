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
import webapp.utils.DBUtils;
import webapp.utils.ManagerUtils;
import webapp.utils.MyUtils;

@WebServlet(urlPatterns = { "/doEditEmployee" })
public class DoEditEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DoEditEmployeeServlet() {
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
		String SSNstr = request.getParameter("SSN");
		String telephone = request.getParameter("telephone");

		// Employee
		String isManagerStr = request.getParameter("ismanager");
		String hourlyRateStr = request.getParameter("hourlyrate");

		// Location
		String zipcodeStr = request.getParameter("zipcode");
		String city = request.getParameter("city");
		String state = request.getParameter("state");

		// Employee
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		Employee employee = null;
		Location location = null;
		String errorString = null;

		try {
			int ssn = Integer.parseInt(SSNstr);
			int zipcode = Integer.parseInt(zipcodeStr);
			double hourlyRate = Double.parseDouble(hourlyRateStr);
			boolean isManager = "Y".equals(isManagerStr);
			
			Employee old = DBUtils.findEmployee(conn, ssn);
			
			location = new Location(zipcode, city, state);
			employee = new Employee(firstname, lastname, address, old.getSSN(), telephone,
					location, old.getDateStarted(), hourlyRate, isManager);
			employee.setUsername(username);
			employee.setPassword(password);
			
			DBUtils.updateLocation(conn, location);
			DBUtils.updatePerson(conn, (Person)employee);
			DBUtils.updateEmployee(conn, employee);
		} catch (SQLException e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}

		// Store infomation to request attribute, before forward to views.
		request.setAttribute("errorString", errorString);
		request.setAttribute("emp", employee);

		// If error, forward to Edit page.
		if (errorString != null) {
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/EditEmployeeView.jsp");
			dispatcher.forward(request, response);
		}

		// If everything nice.
		// Redirect to the product listing page.
		else {
			response.sendRedirect(request.getContextPath() + "/employeeList");
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}