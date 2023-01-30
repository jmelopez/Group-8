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
<title>Home</title>

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
				<a href="/home" class="btn btn-primary">Home</a> 
				<a href="/order" class="btn btn-outline-primary">Order</a> 
				<a href="/account/${user.id}" class="btn btn-outline-primary">Account</a> 
				<a href="/logout" class="btn btn-outline-primary">Logout</a>
			</div>
		</nav>

		<div class="content">
			<h2 class="text-center">Quick Options</h2>
			<div class="d-flex flex-row bd-highlight justify-content-between text-center p-3">

				<div class="card p-3">
	
					<div class="card-body">
						<p class="card-text">Empty card</p>
						<a href="/craftapizza" class="btn btn-primary">NEW ORDER</a>
					</div>
				</div>
				<div class="card p-3">

					<div class="card-body">
						<p class="card-text">Empty card</p>
						<a href="#" class="btn btn-primary">RE-ORDER MY FAVE</a>
					</div>
				</div>
				<div class="card p-3">

					<div class="card-body">
						<p class="card-text">Empty card</p>
						<a href="/craftapizza/random" class="btn btn-primary">SURPRISE ME</a>
					</div>
				</div>
			</div>
			</div>
		</div>
</body>
</html>