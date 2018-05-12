<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>无标题文档</title>


<style type="text/css">
.divOne {
	font-family: 楷体;
	font-size: 14px;
	color: #FFFFFF;
	height: 45px;
	width: 190px;
	text-align: center
}

.divTwo {
	font-family: 楷体;
	font-size: 14px;
	color: #FFFFFF;
	height: 35px;
	width: 120px;
	text-align: center
}

#a {
	background-color: #f2f4f7;
}
#a1 {
	background-color: #333366;
}
#a2 {
	background-color: #663366;
}


#b {
	background-color: #b3c2d8;
}

#c {
	background-color: #6f8bb5;
}

#d {
	background-color: #4a6690;
}
</style>

<script type="text/javascript">
	function winSd() {
		var datetime = new Date();
		var winSettings = "dialogHeight:500px;dialogWidth:700px;status:no;help:no";
		var userId = arguments[0];
		var parentLoginName = arguments[1];
		if (parentLoginName=='') {
			return false;
		}
		var param = "?time=" + datetime + "&userId=" + userId+"&parentLoginName="+parentLoginName;
		bid = window.showModalDialog("user!initAddUser.action" + param, datetime,winSettings);
		window.location.reload();//
	}
	
	function loadMember() {
		self.location.href="member!loadMember.action?userId="+arguments[0];			
	}
	loadSearch =function () {
		var loginName = document.getElementsByName("cmd_search")[0].value;
		self.location.hre="member!loadMember.action?loginName="+loginName;	
	}
</script>

</head>

<body>
	<form method="post" id="cmdForm" name="cmdForm" action="member!loadMember.action">
		<table width="1200" height="40" border="0" align="center"
			cellpadding="0" cellspacing="0">
			<tr>
				<td>
					
					<input type="text" id="cmd_search" name="cmd_search" />
					<input type="button" id="cmdBtn" name="cmdBtn" value="搜索" onclick="loadSearch();" />
					
				</td>
				<td align="right">
					<input type="button" value="返回上一层" onclick="window.history.go(-1)"/>
				</td>
			</tr>
		</table>
	</form>
	<table width="1200" height="40" border="0" align="center"
		cellpadding="0" cellspacing="0">
		<tr align="center" >
			<td height="60" valign="bottom" align="center">

				<div class="divOne" id="${(user.submitMoney==user.realSubmitMoney)?'d':(user.realSubmitMoney<user.submitMoney&&user.realSubmitMoney==0)?'a1':'a2'}"><font color="white">${user.loginName}</font></div>
				<div id="${(user.submitMoney==user.realSubmitMoney)?'b':(user.realSubmitMoney<user.submitMoney&&user.realSubmitMoney==0)?'a1':'a2'}" class="divOne">
					<c:choose>
						<c:when test="${user.level == '1'}">
							用户
						</c:when>
						<c:when test="${user.level == '2'}">
							银级会员
						</c:when>
						<c:when test="${user.level == '3'}">
							代理
						</c:when>
						<c:when test="${user.level == '4'}">
							钻石会员
						</c:when>
					</c:choose>
				</div>
				<div id="${(user.submitMoney==user.realSubmitMoney)?'c':(user.realSubmitMoney<user.submitMoney&&user.realSubmitMoney==0)?'a1':'a2'}" class="divOne">${user.realSubmitMoney}</div>
				<div id="${(user.submitMoney==user.realSubmitMoney)?'d':(user.realSubmitMoney<user.submitMoney&&user.realSubmitMoney==0)?'a1':'a2'}"  class="divOne">
					总：${totalCount}
					左：${leftCount}|右：${rightCount}|新：${todayCount}
					左剩:${leftCountL} | 右剩:${rightCountL}
				</div>

			</td>
		</tr>
		<tr>
			<td align="center"><img src="images/member/index_02.jpg"
				width="1200" height="80" /></td>
		</tr>
	</table>
	<table width="1200" height="40" border="0" align="center"
		cellpadding="0" cellspacing="0">
		<tr>
			<td width="119">&nbsp;</td>
			<td width="180" valign="top">
				<div id="${(user1.submitMoney==user1.realSubmitMoney)?'d':(user1.realSubmitMoney<user1.submitMoney&&user1.realSubmitMoney==0)?'a1':'a2'}" class="divOne"><a href="javascript:void(0)" onclick="loadMember('${user1.userId}')" target="rightFrame"><font color="white">${user1.loginName}</font></a></div>
				<div id="${(user1.submitMoney==user1.realSubmitMoney)?'b':(user1.realSubmitMoney<user1.submitMoney&&user1.realSubmitMoney==0)?'a1':'a2'}" class="divOne">
					<c:choose>
						<c:when test="${user1.level == '1'}">
							用户
						</c:when>
						<c:when test="${user1.level == '2'}">
							银级会员
						</c:when>
						<c:when test="${user1.level == '3'}">
							代理
						</c:when>
						<c:when test="${user1.level == '4'}">
							钻石会员
						</c:when>
					</c:choose>
				</div>
				<div id="${(user1.submitMoney==user1.realSubmitMoney)?'c':(user1.realSubmitMoney<user1.submitMoney&&user1.realSubmitMoney==0)?'a1':'a2'}" class="divOne">${user1.realSubmitMoney}</div>

				<div id="${(user1.submitMoney==user1.realSubmitMoney)?'d':(user1.realSubmitMoney<user1.submitMoney&&user1.realSubmitMoney==0)?'a1':'a2'}" class="divOne">
					<c:if test="${user1==null}">
						<a href="javascript:void(0);" onclick="winSd('${user1.userId}','${user.loginName}');"><font color="white">注册</font></a>
					</c:if>
					<c:if  test="${user1!=null}">
						总：${totalCount1}
						左：${leftCount1}|右：${rightCount1}|新：${todayCount1}
						左剩:${leftCountL1} | 右剩:${rightCountL1}
					</c:if>
				</div>
			</td>
			<td width="207">&nbsp;</td>
			<td width="180"></td>
			<td width="183">&nbsp;</td>
			<td width="180" bgcolor="#FFCC99">
				<div id="${(user2.submitMoney==user2.realSubmitMoney)?'d':(user2.realSubmitMoney<user2.submitMoney&&user2.realSubmitMoney==0)?'a1':'a2'}"  class="divOne"><a href="javascript:void(0)" onclick="loadMember('${user2.userId}')" target="rightFrame"><font color="white">${user2.loginName}</font></a></div>
				<div id="${(user2.submitMoney==user2.realSubmitMoney)?'b':(user2.realSubmitMoney<user2.submitMoney&&user2.realSubmitMoney==0)?'a1':'a2'}"  class="divOne">
					<c:choose>
						<c:when test="${user2.level == '1'}">
							用户
						</c:when>
						<c:when test="${user2.level == '2'}">
							银级会员
						</c:when>
						<c:when test="${user2.level == '3'}">
							代理
						</c:when>
						<c:when test="${user2.level == '4'}">
							钻石会员
						</c:when>
					</c:choose>
				</div>
				<div id="${(user2.submitMoney==user2.realSubmitMoney)?'c':(user2.realSubmitMoney<user2.submitMoney&&user2.realSubmitMoney==0)?'a1':'a2'}"  class="divOne">${user2.realSubmitMoney}</div>

				<div id="${(user2.submitMoney==user2.realSubmitMoney)?'d':(user2.realSubmitMoney<user2.submitMoney&&user2.realSubmitMoney==0)?'a1':'a2'}"  class="divOne">
					<c:if test="${user2==null}">
						<a href="javascript:void(0);" onclick="winSd('${user2.userId}','${user.loginName}');"><font color="white">注册</font></a>
					</c:if>
					<c:if  test="${user2!=null}">
						总：${totalCount2}
						左：${leftCount2}|右：${rightCount2}|新：${todayCount2}
						左剩:${leftCountL2} | 右剩:${rightCountL2}
					</c:if>
				</div>
			</td>
			<td width="136">&nbsp;</td>
		</tr>
	</table>
	<table width="1200" border="0" align="center" cellpadding="0"
		cellspacing="0">
		<tr>
			<td width="1200"><img src="images/member/index_05.jpg"
				width="1200" /></td>
		</tr>
	</table>
	<table width="1200" height="94" border="0" align="center"
		cellpadding="0" cellspacing="0">
		<tr>
			<td width="8">&nbsp;</td>
			<td width="120" bgcolor="#FFCC99">
				<div id="${(user11.submitMoney==user11.realSubmitMoney)?'d':(user11.realSubmitMoney<user11.submitMoney&&user11.realSubmitMoney==0)?'a1':'a2'}"  class="divOne"><a href="javascript:void(0)" onclick="loadMember('${user11.userId}')" target="rightFrame"><font color="white">${user11.loginName}</font></a></div>
				<div id="${(user11.submitMoney==user11.realSubmitMoney)?'b':(user11.realSubmitMoney<user11.submitMoney&&user11.realSubmitMoney==0)?'a1':'a2'}" class="divOne">
					<c:choose>
					<c:when test="${user11.level == '1'}">
						用户
					</c:when>
					<c:when test="${user11.level == '2'}">
						银级会员
					</c:when>
					<c:when test="${user11.level == '3'}">
						代理
					</c:when>
					<c:when test="${user11.level == '4'}">
						钻石会员
					</c:when>
				</c:choose>
				</div>
				<div id="${(user11.submitMoney==user11.realSubmitMoney)?'c':(user11.realSubmitMoney<user11.submitMoney&&user11.realSubmitMoney==0)?'a1':'a2'}" class="divOne">${user11.realSubmitMoney}</div>
				<div id="${(user11.submitMoney==user11.realSubmitMoney)?'d':(user11.realSubmitMoney<user11.submitMoney&&user11.realSubmitMoney==0)?'a1':'a2'}" class="divOne">
					<c:if test="${user11==null}">
						<a href="javascript:void(0);" onclick="winSd('${user11.userId}','${user1.loginName}');"><font color="white">注册</font></a>
					</c:if>
					<c:if  test="${user11!=null}">
						总：${totalCount11}
						左：${leftCount11}|右：${rightCount11}|新：${todayCount11}
						左剩:${leftCountL11} | 右剩:${rightCountL11}
					</c:if>
				</div>
			</td>
			<td width="16">&nbsp;</td>
			<td width="120"></td>
			<td width="16">&nbsp;</td>
			<td width="120"></td>
			<td width="16">&nbsp;</td>
			<td width="120">
				<div id="${(user12.submitMoney==user12.realSubmitMoney)?'d':(user12.realSubmitMoney<user12.submitMoney&&user12.realSubmitMoney==0)?'a1':'a2'}" class="divOne"><a href="javascript:void(0)" onclick="loadMember('${user12.userId}')" target="rightFrame"><font color="white">${user12.loginName}</font></a></div>
				<div id="${(user12.submitMoney==user12.realSubmitMoney)?'b':(user12.realSubmitMoney<user12.submitMoney&&user12.realSubmitMoney==0)?'a1':'a2'}" class="divOne">
					<c:choose>
					<c:when test="${user12.level == '1'}">
						用户
					</c:when>
					<c:when test="${user12.level == '2'}">
						银级会员
					</c:when>
					<c:when test="${user12.level == '3'}">
						代理
					</c:when>
					<c:when test="${user12.level == '4'}">
						钻石会员
					</c:when>
				</c:choose>
				</div>
				<div id="${(user12.submitMoney==user12.realSubmitMoney)?'c':(user12.realSubmitMoney<user12.submitMoney&&user12.realSubmitMoney==0)?'a1':'a2'}"class="divOne">${user12.realSubmitMoney}</div>
				<div id="${(user12.submitMoney==user12.realSubmitMoney)?'d':(user12.realSubmitMoney<user12.submitMoney&&user12.realSubmitMoney==0)?'a1':'a2'}" class="divOne">
					<c:if test="${user12==null}">
						<a href="javascript:void(0);" onclick="winSd('${user12.userId}','${user1.loginName}');"><font color="white">注册</font></a>
					</c:if>
					<c:if  test="${user12!=null}">
						总：${totalCount12}
						左：${leftCount12}|右：${rightCount12}|新：${todayCount12}
						左剩:${leftCountL12} | 右剩:${rightCountL12}
					</c:if> 
				</div>
			</td>
			<td width="16"></td>
			<td width="120"></td>
			<td width="16">&nbsp;</td>
			<td width="120"></td>
			<td width="16">
				<div id="${(user21.submitMoney==user21.realSubmitMoney)?'d':(user21.realSubmitMoney<user21.submitMoney&&user21.realSubmitMoney==0)?'a1':'a2'}" class="divOne"><a href="javascript:void(0)" onclick="loadMember('${user21.userId}')" target="rightFrame"><font color="white">${user21.loginName}</font></a></div>
				<div id="${(user21.submitMoney==user21.realSubmitMoney)?'b':(user21.realSubmitMoney<user21.submitMoney&&user21.realSubmitMoney==0)?'a1':'a2'}" class="divOne">
					<c:choose>
						<c:when test="${user21.level == '1'}">
							用户
						</c:when>
						<c:when test="${user21.level == '2'}">
							银级会员
						</c:when>
						<c:when test="${user21.level == '3'}">
							代理
						</c:when>
						<c:when test="${user21.level == '4'}">
							钻石会员
						</c:when>
					</c:choose>
				</div>
				<div id="${(user21.submitMoney==user21.realSubmitMoney)?'c':(user21.realSubmitMoney<user21.submitMoney&&user21.realSubmitMoney==0)?'a1':'a2'}" class="divOne">${user21.realSubmitMoney}</div>
				<div id="${(user21.submitMoney==user21.realSubmitMoney)?'d':(user21.realSubmitMoney<user21.submitMoney&&user21.realSubmitMoney==0)?'a1':'a2'}" class="divOne">
					<c:if test="${user21==null}">
						<a href="javascript:void(0);" onclick="winSd('${user21.userId}','${user2.loginName}');"><font color="white">注册</font></a>
					</c:if>
					<c:if  test="${user21!=null}">
						总：${totalCount21}
						左：${leftCount21}|右：${rightCount21}|新：${todayCount21}
						左剩:${leftCountL21} | 右剩:${rightCountL21}
					</c:if> 
				</div>
			</td>
			<td width="120"></td>
			<td width="16">&nbsp;</td>
			<td width="120"></td>
			<td width="16">&nbsp;</td>
			<td width="120" bgcolor="#FFCC99">
				<div id="${(user22.submitMoney==user22.realSubmitMoney)?'d':(user22.realSubmitMoney<user22.submitMoney&&user22.realSubmitMoney==0)?'a1':'a2'}"  class="divOne"><a href="javascript:void(0)" onclick="loadMember('${user22.userId}')" target="rightFrame"><font color="white">${user22.loginName}</font></a></div>
				<div id="${(user22.submitMoney==user22.realSubmitMoney)?'b':(user22.realSubmitMoney<user22.submitMoney&&user22.realSubmitMoney==0)?'a1':'a2'}" class="divOne">
					<c:choose>
						<c:when test="${user22.level == '1'}">
							用户
						</c:when>
						<c:when test="${user22.level == '2'}">
							银级会员
						</c:when>
						<c:when test="${user22.level == '3'}">
							代理
						</c:when>
						<c:when test="${user22.level == '4'}">
							钻石会员
						</c:when>
					</c:choose>
				</div>
				<div id="${(user22.submitMoney==user22.realSubmitMoney)?'c':(user22.realSubmitMoney<user22.submitMoney&&user22.realSubmitMoney==0)?'a1':'a2'}" class="divOne">${user22.realSubmitMoney}</div>
				<div id="${(user22.submitMoney==user22.realSubmitMoney)?'d':(user22.realSubmitMoney<user22.submitMoney&&user22.realSubmitMoney==0)?'a1':'a2'}" class="divOne">
					<c:if test="${user22==null}">
						<a href="javascript:void(0);" onclick="winSd('${user22.userId}','${user2.loginName}');"><font color="white">注册</font></a>
					</c:if>
					<c:if  test="${user22!=null}">
						总：${totalCount22}
						左：${leftCount22}|右：${rightCount22}|新：${todayCount22}
						左剩:${leftCountL22} | 右剩:${rightCountL22}
					</c:if>
				</div>
			</td>
			<td width="16">&nbsp;</td>
		</tr>
	</table>
	
	<%-- 
	<table width="1200" border="0" align="center" cellpadding="0"
		cellspacing="0">
		<tr>
			<td width="1200"><img src="images/member/index_010.jpg"
				width="1200" /></td>
		</tr>
	</table>
	--%>
	
	<%--
	<table width="1200" height="94" border="0" align="center"
		cellpadding="0" cellspacing="0">
		<tr>
			<td width="10">
				<div  id="${(user11L.submitMoney==user11L.realSubmitMoney)?'d':(user11L.realSubmitMoney<user11L.submitMoney&&user11L.realSubmitMoney==0)?'a1':'a2'}"  class="divOne"><a href="javascript:void(0)" onclick="loadMember('${user11L.userId}')" target="rightFrame">${user11L.loginName}</a></div>
				<div id="${(user11L.submitMoney==user11L.realSubmitMoney)?'b':(user11L.realSubmitMoney<user11L.submitMoney&&user11L.realSubmitMoney==0)?'a1':'a2'}" class="divOne">
					<c:choose>
						<c:when test="${user11L.level == '1'}">
							铜级会员
						</c:when>
						<c:when test="${user11L.level == '2'}">
							银级会员
						</c:when>
						<c:when test="${user11L.level == '3'}">
							金级会员
						</c:when>
						<c:when test="${user11L.level == '4'}">
							钻石会员
						</c:when>
					</c:choose>
				</div>
				<div id="${(user11L.submitMoney==user11L.realSubmitMoney)?'c':(user11L.realSubmitMoney<user11L.submitMoney&&user11L.realSubmitMoney==0)?'a1':'a2'}" class="divOne">${user11L.submitMoney}</div>
				<div id="${(user11L.submitMoney==user11L.realSubmitMoney)?'d':(user11L.realSubmitMoney<user11L.submitMoney&&user11L.realSubmitMoney==0)?'a1':'a2'}" class="divOne">
					<c:if test="${user11L==null}">
						<a href="javascript:void(0);" onclick="winSd('${user11L.userId}');">注册</a>
					</c:if>
				</div>
			</td>
			<td width="5">&nbsp;</td>
			<td width="">
				<div  id="${(user11R.submitMoney==user11R.realSubmitMoney)?'d':(user11R.realSubmitMoney<user11R.submitMoney&&user11R.realSubmitMoney==0)?'a1':'a2'}"  class="divOne"><a href="javascript:void(0)" onclick="loadMember('${user11R.userId}')" target="rightFrame">${user11R.loginName}</a></div>
				<div  id="${(user11R.submitMoney==user11R.realSubmitMoney)?'b':(user11R.realSubmitMoney<user11R.submitMoney&&user11R.realSubmitMoney==0)?'a1':'a2'}"  class="divOne">
					<c:choose>
						<c:when test="${user11R.level == '1'}">
							铜级会员
						</c:when>
						<c:when test="${user11R.level == '2'}">
							银级会员
						</c:when>
						<c:when test="${user11R.level == '3'}">
							金级会员
						</c:when>
						<c:when test="${user11R.level == '4'}">
							钻石会员
						</c:when>
					</c:choose>
				</div>
				<div id="${(user11R.submitMoney==user11R.realSubmitMoney)?'c':(user11R.realSubmitMoney<user11R.submitMoney&&user11R.realSubmitMoney==0)?'a1':'a2'}" class="divOne">${user11R.submitMoney}</div>
				<div  id="${(user11R.submitMoney==user11R.realSubmitMoney)?'d':(user11R.realSubmitMoney<user11R.submitMoney&&user11R.realSubmitMoney==0)?'a1':'a2'}"  class="divOne">
					<c:if test="${user11R==null}">
						<a href="javascript:void(0);" onclick="winSd('${user11R.userId}');">注册</a>
					</c:if>
				</div>
			</td>
			<td width="5">&nbsp;</td>
			<td width="">
				<div id="${(user12L.submitMoney==user12L.realSubmitMoney)?'d':(user12L.realSubmitMoney<user12L.submitMoney&&user12L.realSubmitMoney==0)?'a1':'a2'}"  class="divOne"><a href="javascript:void(0)" onclick="loadMember('${user12L.userId}')" target="rightFrame">${user12L.loginName}</a></div>
				<div  id="${(user12L.submitMoney==user12L.realSubmitMoney)?'b':(user12L.realSubmitMoney<user12L.submitMoney&&user12L.realSubmitMoney==0)?'a1':'a2'}"  class="divOne">
					<c:choose>
						<c:when test="${user12L.level == '1'}">
							铜级会员
						</c:when>
						<c:when test="${user12L.level == '2'}">
							银级会员
						</c:when>
						<c:when test="${user12L.level == '3'}">
							金级会员
						</c:when>
						<c:when test="${user12L.level == '4'}">
							钻石会员
						</c:when>
					</c:choose>
				</div>
				<div  id="${(user12L.submitMoney==user12L.realSubmitMoney)?'c':(user12L.realSubmitMoney<user12L.submitMoney&&user12L.realSubmitMoney==0)?'a1':'a2'}"  class="divOne">${user12L.submitMoney}</div>
				<div  id="${(user12L.submitMoney==user12L.realSubmitMoney)?'d':(user12L.realSubmitMoney<user12L.submitMoney&&user12L.realSubmitMoney==0)?'a1':'a2'}"  class="divOne">
					<c:if test="${user12L==null}">
						<a href="javascript:void(0);" onclick="winSd('${user12L.userId}');">注册</a>
					</c:if>
				</div>
			</td>
			<td width="5">&nbsp;</td>
			<td width="">
				<div id="${(user12R.submitMoney==user12R.realSubmitMoney)?'d':(user12R.realSubmitMoney<user12R.submitMoney&&user12R.realSubmitMoney==0)?'a1':'a2'}"  class="divOne"><a href="javascript:void(0)" onclick="loadMember('${user12R.userId}')" target="rightFrame">${user12R.loginName}</a></div>
				<div id="${(user12R.submitMoney==user12R.realSubmitMoney)?'b':(user12R.realSubmitMoney<user12R.submitMoney&&user12R.realSubmitMoney==0)?'a1':'a2'}"  class="divOne">
					<c:choose>
						<c:when test="${user12R.level == '1'}">
							铜级会员
						</c:when>
						<c:when test="${user12R.level == '2'}">
							银级会员
						</c:when>
						<c:when test="${user12R.level == '3'}">
							金级会员
						</c:when>
						<c:when test="${user12R.level == '4'}">
							钻石会员
						</c:when>
					</c:choose>
				</div>
				<div id="${(user12R.submitMoney==user12R.realSubmitMoney)?'c':(user12R.realSubmitMoney<user12R.submitMoney&&user12R.realSubmitMoney==0)?'a1':'a2'}"  class="divOne">${user12R.submitMoney}</div>
				<div id="${(user12R.submitMoney==user12R.realSubmitMoney)?'d':(user12R.realSubmitMoney<user12R.submitMoney&&user12R.realSubmitMoney==0)?'a1':'a2'}"  class="divOne">
					<c:if test="${user12R==null}">
						<a href="javascript:void(0);" onclick="winSd('${user12R.userId}');">注册</a>
					</c:if>
				</div>
			</td>

			<td width="5">&nbsp;</td>
			<td width="">
				<div id="${(user21L.submitMoney==user21L.realSubmitMoney)?'d':(user21L.realSubmitMoney<user21L.submitMoney&&user21L.realSubmitMoney==0)?'a1':'a2'}"  class="divOne"><a href="javascript:void(0)" onclick="loadMember('${user21L.userId}')" target="rightFrame">${user21L.loginName}</a></div>
				<div id="${(user21L.submitMoney==user21L.realSubmitMoney)?'b':(user21L.realSubmitMoney<user21L.submitMoney&&user21L.realSubmitMoney==0)?'a1':'a2'}"  class="divOne">
					<c:choose>
						<c:when test="${user21L.level == '1'}">
							铜级会员
						</c:when>
						<c:when test="${user21L.level == '2'}">
							银级会员
						</c:when>
						<c:when test="${user21L.level == '3'}">
							金级会员
						</c:when>
						<c:when test="${user21L.level == '4'}">
							钻石会员
						</c:when>
					</c:choose>
				</div>
				<div id="${(user21L.submitMoney==user21L.realSubmitMoney)?'c':(user21L.realSubmitMoney<user21L.submitMoney&&user21L.realSubmitMoney==0)?'a1':'a2'}"  class="divOne">${user21L.submitMoney}</div>
				<div id="${(user21L.submitMoney==user21L.realSubmitMoney)?'d':(user21L.realSubmitMoney<user21L.submitMoney&&user21L.realSubmitMoney==0)?'a1':'a2'}"  class="divOne">
					<c:if test="${user21L==null}">
						<a href="javascript:void(0);" onclick="winSd('${user21L.userId}');">注册</a>
					</c:if>
				</div>
			</td>
			<td width="5">&nbsp;</td>
			<td width="">
				<div id="${(user21R.submitMoney==user21R.realSubmitMoney)?'d':(user21R.realSubmitMoney<user21R.submitMoney&&user21R.realSubmitMoney==0)?'a1':'a2'}"  class="divOne"><a href="javascript:void(0)" onclick="loadMember('${user21R.userId}')" target="rightFrame">${user21R.loginName}</a></div>
				<div id="${(user21R.submitMoney==user21R.realSubmitMoney)?'b':(user21R.realSubmitMoney<user21R.submitMoney&&user21R.realSubmitMoney==0)?'a1':'a2'}"  class="divOne">
					<c:choose>
						<c:when test="${user21R.level == '1'}">
							铜级会员
						</c:when>
						<c:when test="${user21R.level == '2'}">
							银级会员
						</c:when>
						<c:when test="${user21R.level == '3'}">
							金级会员
						</c:when>
						<c:when test="${user21R.level == '4'}">
							钻石会员
						</c:when>
					</c:choose>
				</div>
				<div id="${(user21R.submitMoney==user21R.realSubmitMoney)?'c':(user21R.realSubmitMoney<user21R.submitMoney&&user21R.realSubmitMoney==0)?'a1':'a2'}"  class="divOne">${user21R.submitMoney}</div>
				<div id="${(user21R.submitMoney==user21R.realSubmitMoney)?'d':(user21R.realSubmitMoney<user21R.submitMoney&&user21R.realSubmitMoney==0)?'a1':'a2'}"  class="divOne">
					<c:if test="${user21R==null}">
						<a href="javascript:void(0);" onclick="winSd('${user21R.userId}');">注册</a>
					</c:if>
				</div>
			</td>
			<td width="5">&nbsp;</td>
			<td width="">
				<div  id="${(user22L.submitMoney==user22L.realSubmitMoney)?'d':(user22L.realSubmitMoney<user22L.submitMoney&&user22L.realSubmitMoney==0)?'a1':'a2'}"  class="divOne"><a href="javascript:void(0)" onclick="loadMember('${user22L.userId}')" target="rightFrame">${user22L.loginName}</a></div>
				<div id="${(user22L.submitMoney==user22L.realSubmitMoney)?'b':(user22L.realSubmitMoney<user22L.submitMoney&&user22L.realSubmitMoney==0)?'a1':'a2'}"  class="divOne">
					<c:choose>
						<c:when test="${user22L.level == '1'}">
							铜级会员
						</c:when>
						<c:when test="${user22L.level == '2'}">
							银级会员
						</c:when>
						<c:when test="${user22L.level == '3'}">
							金级会员
						</c:when>
						<c:when test="${user22L.level == '4'}">
							钻石会员
						</c:when>
					</c:choose>
				</div>
				<div id="${(user22L.submitMoney==user22L.realSubmitMoney)?'c':(user22L.realSubmitMoney<user22L.submitMoney&&user22L.realSubmitMoney==0)?'a1':'a2'}"  class="divOne">${user22L.submitMoney}</div>
				<div id="${(user22L.submitMoney==user22L.realSubmitMoney)?'d':(user22L.realSubmitMoney<user22L.submitMoney&&user22L.realSubmitMoney==0)?'a1':'a2'}"  class="divOne">
					<c:if test="${user22L==null}">
						<a href="javascript:void(0);" onclick="winSd('${user22L.userId}');">注册</a>
					</c:if>
				</div>
			</td>
			<td width="5">&nbsp;</td>
			<td width="">
				<div id="${(user22R.submitMoney==user22R.realSubmitMoney)?'d':(user22R.realSubmitMoney<user22R.submitMoney&&user22R.realSubmitMoney==0)?'a1':'a2'}" class="divOne"><a href="javascript:void(0)" onclick="loadMember('${user22R.userId}')" target="rightFrame">${user22R.loginName}</a></div>
				<div  id="${(user22R.submitMoney==user22R.realSubmitMoney)?'b':(user22R.realSubmitMoney<user22R.submitMoney&&user22R.realSubmitMoney==0)?'a1':'a2'}"  class="divOne">
					<c:choose>
						<c:when test="${user22R.level == '1'}">
							铜级会员
						</c:when>
						<c:when test="${user22R.level == '2'}">
							银级会员
						</c:when>
						<c:when test="${user22R.level == '3'}">
							金级会员
						</c:when>
						<c:when test="${user22R.level == '4'}">
							钻石会员
						</c:when>
					</c:choose>
				</div>
				<div  id="${(user22R.submitMoney==user22R.realSubmitMoney)?'c':(user22R.realSubmitMoney<user22R.submitMoney&&user22R.realSubmitMoney==0)?'a1':'a2'}"  class="divOne">${user22R.submitMoney}</div>
				<div  id="${(user22R.submitMoney==user22R.realSubmitMoney)?'d':(user22R.realSubmitMoney<user22R.submitMoney&&user22R.realSubmitMoney==0)?'a1':'a2'}" class="divOne">
					<c:if test="${user22R==null}">
						<a href="javascript:void(0);" onclick="winSd('${user22R.userId}');"><font color="red">注册</font></a>
					</c:if>
				</div>
			</td>
		</tr>
	</table>
	--%>
	
</body>
</html>
