<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="sr">
    <head>
        <c:url value="/resources/css/style.css" var="cssURL"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Radne aktivnosti</title>
    </head>

    <body>
                    <div class="nav-bar">
			<div class="top-bar">
				<div class="top-bar-left">
					<p id="title">Radne aktivnosti</p>
				</div>
				<div class="top-bar-right">
                                    <p id="welcome">Dobrodošli,</p>
                                    <c:out value="${trenutniKorisnik.ime}"/>
                                    <c:out value="${trenutniKorisnik.prezime}"/>
                                    <div class="logout-div">
                                    <form action="${pageContext.request.contextPath}" method="post">
                                        <input class="log-out" type="submit" value="Odjava" />
                                    </form>
                                    </div>
				</div>
			</div>
			<div class="middle-bar">
                            <ul>
                                <li class="nav-li">
                                    <a class="a-nav-li" href="Unos?action=unos">Unos</a>
                                </li>
                                <li class="nav-li">
                                    <a class="a-nav-li" href="Pregled?action=pregled">Pregled</a>
                                </li>
                                <li class="nav-li">
                                    <a class="a-nav-li" href="Pregled?action=nezapoceto">Nezapočeto</a>
                                </li>
                                <li class="nav-li">
                                    <a class="a-nav-li" href="Pregled?action=utoku">U toku</a>
                                </li>
                                <li class="nav-li">
                                    <a class="a-nav-li" href="Pregled?action=zavrseno">Završeno</a>
                                </li>
                                <li class="nav-li">
                                    <a class="a-nav-li" href="Podesavanja?action=pregled">Podešavanja</a>
                                </li>
                            </ul>
			</div>
                    </div>
    </body>
</html>