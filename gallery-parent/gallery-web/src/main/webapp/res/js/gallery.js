(function($) {
	$(document).ready(function() {
		$(".icon-previous").on("click", function() {
			window.location.href = document.referrer;
		});
		
		$(".icon-close").on("click", function() {
			window.close();
		});
	});
})(jQuery);