package webapp.beans;

public class Trade {
	private Account account;
	private Employee employee;
	private Order order;
	private Stock stock;
	private Transaction transaction;

	public Trade() {

	}

	public Trade(Account account, Employee employee, Order order, Stock stock, Transaction transaction) {
		super();
		this.account = account;
		this.employee = employee;
		this.order = order;
		this.stock = stock;
		this.transaction = transaction;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private int id;
}