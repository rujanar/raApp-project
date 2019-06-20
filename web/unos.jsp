<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML>

<html lang="sr">
    <head>
        <c:url value="/resources/css/style.css" var="cssURL"/>
        <c:url value="/resources/css/jquery.datetimepicker.min.css" var="JScssURL"/>
        <c:url value="/resources/js/jquery.js" var="jsURL"/>
        <c:url value="/resources/js/jquery.datetimepicker.full.js" var="jsURL2"/>
        <c:url value="/resources/js/datetimepicker.js" var="jsURL3"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link  rel="stylesheet" href= "${cssURL}">
        <link  rel="stylesheet" href= "${JScssURL}">
        <script type="text/javascript" src="${jsURL}"></script>
        <script type="text/javascript" src="${jsURL2}"></script>
        <title>RA Aplikacija</title>

    </head>
    <body>
	<div class="container">
            <jsp:include page="header.jsp"/>
		<div class="content">
                    
                    <div class="div-bottom">
                        <h3>Kreirajte novi zadatak ili izmenite postojeći</h3>
                        <form action="Unos" method="POST">
			<p class="text">Zadatak:</p><input class="custom-form" type="text" name="opis" value="${zadatak.opis}" required><br>
                        <p class="text">Rok za završetak zadatka:</p><input class="custom-form-date" type="text" id="datetime" name="zadatakRok" value="${zadatak.zadatak_rok}" required><br>
                        
			<br>
			<table id="tabela-aktivnosti">
				<tr>
					<th>Aktivnost</th>
					<th>Potrebno vreme</th>
					<th>Rok</th>
					<th>Izvršilac</th>
                                        <th>Status</th>
					<th>Opcije</th>
				</tr>
                                <input type="hidden" value="${zadatak.zadatak_id}" name="zadatakId">
                                <!-- Prikaz 10 praznih redova za unos aktivnosti za opciju "Unos" -->
                                
                                <c:if test="${zadatak.zadatak_id==null}">
                                <c:forEach begin="1" end="10" var="brojac">
				<tr>
					<td class="firstcol"><textarea class="custom-textarea" name="aktivnostOpis${brojac}" rows="2"></textarea></td>
                                        <td><input class="custom-form-date" type="text" name="potrebnoVreme${brojac}" ></td>
                                        <td><input class="custom-form-date" type="text" id="datetime${brojac}" name="aktivnostRok${brojac}" autocomplete="off"></td>
                                        <td class="td-dropdown">
                                            <div class="select-style">
                                                <select name="izvrsilac${brojac}">
                                                    <option value="" selected disabled hidden>Izaberite izvršioca</option> 
                                                <c:forEach items="${korisnici}" var="korisnik">
                                                    <option value="${korisnik.korisnik_id}"><c:out value="${korisnik.ime}"/>&nbsp;<c:out value="${korisnik.prezime}"/></option>
                                                </c:forEach>
                                            </select>
                                            </div>
                                        </td>
                                        <td class="td-dropdown">
                                            <div class="select-style">
                                                <select name="status${brojac}" onchange="this.style.backgroundColor=this.options[this.selectedIndex].style.backgroundColor">
                                                <option value="" selected disabled hidden>Izaberite status</option> 
                                                <option style="background-color:#d0f0c0">Nezapočeto</option>
                                                <option style="background-color:#fafacc">U toku</option>
                                                <option style="background-color:#FFD9E0">Završeno</option>
                                            </select>
                                            </div>
                                        </td>
					<td>
                                            <ul>
                                                <li class="action-li-disabled">
                                                    <a class="a-action-li">&nbsp;&nbsp;Obriši&nbsp;&nbsp;</a>
                                                </li>
                                            </ul>       
                                        </td>
                                </tr>
                                    </c:forEach>
                                </c:if>
                                
                                <!-- Prikaz popunjenih redova za aktivnosti za opciju "Izmeni" -->
                                
                                <c:if test="${zadatak.zadatak_id!=null}">
                                    <c:set var="brojac" value="${0}"/>
                                    <c:forEach items="${aktivnost}" var="aktivnost">
                                        <c:if test="${zadatak.zadatak_id==aktivnost.akt_zadatak_id}">
                                            <c:set var="brojac" value="${brojac+1}"/>
                                            <input type="hidden" value="${aktivnost.aktivnost_id}" name="aktivnostId${brojac}">
				<tr>
					<td class="firstcol"><textarea class="custom-textarea" name="aktivnostOpis${brojac}" rows="2">${aktivnost.aktivnost_opis}</textarea></td>
                                        <td><input class="custom-form-date" type="text" name="potrebnoVreme${brojac}" value="${aktivnost.potrebno_vreme}"></td>
                                        <td><input class="custom-form-date" type="text" id="datetime${brojac}" name="aktivnostRok${brojac}" value="${aktivnost.aktivnost_rok}" autocomplete="off"></td>
                                        <td class="td-dropdown">
                                            <div class="select-style">
                                                <select name="izvrsilac${brojac}" >
                                                
                                                <c:forEach items="${korisnici}" var="korisnik">
                                                    <c:if test="${korisnik.korisnik_id==aktivnost.akt_korisnik_id}">
                                                        <option selected value="${korisnik.korisnik_id}"><c:out value="${korisnik.ime}"/>&nbsp;<c:out value="${korisnik.prezime}"/></option>
                                                    </c:if>
                                                    <c:if test="${korisnik.korisnik_id!=aktivnost.akt_korisnik_id}">
                                                    <option value="${korisnik.korisnik_id}"><c:out value="${korisnik.ime}"/>&nbsp;<c:out value="${korisnik.prezime}"/></option>
                                                    </c:if>
                                                </c:forEach>
                                            </select>
                                            </div>
                                        </td>
                                        <td class="td-dropdown">
                                            <div class="select-style">
                                                <select name="status${brojac}" onchange="this.style.backgroundColor=this.options[this.selectedIndex].style.backgroundColor">
                                                <c:choose> 
                                                    <c:when test="${aktivnost.status=='Nezapočeto'}">
                                                        <option selected style="background-color:#d0f0c0">Nezapočeto</option>
                                                        <option style="background-color:#fafacc">U toku</option>
                                                        <option style="background-color:#FFD9E0">Završeno</option>
                                                    </c:when>
                                                    <c:when test="${aktivnost.status=='U toku'}">
                                                        <option style="background-color:#d0f0c0">Nezapočeto</option>
                                                        <option selected style="background-color:#fafacc">U toku</option>
                                                        <option style="background-color:#FFD9E0">Završeno</option>
                                                    </c:when>
                                                    <c:when test="${aktivnost.status=='Završeno'}">
                                                        <option style="background-color:#d0f0c0">Nezapočeto</option>
                                                        <option style="background-color:#fafacc">U toku</option>
                                                        <option selected style="background-color:#FFD9E0">Završeno</option>
                                                    </c:when>
                                                </c:choose>
                                            </select>
                                            </div>
                                        </td>
					<td>
                                            <ul>
                                                <li class="action-li">
                                                    <a class="a-action-li" onclick="return confirm('Da li ste sigurni da želite da obrišete aktivnost?') "href="Unos?action=brisi&aktivnost_id=${aktivnost.aktivnost_id}&akt_zadatak_id=${aktivnost.akt_zadatak_id}" >&nbsp;&nbsp;Obriši&nbsp;&nbsp;</a>
                                                </li>
                                            </ul>    
                                        </td>
                                </tr>
                                
                                        </c:if>
                                    </c:forEach>
                                
                                <!-- Prikaz ostalih nepopunjenih redova za aktivnosti za opciju "Izmeni" -->
                                
                                <c:forEach begin="${brojac+1}" end="10" var="brojac2">
                                <tr>
					<td class="firstcol"><textarea class="custom-textarea" name="aktivnostOpis${brojac2}" rows="2"></textarea></td>
                                        <td><input class="custom-form-date" type="text" name="potrebnoVreme${brojac2}" ></td>
                                        <td><input class="custom-form-date" type="text" id="datetime${brojac2}" name="aktivnostRok${brojac2}" autocomplete="off"></td>
                                        <td class="td-dropdown">
                                            <div class="select-style">
                                                <select name="izvrsilac${brojac2}">
                                                    <option value="" selected disabled hidden>Izaberite izvršioca</option> 
                                                <c:forEach items="${korisnici}" var="korisnik">
                                                    <option value="${korisnik.korisnik_id}"><c:out value="${korisnik.ime}"/>&nbsp;<c:out value="${korisnik.prezime}"/></option>
                                                </c:forEach>
                                            </select>
                                            </div>
                                        </td>
                                        <td class="td-dropdown">
                                            <div class="select-style">
                                                <select name="status${brojac2}" onchange="this.style.backgroundColor=this.options[this.selectedIndex].style.backgroundColor">
                                                <option value="" selected disabled hidden>Izaberite status</option> 
                                                <option style="background-color:#d0f0c0">Nezapočeto</option>
                                                <option style="background-color:#fafacc">U toku</option>
                                                <option style="background-color:#FFD9E0">Završeno</option>
                                            </select>
                                            </div>
                                        </td>
					<td>
                                            <ul>
                                                <li class="action-li-disabled">
                                                    <a class="a-action-li">&nbsp;&nbsp;Obriši&nbsp;&nbsp;</a>
                                                </li>
                                            </ul>    
                                        </td>
                                </tr>
                                </c:forEach>
                                </c:if>
                                <tr>
                                    <td><input class="login-btn" type="submit" value="Sačuvaj"></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
			</table>
			</form>
		</div>
                </div>
            <script type="text/javascript" src="${jsURL3}"></script>
        </div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>