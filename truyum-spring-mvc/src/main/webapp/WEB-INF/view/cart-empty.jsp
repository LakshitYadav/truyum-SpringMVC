<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cart Empty</title>
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
	<br>
	<h2 id="msg">
		No items in cart. Use 'Add to Cart' option in <a 
			href="/show-menu-list-customer">Menu Item List</a>
	</h2>
	<footer>Copyright &copy; 2019</footer>
</body>
</html>