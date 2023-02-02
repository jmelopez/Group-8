<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- form tag -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>

<!-- Number format import-->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Craft-A-Pizza</title>

<!-- for Bootstrap CSS -->
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<!-- YOUR own local CSS -->
<link rel="stylesheet" type="text/css" href="/css/style.css">
<!-- For any Bootstrap that uses JS or jQuery-->
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>

	<div class="contatiner">
		<nav class="navbar navbar-light bg-light p-3">
			<h1 class="">Pizza Pete's</h1>
			<div class="btn-group btn-group-lg" role="group"
				aria-label="Basic button group">
				<a href="/home" class="btn btn-outline-primary">Home</a> <a
					href="/order" class="btn btn-primary">Order (<c:out
						value="${totalOrders}"></c:out>)
				</a> <a href="/account/${user.id}" class="btn btn-outline-primary">Account</a>
				<a href="/logout" class="btn btn-outline-primary">Logout</a>
			</div>
		</nav>

		<div class="content">
			<h1>Craft-A-Pizza</h1>
			<div class="col-3">
				<form:form action="/craftapizza/new" method="POST"
					modelAttribute="newPizzaOrder">
					<label>Delivery Type:</label>
					<form:select path="deliveryMethod" class="form-select"
						aria-label="Default select">
						<form:option value="CarryOut">CarryOut</form:option>
						<form:option value="Delivery">Delivery</form:option>
					</form:select>
					<label>Size:</label>
					<form:select path="size" class="form-select"
						aria-label="Default select">
						<form:option value="Small">Small</form:option>
						<form:option value="Medium">Medium</form:option>
						<form:option value="Large">Large</form:option>
					</form:select>
					<label>Crust:</label>
					<form:select path="crust" class="form-select"
						aria-label="Default select">
						<form:option value="Thin">Thin</form:option>
						<form:option value="Thick">Thick</form:option>
						<form:option value="Stuffed">Stuffed</form:option>
					</form:select>
					<label>QTY:</label>
					<form:select path="quantity" class="form-select"
						aria-label="Default select">
						<form:option value="1">1</form:option>
						<form:option value="2">2</form:option>
						<form:option value="3">3</form:option>
					</form:select>

					<form:label path="hasPepperoni">Pepperoni</form:label>
					<form:checkbox path="hasPepperoni" value="true"/>
					
					<form:label path="hasSausage">Sausage</form:label>
					<form:checkbox path="hasSausage" value="true"/>
					
					<form:label path="hasMushrooms">Mushrooms</form:label>
					<form:checkbox path="hasMushrooms" value="true"/>
					
					<form:label path="hasExtraCheese">Extra Cheese</form:label>
					<form:checkbox path="hasExtraCheese" value="true"/>
					
					<form:label path="hasOnions">Onions</form:label>
					<form:checkbox path="hasOnions" value="true"/>
					
					<form:label path="hasAnchovies">Anchovies</form:label>
					<form:checkbox path="hasAnchovies" value="true"/>
					
					<form:label path="hasEggplant">Eggplant</form:label>
					<form:checkbox path="hasEggplant" value="true"/>
					
					<form:label path="hasArtichokes">Artichokes</form:label>
					<form:checkbox path="hasArtichokes" value="true"/>
					
					<form:label path="hasBroccoli">Broccoli</form:label>
					<form:checkbox path="hasBroccoli" value="true"/>
					
					<form:label path="hasPineApple">Pineapple</form:label>
					<form:checkbox path="hasPineApple" value="true"/>

					<form:input type="hidden" path="favorite" value="false" />
					<input type="submit" class="btn btn-primary col-sm-3"
						value="Add to Order" />
				</form:form>
			</div>

		</div>
	</div>
</body>
</html>