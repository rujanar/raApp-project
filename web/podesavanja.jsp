<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML>

<html lang="sr">
    <head>
        <c:url value="/resources/css/style.css" var="cssURL"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link  rel="stylesheet" href= "${cssURL}">
        <title>RA Aplikacija</title>

    </head>
    <body>
	<div class="container">
            <jsp:include page="header.jsp"/>
		<div class="content">
                    
                    <div class="div-bottom">
                        <h3>Korisnici</h3>
                        <table class="hover-table-pdsv">
                        <tr>
                            <th>Korisnik</th>
                            <th>Email</th>
                            <th>Kategorija</th>
                            <th>Uloga</th>
                            <th>Opcije</th>
                        </tr>
                        <c:forEach items="${korisnici}" var="korisnik">
                        <tr>
                            <td><c:out value="${korisnik.ime}"/>&nbsp;<c:out value="${korisnik.prezime}"/></td>
                            <td><c:out value="${korisnik.email}"/></td>
                            <c:forEach items="${kategorije}" var="kategorija">
                                <c:if test="${korisnik.kor_kategorija_id==kategorija.kategorija_id}">
                                    <td><c:out value="${kategorija.naziv}"/></td>
                                </c:if>
                            </c:forEach>
                            <c:forEach items="${uloge}" var="uloga">
                                <c:if test="${korisnik.kor_uloga_id==uloga.uloga_id}">
                                    <td><c:out value="${uloga.naziv}"/></td>
                                </c:if>
                            </c:forEach>
                            <td>
                                <ul>
                                    <li class="action-li">
                                        <a class="a-action-li" href="Podesavanja?action=edit&korisnik_id=${korisnik.korisnik_id}">Izmeni</a>
                                    </li>
                                    <li class="action-li">
                                        <a class="a-action-li" onclick="return confirm('Da li ste sigurni da želite da obrišete korisnika?')" href="Podesavanja?action=delete&korisnik_id=${korisnik.korisnik_id}">&nbsp;&nbsp;Briši&nbsp;&nbsp;</a>
                                    </li>
                                </ul>        
                            </td>
                        </tr>
                        </c:forEach>
                    </table>
                    <ul>
                        <li class="action-li">
                            <a class="a-action-li" href="Podesavanja?action=insert">Novi unos</a>
                        </li>
                    </ul>
                        
                    <h3>Kategorije</h3>
                    <table class="hover-table-pdsv">
                        <tr>
                            <th>Naziv</th>
                            <th>Email</th>
                            <th>Opcije</th>
                        </tr>
                        <c:forEach items="${kategorije}" var="kategorija">
                        <tr>
                            <td><c:out value="${kategorija.naziv}"/></td>
                            <td><c:out value="${kategorija.email}"/></td>
                            <td>
                                <ul>
                                    <li class="action-li">
                                        <a class="a-action-li" href="Podesavanja?action=kedit&kategorija_id=${kategorija.kategorija_id}">Izmeni</a>
                                    </li>
                                    <li class="action-li">
                                        <a class="a-action-li" onclick="return confirm('Da li ste sigurni da želite da obrišete kategoriju?')" href="Podesavanja?action=kdelete&kategorija_id=${kategorija.kategorija_id}">&nbsp;&nbsp;Briši&nbsp;&nbsp;</a>
                                    </li>
                                </ul>        
                            </td>
                        </tr>
                        </c:forEach>
                    </table>
                    <ul>
                        <li class="action-li">
                            <a class="a-action-li" href="Podesavanja?action=kinsert">Novi unos</a>
                        </li>
                    </ul>
                    
                    <h3>Uloge</h3>
                    <table class="hover-table-pdsv">
                        <tr>
                            <th>Naziv</th>
                            <th>Ovlašćenja</th>
                        </tr>
                        <c:forEach items="${uloge}" var="uloga">
                        <tr>
                            <td><c:out value="${uloga.naziv}"/></td>
                            <td><c:out value="${uloga.ovlascenja}"/></td>
                        </tr>
                        </c:forEach>
                    </table>
                    </div>
                </div>
        </div>
       <jsp:include page="footer.jsp"/>
    </body>
</html>