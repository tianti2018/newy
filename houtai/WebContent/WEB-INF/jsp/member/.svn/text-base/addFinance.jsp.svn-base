<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="/struts-tags" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title></title>
<base target="_self" />
<link href="images/common_res/css/jquery_validate.css" rel="stylesheet" type="text/css"/>
<link href="images/common_res/css/jquery.alerts.css" rel="stylesheet" type="text/css"/>
<link href="images/core_res/css/front.css" rel="stylesheet" type="text/css"/>
<link href="images/core_res/css/admin.css" rel="stylesheet" type="text/css"/>
<link href="images/core_res/css/theme.css" rel="stylesheet" type="text/css"/>
 
<script src="images/common_res/js/jquery.js" type="text/javascript"></script>
<script src="images/common_res/js/jquery_validate.js" type="text/javascript"></script>
<script src="images/common_res/js/pony.js" type="text/javascript"></script>
<script src="images/common_res/js/jquery.ui.draggable.js" type="text/javascript"></script>
<script src="images/common_res/js/jquery.alerts.js" type="text/javascript"></script>
 
<script src="images/core_res/js/front.js" type="text/javascript"></script>
<script src="images/core_res/js/admin.js" type="text/javascript"></script>

<script src="js/common.js" type="text/javascript"></script>

<script type="text/javascript">

var frmRole = null;
function body_onload () {

	frmRole = document.frmRole;
	
	var message = "<c:out value="${message}"/>";
	
	if (message != "" && message!=null) {
		alert(message);		
		if ("提现申请成功"==message && window.dialogArguments!='') {
			window.close();
			window.returnValue = "true";
		}
	}
}

function saveOrUpdate()
{
	
	var tixianMoney = frmRole.tixianMoney.value;
	var bankName = frmRole.bankName.value;
	var openBankAddr = frmRole.openBankAddr.value;
	var account = frmRole.account.value;
	//var accountHolder = frmRole.accountHolder.value;
	
	if (tixianMoney=='') {
		alert("提现金额不能为空");
		frmRole.tixianMoney.focus();
		return false;
	}
	else {
		
		var matchOne = /^\+?[1-9][0-9]*$/;
		if (!matchOne.test(tixianMoney)) {
			alert("对不起，格式不正确，请输入正整数！");
			frmRole.tixianMoney.focus();
			return false;
		}
	}

	if(checkspace(bankName))  {		
		alert("对不起，银行名称不能为空，请重新输入！");
		frmRole.bankName.focus();
		return false;
	}
	
	if(checkspace(openBankAddr))  {		
		alert("对不起，开户行地址不能为空，请重新输入！");
		frmRole.openBankAddr.focus();
		return false;
	}
	
	if(checkspace(account))  {		
		alert("对不起，账户不能为空，请重新输入！");
		frmRole.account.focus();
		return false;
	}
	
// 	if(checkspace(accountHolder))  {		
// 		alert("对不起，账户持有人姓名不能为空，请重新输入！");
// 		frmRole.accountHolder.focus();
// 		return false;
// 	}
			
	frmRole.submit();
}

</script>
 
</head>


<body onload="body_onload();">
<div class="body-box">
<div class="rhead">
		<div class="rpos">您的当前位置: 添加账户</div>
		<div class="clear"></div>
	</div>
<div class="rhead">	
	<div class="clear"></div>
</div>
<form id="frmRole" name="frmRole" method="post" action="user!apptixian.action" >

	<table width="100%" class="pn-ftable" cellpadding="2" cellspacing="1" border="0">
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">提现金额：</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" id="tixianMoney" name="tixianMoney" maxlength="60" size="20"/><font color="red">*</font>
			</td>
		</tr>	
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">银行名称：</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" id="bankName" name="bankName" readonly="readonly" maxlength="60" size="20" value="中国农业银行"/>
			</td>
		</tr>	
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">开户行：</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" id="openBankAddr" name="openBankAddr" value='${tixian.openBankAddr }' maxlength="60" size="20"/><font color="red">*</font>
			</td>
		</tr>
		<tr>
			<td width="20%" class="pn-flabel pn-flabel-h">银行账号：</td>
			<td width="80%" class="pn-fcontent">
				<input type="text" id="account" name="account" maxlength="60" value='${tixian.account }'  size="20"/><font color="red">*</font>
			</td>
		</tr>			
<!-- 		<tr> -->
<!-- 			<td width="20%" class="pn-flabel pn-flabel-h">账户持有人姓名：</td> -->
<!-- 			<td width="80%" class="pn-fcontent"> -->
<%-- 				<input type="text" id="accountHolder" name="accountHolder" value='${tixian.accountHolder}' maxlength="60" size="20"/><font color="red">*</font> --%>
<!-- 			</td> -->
<!-- 		</tr> -->
		<tr>
			<td colspan="2" class="pn-fbutton">
			<input type="button" onclick="return saveOrUpdate();" value="保存"/>&nbsp;&nbsp;&nbsp;<input type="reset" value="重置"/></td>
		</tr>
	</table>
</form>
</div>
</body>
</html>
