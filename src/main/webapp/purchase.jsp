<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Log in with your account</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

<div class="container">
	<c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <h2>Welcome ${pageContext.request.userPrincipal.name}&emsp;<a onclick="document.forms['logoutForm'].submit()">Logout</a>&nbsp;&nbsp;&nbsp;<a href="${contextPath}/purchase">Transaction</a>&nbsp;&nbsp;&nbsp;<a href="${contextPath}/refill">Credit</a></h2>
    </c:if>
	
    <!-- form method="POST" action="${contextPath}/purchase" modelAttribute="wallet">
        <div> 
	        <h2>Wallet Balance</h2><h2>${udetails}</h2>
	        <h3 class="form-heading">Purchase Amount</h3>
	        <p><input name="cat"  type="text" class="form-control" placeholder="Category"/></p>
	        <h3 class="form-heading">Purchase Amount</h3>
	        <p><input name="amt"  type="text" class="form-control" placeholder="Amount"/></p>
	        <input type="hidden" width="200px" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	        <p><button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button></p>
        </div>
    </form-->
    
    <form:form method="POST" action="purchase" modelAttribute="wallet">
        <div> 
        <h2>Wallet Balance</h2><h2>${udetails}</h2>
	        <h2 class="form-signin-heading">Create your account</h2>
       		<spring:bind path="category">
	            <div class="form-group ${status.error ? 'has-error' : ''}">
	                <!-- form:input type="text" path="category" class="form-control" placeholder="Category" autofocus="true"--><!-- /form:input-->
	                <!-- form:errors path="category"--><!-- /form:errors-->
	                
	                <form:select path="category" class="form-control"  autofocus="true">
	                	<form:option value="Supermarkets">Supermarkets</form:option>
		                <form:option value="Medical">Medical Services</form:option>
		                <form:option selected="selected" value="Restaurants">Restaurants</form:option>
		                <form:option value="entertainment">Travel/Entertainment</form:option>
		                <form:option value="merchandise">Merchandise/Retails</form:option>	                
	                </form:select>
	                <form:errors path="category"></form:errors>
	                
	            </div>
        	</spring:bind>

        	<spring:bind path="purchaseAmt">
	            <div class="form-group ${status.error ? 'has-error' : ''}">
	                <form:input type="text" path="purchaseAmt" class="form-control" placeholder="Amount"></form:input>
	                <form:errors path="purchaseAmt"></form:errors>
	            </div>
        	</spring:bind>

	        <p><button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button></p>
        </div>
    </form:form>
    

</div>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
