<%-- 
    Document   : vue
    Created on : 6 nov. 2019, 14:16:07
    Author     : pedago
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Saisie d'un taux de remise</title>
    </head>
    <body>
        <h1>Edition des taux de remise</h1>
        <form>
            Code : <input input type="text" name="code"><br />
            Taux : <input input type="text" name="taux">
            <input type="submit" value="Ajouter">
        </form>
        <table>
            <tr>
                <th>Code</th><th>Taux</th><th>Action</th>
            <c:forEach var="entry" items="${discount}">
                <tr>
                    <td> ${entry.code} </td>
                    <td> ${entry.taux} </td>
                    <td><input type="submit" value="DELETE"></td>
                </tr>
            </c:forEach>
            </tr>
        </table>
    </body>
</html>
