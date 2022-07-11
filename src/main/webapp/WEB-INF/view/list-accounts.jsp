<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
<head>
    <title>Account</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<section>
    <h1>Account List</h1>
    <table>
        <tr>
            <th>Pesel</th>
            <th>Link</th>
        </tr>
        <c:forEach items="${themeList}" var="theme" varStatus="status">
            <tr>
                <td>${theme}</td>
                <td><a href="showAccountInformation?pesel=${theme}">View Info</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>