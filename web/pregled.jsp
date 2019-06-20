<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="sr">
    <head>
        <c:url value="/resources/css/style.css" var="cssURL"/>
        <c:url value="/resources/css/jquery.datetimepicker.min.css" var="JScssURL"/>
        <c:url value="/resources/js/jquery.js" var="jsURL"/>
        <c:url value="/resources/js/datetimepicker.js" var="jsURL3"/>
        <c:url value="/resources/js/jquery.datetimepicker.full.js" var="jsURL2"/>
        <link  rel="stylesheet" href= "${cssURL}">
        <link  rel="stylesheet" href= "${JScssURL}">
        <script type="text/javascript" src="${jsURL}"></script>
        <script type="text/javascript" src="${jsURL2}"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>RA Aplikacija</title>
    </head>

    <body>
	<div class="container">
                    <jsp:include page="header.jsp"/>
            <div class="content">
                <div class="div-bottom">
                <h3>Pregled svih zadataka i aktivnosti</h3>
                </div>
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
                            <th>Status</th>
                            <th>Opcije</th>
                        </tr>
                                
                            <!-- Kreiranje liste za broj aktivnosti za svaki zadatak (potrebno za spajanje redova) -->
                            <% 
                            List num = new ArrayList();
                            %>
                                <c:forEach items="${zadaci}" var="zadatak">
                                    <% int counter = 0; %>
                                <c:forEach items="${aktivnosti}" var="aktivnost">
                                    <c:if test="${aktivnost.akt_zadatak_id==zadatak.zadatak_id}">
                                            <% counter++;%>
                                    </c:if>
                                </c:forEach>
                                    <% num.add(counter); %>
                                </c:forEach> 
                                
                            <!-- Brojač za redni broj  -->
                                <% int i = 0; %>
                                
                        <!-- Ispis zadataka  -->
                            <c:forEach items="${zadaci}" var="zadatak">
                        <tr>
                                        <!-- Redni broj  -->
                                            <% out.print("<td class='td-centar' rowspan='" + num.get(i) + "'>" + (i+1) + "."); %> <% out.print("</td>");%>
                                            
                                        <!-- Opis zadatka  -->
                                            <% out.print("<td rowspan='" + num.get(i) + "'>"); %><c:out value="${zadatak.opis}"/></td>
                                        
                                        <!-- Nalogodavac  -->
                                            <% out.print("<td class='td-centar' rowspan='" + num.get(i) + "'>"); %>
                                            <c:forEach items="${korisnici}" var="korisnici">
                                                    <c:if test="${zadatak.zad_korisnik_id==korisnici.korisnik_id}">
                                                    <c:out value="${korisnici.ime}" /> <c:out value="${korisnici.prezime}" />
                                                    </c:if>
                                                </c:forEach>
                                            </td>
                                            
                                        <!-- Rok izvrsenja zadatka  -->
                                            <% out.print("<td class='td-centar' rowspan='" + num.get(i) + "'>"); %><c:out value="${zadatak.zadatak_rok}"/></td>
    
                                        <c:set var="print" value="${0}" />
                                        <c:forEach items="${aktivnosti}" var="aktivnost">
                                        <c:if test="${aktivnost.akt_zadatak_id==zadatak.zadatak_id}">
                                            
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
                                            <c:choose>
                                                <c:when test="${aktivnost.status=='Nezapočeto'}">
                                            <td class='td-centar' style="background-color: #d0f0c0;"><c:out value="${aktivnost.status}" /></td>
                                                </c:when>
                                                <c:when test="${aktivnost.status=='U toku'}">
                                            <td class='td-centar' style="background-color: #fafacc;"><c:out value="${aktivnost.status}" /></td>
                                                </c:when>
                                                <c:when test="${aktivnost.status=='Završeno'}">
                                            <td class='td-centar' style="background-color: #FFD9E0;"><c:out value="${aktivnost.status}" /></td>
                                                </c:when>
                                            </c:choose>
                                            <c:if test="${print==0}">
                                            
                                            <!-- Akcije za zadatak -->
                                            <% out.print("<td class='td-centar-zad' rowspan='" + num.get(i) + "'>"); %>
                                                <ul>
                                                    <li class="action-li">
                                                        <a class="a-action-li" href="Pregled?action=edit&zadatak_id=${zadatak.zadatak_id}">Izmeni</a>
                                                    </li>
                                                    <li class="action-li">
                                                        <a class="a-action-li" onclick="return confirm('Da li ste sigurni da želite da obrišete zadatak?')" href="Pregled?action=delete&zadatak_id=${zadatak.zadatak_id}">Obriši</a>
                                                    </li>
                                                </ul>    
                                            </td>
                                            <c:set var="print" value="${1}"/>
                                        </c:if>
                                    </tr>
                                        </c:if>
                                    </c:forEach> 
                                    <% i++;%>
                                </c:forEach>
			</table>
                                
                <div class="div-bottom">
                    <h3>Kriterijumi za pretragu</h3>
                    <form action="Pretraga" method="POST">
                        <table>
                            <tr>
                                <td><p class="text">Nalogodavac: </p></td>
                                <td class="td-dropdown">
                                    <div class="select-style">
                                        <select name="pNalogodavac">
                                            <option value="" selected disabled hidden>Izaberite nalogodavca</option> 
                                                <c:forEach items="${korisnici}" var="korisnik">
                                            <option value="${korisnik.korisnik_id}"><c:out value="${korisnik.ime}"/>&nbsp;<c:out value="${korisnik.prezime}"/></option>
                                                </c:forEach>
                                        </select>
                                    </div>
                                </td>
                                <td></td>
                                <td></td>
                            </tr>
                            <tr>
                                <td><p class="text">Tekst zadatka: </p></td>
                                <td><input class="custom-form-date" type="text" name="pTekst"></td>
                                <td></td>
                                <td></td>
                            </tr>
                            <tr>
                                <td><p class="text">Vremenski period od: </p></td>
                                <td><input class="custom-form-date" type="text" name="pPeriod1" id="datetime11"></td>
                                <td><p class="text"> do: </p></td>
                                <td><input class="custom-form-date" type="text" name="pPeriod2" id="datetime12"></td>
                            </tr>
                            <tr>
                                <td colspan="4"><input class="login-btn" type="submit" value="Pretraži"></td>
                            </tr>
                        </table>
                    </form>
                </div>
                    
            </div>
     
	</div>	
        <script type="text/javascript" src="${jsURL3}"></script>
        <script>                        
        function obrisi()
        {
        var x = confirm("Are you sure you want to delete?");
        if (x)
            return true;
        else
          return false;
        }
        </script>
        <jsp:include page="footer.jsp"/>
    </body>
</html>