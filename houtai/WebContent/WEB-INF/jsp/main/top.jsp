
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript" src="<%=request.getContextPath()%>/guanwang/js/jquery.1.8.2.min.js" ></script>
<title>无标题文档</title>
<style type="text/css">
<!--
	body {
		margin-left: 0px;
		margin-top: 0px;
		margin-right: 0px;
		margin-bottom: 0px;
	}
	.STYLE1 {
		font-size: 12px;
		color: #000000;
	}
	.STYLE5 {font-size: 12}
	.STYLE7 {font-size: 12px; color: #FFFFFF; }
    .style1
    {
        color: #FFFFFF;
    }
    .style2
    {
        text-decoration: none;
    }
-->

</style>
<script language="javascript" type="text/javascript">

	function exist() {
		var loginPage = '${loginPage}';
		if (confirm("是否决定退出系统")) {
			window.open("login!loginOut.action?flag=flushTwo&loginPage="+loginPage,"_top");
		}
		else {
			return false;
		}
	}


	function winSd() {
		var datetime = new Date();
		var winSettings = "dialogHeight:500px;dialogWidth:700px;status:no;help:no";
		
		var param = "?time="+datetime;
		
		bid = window.showModalDialog("#"+param,'',winSettings);	
		
		//window.showmodaldialog("",);
	}
	function winSdOne() {
		//var datetime = new Date();
		var qqnum = "30438686655";
		var winSettings = "dialogHeight:300px;dialogWidth:400px;status:no;help:no;scroll:no";
		
		var param = "?qqNum="+qqnum;
		
		bid = window.showModalDialog("kefu!kefuonline.action"+param,'',winSettings);	
		
		//window.showmodaldialog("",);
	}
	//滚动插件
	(function($){
		$.fn.extend({
			Scroll:function(opt,callback){
					//参数初始化
					if(!opt) var opt={};
					var _this=this.eq(0).find("ul:first");
					var lineH=_this.find("li:first").height()/2, //获取行高
						line=opt.line?parseInt(opt.line,10):parseInt(this.height()/lineH,10), //每次滚动的行数，默认为一屏，即父容器高度
						speed=opt.speed?parseInt(opt.speed,10):1000, //卷动速度，数值越大，速度越慢（毫秒）
						timer=opt.timer?parseInt(opt.timer,10):5000; //滚动的时间间隔（毫秒）
					if(line==0) line=1;
					var upHeight=0-line*lineH;
					//滚动函数
					scrollUp=function(){
							_this.animate({
									marginTop:upHeight
							},speed,function(){
									for(i=1;i<=line;i++){
											_this.find("li:first").appendTo(_this);
									}
									_this.css({marginTop:0});
							});
					}
					//鼠标事件绑定
					_this.hover(function(){
							clearInterval(timerID);
					},function(){
							timerID=setInterval("scrollUp()",timer);
					}).mouseout();
			}       
		});
	})(jQuery);
		
</script>
</head>

<body>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  <tr>
	    <td height="57" background="images/main_03.gif">
	    	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		      <tr>
		        <td width="378" height="57" background="<c:if test="${loginPage=='2'}">images/main_01.gif</c:if><c:if test="${loginPage=='1'}">images/main_001.gif</c:if>">&nbsp;</td>
		        <td><div id="scrollDiv" style="z-index:9999">
		
</div></td>
		        <td width="281" valign="bottom">
		        	<table width="100%" border="0" cellspacing="0" cellpadding="0">
			          <tr>
			            <td width="33" height="27">
			            	<img src="images/main_05.gif" width="33" height="27" />
			            </td>
			            <td width="248" background="images/main_06.gif">
			            	<table width="225" border="0" align="center" cellpadding="0" cellspacing="0">
				              <tr>
				                <td height="17">
				                	<div align="right">
				                		
				                	</div>
				                </td>
				                <td>
				                	<div align="right">
				                		                						                						                	
				                	</div>
				                </td>
				                <td>
				                	<div align="right">				                		
				                		<img src="images/quit.gif" width="69" height="17" onclick="exist();"/>				                					                	
				                	</div>
				                </td>
				              </tr>
			            	</table>
			            </td>
			          </tr>
		        	</table>
		        </td>
		      </tr>
	    	</table>
	  	</td>
	  	
	  </tr>
	  <tr>
	    <td height="40" background="images/main_10.gif">
		    <table width="100%" border="0" cellspacing="0" cellpadding="0">
		      <tr>
		        <td width="194" height="40" background="images/main_07.gif">&nbsp;</td>
		        <td>
		        	<table width="100%" border="0" cellspacing="0" cellpadding="0">
			          <tr>
			            <td width="21">
			            	<img src="images/main_13.gif" width="19" height="14" />
			            </td>
			            <td width="35" class="STYLE7">
			            	<div align="center">
			            		<a target="_parent" class="style2">
			            			<span class="style1">首页</span>			            						            			
			            		</a>
			            	</div>
			            </td>
			            <td width="70" class="STYLE7">
			            	<div align="center">
			            		<a  class="style2" >
			            		<%-- 	<span class="style1">在线客服</span>	 --%>		            						            			
			            		</a>
			            	</div>
			            </td>
			            <td>&nbsp;</td>
			          </tr>
		        	</table>
		        </td>	        
		        <td width="248" background="images/main_11.gif">
		        	<table width="100%" border="0" cellspacing="0" cellpadding="0">
			          <tr>
			            <td width="16%"><span class="STYLE5"></span></td>
			            <td width="75%">
			            	<div align="center">
			            		<span class="STYLE7">	            			
			            			<script language="JavaScript" type="text/javascript">
													var enabled = 0;
													today = new Date();
													var day;
													var date;
													if (today.getDay() == 0)
														day = " 星期日"
													if (today.getDay() == 1)
														day = " 星期一"
													if (today.getDay() == 2)
														day = " 星期二"
													if (today.getDay() == 3)
														day = " 星期三"
													if (today.getDay() == 4)
														day = " 星期四"
													if (today.getDay() == 5)
														day = " 星期五"
													if (today.getDay() == 6)
														day = " 星期六"
													date = (today.getFullYear()) + "年" + (today.getMonth() + 1) + "月"
															+ today.getDate() + "日" + day + "";
													document.write(date);
													// -->
												</script>
			            		</span>
			            	</div>
			            </td>
			            <td width="9%">&nbsp;</td>
			          </tr>
		        	</table>
		        </td>
		      </tr>
		    </table>
		  </td>
		</tr>
	</table>
	
</body>

</html>
