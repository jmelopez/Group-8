<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- form tag -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>  

<!-- Number format import-->
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>

	<!-- for Bootstrap CSS -->
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
	<!-- YOUR own local CSS -->
	<link rel="stylesheet" type="text/css" href="/css/style.css">
	<!-- For any Bootstrap that uses JS or jQuery-->
	<script src="/webjars/jquery/jquery.min.js"></script>
	<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div class="container">
		<div class="content">
			<div class="header row">
				<h1 class="col-8 log-head">Login Now!</h1>
				<a class=" col-4 align-self-center" href="/register">Don't have an account? Register here!</a>
			</div>
			<div class="form">
	   			<form:form action="/login" method="POST" modelAttribute="newLogin">
	   				<div class="errors">
	                       <p><form:errors path="email"/></p>
	                       <p><form:errors path="password"/></p>
	                   </div>
	   				
	   				<div class="form_input row mb-3 gy-4">
	    				<form:label path="email" class="col-sm-2 col-form-label fw-bold">Email Address:</form:label>
	    				<div class="col-sm-10">
	    					<form:input path="email" class="form-control"/>
	    				</div>

	    				<form:label path="password" class="col-sm-2 col-form-label fw-bold">Password:</form:label>
	    				<div class="col-sm-10">
	    					<form:input path="password" type="password" class="form-control"/>
	    				</div>	        			
	    			</div>
	    			
					<div class="d-grid gap-2 col-6 mx-auto">
		 				 <button class="btn btn-danger" type="submit">Login</button>
					</div>
	    			
	   			</form:form>
	   		</div>
		</div>
	</div>
</body>
</html>