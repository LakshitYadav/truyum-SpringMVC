<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>Menu Item List Admin</title>
<link rel="stylesheet" href="/style/styles.css">
</head>
<body>
	<header>
	
			<div class="head">
				<label for="label" id="label">truYum</label>
			</div>
			<div class="logo">
				<img id="logoImage" src="/images/logo.png" >
			</div>
			<div class="navDiv">
				<nav>
					<ul class="option">
						<li><a id="linkColor" href="/show-menu-list-admin">Menu</a></li>

					</ul>
				</nav>
			</div>
	</header>
	<h1>Menu Items</h1>
	<table>
		<tr>
			<th id="firstRow">Name</th>
			<th>Price</th>
			<th>Active</th>
			<th>Date of Launch</th>
			<th>Category</th>
			<th>Free Delivery</th>
			<th>Action</th>
		</tr>
		<c:forEach var="menuItem" items="${menuItemList}">
		<tr>
			<td id="firstRow">${menuItem.name}</td>
			<td>Rs. ${menuItem.price}</td>
			<td><c:if test="${menuItem.active==true}">Yes</c:if>
						<c:if test="${menuItem.active==false}">No</c:if></td>
			<td><fmt:formatDate value="${menuItem.dateOfLaunch}" pattern="dd/MM/yyyy"/></td>
			<td>${menuItem.category}</td>
			<td><c:if
							test="${menuItem.freeDelivery==true}">Yes</c:if> <c:if
							test="${menuItem.freeDelivery==false}">No</c:if></td>
			<td><a href="/show-edit-menu-item?menuItemId=${menuItem.id}">Edit</a></td>
		</tr>
		
</c:forEach>
	</table>

	<footer>Copyright &copy; 2019</footer>

</body>
</html>