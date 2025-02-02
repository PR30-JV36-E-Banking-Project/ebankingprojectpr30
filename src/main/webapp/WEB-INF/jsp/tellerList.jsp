<%-- 
    Document   : tellerList
    Created on : Oct 12, 2020, 9:58:16 PM
    Author     : Hoang Duy Nhat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="<c:url value="../../resources/css/bootstrap.min.css" />"
              rel="stylesheet">
	<script src="<c:url value="../../resources/js/jquery-3.4.1.min.js" />"></script>
	<script src="<c:url value="../../resources/js/bootstrap.min.js" />"></script>
        <title>Admin Manager</title>
    </head>
    <body>
	<div class="container">
	    <div class="col-md-offset-1 col-md-10">
		<h2>Manage Teller Page For Admin</h2>
		<hr />

		<input type="button" value="Add Teller"
		       onclick="window.location.href = 'showFormForAdd'; return false;"
		       class="btn btn-primary" />
		<br/><br/>
		<div class="panel panel-info">
		    <div class="panel-heading">
			<div class="panel-title">Teller List</div>
		    </div>
		    <div class="panel-body">
			<table class="table table-striped table-bordered">
			    <tr>
				<th>Teller ID</th>
				<th>Adress</th>
				<th>Email</th>
				<th>Action</th>
			    </tr>

			    <!-- loop over and print our customers -->
			    <c:forEach var="tempTeller" items="${pagedListHolder.pageList}">

				<!-- construct an "update" link with customer id -->
				<c:url var="updateLink" value="/updateTellerForm">
				    <c:param name="tellerID" value="${tempTeller.tellerID}" />
				</c:url>

				<!-- construct an "delete" link with customer id -->
				<c:url var="deleteLink" value="/deleteTeller">
				    <c:param name="tellerID" value="${tempTeller.tellerID}" />
				</c:url>
				<tr>
				    <td>${tempTeller.tellerID}</td>
				    <td>${tempTeller.address}</td>
				    <td>${tempTeller.email}</td>

				    <td>
					<!-- display the update link --> 
                                        <a href="${updateLink}" class="info">Update</a>| 
                                        <a href="${deleteLink}" class="danger"	onclick="if (!(confirm('Are you sure you want to delete this teller?')))
					    return false">Delete</a>
				    </td>
				</tr>
			    </c:forEach>
			</table>
                        <c:url value="/list-teller" var="pagedLink">
                                <c:param name="p" value="~" />
                        </c:url>
                        <tg:paging pagedListHolder="${pagedListHolder}"	pagedLink="${pagedLink}" />
		    </div>
		</div>
	    </div>
	</div>
    </body>
</html>
