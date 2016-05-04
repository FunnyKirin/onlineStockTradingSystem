var currentStocks = $('.currentStocks'), $currentStocks = currentStocks.clone (true);
var datePicker = $('#datePicker'), $datePicker = datePicker.clone (true);
var $date = $('#datePicker'), $datePicker = $date.clone (true);
var $date = $('#datePicker'), $datePicker = $date.clone (true);
var $date = $('#datePicker'), $datePicker = $date.clone (true);
var $date = $('#datePicker'), $datePicker = $date.clone (true);
var $date = $('#datePicker'), $datePicker = $date.clone (true);
var bestSellers = $('.bestSellers'), $bestSellers = bestSellers.clone (true);
var suggestion = $('.suggestion'), $suggestion = suggestion.clone (true);
var search = $('.search'), $search = search.clone(true);

var $currentStocksHeader = '<div class="row"><div class="col-lg-12"><h1 class="page-header">Current Stocks</h1></div></div>';
var $trailingHeader = '<div class="row"><div class="col-lg-12"><h1 class="page-header">Trailing-stop History</h1></div></div>';
var $hiddenHeader = '<div class="row"><div class="col-lg-12"><h1 class="page-header">Hidden-stop History</h1></div></div>';

var $help = $("<div class='row'><div class='col-lg-12'><h1 class='page-header'>Help</h1></div></div><div class='ripple'><h2>Problems about the website</h2>" +
"<p>Please email gofaraway@gmail.com</p><h2>Problems about the transactions</h2><p>Please email liyun@gmail.com</p></div>");

$(document).ready(function() {
	currentStocks.remove();
	datePicker.remove();
	bestSellers.remove();
	suggestion.remove();
	search.remove();
});
$('#currentStocks').on('click', function (e) {
	$('#dynamic').empty();
	$('#dynamic').append($currentStocksHeader);
	$('#dynamic').append($currentStocks);
});
$('#orders').on('click', function (e) {
	$('#dynamic').empty();
	$('#dynamic').append($datePicker);
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
$('#help').on('click', function (e) {
	$('#dynamic').empty();
	$('#dynamic').append($help);
});