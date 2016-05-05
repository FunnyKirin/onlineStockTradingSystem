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

import webapp.beans.Account;
import webapp.beans.Client;
import webapp.beans.History;
import webapp.beans.OrderHistory;
import webapp.beans.Stock;
import webapp.beans.hasStock;
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
	}
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	Connection conn = MyUtils.getStoredConnection(request);
		HttpSession session= request.getSession();
		Account thisClient = (Account)MyUtils.getLoginedUser(session);
		int Id= thisClient.getId();
		ArrayList<hasStock> currentStocks;
		ArrayList<History> orders;
		ArrayList<Stock> bestSellers;
		ArrayList<Stock> suggestions;
    	try{
    		
    	
		currentStocks = ClientUtils.getCurrentStocks(conn, Id);
		//request.setAttribute("stocks", currentStocks);
		//order history
		orders = ClientUtils.getOrderHistory(conn, Id);
		//request.setAttribute("orders", orderHistory);
		//bestSeller
		bestSellers=ClientUtils.getBestSellers(conn);
	//	request.setAttribute("bestSellers", bestSeller);
		//suggestion
		suggestions = ClientUtils.getStockSuggestions(conn, Id);
//		request.setAttribute("suggestions", suggestion);

    	//ArrayList<Stock> currentStocks = (ArrayList<Stock>)request.getAttribute("stocks");
    	//ArrayList<Stock> orders = (ArrayList<Stock>)request.getAttribute("orders");
    	//ArrayList<Stock> bestSellers = (ArrayList<Stock>)request.getAttribute("bestSellers");
    	//ArrayList<Stock> suggestions = (ArrayList<Stock>)request.getAttribute("suggestions");

    	request.setAttribute("stocks", currentStocks);
    	request.setAttribute("orders", orders);
    	request.setAttribute("bestSellers", bestSellers);
    	request.setAttribute("suggestions", suggestions);
    	}	 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
        
        if (request.getParameter("searchHistoryButton") != null) {
        	String searchText = request.getParameter("searchHistoryText");
        	try {
        		searchHistory(Integer.parseInt(searchText),request, response);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        if (request.getParameter("searchStockHistoryButton") != null) {
        	String searchText = request.getParameter("searchStockHistoryText");
        	try {
        		StockHistory(searchText,request, response);

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
    
    public void searchHistory(int input, HttpServletRequest request, HttpServletResponse response)
    	throws ServletException, IOException, SQLException {
    	ArrayList<OrderHistory> result = new ArrayList<OrderHistory>();
		Connection conn = MyUtils.getStoredConnection(request);
    	try {
			result=ClientUtils.searchOrderHistory(input, conn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.out.println("out:"+result.get(0).getDate());
		request.setAttribute("OrderHistorys", result);
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/MainClient.jsp");
		dispatcher.forward(request, response);
		
    }
    
    public void StockHistory(String input, HttpServletRequest request, HttpServletResponse response)
        	throws ServletException, IOException, SQLException {
    
    	ArrayList<OrderHistory> result = new ArrayList<OrderHistory>();
    	Connection conn = MyUtils.getStoredConnection(request);
    	try {
			result=ClientUtils.StockHistory(input, conn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("StockHistory", result);
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/MainClient.jsp");
		dispatcher.forward(request, response);
    }
}
