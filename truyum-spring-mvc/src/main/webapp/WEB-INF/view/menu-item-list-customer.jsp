<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib  uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    <%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Menu Item List Customer</title>
<link rel="stylesheet" href="/style/styles.css">
</head>
<body>
	<header>
			<div class="head">
				<label for="label" id="label">truYum</label>
			</div>
			<div class="logo">
				<img src="/images/logo.png" id="logoImage">
			</div>
			<div class="navDiv">
				<nav>
					<ul class="option">
						<li><a id="linkColor" href="/show-menu-list-customer">Menu</a></li>
						<li><a id="linkColor" href="/show-cart?userId=1">Cart</a></li>
					</ul>
				</nav>
			</div>
	</header>
	<h1>Menu Items</h1>
	<h2><c:if test="${addCartStatus}">Item added to Cart Successfully</c:if></h2>
	<table>
		<tr>
			<th id="firstRow">Name</th>
			<th>Free Delivery</th>
			<th>Price</th>
			<th>Category</th>
			<th>Action</th>
		</tr>
		<c:forEach var="menuItem" items="${menuItemListCustomer}">
		<tr>
			<td id="firstRow">${menuItem.name}</td>
			<td><c:if test="${menuItem.freeDelivery==true}">Yes</c:if>
					<c:if test="${menuItem.freeDelivery==false}">No</c:if></td>
			<td>Rs. ${menuItem.price}</td>
			<td>${menuItem.category}</td>
			<td><a href="/add-to-cart?menuItemId=${menuItem.id}">Add to Cart</a></td>
		</tr>
		</c:forEach>
		
	</table>

	<footer>Copyright &copy; 2019</footer>
</body>
</html>