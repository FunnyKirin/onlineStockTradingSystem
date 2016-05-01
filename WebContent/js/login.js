$(document).ready(function() {
	$(document).on("click", ".login__submit", function() {
		$.get("/clientLogin", function(data) {
			alert(data);
		});
	});
});