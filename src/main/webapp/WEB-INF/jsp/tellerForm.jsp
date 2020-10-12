<%-- 
    Document   : tellerFormForAdd
    Created on : Oct 13, 2020, 12:30:46 AM
    Author     : Hoang Duy Nhat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Teller Manager</title>
<!--        <link href="../../resources/css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <script src="../../resources/js/bootstrap.js" type="text/javascript"></script>
        <script src="../../resources/js/jquery-2.2.3.min.js" type="text/javascript"></script>-->
	<link href="<c:url value="../../resources/css/bootstrap.css" />" rel="stylesheet">
	<script src="<c:url value="../../resources/js/jquery-2.2.3.min.js" />"></script>
	<script src="<c:url value="../../resources/js/bootstrap.js" />"></script>
    </head>
    <body>
	<div class="container">
	    <div class="col-md-offset-2 col-md-7">
		<h2 class="text-center">ADD NEW TELLER OR UPDATE TELLER'S INFORMATION</h2>
		<div class="panel panel-info">
		    <div class="panel-heading">
			<div class="panel-title">Manage Teller</div>
		    </div>
		    <div class="panel-body">
			<form:form action="saveTeller" cssClass="form-horizontal"
				   method="post" modelAttribute="teller">

			    <!-- need to associate this data with customer id -->
			    <form:hidden path="tellerID" />

			    <div class="form-group">
				<label for="address" class="col-md-3 control-label">Address</label>
				<div class="col-md-9">
				    <form:input path="address" cssClass="form-control" />
				</div>
			    </div>
			    <div class="form-group">
				<label for="email" class="col-md-3 control-label">Email</label>
				<div class="col-md-9">
				    <form:input path="email" cssClass="form-control" />
				</div>
			    </div>

			    <div class="form-group">
				<!-- Button -->
				<div class="col-md-offset-3 col-md-9">
				    <form:button cssClass="btn btn-primary">Submit</form:button>
				    </div>
				</div>

			</form:form>
		    </div>
		</div>
	    </div>
	</div>
    </body>
</html>
