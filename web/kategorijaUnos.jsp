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
                        <c:if test="${kategorija.kategorija_id==null}">
                            <h3>Unos nove kategorije</h3>
                        </c:if>
                        <c:if test="${kategorija.kategorija_id!=null}">
                            <h3>Izmena podataka o kategoriji</h3>
                        </c:if>
                        
                        <form action="Podesavanja" method="POST" onsubmit="return checkform(this)">
                            <input type="hidden" value="${kategorija.kategorija_id}" name="kategorijaId">
                        <table> 
                            <c:if test="${kategorija.kategorija_id!=null}">
                        <tr>
                            <td><p class="text">Naziv: </p></td>
                            <td><input type="text" class="custom-form-date" value="${kategorija.naziv}" name="kategorijaNaziv" required></td>
                        </tr>
                        <tr>
                            <td><p class="text">Email: </p></td>
                            <td><input type="text" class="custom-form-date" value="${kategorija.email}" name="kategorijaEmail" required></td>
                        </tr>
                            </c:if>
                            
                            <c:if test="${kategorija.kategorija_id==null}">
                        <tr>
                            <td><p class="text">Naziv: </p></td>
                            <td><input type="text" class="custom-form-date" name="kategorijaNaziv" required></td>
                        </tr>
                        <tr>
                            <td><p class="text">Email: </p></td>
                            <td><input type="text" class="custom-form-date" name="kategorijaEmail" required></td>
                        </tr>
                            </c:if>
                            <tr>
                                <td><input type="submit" class="login-btn" value="Sačuvaj"></td>
                                <td></td>
                            </tr>
                        </table>             
                        </form>
                    </div>
                </div>
        </div>
         <jsp:include page="footer.jsp"/>
    </body>
</html>