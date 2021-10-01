<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
</head>

<body class="bg-light">
        <div class="container p-2">
           <div class="row ml-1 mr-1">
           
           
              <div class="col-md-4 py-5 bg-info text-white">
                    <h2>Login</h2>
                    <form action="${contextPath}/login" method="POST" >
                        <div class="form-group  ${error != null ? 'has-error' : ''}">
                          <span>${message}</span>
                          
                            <label for="email">Email address</label>
                            <input type="text" class="form-control" name="email" placeholder="Enter email">
                            
                            <label for="password">Password</label>
                            <input type="password" class="form-control" name="password" placeholder="Password">
                            
                          <span class="error-msg">${error}</span>
                        </div>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        <input type="submit" value="Submit" class="btn btn-primary">
                    </form>
                     <h5 class="text-center">
					    <a href="${contextPath}/registration" class="login_css">Create an account</a>
				     </h5>
                </div>
                
                
            </div>
        </div>
        <!-- /container -->
        
        <!-- SCRIPTS -->
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        
</body>
</html>