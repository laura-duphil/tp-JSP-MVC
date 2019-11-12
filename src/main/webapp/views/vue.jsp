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
        <form method='GET'>
            <div>
                <label for="code">Code :</label> <input input type="text" id="code" name="code"><br />
            </div>
            <div>
               <label for="taux">Taux :</label> <input input type="number" id="taux" name="howmuch" placeholder="0" step="0.01" min="0" max="100">
            </div>
            <input type="hidden" name="action" value="ADD">
            <input type="submit" value="Ajouter">
        </form>
        <br>
        <table border=2>
            <tr>
                <th>Code</th><th>Taux</th><th>Action</th>
            </tr>
            <c:forEach var="discount" items="${selectedCode}">
                <form>
                <tr>
                    <td> <input type="text" name = "code" value="${discount.code}"></td>
                    <td> <input type="text" name = "taux" value="${discount.taux}"></td>
                    <td><input type="Ssubmit" name = "action" value="DELETE"></td>
                </tr>
                </form>
            </c:forEach>
        </table>
    </body>
</html>
