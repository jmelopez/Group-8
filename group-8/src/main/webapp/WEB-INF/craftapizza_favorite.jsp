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
			<h1 class="text-center">Reorder Your Favorite Pizza:</h1>

			<form:form action="/craftapizza/new" method="POST" modelAttribute="favoritePizza">
					<div class="card mx-auto text-center bg-warning m-4" style="width: 36rem">
						<span class="d-md-table p-2 bd-highlight">Delivery Method: <c:out value="${favoritePizza.deliveryMethod}"></c:out> </span> 
						<span class="d-md-table p-2 bd-highlight">Size: <c:out value="${favoritePizza.size}"></c:out></span> 
						<span class="d-md-table p-2 bd-highlight">Crust: <c:out value="${favoritePizza.crust}"></c:out></span> 
						<span class="d-md-table p-2 bd-highlight">QTY: <c:out value="${favoritePizza.quantity}"></c:out></span>
						<form:input type="hidden" path="deliveryMethod" value="${favoritePizza.deliveryMethod}" />
						<form:input type="hidden" path="size" value="${favoritePizza.size}" />
						<form:input type="hidden" path="crust" value="${favoritePizza.crust}" />
						<form:input type="hidden" path="quantity" value="${favoritePizza.quantity}" />
						<form:input type="hidden" path="favorite" value="true" />
						<input type="submit" class="btn btn-primary col-sm-3 mx-auto m-2"
							value="Add to Order" />
					</div>
			</form:form>
		</div>
	</div>
</body>
</html>