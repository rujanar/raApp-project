<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
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
                    <div class="div-bottom">
                    <h1>Greška</h1>
                    <p>Korisničko ime i/ili lozinka su netačni.</p> 
                    </div>
		</div>

        <jsp:include page="footer.jsp"/>
    </body>
</html>