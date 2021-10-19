<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
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
<!-- REGISTRATION FORM SOURCE-CODE URL: >>>>>>>> https://bootsnipp.com/snippets/RlmZA -->

<title>Faculty page</title>

<link rel="stylesheet" href="${contextPath}/resources/css/bootstrap.min.css">
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" id="bootstrap-css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="faculty-page.css" type="text/css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>

<script type="text/javascript">
	$(document).ready(function() {

		var selectedItem = localStorage.getItem("locales");
		$('#locales').val(selectedItem ? selectedItem : 'en');
		$('#locales').change(function() {

			var selectedOption = $('#locales').val();
			if (selectedOption) {
				window.location.replace('?lang=' + selectedOption);
				localStorage.setItem("locales", selectedOption);
				
			}
		});
	});
</script>

</head>
<body>

	<!-- Sidebar -->
	<div class="w3-sidebar w3-light-grey w3-bar-block my-sidebar">
		<h3 class="w3-bar-item"><spring:message code="all.menu"/></h3>
		<a href="/home" class="w3-bar-item w3-button"><spring:message code="all.home"/></a>
		<a href="/applicants" class="w3-bar-item w3-button"><spring:message code="all.enrolled-applicants"/></a>
	</div>

	<!-- Page Content -->
	
	<div class="div-left">

		<div class="w3-container w3-teal">

			<c:if test="${pageContext.request.userPrincipal.name != null}">
				<form id="logoutForm" method="POST" action="${contextPath}/logout">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				</form>
				<h1>${list} faculty</h1>
				<h2> <spring:message code="all.user-notify"/> "${pageContext.request.userPrincipal.name}"  |
					<button class="user-logout-button" onclick="document.forms['logoutForm'].submit()">
					<h4><spring:message code="all.logout"/></h4></button>

				</h2>
			</c:if>
		</div>

		<div class="w3-container">
		        
              <table class="lists-table">
						<tr>
							<th><spring:message code="prf.name"/></th>
							<th><spring:message code="prf.surname"/></th>
							<th><spring:message code="prf.faculty"/></th>
							<th><spring:message code="prf.marks"/></th>
							<!-- <th>Personal Cabinet</th> -->
						</tr>
						<c:forEach var="applicant" items="${faculty_applicants_list}">
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
					 
			<!-- / END class="w3-container" / -->
		</div>
		
		<!-- / END Page Content / -->
	</div>

	<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>