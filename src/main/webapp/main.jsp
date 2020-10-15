<%-- 
    Document   : main
    Created on : 13. 10. 2020, 17:16:54
    Author     : Petr Smejkal
--%>
<?encoding="UTF-8"?>
<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<!-- Main webpage with table of users and form for adding new users -->
<head>
    <title>Záznamy</title>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>

<body>
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>Jméno a Přijmení</th>
                <th>E-mail</th>
                <th>Datum narození</th>
                <th>Pohlaví</th>
                <th>Maximálně dosažené vzdělání</th>
                <th>Zájmy</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="user" items="${listUser}">

                <tr>
                    <td>
                        <c:out value="${user.name_surname}" />
                    </td>
                    <td>
                        <c:out value="${user.email}" />
                    </td>
                    <td>
                        <c:out value="${user.birthday}" />
                    </td>
                    <td>
                        <c:out value="${user.gender}" />
                    </td>
                    <td>
                        <c:out value="${user.education}" />
                    </td>
                    <td>
                        <c:out value="${user.hobby}" />
                    </td>
                </tr>
            </c:forEach>
            
        </tbody>

    </table>
    <form action = "/web/insert" method = "GET" accept-charset="utf-8">
        Jméno a přijmení: <input type = "text"  name = "name_surname" pattern=".+ .+" required>
        <br />
        E-mail: <input type = "email"  name = "email" required/>
        <br />
        Datum narození: <input type ="date"  name="birthday" required/>
        <br />
        Pohlaví: <br><input type="radio" id="male" name="gender" value="Muž" required>
        <label for="male">Muž</label><br>
        <input type="radio" id="female" name="gender" value="Žena">
        <label for="female">Žena</label><br>
        Maximálně dosažené vzdělání: <select name="education" value="<c:out value='${user.education}'/>" required>
            <option value="Bez vzdělání">Bez vzdělání</option>
            <option value="Neúplné základní vzdělání">Neúplné základní vzdělání</option>
            <option value="Základní vzdělání">Základní vzdělání</option>
            <option value="Nižší střední vzdělání">Nižší střední vzdělání</option>
            <option value="Nižší střední odborné vzdělání">Nižší střední odborné vzdělání</option>
            <option value="Střední odborné vzdělání s výučním listem">Střední odborné vzdělání s výučním listem</option>
            <option value="Střední nebo střední odborné vzdělání bez maturity i výučního listu">Střední nebo střední odborné vzdělání bez maturity i výučního listu</option>
            <option value="Úplné střední všeobecné vzdělání">Úplné střední všeobecné vzdělání</option>
            <option value="Úplné střední odborné vzdělání s vyučením i maturitou">Úplné střední odborné vzdělání s vyučením i maturitou</option>
            <option value="Úplné střední odborné vzdělání s maturitou (bez vyučení)">Úplné střední odborné vzdělání s maturitou (bez vyučení)</option>
            <option value="Vyšší odborné vzdělání">Vyšší odborné vzdělání</option>
            <option value="Vyšší odborné vzdělání v konzervatoři">Vyšší odborné vzdělání v konzervatoři</option>
            <option value="Vysokoškolské bakalářské vzdělání">Vysokoškolské bakalářské vzdělání</option>
            <option value="Vysokoškolské magisterské vzdělání">Vysokoškolské magisterské vzdělání</option>
            <option value="Vysokoškolské doktorské vzdělání">Vysokoškolské doktorské vzdělání</option>
        </select>
        <br>
        Zájmy: <br>
        <input type="checkbox" name="hobby1" value="Sport">
        <label for="hobby1">Sport</label><br>
        <input type="checkbox" name="hobby2" value="Literatura">
        <label for="hobby2">Literatura</label><br>
        <input type="checkbox" name="hobby3" value="Programování">
        <label for="hobby3">Programování</label><br>
        <input type="checkbox" name="hobby4" value="Cestování">
        <label for="hobby4">Cestování</label><br>
        <input type="checkbox" onclick="var input = document.getElementById('other'); if (this.checked) {
                    input.disabled = false;
                    input.focus();
                } else {
                    input.disabled = true;
                }" /> Jiné... 
        <input id="other" name="other" disabled="disabled"/>
        <br />
        <input type = "submit" value="Založit" name="submit" />
    </form>
</body>

