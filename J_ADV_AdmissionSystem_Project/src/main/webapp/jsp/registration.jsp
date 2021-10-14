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
    <title>Register</title>
    <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" id="bootstrap-css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>

<body class="bg-light">
        <div class="container p-2">
           <div class="row ml-1 mr-1">

                <div class="col-md-8 py-5 border">
                    <h2>Register</h2>
                    <form:form method="POST" modelAttribute="userForm" >
                    
                    <spring:bind path="name">    
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <label for="input_fname">First Name</label>
                            <form:input type="text" path="name" class="form-control" name="input_fname" placeholder="First name" required="true"></form:input>
                            <form:errors path="name"></form:errors>
                        </div>
                    </spring:bind>
                        
                    <spring:bind path="surname">	
                        <div class="form-group  ${status.error ? 'has-error' : ''}">
                            <label for="input_lname">Last Name</label>
                            <form:input type="text" path="surname" class="form-control" name="input_lname" placeholder="Last name" required="true"></form:input>
                            <form:errors path="surname"></form:errors>
                        </div>
                    </spring:bind>
                        
                    <spring:bind path="email">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <label for="input_email">Email Address</label>
                            <form:input type="text" path="email" class="form-control" name="input_email" placeholder="Enter email" required="true"></form:input>
                            <form:errors path="email"></form:errors>
                            <span>${msg}</span>
                        </div>
                    </spring:bind>
                        
                    <spring:bind path="password">
                        <div class="form-group  ${status.error ? 'has-error' : ''}">
                            <label for="input_password">Password</label>
                            <form:input type="password" path="password" class="form-control" name="input_password" aria-describedby="passwordHelp" placeholder="****" required="true"></form:input>
                            <form:errors path="password"></form:errors>
                        </div>
                    </spring:bind>
                        
                    <spring:bind path="passwordConfirm">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <label for="input_conf_password">Confirm Password</label>
                            <form:input type="password" path="passwordConfirm" class="form-control" name="input_conf_password" placeholder="****" required="true"></form:input>
                            <form:errors path="passwordConfirm"></form:errors>
                        </div>
                    </spring:bind>
                        
                        <button type="submit" class="btn btn-primary">Submit</button>
                    </form:form>
                </div>
                
                
            </div>
        </div>
        <!-- /container -->
        
        <!-- SCRIPTS -->
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        
</body>
</html>