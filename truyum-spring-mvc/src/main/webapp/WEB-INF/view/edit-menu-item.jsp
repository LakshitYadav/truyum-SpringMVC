<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Menu Item</title>
<link rel="stylesheet" href="/style/styles.css">
<script src="/js/script.js"></script>
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
					<li><a id="linkColor" href="/show-menu-list-admin">Menu</a></li>

				</ul>
			</nav>
		</div>
	</header>
	<h1>Edit Menu Item</h1>
	<br>

	<sf:form action="edit-menu-item" modelAttribute="menuItem"
			method="POST">
		<table >
			<tr>
				<th><sf:label path="name">Name</sf:label></th>
			</tr>
			<tr>
				<td id="width"><sf:input path="name" name="title" id="title"/></td>
			</tr>
			<tr>
					<td><span><sf:errors id="error" path="name"/> </span></td>
				</tr>
				<tr>
					<td><sf:hidden path="id" /></td>
				</tr>
			<tr>
				<th id="border1"><sf:label path="price">Price(in Rs.)</sf:label></th>
				<th id="border1"><sf:label path="active">Active</sf:label></th>
				<th id="border1"><sf:label path="dateOfLaunch">Date of
						Launch</sf:label></th>
				<th id="border1"><sf:label path="category">Category</sf:label></th>
			<tr>
				<td><sf:input path="price" name="price" id="price"/></td>
				<td ><sf:radiobutton path="active" name="inStock" id="yesinStock" value="Yes"/> 
				<span>Yes</span> 
				<sf:radiobutton path="active" name="inStock" id="noinStock" value="No"/> 
				<span>No</span></td>
				<td><sf:input path="dateOfLaunch" name="dateOfLaunch"
					id="dateOfLaunch" onchange="messageDisplay()"/></td>
				<td ><sf:select path="category" id="category" items="${categoryList}"/></td>
			</tr>
			<tr>
					<td>
					     <span id="errors"><sf:errors id="error" path="price" /></span>
					</td>
					<td></td>
					<td><span id="errors"><sf:errors id="error" path="dateOfLaunch" /></span></td>
				</tr>
			<tr>
				<td id="border1"><sf:checkbox path="freeDelivery" name="freeDelivery"
					id="freeDelivery"/></td>
			</tr>
			<tr><td><sf:label id="textLabel" path="freeDelivery">Free Delivery</sf:label></td></tr>
			

			<tr>
				<th id="border"><input type="submit" name="submit" id="submit"
					value="Save"></th>
			</tr>
		</table>
	</sf:form>
	<footer id="footer">Copyright &copy; 2019</footer>
</body>
</html>