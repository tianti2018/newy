var t;
	$(".banner img").hide();
	$(".banner img:first").show();
	var n = 0;
	var xx = 0;

	function showImg() {
		if (n < $(".banner img").length - 1) {
			n = n + 1;
		} else {
			n = 0;
		}
		//alert(n);
		$(".banner img").hide();
		$(".banner img").eq(n).fadeIn(1000);
		//$(".banner img").eq(n).show();			
	}
	var interval = setInterval(showImg, 4000);
	function shows(num) {
		if (xx != num) {
			showImg();
			xx = num;
			clearInterval(interval);
			interval = setInterval(showImg, 4000);
		}
	}