<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cart</title>
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
	<h1>Cart</h1>
	<h2><c:if test="${removeCartItemStatus}">Item removed from Cart successfully</c:if></h2>
	<section>
	<table>
		<tr>
			<th id="firstRow">Name</th>
			<th>Free Delivery</th>
			<th>Price</th>

		</tr>
		<c:forEach var="usercart" items="${cart}">
		<tr>
			<td id="firstRow">${usercart.name}</td>
			<td><c:if test="${usercart.freeDelivery==true}">Yes</c:if> 
			<c:if test="${usercart.freeDelivery==false}">No</c:if></td>
			<td>Rs. ${usercart.price}</td>
			<td><a href="/remove-cart?menuItemId=${usercart.id}&userId=1">Delete</a></td>

		</tr>
		</c:forEach>
		
		<tr>
		<th id="firstRow"></th>
		<th>Total</th>
		<th>Rs. ${total}</th>

		</tr>


	</table>
	</section>
	<footer>Copyright &copy; 2019</footer>
</body>
</html>