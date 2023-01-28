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
<title>Register</title>

	<!-- for Bootstrap CSS -->
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
	<!-- YOUR own local CSS -->
	<link rel="stylesheet" type="text/css" href="/css/style.css">
	<!-- For any Bootstrap that uses JS or jQuery-->
	<script src="/webjars/jquery/jquery.min.js"></script>
	<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<div class="content">
			<div class="header row">
				<h1 class="col-8 log-head">Register Now!</h1>
				<a class="col-4 align-self-center" href="/">Already have an account? Log in here!</a>
			</div>
			<div class="form">
				<form:form action="/register" method="POST" modelAttribute="newUser">
	    			
	   				<div class="errors">
	   					<p><form:errors path="firstName"/></p>
	   					<p><form:errors path="lastName"/></p>
	                       <p><form:errors path="email"/></p>
	                       <p><form:errors path="address"/></p>
	                       <p><form:errors path="city"/></p>
	                       <p><form:errors path="state"/></p>
	                       <p><form:errors path="zipcode"/></p>
	                       <p><form:errors path="password"/></p>
	                       <p><form:errors path="confirm"/></p>
	   				</div>
	    				
					<div class="form_input row mb-3 gy-4 ">
		   				
		   				<form:label path="firstName" class="firstName col-sm-2 col-form-label fw-bold">First Name:</form:label>
		   				<div class="col-sm-4">
		   					<form:input path="firstName" class="input-first form-control"/>
		   				</div>
				     			
	     				<form:label path="lastName" class="lastName col-sm-2 col-form-label fw-bold">Last Name:</form:label>
	     				<div class="col-sm-4">
	     					<form:input path="lastName" class="form-control"/>
	     				</div>	     				
	
	     				<form:label path="email" class="col-sm-2 col-form-label fw-bold">Email:</form:label>
	     				<div class="col-sm-10">
	     					<form:input path="email" class="form-control"/>
	     				</div>
	
	     				<form:label path="address" class="col-sm-2 col-form-label fw-bold">Address:</form:label>
	     				<div class="col-sm-10">
	     					<form:input path="address" class="form-control"/>
	     				</div>
	     			
	     				<form:label path="city" class="col-sm-2 col-form-label fw-bold">City:</form:label>
	     				<div class="col-sm-4">
	     					<form:input path="city" class="form-control"/>
	     				</div>
	     			
	     				<form:label path="state" class="col-sm-1 col-form-label fw-bold">State:</form:label>
	     				<div class="col-sm-1">
		     				<form:select class="form-select form-select-sm" path="state">
		     				
		     					<form:option value="NONE"> -- </form:option>
		     					<form:option value="AL"> AL </form:option>
		     					<form:option value="AK"> AK </form:option>
		     					<form:option value="AZ"> AZ </form:option>
		     					<form:option value="AR"> AR </form:option>
		     					<form:option value="AS"> AS </form:option>
		     					<form:option value="CA"> CA </form:option>
		     					<form:option value="CO"> CO </form:option>
		     					<form:option value="CT"> CT </form:option>
		     					<form:option value="DE"> DE </form:option>
		     					<form:option value="DC"> DC </form:option>
		     					<form:option value="FL"> FL </form:option>
		     					<form:option value="GA"> GA </form:option>
		     					<form:option value="GU"> GU </form:option>
		     					<form:option value="HI"> HI </form:option>
		     					<form:option value="ID"> ID </form:option>
		     					<form:option value="IL"> IL </form:option>
		     					<form:option value="IN"> IN </form:option>
		     					<form:option value="IA"> IA </form:option>
		     					<form:option value="KS"> KS </form:option>
		     					<form:option value="KY"> KY </form:option>
		     					<form:option value="LA"> LA </form:option>
		     					<form:option value="ME"> ME </form:option>
		     					<form:option value="MD"> MD </form:option>
		     					<form:option value="MA"> MA </form:option>
		     					<form:option value="MI"> MI </form:option>
		     					<form:option value="MN"> MN </form:option>
		     					<form:option value="MS"> MS </form:option>
		     					<form:option value="MO"> MO </form:option>
		     					<form:option value="OH"> OH </form:option>
		     					<form:option value="OK"> OK </form:option>
		     					<form:option value="OR"> OR </form:option>
		     					<form:option value="PA"> PA </form:option>
		     					<form:option value="PR"> PR </form:option>
		     					<form:option value="RI"> RI </form:option>
		     					<form:option value="SC"> SC </form:option>
		     					<form:option value="SD"> SD </form:option>
		     					<form:option value="TN"> TN </form:option>
		     					<form:option value="TX"> TX </form:option>
		     					<form:option value="TT"> TT </form:option>
		     					<form:option value="UT"> UT </form:option>
		     					<form:option value="VT"> VT </form:option>
		     					<form:option value="VA"> VA </form:option>
		     					<form:option value="VI"> VI </form:option>
		     					<form:option value="WA"> WA </form:option>
		     					<form:option value="WV"> WV </form:option>
		     					<form:option value="WI"> WI </form:option>
		     					<form:option value="WY"> WY </form:option>
		     				</form:select>
	     				</div>
	     			
	     				<form:label path="zipcode" class="col-sm-1 col-form-label fw-bold">Zipcode:</form:label>
	     				<div class="col-sm-3">
	     					<form:input path="zipcode" class="form-control"/>
	     				</div>
		     			
	     				<form:label path="password" class="col-sm-2 col-form-label fw-bold">Password:</form:label>
	     				<div class="col-sm-4">
	     					<form:input path="password" type="password" class="form-control"/>
	     				</div>
		     			
	     				<form:label path="confirm" class="col-sm-2 col-form-label fw-bold">Confirm Password:</form:label>
	     				<div class="col-sm-4">
	     					<form:input path="confirm" type="password" class="form-control"/>
	     				</div>
		     			
		     			<div class="d-grid gap-2 col-6 mx-auto">
				  			<button class="btn btn-danger" type="submit">Register</button>
						</div>
					</div>
	    		</form:form>
	    	</div>    
		</div>
	</div>
</body>
</html>