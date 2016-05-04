package webapp.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

		String handle = request.getParameter("handle");
		String content = "<h3>";
		switch (handle) {

		default:
			content += "Invalid Operation";
		}

		content += "</h3>";
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