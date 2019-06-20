<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <c:url value="/resources/css/style.css" var="cssURL"/>
        <link  rel="stylesheet" href= "${cssURL}">
        <title>RA Aplikacija</title>
    </head>

    <body>
		<div class="container">
			<div class="top-bar">
			<p>RA Aplikacija</p>
			</div>
		</div>
		<div class="content">
			<div class="loginform">
                            <form action="Login" method="POST">
				<br>
				<p class="logintext">Korisničko ime:</p><input class="custom-form" type="text" name="username">
				<p class="logintext">Lozinka:</p><input class="custom-form" type="password" name="password">
				<input class="login-btn" type="submit" value="Prijava"> 
			</form>
			</div>
                        
		</div>

        <jsp:include page="footer.jsp"/>
    </body>
</html>