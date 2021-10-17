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
    <title>Log In</title>
    <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" id="bootstrap-css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="login.css" type="text/css" >
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
           
              <div class="col-md-4 py-5 bg-info text-white">
                    <h2><spring:message code="login.sign-in"/></h2>
                    <form action="${contextPath}/login" method="POST" >
                        <div class="form-group  ${error != null ? 'has-error' : ''}">
                          <span>${message}</span>
                          
                            <label for="email"><spring:message code="login.email"/></label>
                            <input type="text" class="form-control login-upper-field" name="email" 
                                   placeholder="<spring:message code='login.email-placeholder'/>">
                            
                            <label for="password"><spring:message code="login.password"/></label>
                            <input type="password" class="form-control login-bottom-field" name="password" 
                                   placeholder="<spring:message code='login.password-placeholder'/>">
                            
                          <span class="error-msg">${error}</span>
                        </div>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        <input type="submit" value="<spring:message code="login.submit"/>" class="btn btn-primary">
                    </form>

				<h5 class="text-center">
					    <a href="${contextPath}/registration" class="login_css">
					    <spring:message code="login.create-account"/></a>
				     </h5>
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