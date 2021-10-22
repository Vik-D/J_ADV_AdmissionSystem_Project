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
<!-- REGISTRATION FORM SOURCE-CODE URL: >>>>>>>> https://bootsnipp.com/snippets/7n34K -->

<title>Approvals page</title>

<link rel="stylesheet" href="${contextPath}/resources/css/bootstrap.min.css">
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" id="bootstrap-css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="approvals.css" type="text/css">
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
		
		<security:authorize access="hasRole('ROLE_ADMIN')"> 
		<a href="/approvals" class="w3-bar-item w3-button"><spring:message code="all.approvals"/></a>
		</security:authorize>
		
		<security:authorize access="hasRole('ROLE_ADMIN')"> 
		<c:forEach var="faculty" items="${faculties_list}">
		<a href="/approvals_${faculty.getFacultyId()}" class="w3-bar-item w3-button">${faculty.getFacultyName()}</a>
		</c:forEach>
		</security:authorize>
				<div class="language-choose main-menu">

					<fieldset>
						<label><spring:message code="all.choose_language"/></label> 
						<select id="locales">
							<option value="en"><spring:message code="all.english"/></option>
							<option value="ua"><spring:message code="all.ukrainian"/></option>
						</select>

					</fieldset>

				</div>
	</div>

	<!-- Page Content -->
	
	<div class="div-left">

		<div class="w3-container w3-teal">

			<c:if test="${pageContext.request.userPrincipal.name != null}">
				<form id="logoutForm" method="POST" action="${contextPath}/logout">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				</form>
				<h1><spring:message code="apr.title"/></h1>
				<h2> <spring:message code="all.user-notify"/> "${pageContext.request.userPrincipal.name}"  |
					<button class="user-logout-button" onclick="document.forms['logoutForm'].submit()">
					<h4><spring:message code="all.logout"/></h4></button>

				</h2>
			</c:if>
		</div>
		
		<!-- profile data container -->
		<c:choose>
		
		<c:when test="${mode == 'PROFILES_BY_FACULTY' }">
		
			<div class="container approvals-row-contr">
			<form:form class="final-admittance-form"> 
			<div class="form-row approval-upper-content">
			<table class="final-admittance-table">
					<tr>
					<th class="name-display"><b> <spring:message code="apr.name-surname"/></b></th>
					<th class="profile-data-display"><b> <spring:message code="apr.faculty-enrolled"/></b></th>
					<th class="profile-data-display"><b> <spring:message code="apr.marks"/></b></th>
					</tr>
			</table>
			</div>
					<div class="approval-controls">
						<input type="checkbox" class="custom-control-input" id="finalCheckAll" required> 
						<label class="custom-control-label" for="finalCheckAll"><spring:message code="apr.admit-all"/></label>
						<button class="btn btn-primary" type="submit"><spring:message code="apr.submit"/></button>
					</div>
			</form:form><!-- this line to be checked, see: line 100 -->
			 <c:forEach var="a_profile" items="${approved_profiles}"> 
				<form:form class="final-admittance-form"> 
					<div class="form-row approval-upper-content">
					<table class="final-admittance-table">
					<tr>
						<td class="name-display">${a_profile.getUser().getName()} ${a_profile.getUser().getSurname()}</td>
						<td class="profile-data-display"> ${a_profile.getFaculty().getFacultyName()}</td>
						<td class="profile-data-display"> ${a_profile.getTotalMarksAmount()}</td>
					</tr>
					</table>
					</div>

					<div class="approval-controls">
						<input type="checkbox" class="custom-control-input" id="finalCheck" required> 
						<label class="custom-control-label" for="finalCheck"><spring:message code="apr.admit"/></label>
						<button class="btn btn-primary" type="submit"><spring:message code="apr.submit"/></button>
					</div>
				</form:form>
				</c:forEach> 
				
			</div>
			
		</c:when>
		
		<c:when test="${mode == 'PROFILES_APPROVAL' }">
		<c:forEach var="profile" items="${profiles_list}">
			<div class="container approvals-row-contr">
				<form:form action="${contextPath}/approvals" method="POST"> 
					<input type="hidden" name="profileID" value="${profile.getProfileId()}"/>
					<div class="form-row approval-upper-content">
						<p> 
							<spring:message code="apr.profile"/> <b><spring:message code="apr.profileNum"/>${profile.getProfileId()}.</b> 
							<spring:message code="apr.applicant-name-surname"/> <b>${profile.getUser().getName()} ${profile.getUser().getSurname()}.</b> 
							<spring:message code="apr.faculty"/> <b>${profile.getFaculty().getFacultyName()}.</b> 
							<a href="/fileDownload?profileID=${profile.getProfileId()}" target="_blank" class="download-reference">
							<i><spring:message code="apr.download-msg"/></i></a>
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
								<input type="checkbox" class="custom-control-input" id="approveCheck" required> 
								<label class="custom-control-label" for="approveCheck"><spring:message code="apr.approve"/></label>
								<button class="btn btn-primary" type="submit"><spring:message code="apr.submit"/></button>
								</div>
							</div>
						</div>
					</div>

				</form:form>
			</div>
			<!-- profile data container`s end -->
		</c:forEach>
		</c:when>
	</c:choose>
		<!-- / END Page Content / -->
	</div>


	<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>