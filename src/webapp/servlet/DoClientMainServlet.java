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
import webapp.beans.History;
import webapp.beans.Stock;
import webapp.utils.ClientUtils;
import webapp.utils.DBUtils;
import webapp.utils.ManagerUtils;
import webapp.utils.MyUtils;

@WebServlet(urlPatterns = { "/doClientMain" })
public class DoClientMainServlet extends HttpServlet{
	private static final long serialVersionUID= 1L;
	
	public DoClientMainServlet(){
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
/**
		Connection conn = MyUtils.getStoredConnection(request);

		String handle = request.getParameter("handle");
		String content = "<h3>";
		switch (handle) {
		case "stock_by_name":
			try {
				ArrayList<Stock> stocks = ClientUtils.getStocksByName(conn);
				if (stocks.isEmpty()) {
					content += "You don't got no stock babe";
				} else {
					for (Stock s : stocks) {
						content += s + "<br />";
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "stock_by_symbol":
			try {
				ArrayList<Stock> stocks = ClientUtils.getStocksByName(conn);
				if (stocks.isEmpty()) {
					content += "You don't got no stock babe";
				} else {
					for (Stock s : stocks) {
						content += s + "<br />";
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		default:
			content += "Invalid Operation";
		}

		content += "</h3>";
		request.setAttribute("mainPanel", content);
		// forward
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/MainClient.jsp");
		dispatcher.forward(request, response);
		**/
	}
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("searchNameButton") != null) {
        	String searchText = request.getParameter("searchText");
        	try {
				searchByName(searchText,request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
        if (request.getParameter("searchTypeButton") != null) {
        	String searchText = request.getParameter("searchText");
        	try {
				searchByType(searchText,request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }
    
    public void searchByName(String input, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
    	ArrayList<Stock> result = new ArrayList<Stock>();
		Connection conn = MyUtils.getStoredConnection(request);

    	try {
			result=ClientUtils.searchStocksByName(conn, input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		request.setAttribute("searchResult", result);
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/MainClient.jsp");
		dispatcher.forward(request, response);
    }
    
    public void searchByType(String input, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
    	ArrayList<Stock> result = new ArrayList<Stock>();
		Connection conn = MyUtils.getStoredConnection(request);

    	try {
			result=ClientUtils.searchStocksByType(conn, input);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		request.setAttribute("searchResult", result);
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/MainClient.jsp");
		dispatcher.forward(request, response);
    }
}
