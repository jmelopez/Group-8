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
<title>Craft-A-Pizza Fav</title>

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
			<h1>Reorder Your Favorite Pizza:</h1>

			<form:form action="/craftapizza/new" method="POST" modelAttribute="favoritePizza">
				<div class="card">
					<div class="card-body">
						<span class="d-md-table p-2 bd-highlight">Delivery Method: <c:out value="${favoritePizza.deliveryMethod}"></c:out> </span> 
						<span class="d-md-table p-2 bd-highlight">Size: <c:out value="${favoritePizza.size}"></c:out></span> 
						<span class="d-md-table p-2 bd-highlight">Crust: <c:out value="${favoritePizza.crust}"></c:out></span> 
						<span class="d-md-table p-2 bd-highlight">QTY: <c:out value="${favoritePizza.quantity}"></c:out></span>
						<!-- TOPPINGS -->
						<span class="d-md-table p-2 bd-highlight">QTY: <c:out value="${favoritePizza.quantity}"></c:out></span>
						<div class=col>
							<h5>Toppings</h5>
							<c:if test="${favoritePizza.hasPepperoni}">
								<span>Pepperoni, </span>
							</c:if>
							<c:if test="${favoritePizza.hasSausage}">
								<span>Sausage, </span>
							</c:if>
							<c:if test="${favoritePizza.hasMushrooms}">
								<span>Mushrooms, </span>
							</c:if>
							<c:if test="${favoritePizza.hasExtraCheese}">
								<span>Extra Cheese, </span>
							</c:if>
							<c:if test="${favoritePizza.hasOnions}">
								<span>Onions, </span>
							</c:if>
							<c:if test="${favoritePizza.hasAnchovies}">
								<span>Anchovies, </span>
							</c:if>
							<c:if test="${favoritePizza.hasEggplant}">
								<span>Eggplant, </span>
							</c:if>
							<c:if test="${favoritePizza.hasArtichokes}">
								<span>Artichokes, </span>
							</c:if>
							<c:if test="${favoritePizza.hasBroccoli}">
								<span>Broccoli, </span>
							</c:if>
							<c:if test="${favoritePizza.hasPineApple}">
								<span>Pineapple, </span>
							</c:if>
						</div>
						<!-- Convert PastOrder Favorite to New PizzaOrder -->
						<form:input type="hidden" path="deliveryMethod" value="${favoritePizza.deliveryMethod}" />
						<form:input type="hidden" path="size" value="${favoritePizza.size}" />
						<form:input type="hidden" path="crust" value="${favoritePizza.crust}" />
						<form:input type="hidden" path="quantity" value="${favoritePizza.quantity}" />
						<!-- TOPPINGS: -->
						<form:input type="hidden" path="hasPepperoni" value="${favoritePizza.hasPepperoni}" />
						<form:input type="hidden" path="hasSausage" value="${favoritePizza.hasSausage}" />
						<form:input type="hidden" path="hasMushrooms" value="${favoritePizza.hasMushrooms}" />
						<form:input type="hidden" path="hasExtraCheese" value="${favoritePizza.hasExtraCheese}" />
						<form:input type="hidden" path="hasOnions" value="${favoritePizza.hasOnions}" />
						<form:input type="hidden" path="hasAnchovies" value="${favoritePizza.hasAnchovies}" />
						<form:input type="hidden" path="hasEggplant" value="${favoritePizza.hasEggplant}" />
						<form:input type="hidden" path="hasArtichokes" value="${favoritePizza.hasArtichokes}" />
						<form:input type="hidden" path="hasBroccoli" value="${favoritePizza.hasBroccoli}" />
						<form:input type="hidden" path="hasPineApple" value="${favoritePizza.hasPineApple}" />
						
						<form:input type="hidden" path="favorite" value="true" />
					</div>
				</div>
				<input type="submit" class="btn btn-primary col-sm-3"
					value="Add to Order" />
			</form:form>
		</div>
	</div>
</body>
</html>