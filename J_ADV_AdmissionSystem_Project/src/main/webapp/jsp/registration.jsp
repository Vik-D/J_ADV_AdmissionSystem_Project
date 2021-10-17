<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
	<!-- SOURCE CODE URL:  https://bootsnipp.com/snippets/PDVm9 -->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Register</title>
    <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" id="bootstrap-css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="registration.css" type="text/css" >
    
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

<body class="bg-light">
	
				<div class="language-choose">

					<fieldset>
						<label><spring:message code="login.choose_language"/></label> 
						<select id="locales">
							<option value="en"><spring:message code="login.english"/></option>
							<option value="ua"><spring:message code="login.ukrainian"/></option>
						</select>

					</fieldset>

				</div>		

        <div class="container p-2">
           <div class="row ml-1 mr-1">

                <div class="col-md-4 py-5 border">
                    <h2><spring:message code="register.register"/></h2>
                    <form:form method="POST" modelAttribute="userForm" >
                    
                    <spring:bind path="name">    
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <label for="input_fname"><spring:message code="register.name"/></label>
                            <form:input type="text" path="name" class="form-control" name="input_fname" 
                                        placeholder="<spring:message code='register.name'/>" required="true"></form:input>
                            <form:errors path="name"></form:errors>
                        </div>
                    </spring:bind>
                        
                    <spring:bind path="surname">	
                        <div class="form-group  ${status.error ? 'has-error' : ''}">
                            <label for="input_lname"><spring:message code="register.surname"/></label>
                            <form:input type="text" path="surname" class="form-control" name="input_lname" 
                                        placeholder="<spring:message code='register.surname'/>" required="true"></form:input>
                            <form:errors path="surname"></form:errors>
                        </div>
                    </spring:bind>
                        
                    <spring:bind path="email">
                        <div class="form-group ${status.error ? 'has-error' : ''} registration-warning">
                            <label for="input_email"><spring:message code="login.email"/></label>
                            <form:input type="text" path="email" class="form-control" name="input_email" 
                                        placeholder="<spring:message code='login.email-placeholder'/>" required="true"></form:input>
                            <form:errors path="email"></form:errors>
                            <span class="span-registration-warning">${msg}</span>
                        </div>
                    </spring:bind>
                        
                    <spring:bind path="password">
                        <div class="form-group  ${status.error ? 'has-error' : ''}">
                            <label for="input_password"><spring:message code="login.password"/></label>
                            <form:input type="password" path="password" class="form-control" name="input_password" aria-describedby="passwordHelp" placeholder="****" required="true"></form:input>
                            <form:errors path="password"></form:errors>
                        </div>
                    </spring:bind>
                        
                    <spring:bind path="passwordConfirm">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <label for="input_conf_password"><spring:message code="register.confirm-password"/></label>
                            <form:input type="password" path="passwordConfirm" class="form-control" name="input_conf_password" placeholder="****" required="true"></form:input>
                            <form:errors path="passwordConfirm"></form:errors>
                        </div>
                    </spring:bind>
                        
                        <button type="submit" class="btn btn-primary"><spring:message code="login.submit"/></button>
                    </form:form>
                </div>
                
                <div class="col-md-4">
				<img alt="Img"
					src="https://manometcurrent.com/wp-content/uploads/2021/06/Online-Admissions-Software-Market.png"
					class="welcome-img">
			</div>
			
            </div>
        </div>
        <!-- /container -->
        
        <!-- SCRIPTS -->
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        
</body>
</html>