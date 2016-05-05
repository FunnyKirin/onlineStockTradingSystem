package webapp.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
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
import webapp.utils.DBUtils;
import webapp.utils.MyUtils;

@WebServlet(urlPatterns = { "/doEmployeeRegister" })
public class DoEmployeeRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DoEmployeeRegisterServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Person
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String address = request.getParameter("address");
		String SSNstr = request.getParameter("SSN");
		String telephone = request.getParameter("telephone");

		// Employee
		String isManager = request.getParameter("ismanager");
		String hourlyRateStr = request.getParameter("hourly_rate");

		// Location
		String zipcodeStr = request.getParameter("zipcode");
		String city = request.getParameter("city");
		String state = request.getParameter("state");

		// Client
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String confirmpass = request.getParameter("confirmpass");
		
		Calendar cal = Calendar.getInstance();
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		String dateStr = year + "-" + month + "-" + day;

		//Client client = null;
		Location location = null;
		Employee employee = null;
		boolean hasError = false;
		String errorString = null;

		if (username == null || password == null || firstname == null || lastname == null 
				|| SSNstr == null || city == null || dateStr == null || state == null 
				|| zipcodeStr == null || isManager == null || hourlyRateStr == null
				|| address == null || telephone == null || confirmpass == null) {
			hasError = true;
			errorString = "Fill out everything before you proceed";
		} else if (username.length() == 0 || password.length() == 0 || firstname.length() == 0 
				|| SSNstr.length() == 0 || city.length() == 0 || dateStr.length() == 0 
				|| (!(isManager.equals("Y") || isManager.equals("N"))) || lastname.length() == 0
				|| zipcodeStr.length() == 0 || address.length() == 0 || state.length() == 0
				|| telephone.length() == 0 || confirmpass.length() == 0
				|| hourlyRateStr.length() == 0) {
			hasError = true;
			errorString = "Fill out everything before you proceed";
		} else if (!password.equals(confirmpass)) {
			hasError = true;
			errorString = "You didn't confirm your password properly";
		} else {
			Connection conn = MyUtils.getStoredConnection(request);
			try {
				// Check field formats
				if (!(telephone.matches("[0-9]+") && telephone.length() == 10)) {
					throw new Exception("Invalid telephone");
				}
				
				int zipcode = Integer.parseInt(zipcodeStr);
				int SSN = Integer.parseInt(SSNstr);
				double hourlyRate = Double.parseDouble(hourlyRateStr);
				Date date = Date.valueOf(dateStr);
				
				if (zipcode < 10000 || zipcode > 99999) {
					throw new NumberFormatException("Invalid zip");
				} else if (SSN < 100000000 || SSN > 999999999) {
					throw new NumberFormatException("Invalid SSN");
				} else if (hourlyRate <= 0) {
					throw new NumberFormatException("Invalid salary");
				}
				
				location = new Location(zipcode, city, state);
				employee = new Employee(firstname, lastname, address, SSN, telephone, location,
						date, hourlyRate, "Y".equals(isManager));
				//client = new Client(firstname, lastname, address, SSN, telephone, location, email, 0, creditCardNum);

				employee.setUsername(username);
				employee.setPassword(password);
				DBUtils.insertEmployee(conn, employee);

			} catch (SQLException e) {
				hasError = true;
				errorString = e.getMessage();
				e.printStackTrace();
			} catch (NumberFormatException e) {
				hasError = true;
				errorString = e.getMessage();
			} catch (IllegalArgumentException e2) {
				hasError = true;
				errorString = "Invalid date";
			} catch (Exception e) {
				hasError = true;
				errorString = e.getMessage();
			}
		}

		// If error, forward to /WEB-INF/views/RegisterView.jsp
		if (hasError) {
			employee = new Employee();

			// Store information in request attribute, before forward.
			request.setAttribute("errorString", errorString);

			// Forward to /WEB-INF/views/RegisterView.jsp
			RequestDispatcher dispatcher //
					= this.getServletContext().getRequestDispatcher("/WEB-INF/views/RegisterViewEmployee.jsp");

			dispatcher.forward(request, response);
		}

		// If no error
		// Store user information in Session
		// And redirect to userInfo page.
		else {
			HttpSession session = request.getSession();
			MyUtils.storeLoginedUser(session, employee);

			// Redirect to userInfo page.
			response.sendRedirect(request.getContextPath() + "/employeeList");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}