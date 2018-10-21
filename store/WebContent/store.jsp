<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Store</title>
</head>
<body>
	<table id="storeTable" class="displayStore" border="1">
	    <tr>
			<c:forEach var="storeEntity" items="${displayList}" varStatus="status"> 
				<td>
					<div align="center">
						<img src="<%=path %>/picture/1.jpg" height="100" width="100">
					</div>
					<div align="center">
						<c:out value="${storeEntity.getS_name()}"/>
					</div>
					<div align="center">
						￥<c:out value="${storeEntity.getS_price()}"/>
					</div>
					<div align="center">
						<a class="addToShoppingCart" href="<%=path %>/StoreServlet?state=addToShoppingCart&addStoreId=<c:out value="${storeEntity.getS_id()}"></c:out>">添加到购物车</a>
					</div>
				</td>
				<c:if test="${status.count%5==0}">
					<tr>
					</tr>
				</c:if>
			</c:forEach>
		</tr>
	</table>
	<hr>
	<form action="<%=path %>/StoreServlet?state=selectStore" method="post">
		<table>
			<tr>
				<td>
					商品名称：<input type="text" name="storeName" />
				</td>
				<td>
					价格（￥）：<input type="text" name="storePrice" />
				</td>
				<td><input type="submit" value="查询商品" /></td>
			</tr>
		</table>
	</form>
	<hr>
	<h2>购物车↓</h2>
	<table id="shoppingTable" class="displayShoppingCart">
 	<tr>
      <td>商品名称</td>
      <td>价格</td>
      <td>操作</td>
    </tr>
	<c:forEach var="storeEntity" items="${shoppingList}">
		<tr>
	      <td class="list_txt"><c:out value="${storeEntity.getS_name()}"/></td>
	      <td class="list_txt">￥<c:out value="${storeEntity.getS_price()}"/></td>
	      <td><a class="removeFromShoppingCart" href="<%=path %>/StoreServlet?state=removeFromShoppingCart&deleteStoreId=<c:out value="${storeEntity.getS_id()}"></c:out>">移出购物车</a></td>
	    </tr>
    </c:forEach>
    <tr>
    	<td class="list_txt">共${pageCount}件商品</td>
	    <td class="list_txt">￥${priceCount}</td>
	    
    </tr>
</table>
</body>
</html>