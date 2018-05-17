/**
 * Created by li on 2015/8/3.
 */
window.onload = function() {
	var newSlideSize = function slideSize() {
		var race = 3;
		if ($("#swiper-container-height").val()) {
			race = $("#swiper-container-height").val();
		}
		var w = Math.ceil($(".swiper-container").width() / race/*--调整高度---*/);
		$(".swiper-container,.swiper-wrapper,.swiper-slide").height(w);
	};
	newSlideSize();
	$(window).resize(function() {
		newSlideSize();
	});

	var ispagination = '.pagination';
	var isautoPlay = 5000;
	if ($(".swiper-container").find('img').length <= 1) {
		ispagination = '';
		isautoPlay = 0;
	}

	var mySwiper = new Swiper('.swiper-container', {
		pagination : ispagination,
		loop : true,
		autoplay : isautoPlay,
		autoplayDisableOnInteraction : false,
		paginationClickable : true,
		noSwiping : true,
		onSlideChangeEnd : function(swiper) {
			mySwiper.startAutoplay();
		},
		onTouchEnd : function(swiper) {
			mySwiper.startAutoplay();
		}
	});

};