$('#mailing').on('click', function (e) {
	$('#dynamic').empty();
	var $content = $("<div>${mailingList}</div>");
	$('#dynamic').append($content);
});