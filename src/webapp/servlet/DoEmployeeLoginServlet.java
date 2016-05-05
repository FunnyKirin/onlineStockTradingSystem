package webapp.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webapp.beans.Employee;
import webapp.utils.DBUtils;
import webapp.utils.MyUtils;

@WebServlet(urlPatterns = { "/doEmployeeLogin" })
public class DoEmployeeLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DoEmployeeLoginServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String rememberMeStr = request.getParameter("rememberMe");
		boolean remember = "Y".equals(rememberMeStr);

		Employee employee = null;
		boolean hasError = false;
		String errorString = null;

		if (username == null || password == null || username.length() == 0 || password.length() == 0) {
			hasError = true;
			errorString = "Required username and password";
		} else {
			Connection conn = MyUtils.getStoredConnection(request);
			try {

				employee = DBUtils.loginAsEmployee(conn, username, password);

				if (employee == null) {
					hasError = true;
					errorString = "Invalid username or password";
				}
			} catch (SQLException e) {
				e.printStackTrace();
				hasError = true;
				errorString = e.getMessage();
			}
		}

		// If error, forward to /WEB-INF/views/login.jsp
		if (hasError) {
			employee = new Employee();

			// Store information in request attribute, before forward.
			request.setAttribute("errorString", errorString);
			request.setAttribute("user", employee);

			// Forward
			RequestDispatcher dispatcher //
					= this.getServletContext().getRequestDispatcher("/WEB-INF/views/LoginViewEmployee.jsp");

			dispatcher.forward(request, response);
		}

		// If no error
		// Store user information in Session
		// And redirect to userInfo page.
		else {
			HttpSession session = request.getSession();
			MyUtils.storeLoginedUser(session, employee);

			// If user checked "Remember me".
			if (remember) {
				MyUtils.storeUserCookie(response, employee);
			}

			// Else delete cookie.
			else {
				MyUtils.deleteUserCookie(response);
			}

			if (employee.getIsManager())
				response.sendRedirect(request.getContextPath() + "/managerMain");
			else
				response.sendRedirect(request.getContextPath() + "/employeeMain");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}