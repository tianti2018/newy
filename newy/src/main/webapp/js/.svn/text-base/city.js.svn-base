$(function(){
	
  $(".city-nav a").click(function(){
	  $(this).parent(".city-nav").children("a").removeClass("hover");
	  $(this).addClass("hover");
	  var href = $(this).attr("href");
	  var pos = $(href).offset().top - $("#header").height();
	  $("html,body").stop().animate({scrollTop: pos},500,"swing");
	  return false;
  });
  
});