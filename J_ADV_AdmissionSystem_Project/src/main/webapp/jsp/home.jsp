<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">

<title>Home page</title>

<link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="home.css" type="text/css" >
</head>
<body>

	<!-- Sidebar -->
	<div class="w3-sidebar w3-light-grey w3-bar-block" style="width: 15%">
		<h3 class="w3-bar-item">Menu</h3>
		<a href="/home" class="w3-bar-item w3-button">Home</a>
		<a href="/users" class="w3-bar-item w3-button">Registered users list</a>
		<a href="/applicants" class="w3-bar-item w3-button">Enrolled applicants list</a>
		<a href="/faculties" class="w3-bar-item w3-button">Faculties list</a>
	</div>

	<!-- Page Content -->
	<div style="margin-left: 15%">
	
	
		<div class="w3-container w3-teal">
			
			<c:if test="${pageContext.request.userPrincipal.name != null}">
			 <form id="logoutForm" method="POST" action="${contextPath}/logout">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			 </form>
			 <h1> ${list} </h1>
			 <h2> You are logged in as "${pageContext.request.userPrincipal.name}"  | 
				<button class="user-logout-button"  onclick="document.forms['logoutForm'].submit()"><h4>Logout</h4></button>	
			 </h2>
		   </c:if>
		   
		</div>

		<div class="w3-container">

			<c:choose>
				<c:when test="${mode == 'FACULTIES_LIST' }">
					<table class="lists-table">
						<tr>
							<th>Faculty name</th>
							<th>Enrollment</th>
						</tr>
						<c:forEach var="faculty" items="${faculties_list}">
						
							<tr>
								<td>${faculty.facultyName}</td>
								
								
								<c:if test="${applicant == null}">
								<td><a href="enroll_${faculty.facultyId}">Press to enroll</a></td>
								</c:if>
								<c:if test="${applicant != null && applicant.faculty.getFacultyId()==faculty.facultyId}">
								<td>ENROLLED</td>
								</c:if>
								<c:if test="${applicant != null && applicant.faculty.getFacultyId()!=faculty.facultyId}">
								<td><p></p></td>
								</c:if>
								
							</tr>
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
						</c:forEach>
					</table>
				</c:when>
				
			    <c:when test="${mode == 'APPLICANTS_LIST' }">
					<table class="lists-table">
						<tr>
							<th>Name</th>
							<th>Surname</th>
							<th>Faculty</th>
							<th>Marks total</th>
							
						</tr>
						<c:forEach var="applicant" items="${applicants_list}">
							<tr>
								<td>${applicant.getUser().getName()}</td>
								<td>${applicant.getUser().getSurname()}</td>
								<td>${applicant.getFaculty().getFacultyName()}</td>
								<td>${applicant.getTotalMarksAmount()}</td>
							</tr>
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
						</c:forEach>
					</table>
				</c:when>  
			
				 <c:when test="${mode == 'USERS_LIST' }">
					<table class="lists-table">
						<tr>
							<th>Surname</th>
							<th>Name</th>
						</tr>
						<c:forEach var="user" items="${users_list}">
							<tr>
								<td>${user.surname}</td>
								<td>${user.name}</td>
							</tr>
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
						</c:forEach>
					</table>
				</c:when>
				
				
			</c:choose>

				<!-- / END class="w3-container" / -->
		</div>
		
		<!-- / END Page Content / -->
	</div>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>