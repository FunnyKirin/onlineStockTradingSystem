$('#help').on('click', function (e) {
	$('#dynamic').empty();
	var $content = $("<div class='row'><div class='col-lg-12'><h1 class='page-header'>Help</h1></div></div><div class='ripple'><h2>Problems about the website</h2>" +
			"<p>Please email gofaraway@gmail.com</p><h2>Problems about the transactions</h2><p>Please email liyun@gmail.com</p></div>");
	$('#dynamic').append($content);
});


//<input type="text" name="query" value="${messages.query}">
var $currentStocks = '<div class="row"><div class="col-lg-12"><h1 class="page-header">Current Stocks</h1></div></div>';
var $search = '<form role="search"><div class="form-group"><input type="text" class="form-control" placeholder="Search Stocks"></div></form>';
var $search = '<form role="search"><div class="form-group"><input type="text" class="form-control" placeholder="Search Stocks"></div></form>';
var $search = '<form role="search"><div class="form-group"><input type="text" class="form-control" placeholder="Search Stocks"></div></form>';

$('#currentStocks').on('click', function (e) {
	$('#dynamic').empty();
	$('#dynamic').append($currentStocks);
});
$('#orders').on('click', function (e) {
	$('#dynamic').empty();
	$('#dynamic').append($search);
});
$('#searchStock').on('click', function (e) {
	$('#dynamic').empty();
	$('#dynamic').append($search);
});
$('#Best-sellers').on('click', function (e) {
	$('#dynamic').empty();
	$('#dynamic').append($search);
});
$('#Suggestions').on('click', function (e) {
	$('#dynamic').empty();
	$('#dynamic').append($search);
});