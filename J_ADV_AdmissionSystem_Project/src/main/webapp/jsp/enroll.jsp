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
<!-- REGISTRATION FORM SOURCE-CODE URL: >>>>>>>> https://bootsnipp.com/snippets/RlmZA -->

<title>Enroll page</title>

<link rel="stylesheet" href="${contextPath}/resources/css/bootstrap.min.css">
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" id="bootstrap-css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="faculty-page.css" type="text/css" >
</head>
<body>




	<!-- Sidebar -->
	<div class="w3-sidebar w3-light-grey w3-bar-block" style="width: 15%">
		<h3 class="w3-bar-item">Menu</h3>
		<a href="/home" class="w3-bar-item w3-button">Home</a>
		<a href="#" class="w3-bar-item w3-button">Enrolled applicants list</a>
	</div>

	<!-- Page Content -->
	
	<div style="margin-left: 15%">


		<div class="w3-container w3-teal">

			<c:if test="${pageContext.request.userPrincipal.name != null}">
				<form id="logoutForm" method="POST" action="${contextPath}/logout">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				</form>
				<h1>${list} faculty</h1>
				<h2> You are logged in as "${pageContext.request.userPrincipal.name}"  |
					<button class="user-logout-button" onclick="document.forms['logoutForm'].submit()"><h4>Logout</h4></button>

				</h2>
			</c:if>
		</div>

		<div class="w3-container">

              <%-- 	*************** TO BE FURTHER REVIEWED, REDESIGNED AND ENCORPORATED ***************
              
              <table class="lists-table">
						<tr>
							<th>Name</th>
							<th>Surname</th>
							<th>Personal Cabinet</th>
						</tr>
						<c:forEach var="applicant" items="${applicants_list}">
							<tr>
								<td>${applicant.getUser().getName()}</td>
								<td>${applicant.getUser().getSurname()}</td>
								<td><a href="enterCabinet?id=${applicant.profileId}"></a></td>
							</tr>
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
						</c:forEach>
					</table>
					 
					*****************************************************                  --%>
		
			<div class="container">
				<div class="row">
					<h2 style="padding-left: 30%">Please, Fill-In An Enrollment Form!</h2>
							
					<form class="form-horizontal" action="/faculty-page" method="POST">
						<fieldset>	
					
					<c:set var="currentUser" value="<%= pageContext.findAttribute(\"user\") %>" /> 
					
							<!-- Text input-->
							<div class="form-group">
								<label class="col-md-4 control-label" for="nameinput">Check your first name (change if incorrect)</label>
								<div class="col-md-4">
									<input id="textinput" name="nameinput" value="${currentUser.name}"
									class="form-control input-md" type="text">
									<span class="help-block"> </span>
								</div>
							</div>

							<!-- Text input-->
							<div class="form-group">
								<label class="col-md-4 control-label" for="surnameinput">Check your last name (change if incorrect)</label>
								<div class="col-md-4">
									<input id="textinput" name="surnameinput" value="${currentUser.surname}" class="form-control input-md" type="text">
									<span class="help-block"> </span>
								</div>
							</div>

							<!-- Text input-->
							<div class="form-group">
								<label class="col-md-4 control-label" for="emailinput">Check your email (change if incorrect)</label>
								<div class="col-md-4">
									<input id="textinput" name="emailinput" value="${pageContext.request.userPrincipal.name}" 
									 class="form-control input-md" type="text"> <span class="help-block">
									</span>
								</div>
							</div>
							<p class="alert-msg" style="margin-left: 105px; display: inline-block"> Enter your marks from certificate into each of the required subjects field accordingly! </p>
							
							<c:forEach var="subject" items="${subjects}">
							
							<!-- Text input-->
							<div class="form-group">
								
								<label class="col-md-4 control-label" for="textinput"> ${subject} </label>
								<div class="col-md-4">
									<input id="textinput" name="markinput" placeholder="REQUIRED SUBJECT!!! Enter your marks here! " 
									 class="form-control input-md" type="number"> <span class="help-block">
									</span>
								</div>
							</div>
							
							</c:forEach>

				<!--   **************** TO BE IMPLEMENTED IN THE NEXT LESSON ************
							
							<div class="form-group">
								<label class="col-md-4 control-label" for="filebutton">Upload your school marks certificate </label>
								<div class="col-md-4">
									<input name="filebutton" class="input-file" id="textfilebutton" type="file">
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-md-4 control-label" for="filebutton">Upload your photo</label>
								<div class="col-md-4">
									<input name="filebutton" class="input-file" id="photofilebutton" type="file">
								</div>
							</div> 
							
							************************** END TO BE IMPLEMENTED ******************************* -->
							
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
							<!-- Button -->
							<div class="form-group">
								<label class="col-md-4 control-label" for="singlebutton"> </label>
								<div class="col-md-4">
									<button id="singlebutton" name="singlebutton" class="btn btn-primary">Submit</button>
								</div>
							</div>

						</fieldset>
					</form>

				</div>
			</div>
			

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