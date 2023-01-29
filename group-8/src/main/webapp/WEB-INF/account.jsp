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
<title>Account</title>

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
					href="/order" class="btn btn-outline-primary">Order</a> <a
					href="/account" class="btn btn-primary">Account</a> <a
					href="/logout" class="btn btn-outline-primary">Logout</a>
			</div>
		</nav>
		<div class="content">
			<div
				class="d-flex flex-row bd-highlight justify-content-between text-center p-3">
				<div class="flex-grow-1 p-3">
					<h1>Account Info</h1>
					<form:form action="/account/${user.id}" method="POST"
						modelAttribute="user">
						<input type="hidden" name="_method" value="put">

						<div class="row mb-3">
							<label for="inputEmail3" class="col-sm-2 col-form-label">First
								Name</label>
								
							<div class="col-sm-10">
								<input type="text" class="form-control" placeholder="${user.firstName}">
							</div>
						</div>

						<div class="row mb-3">
							<label class="col-sm-2 col-form-label">Last Name</label>
							<div class="col-sm-10">
								<input type="text" class="form-control"  placeholder="${user.lastName}">
							</div>
						</div>

						<div class="row mb-3">
							<label class="col-sm-2 col-form-label">Email</label>
							<div class="col-sm-10">
								<input type="email" class="form-control"  placeholder="${user.email}">
							</div>
						</div>

						<div class="row mb-3">
							<label class="col-sm-2 col-form-label">Address</label>
							<div class="col-sm-10">
								<input type="text" class="form-control"  placeholder="${user.address}">
							</div>
						</div>

						<div class="row mb-3">
							<label class="col-sm-2 col-form-label">City</label>
							<div class="col-sm-7">
								<input type="text" class="form-control"  placeholder="${user.city}">

							</div>
							<form:label path="state" class="col-sm-1 col-form-label fw-bold">State:</form:label>
							<div class="col-sm-2">
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
						</div>

						<input type="submit" class="btn btn-primary col-6" value="Update" />
					</form:form>
				</div>

				<div class="flex-grow-1 p-3">
					<h1>Past Orders</h1>
					<table class="table">
						<thead>
							<tr>
								<td>
									<div class="card m-2">
										<div class="card-body">Pizza Details</div>
									</div>
									<div class="card m-2">
										<div class="card-body">Pizza Details</div>
									</div>
									<div class="card m-2">
										<div class="card-body">Pizza Details</div>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>