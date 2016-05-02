$('#help').on('click', function (e) {
	$('#dynamic').empty();
	var $content = $("<div class='row'><div class='col-lg-12'><h1 class='page-header'>Help</h1></div></div><div class='ripple'><h2>Problems about the website</h2>" +
			"<p>Please email gofaraway@gmail.com</p><h2>Problems about the transactions</h2><p>Please email liyun@gmail.com</p></div>");
	$('#dynamic').append($content);
});