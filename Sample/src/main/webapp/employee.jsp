<%@page import = "com.employee" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/employee.js"></script>
</head>
<body> 
<div class="container"><div class="row"><div class="col-6"> 
<h1>Employee Management</h1>
<form id="formEmployee" name="formEmployee" action="employee.jsp">
 Employee code: 
 <input id="employeeCode" name="employeeCode" type="text" 
 class="form-control form-control-sm">
 <br> Employee name: 
 <input id="employeeName" name="employeeName" type="text" 
 class="form-control form-control-sm">
 <br> Employee Phone: 
 <input id="employeePhone" name="employeePhone" type="text" 
 class="form-control form-control-sm">
 <br> Employee description: 
 <input id="employeeDesc" name="employeeDesc" type="text" 
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
 <input type="hidden" id="hidEmployeeIDSave" 
 name="hidEmployeeIDSave" value="">
</form>
</br>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divEmployeeGrid">
 <%
 employee employeeObj = new employee(); 
 out.print(employeeObj.readEmployee()); 
 %>
</div>
</div> </div> </div> 
</body>
</html>
