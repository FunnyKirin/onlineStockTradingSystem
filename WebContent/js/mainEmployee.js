var $help = $("<div class='row'><div class='col-lg-12'><h1 class='page-header'>Help</h1></div></div><div class='ripple'><h2>Problems about the website</h2>" +
"<p>Please email gofaraway@gmail.com</p><h2>Problems about the transactions</h2><p>Please email liyun@gmail.com</p>" +
"<h2>Mailing List</h2><p>Produce customer mailing lists</p>" +
"<h2>Give Suggestion</h2><p>Produce a list of stock suggestions for a given customer (based on that customer's past orders)</p>" +
"<h2>Customer Info</h2><p>Give information of customers</p>" +
"<h2>Record an Order</h2><p>Record an Order</p>" +
"<h2>Review Orders</h2><p>Give information about the orders</p>" +
"</div>");

$('#mailing').on('click', function (e) {
	$('#dynamic').empty();
	var $content = $("<div>${mailingList}</div>");
	$('#dynamic').append($content);
});
$('#help').on('click', function (e) {
	$('#dynamic').empty();
	$('#dynamic').append($help);
});
var $helpMan = $("<div class='row'><div class='col-lg-12'><h1 class='page-header'>Help</h1></div></div><div class='ripple'><h2>Problems about the website</h2>" +
		"<p>Please email gofaraway@gmail.com</p><h2>Problems about the transactions</h2><p>Please email liyun@gmail.com</p>" +
		"<h2>Stocks by Customer Name</h2><p>Stocks list by the customer name</p>" +
		"<h2>Stocks by Stock Symbol</h2><p>Stocks list by the stock symbol</p>" +
		"<h2>Best Employee</h2><p>Get the best employee</p>" +
		"<h2>Best Client</h2><p>Get the best client</p>" +
		"<h2>Employee Info</h2><p>Give information about the employees</p>" +
		"<h2>List Stocks</h2><p>List information about the stocks</p>" +
		"<h2>Actively Traded Stocks</h2><p>Give the actively traded stocks</p>" +
		"<h2>Get Mac Revenue by Stock Type</h2><p>Get Mac Revenue by Stock Type</p>" +
		"<h2>Get Max Revenue by Stock Symbol</h2><p>Get Max Revenue by Stock Symbol</p>" +
		"<h2>Get Max Revenue by Customer ID</h2><p>Get Max Revenue by Customer ID</p>" +
		"<h2>Backup database</h2><p>Back up the database files</p>" +
		"</div>");

		$('#helpMan').on('click', function (e) {
			$('#dynamic').empty();
			$('#dynamic').append($helpMan);
		});