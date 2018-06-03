<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>list</title>
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

<script language="javascript" type="text/javascript">

	function body_onload () {
		
		var message = "<c:out value="${message}"/>";
		
		if (message != "" && message!=null) {
			alert(message);					
		}
	}

	function addProduct() {
		self.location.href="products!initAdd.action";			
	}
			
	function deleteProduct() {
		if(confirm('您确定删除吗？')) {

			self.location.href="products!delete.action?productId="+arguments[0];	
			
			return true;	
		}
		return false;
	
	}
	function yulan(productId){
		window.open("products!product.action?productId="+productId, "newwindow","height=600,width=400,status=no");
	}
	
</script>


</head>
<body onload="body_onload();">
	<div class="body-box">
	<div class="rhead">
		<div class="rpos">您的当前位置: 商品管理</div>
		<div class="clear"></div>
	</div>
	<div class="rhead">
		<form class="ropt" method="post">
			
	&nbsp; <input type="button" value="新增商品" onclick="addProduct();"/>
			
		</form>
		<div class="clear"></div>
	</div>
	<table class="pn-ltable" width="100%" cellspacing="1" cellpadding="0" border="0">
		<thead class="pn-lthead">
		<tr>
			<th width="32">序号</th>
			<th width="100">商品名称</th>
			<th width="60">商品首图</th>
			<th width="100">商品价格</th>
			<th width="180">上传时间</th>
			<!-- <th width="100">上传人Id</th> -->
			<th width="100">上传人</th>
			<th width="100">商品样式</th>	
			<!-- <th width="100">返利模式</th>	 -->
			<th width="100">小二价格</th>	
			<th width="100">掌柜价格</th>	
			<th width="100">大掌柜价格</th>	
			<th width="100">进货价价格</th>	
			<th width="100">库存</th>
			<th width="100">运费</th>	
			<th width="100">规格</th>
			<th width="100">限购数量</th>
			<th width="100">排序值</th>
			<th width="100">同款特征码</th>
			<th width="100">状态</th>
			<th width="150">操作</th>							
		</tr>
		</thead>
		<tbody class="pn-ltbody">
		
			<c:forEach items="${litPager}" var="product" varStatus="status">
			<tr onmouseover="Pn.LTable.lineOver(this);" onmouseout="Pn.LTable.lineOut(this);">
				<td>${status.index+1}</td>
				<td align="center">
					<a href="#" onclick="yulan(${product.productsId})" style="text-decoration:underline;color: blue;"/>${product.name}</a>
				</td>
				<td align="center">
					<img src="${product.headUrl }" style="width: 50px"/>
				</td>
				<td align="center">${product.price}</td>
				<td align="center">${product.createDate}</td>
				<td align="center">${product.userName}</td>
				<td align="center">
					<c:if test="${product.type==0}">轮播图</c:if>
					<c:if test="${product.type==1}">一行一款</c:if>
					<c:if test="${product.type==2}">一行两款</c:if>
				</td>
				<td align="center">${product.levelone}</td>
				<td align="center">${product.leveltwo}</td>
				<td align="center">${product.levelthr}</td>
				<td align="center">${product.levelfor}</td>
				<td align="center">${product.stock}</td>
				<td align="center">${product.transFee}</td>
				<td align="center">${product.guige}</td>
				<td align="center">${product.limitNum}</td>
				<td align="center">${product.paixu}</td>
				<td align="center">${product.prodType}</td>
				<td align="center">
					<c:if test="${product.status==0}">未上架</c:if>
					<c:if test="${product.status==1}">已上架</c:if>
				</td>
				<td align="center">
					<c:if test="${product.status==0}">
						<a href="products!initModify.action?productId=${product.productsId}&&currentPage=<s:property value="pager.currentPage"/>" class="pn-loperator">修改</a>
						<a href="javascript:void(0);" onclick="deleteProduct('${product.productsId}');" class="pn-loperator">删除</a>
						<a href="products!shangJia.action?productId=${product.productsId}&&currentPage=<s:property value="pager.currentPage"/>" class="pn-loperator">上架</a>
					</c:if>
					<c:if test="${product.status==1}">
						<a href="products!xiaJia.action?productId=${product.productsId}&&currentPage=<s:property value="pager.currentPage"/>" class="pn-loperator">下架</a>
					</c:if>
				</td>
			</tr>
		</c:forEach>   
		</tbody>
	</table>

	 <!-- 导入分页组件  -->
     <c:import url="/WEB-INF/jsp/page/page.jsp">
     	<c:param name="pageActionUrl" value="products!listAll.action"/>
     </c:import>

</div>

</body>
</html>
