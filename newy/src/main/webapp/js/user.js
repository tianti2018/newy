$(function(){
	
  $(".my_account .item .item_t").click(function(){
	  if($(this).hasClass('item_t2')){
		$(this).parent(".item").children(".item_m").slideUp();
		$(this).removeClass("item_t2");
	  }else{
		$(this).parent(".item").children(".item_m").slideDown();
		$(this).addClass("item_t2");
	  }
  });
	
  $(".my_order .item .item_t").click(function(){
	  if($(this).hasClass('item_t2')){
		$(this).parent(".item").children(".item_m").slideUp();
		$(this).removeClass("item_t2");
	  }else{
		$(this).parent(".item").children(".item_m").slideDown();
		$(this).addClass("item_t2");
	  }
  });
  
});