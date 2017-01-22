<%--
  Created by IntelliJ IDEA.
  User: mateusz
  Date: 30.11.16
  Time: 23:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>User list</title>
</head>
<body>
<div align="center">
    <h1>Users List</h1>

<ol>
        <c:forEach var="user" items="${webUsers}" varStatus="status">
            <li> ${user.name} -> ${user.password}</li>
 <%--           <tr>
                <td>${user.name}</td>
                <td>${user.password}</td>
                <td>
                    :)
                </td>
            </tr>--%>
        </c:forEach>
</ol>
</div>
</body>
</html>
</body>
</html>
