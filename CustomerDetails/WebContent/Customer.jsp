<%@page import="com.Customer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/Customer.js"></script>
</head>
<body> 
<div class="container"><div class="row"><div class="col-6"> 
<h1>Customer</h1>
<form id="formCus" name="formCus">
 Wiring Method: 
 <input id="wiringMethod" name="wiringMethod" type="text" 
 class="form-control form-control-sm">
 <br> User Name: 
 <input id="fullName" name="fullName" type="text" 
 class="form-control form-control-sm">
 <br> Phone Number: 
 <input id="phoneNo" name="phoneNo" type="text" 
 class="form-control form-control-sm">
 <br> ElectricID: 
 <input id="electricID" name="electricID" type="text" 
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
 <input type="hidden" id="hidPIDSave" 
 name="hidPIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divCustomerGrid">
	<%
	Customer customerObj = new Customer(); 
	 		out.print(customerObj.readCone());
	%>
</div>
</div> </div> </div> 
</body>
</html>