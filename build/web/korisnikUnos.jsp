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
                        <c:if test="${korisnik.korisnik_id==null}">
                            <h3>Unos novog korisnika</h3>
                        </c:if>
                        <c:if test="${korisnik.korisnik_id!=null}">
                            <h3>Izmena podataka korisnika</h3>
                        </c:if>
                        
                        <form action="Podesavanja" method="POST">
                            <input type="hidden" value="${korisnik.korisnik_id}" name="korisnikId">
                        <table> 
                            <c:if test="${korisnik.korisnik_id!=null}">
                        <tr>
                            <td><p class="text">Ime: </p></td>
                            <td><input type="text" class="custom-form-date" value="${korisnik.ime}" name="korisnikIme" required></td>
                        </tr>
                        <tr>
                            <td><p class="text">Prezime: </p></td>
                            <td><input type="text" class="custom-form-date" value="${korisnik.prezime}" name="korisnikPrezime" required></td>
                        </tr>
                        <tr>
                            <td><p class="text">Email: </p></td>
                            <td><input type="text" class="custom-form-date" value="${korisnik.email}" name="korisnikEmail" required></td>
                        </tr>
                        <tr>
                            <td><p class="text">Korisničko ime: </p></td>
                            <td><input class="custom-form-date" type="text" value="${korisnik.username}" name="user" required></td>
                        </tr>    
                        <tr>
                            <td><p class="text">Lozinka: </p></td>
                            <td><input class="custom-form-date" type="password" value="${korisnik.password}" name="pass" id="pass" required></td>
                        </tr>
                        <tr>
                            <td><p class="text">Potvrdite lozinku: </p></td>
                            <td><input type="password" class="custom-form-date" name="passconfirm" id="passconfirm" onchange='check()' required></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><span id='message'></span></td>
                        </tr>    
                        <tr>
                            <td><p class="text">Kategorija: </p></td>
                            <td class="td-dropdown">
                                <div class="select-style-pod">
                                    <select name="KorisnikKateg" required>
                                        <c:forEach items="${kategorije}" var="kategorija">
                                            <c:if test="${korisnik.kor_kategorija_id==kategorija.kategorija_id}">
                                                <option selected value="${kategorija.kategorija_id}" ><c:out value="${kategorija.naziv}"/></option>  
                                            </c:if>
                                            <c:if test="${korisnik.kor_kategorija_id!=kategorija.kategorija_id}">
                                                <option value="${kategorija.kategorija_id}"><c:out value="${kategorija.naziv}"/></option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </div>
                            </td>
                        </tr>    
                        <tr>
                            <td><p class="text">Uloga: </p></td>
                            <td class="td-dropdown">
                                <div class="select-style-pod">
                                    <select name="KorisnikUloga" required>
                                        <c:forEach items="${uloge}" var="uloga">
                                            <c:if test="${korisnik.kor_uloga_id==uloga.uloga_id}">
                                                <option selected value="${uloga.uloga_id}" ><c:out value="${uloga.naziv}"/></option>  
                                            </c:if>
                                            <c:if test="${korisnik.kor_uloga_id!=uloga.uloga_id}">
                                                <option value="${uloga.uloga_id}"><c:out value="${uloga.naziv}"/></option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </div>
                            </td>
                        </tr>   
                            </c:if>
                            
                            <c:if test="${korisnik.korisnik_id==null}">
                        <tr>
                            <td><p class="text">Ime: </p></td>
                            <td><input type="text" class="custom-form-date" name="korisnikIme" required></td>
                        </tr>
                        <tr>
                            <td><p class="text">Prezime: </p></td>
                            <td><input type="text" class="custom-form-date" name="korisnikPrezime" required></td>
                        </tr>
                        <tr>
                            <td><p class="text">Email: </p></td>
                            <td><input type="text" class="custom-form-date" name="korisnikEmail" required></td>
                        </tr>
                        <tr>
                            <td><p class="text">Korisničko ime: </p></td>
                            <td><input type="text" class="custom-form-date" name="user" required></td>
                        </tr>
                        <tr>
                            <td><p class="text">Lozinka: </p></td>
                            <td><input type="password" class="custom-form-date" name="pass" id="pass2" required></td>
                        </tr>
                        <tr>
                            <td><p class="text">Potvrdite lozinku: </p></td>
                            <td><input type="password" class="custom-form-date" name="passconfirm" id="passconfirm2" onchange='check2()' required></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><span id='message2'></span></td>
                        </tr>
                        <tr>
                            <td><p class="text">Kategorija: </p></td>
                            <td class="td-dropdown">
                                <div class="select-style-pod">
                                    <select name="KorisnikKateg" required>
                                            <option value="" selected disabled hidden>Izaberite kategoriju</option>
                                        <c:forEach items="${kategorije}" var="kategorija">
                                            <option value="${kategorija.kategorija_id}">${kategorija.naziv}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td><p class="text">Uloga: </p></td>
                            <td class="td-dropdown">
                                <div class="select-style-pod">
                                    <select name="KorisnikUloga" required>
                                            <option value="" selected disabled hidden>Izaberite ulogu</option>    
                                        <c:forEach items="${uloge}" var="uloga">
                                            <option value="${uloga.uloga_id}" >${uloga.naziv}</option> 
                                        </c:forEach>
                                    </select>
                                </div>
                            </td>
                        </tr> 
                            </c:if>
                            <tr>
                                <td><input type="submit" class="login-btn" id="submit" value="Sačuvaj" disabled></td>
                                <td></td>
                            </tr>
                        </table>             
                        </form>
                    </div>
                </div>
                <script>
                    var check = function() {
                    if (document.getElementById('pass').value ===
                    document.getElementById('passconfirm').value) {
                    document.getElementById('message').style.color = 'green';
                    document.getElementById('message').innerHTML = 'Lozinka je ispravna';
                    document.getElementById('submit').disabled = false;
                } else {
                    document.getElementById('message').style.color = 'red';
                    document.getElementById('message').innerHTML = 'Lozinka nije ispravna';
                    document.getElementById('submit').disabled = true;
                }
            };
            var check2 = function() {
                    if (document.getElementById('pass2').value ===
                    document.getElementById('passconfirm2').value) {
                    document.getElementById('message2').style.color = 'green';
                    document.getElementById('message2').innerHTML = 'Lozinka je ispravna';
                    document.getElementById('submit').disabled = false;
                } else {
                    document.getElementById('message2').style.color = 'red';
                    document.getElementById('message2').innerHTML = 'Lozinka nije ispravna';
                    document.getElementById('submit').disabled = true;
                }
            }
                </script>
        </div>
       <jsp:include page="footer.jsp"/>
    </body>
</html>