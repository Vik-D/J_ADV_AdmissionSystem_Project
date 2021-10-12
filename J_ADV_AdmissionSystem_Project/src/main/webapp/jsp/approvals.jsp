<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<!-- REGISTRATION FORM SOURCE-CODE URL: >>>>>>>> https://bootsnipp.com/snippets/7n34K -->

<title>Approvals page</title>

<link rel="stylesheet" href="${contextPath}/resources/css/bootstrap.min.css">
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" id="bootstrap-css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="approvals.css" type="text/css" >
</head>
<body>




	<!-- Sidebar -->
	<div class="w3-sidebar w3-light-grey w3-bar-block my-sidebar">
		<h3 class="w3-bar-item">Menu</h3>
		<a href="/home" class="w3-bar-item w3-button">Home</a>
		<a href="#" class="w3-bar-item w3-button">Enrolled applicants list</a>
	</div>

	<!-- Page Content -->
	
	<div class="div-left">


		<div class="w3-container w3-teal">

			<c:if test="${pageContext.request.userPrincipal.name != null}">
				<form id="logoutForm" method="POST" action="${contextPath}/logout">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				</form>
				<h1>Approvals</h1>
				<h2> You are logged in as "${pageContext.request.userPrincipal.name}"  |
					<button class="user-logout-button" onclick="document.forms['logoutForm'].submit()"><h4>Logout</h4></button>

				</h2>
			</c:if>
		</div>
		
		<!-- profile data container -->

		<c:forEach var="profile" items="${profiles_list}">
			<div class="container approvals-row-contr">
				<form:form action="${contextPath}/approvals" method="POST"> 
					<input type="hidden" name="profileID" value="${profile.getProfileId()}"/>
					<div class="form-row approval-upper-content">
						<p> 
							Profile <b>#${profile.getProfileId()}.</b> Applicant name/surname: <b>${profile.getUser().getName()}
							${profile.getUser().getSurname()}.</b> Faculty:
							<b>${profile.getFaculty().getFacultyName()}.</b> 
							<a href="/fileDownload?id=${profile.getProfileId()}" target="_blank" class="download-reference"><i>Download profile certificate</i></a>
						</p>
					</div>


					<div class="form-row ">

						<c:forEach var="subject" items="${profile.getMarksTable().entrySet()}">
							<div class="col-md-2 mb-3">
								<label for="markApprove">${subject.getKey()}</label> 
								<input type="number" class="form-control is-valid" id="markApprove"
									   name="markApprove" value="${subject.getValue()}" required/>
								<input type="hidden" name="subjectName" value="${subject.getKey()}"/>
							</div>
						</c:forEach>

						<div class="form-group custom-control ">
							<div class="custom-control custom-checkbox">
								<div style="height: 1.8em"></div>
								<div class="approval-controls">
								<input type="checkbox" class="custom-control-input" id="finalCheck" required> 
								<label class="custom-control-label" for="finalCheck">Approve</label>
								<button class="btn btn-primary" type="submit">Submit</button>
								</div>
							</div>
						</div>
					</div>


				</form:form>
			</div>
			<!-- profile data container`s end -->
		</c:forEach>

		<!-- / END Page Content / -->
	</div>


	<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>