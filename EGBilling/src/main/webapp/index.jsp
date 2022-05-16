<%@page import="model.Billing"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Billing Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.js"></script>
<script src="Components/main.js"></script>

</head>
<body>	
		<div class="container"><div class="row"><div class="col-6">
				
				<h1>Billing Management</h1>
			<form id="formBilling" name="formBilling">
				 Customer ID:
				 <input id="CID" name="CID" type="text" class="form-control form-control-sm">
				 
				 <br> Bill Number:
				 <input id="BN" name="BN" type="text" class="form-control form-control-sm">
				 
				 <br> Bill Number :
				 <input id="PR" name="PR" type="text" class="form-control form-control-sm">
				 
				 <br> Previous Reading :
				 <input id="CR" name="CR" type="text" class="form-control form-control-sm">
				 
				 <br>Due Amount :
				 <input id="DA" name="DA" type="text" class="form-control form-control-sm">
				 
				 <br>Billing Date :
				 <input id="BD" name="BD" type="text" class="form-control form-control-sm">
				 
				 <br>
				 <input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
				 <input type="hidden" id="hidSave" name="hidSave" value="">
			</form>
			
			
			<div id="alertSuccess" class="alert alert-success"></div>
			<div id="alertError" class="alert alert-danger"></div>
			<br>
			
			<div id="divBillingGrid">
			<%
			Billing BillingObj = new Billing();
		 	out.print(BillingObj.readBilling());
			 %>
</div>
</div> </div> </div> 
			

</body>
</html>