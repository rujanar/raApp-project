<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="sr">
    <head>
        <c:url value="/resources/css/style.css" var="cssURL"/>
        <link  rel="stylesheet" href= "${cssURL}">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>RA Aplikacija</title>
    </head>

    <body>
		<div class="container">
                    <jsp:include page="header.jsp"/>
                    <div class="content">
                        <div class="div-bottom">
                        <h3>Pregled Vaših aktivnosti koje su u toku</h3>
                        </div>
                        
                        <!-- Ispis ako ima pronadjenih rezultata  -->
                        <c:if test="${not empty trenutniZadaci}">
			<table class="hover-table">
                            <!-- Naslovi  -->
				<tr>
					<th>Redni broj</th>
					<th>Zadaci</th>
					<th>Nalogodavac</th>
					<th>Rok za <br>završetak zadatka</th>
					<th>Aktivnosti</th>
					<th>Potrebno <br>vreme (h)</th>
					<th>Rok za aktivnosti</th>
					<th>Izvršilac aktivnosti</th>
                                        <th>&nbsp;&nbsp;&nbsp;Status&nbsp;&nbsp;&nbsp;</th>
                                        <th>Promeni status</th>
                                </tr>  
                                <!-- Kreiranje liste za broj aktivnosti za svaki zadatak (potrebno za spajanje redova) -->
                                <% 
                                List num = new ArrayList();
                                %>
                                <c:forEach items="${trenutniZadaci}" var="zadatak">
                                    <% int counter = 0; %>
                                    <c:forEach items="${aktivnosti}" var="aktivnost">
                                        <c:if test="${aktivnost.akt_zadatak_id==zadatak.zadatak_id}">
                                            <c:if test="${aktivnost.status=='U toku'}">
                                            <% counter++;%>
                                            </c:if>
                                        </c:if>
                                    </c:forEach>
                                    <% num.add(counter); %>
                                </c:forEach> 
                                <c:out value="${num.get(1)}"/>
                                <!-- Brojač za redni broj  -->
                                <% int i = 0; %>
                                
                                <!-- Ispis zadataka  -->
                                <c:forEach items="${trenutniZadaci}" var="zadatak">
                                    <tr>
                                        <!-- Redni broj  -->
                                            <% out.print("<td class='td-centar' rowspan='" + num.get(i) + "'>" + (i+1) + "."); %> <% out.print("</td>");%>
                                            
                                        <!-- Opis zadatka  -->
                                        <% out.print("<td rowspan='" + num.get(i) + "'>"); %><c:out value="${zadatak.opis}"/> <% out.print("</td>");%>
                                        
                                        <!-- Nalogodavac  -->
                                            <% out.print("<td class='td-centar' rowspan='" + num.get(i) + "'>"); %>
                                            <c:forEach items="${korisnici}" var="korisnici">
                                                    <c:if test="${zadatak.zad_korisnik_id==korisnici.korisnik_id}">
                                                    <c:out value="${korisnici.ime}" /> <c:out value="${korisnici.prezime}" />
                                                    </c:if>
                                                </c:forEach>
                                             <% out.print("</td>");%>
                                            
                                        <!-- Rok izvrsenja zadatka  -->
                                        <% out.print("<td class='td-centar' rowspan='" + num.get(i) + "'>"); %><c:out value="${zadatak.zadatak_rok}"/> <% out.print("</td>");%>
    
                                        <c:forEach items="${aktivnosti}" var="aktivnost">
                                        <c:if test="${aktivnost.akt_zadatak_id==zadatak.zadatak_id}">
                                            <c:if test="${aktivnost.status=='U toku'}">
                                            <!-- Opis aktivnosti za svaki zadatak  -->
                                            <td><c:out value="${aktivnost.aktivnost_opis}" /></td>
                                            
                                            <!-- Potrebno vreme aktivnosti  -->
                                            <td class='td-centar'><c:out value="${aktivnost.potrebno_vreme}" /></td>
                                            
                                            <!-- Rok aktivnosti -->
                                            <td class='td-centar'><c:out value="${aktivnost.aktivnost_rok}"/></td>
                                            
                                            <!-- Izvrsilac aktivnosti -->
                                            <td class='td-centar'>
                                                <c:forEach items="${korisnici}" var="korisnici">
                                                    <c:if test="${aktivnost.akt_korisnik_id==korisnici.korisnik_id}">
                                                    <c:out value="${korisnici.ime}" /> <c:out value="${korisnici.prezime}" />
                                                    </c:if>
                                                </c:forEach>
                                            </td>
                                            
                                            <!-- status aktivnosti -->
                                            <td class='td-centar' style="background-color: #fafacc;"><c:out value="${aktivnost.status}" /></td>
                                            
                                            <!-- Akcije za zadatak -->
                                            <td class="td-centar-zad">
                                                <ul>
                                                    <li class="action-li-zavrseno">
                                                        <a class="a-action-li-status" onclick="return confirm('Da li ste sigurni da želite da promenite status?')" href="Pregled?action=statusz&aktivnost_id=${aktivnost.aktivnost_id}">Završeno</a>
                                                    </li>
                                                </ul>   
                                            </td>
                                    </tr>
                                        </c:if>
                                        </c:if>
                                    </c:forEach> 
                                    <% i++;%>
                                </c:forEach>
			</table>
                        </c:if>
                        <div class="div-bottom">
                        <!-- Ispis ako nema pronadjenih rezultata  -->
                        <c:if test="${empty trenutniZadaci}">
                            <h4>Nemate aktivnosti koje su u toku.</h4>
                        </c:if>
                        </div>
		</div>
	</div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>