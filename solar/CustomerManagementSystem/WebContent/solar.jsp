<%@page import="com.Solar"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Solar Management</title>
		<link rel="stylesheet" href="Views/bootstrap.min.css">
		<script src="Components/jquery.min.js"></script>
		<script src="Components/customer.js"></script>
	</head>

	<body> 
		<div class="container"><div class="row"><div class="col-6"> 
		<h1>Solar Panel Management </h1><br>
		
			<form id="formSolar" name="formSolar">
			
 				Customer Name : 
 				<input id="customername" name="customername" type="text" 
 				class="form-control form-control-sm"> <br>
			    
			    Panel Type : 
 				<input id="paneltype" name="paneltype" type="text" 
 				class="form-control form-control-sm"><br> 
 				
 				Generate Power : 
 				<input id="generatepower" name="generatepower" type="text" 
				class="form-control form-control-sm"><br>
 				
 				No Of Panels : 
 				<input id="noOfPanels" name="noOfPanels" type="text" 
 				class="form-control form-control-sm"><br> 
 				
 				
 				<input id="btnSave" name="btnSave" type="button" value="Save" 
 				class="btn btn-primary">
 				<input type="hidden" id="hidSolarIDSave" 
				name="hidSolarIDSave" value="">
				
			</form>
			<br>
			
		<div id="alertSuccess" class="alert alert-success"></div>
		<div id="alertError" class="alert alert-danger"></div>
		<br>
		
		<div id="divSolarGrid">
 		<%
 		Solar solarObj = new Solar(); 
 		 		out.print(solarObj.readSolar());
 		%>
	</div>
	
	</div> </div> </div> 
</body>
</html>
