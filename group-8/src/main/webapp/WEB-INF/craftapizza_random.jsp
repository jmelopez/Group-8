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
					href="/order" class="btn btn-primary">Order (<c:out value="${totalOrders}"></c:out>)</a> <a
					href="/account/${user.id}" class="btn btn-outline-primary">Account</a>
				<a href="/logout" class="btn btn-outline-primary">Logout</a>
			</div>
		</nav>

		<div class="content">
			<h1>Craft-A-Random-Pizza</h1>

			<form:form action="/craftapizza/new" method="POST" modelAttribute="newRandomPizza">
				<label>Delivery Type:</label>
				<form:select path="deliveryMethod" class="form-select"
					aria-label="Default select">
					<form:option value="CarryOut">CarryOut</form:option>
					<form:option value="Delivery">Delivery</form:option>
				</form:select>
				<label>Random Size:</label>
				<form:select path="size" class="form-select"
					aria-label="Default select">
					<form:option value="Small">Small</form:option>
					<form:option value="Medium">Medium</form:option>
					<form:option value="Large">Large</form:option>
				</form:select>
				<label>Random Crust:</label>
				<form:select path="crust" class="form-select"
					aria-label="Default select">
					<form:option value="Thick">Thick</form:option>
					<form:option value="Thin">Thin</form:option>
					<form:option value="Stuffed">Stuffed</form:option>
				</form:select>
				<label>QTY:</label>
				<form:select path="quantity" class="form-select"
					aria-label="Default select">
					<form:option value="1">1</form:option>
					<form:option value="2">2</form:option>
					<form:option value="3">3</form:option>
					
					
					<form:input type="hidden" path="hasPepperoni" value="${newRandomPizza.hasPepperoni}" />
					<form:input type="hidden" path="hasSausage" value="${newRandomPizza.hasSausage}" />
					<form:input type="hidden" path="hasMushrooms" value="${newRandomPizza.hasMushrooms}" />
					<form:input type="hidden" path="hasExtraCheese" value="${newRandomPizza.hasExtraCheese}" />
					<form:input type="hidden" path="hasOnions" value="${newRandomPizza.hasOnions}" />
					<form:input type="hidden" path="hasAnchovies" value="${newRandomPizza.hasAnchovies}" />
					<form:input type="hidden" path="hasEggplant" value="${newRandomPizza.hasEggplant}" />
					<form:input type="hidden" path="hasArtichokes" value="${newRandomPizza.hasArtichokes}" />
					<form:input type="hidden" path="hasBroccoli" value="${newRandomPizza.hasBroccoli}" />
					<form:input type="hidden" path="hasPineApple" value="${newRandomPizza.hasPineApple}" />
				</form:select>
				
					<div class=col>
							<h5>Toppings</h5>
							<c:if test="${newRandomPizza.hasPepperoni}">
								<span>Pepperoni, </span>
							</c:if>
							<c:if test="${newRandomPizza.hasSausage}">
								<span>Sausage, </span>
							</c:if>
							<c:if test="${newRandomPizza.hasMushrooms}">
								<span>Mushrooms, </span>
							</c:if>
							<c:if test="${newRandomPizza.hasExtraCheese}">
								<span>Extra Cheese, </span>
							</c:if>
							<c:if test="${newRandomPizza.hasOnions}">
								<span>Onions, </span>
							</c:if>
							<c:if test="${newRandomPizza.hasAnchovies}">
								<span>Anchovies, </span>
							</c:if>
							<c:if test="${newRandomPizza.hasEggplant}">
								<span>Eggplant, </span>
							</c:if>
							<c:if test="${newRandomPizza.hasArtichokes}">
								<span>Artichokes, </span>
							</c:if>
							<c:if test="${newRandomPizza.hasBroccoli}">
								<span>Broccoli, </span>
							</c:if>
							<c:if test="${newRandomPizza.hasPineApple}">
								<span>Pineapple, </span>
							</c:if>
						</div>

				<form:input type="hidden" path="favorite" value="false" />
				<input type="submit" class="btn btn-primary col-sm-3" value="Add to Order" />
			</form:form>
		</div>
	</div>
</body>
</html>