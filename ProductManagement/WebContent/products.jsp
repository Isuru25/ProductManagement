<%@page import= "com.Product" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Product Management</title>
<link rel="stylesheet" href="Views/boostrap.min.css">
<script src="Component/jQuery-3.2.1.min.js"></script>
<script src="Component/products.js"></script>
</head>
<body>

<div class="container"><div class="row"><div class="col-6">

<h1>Product Management </h1>
<form id="formProduct" name="formProduct" method="post" action="products.jsp">
 Product Name: 
<input id="name" name="name" type="text" 
 class="form-control form-control-sm">
<br> Category: 
<input id="category" name="category" type="text" 
 class="form-control form-control-sm">
<br> Description: 
<input id="description" name="description" type="text" 
 class="form-control form-control-sm">
<br> Price: 
<input id="price" name="price" type="text" 
 class="form-control form-control-sm">
<br>
<input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
<input type="hidden" id="hidIdSave" name="hidIdSave" value="">
</form>

<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>


<br>
<div id="divProductsGrid">

<%
 Product productObj = new Product(); 
 out.print(productObj.readProducts()); 
%>
</div>

</div></div></div>

</body>
</html>