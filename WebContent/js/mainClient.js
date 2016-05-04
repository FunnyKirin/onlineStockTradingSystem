var $currentStocks = $('.currentStocks'), $saved = $currentStocks.clone (true);
var $currentStocksHeader = '<div class="row"><div class="col-lg-12"><h1 class="page-header">Current Stocks</h1></div></div>';
var $date = $('#datePicker'), $datePicker = $date.clone (true);

var $search = '<form role="search"><div class="form-group"><input type="text" class="form-control" placeholder="Search Stocks"></div></form>';
var $search = '<form role="search"><div class="form-group"><input type="text" class="form-control" placeholder="Search Stocks"></div></form>';
var $search = '<form role="search"><div class="form-group"><input type="text" class="form-control" placeholder="Search Stocks"></div></form>';
var $help = $("<div class='row'><div class='col-lg-12'><h1 class='page-header'>Help</h1></div></div><div class='ripple'><h2>Problems about the website</h2>" +
"<p>Please email gofaraway@gmail.com</p><h2>Problems about the transactions</h2><p>Please email liyun@gmail.com</p></div>");

$(document).ready(function() {
	$currentStocks.remove();
});
$(function() {
    $('input[name="daterange"]').daterangepicker();
});
$('#currentStocks').on('click', function (e) {
	$('#dynamic').empty();
	$('#dynamic').append($currentStocksHeader);
	$('#dynamic').append($saved);
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