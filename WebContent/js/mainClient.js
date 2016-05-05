var currentStocks = $('.currentStocks'), $currentStocks = currentStocks.clone (true);
var trailingHistory = $('.trailingHistory'), $trailingHistory = trailingHistory.clone (true);
var hiddenHistory = $('.hiddenHistory'), $hiddenHistory = hiddenHistory.clone (true);
var stockPriceHis = $('.stockPriceHis'), $stockPriceHis = stockPriceHis.clone (true);
var orderHis = $('.orderHistories'), $orderHis = orderHis.clone (true);
var bestSellers = $('.bestSellers'), $bestSellers = bestSellers.clone (true);
var suggestion = $('.suggestion'), $suggestion = suggestion.clone (true);
var search = $('.searchResult'), $search = search.clone(true);

var $currentStocksHeader = '<div class="row"><div class="col-lg-12"><h1 class="page-header">Current Stocks</h1></div></div>';
var $trailingHeader = '<div class="row"><div class="col-lg-12"><h1 class="page-header">Trailing-stop History</h1></div></div>';
var $hiddenHeader = '<div class="row"><div class="col-lg-12"><h1 class="page-header">Hidden-stop History</h1></div></div>';

var $help = $("<div class='row'><div class='col-lg-12'><h1 class='page-header'>Help</h1></div></div><div class='ripple'><h2>Problems about the website</h2>" +
"<p>Please email gofaraway@gmail.com</p><h2>Problems about the transactions</h2><p>Please email liyun@gmail.com</p></div>");

$(document).ready(function() {
	currentStocks.remove();
	trailingHistory.remove();
	hiddenHistory.remove();
	stockPriceHis.remove();
	orderHis.remove();
	bestSellers.remove();
	suggestion.remove();
	search.remove();
});
$('#currentStocks').on('click', function (e) {
	$('#dynamic').empty();
	$('#dynamic').append($currentStocksHeader);
	$('#dynamic').append($currentStocks);
});
function getStopHis(order) {
	$('#dynamic').empty();
	return order;
}
$('#trailingHistory').on('click', function (e) {
	$('#dynamic').empty();
	$('#dynamic').append($trailingHistory);
});
$('#hiddenHistory').on('click', function (e) {
	$('#dynamic').empty();
	$('#dynamic').append($hiddenHistory);
});
$('#stockPriceHis').on('click', function (e) {
	$('#dynamic').empty();
	$('#dynamic').append($datePicker);
});
$('#orderHistories').on('click', function (e) {
	$('#dynamic').empty();
	$('#dynamic').append($orderHis);
});
$('#stockByType').on('click', function (e) {
	$('#dynamic').empty();
	$('#dynamic').append($datePicker);
});
$('#searchStock').on('click', function (e) {
	$('#dynamic').empty();
	$('#dynamic').append($search);
});
$('#Best-sellers').on('click', function (e) {
	$('#dynamic').empty();
	$('#dynamic').append($bestSellers);
});
$('#Suggestions').on('click', function (e) {
	$('#dynamic').empty();
	$('#dynamic').append($suggestion);
});
$('#help').on('click', function (e) {
	$('#dynamic').empty();
	$('#dynamic').append($help);
});