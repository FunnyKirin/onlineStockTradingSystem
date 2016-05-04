package webapp.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import webapp.beans.Client;
import webapp.utils.DBUtils;
import webapp.utils.ManagerUtils;
import webapp.utils.MyUtils;

@WebServlet(urlPatterns = { "/deleteProduct" })
public class DeleteCustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public DeleteCustomerServlet() {
        super();
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
 
        int id = Integer.parseInt(request.getParameter("id"));
 
        String errorString = null;
 
        try {
            DBUtils.deleteClient(conn, id);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
         
 
        // If an error redirected to an error page.
        if (errorString != null) {
 
            // Store the information in the request attribute, before forward to views.
            request.setAttribute("errorString", errorString);
            //
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/deleteCustomerErrorView.jsp");
            dispatcher.forward(request, response);
        }
 
        // If everything nice.
        // Redirect to the product listing page.        
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