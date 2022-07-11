<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html>
<head>
    <title>Account</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<section>
    <h1>Account Information for Pesel: ${param.pesel}</h1>
    ${message}
    <form:form action="exchangePlnToUsd" method="POST">
        <input type="hidden" id="pesel" name="pesel" value="${param.pesel}">
        <label for="amount">Amount:</label> <input type="text" id="amount" name="amount"
                                                   placeholder="What's the amount in PLN to exchange to USD?"/><br>
        <input type="submit" value="Exchange PLN to USD"/>
    </form:form>
    <br>
    <form:form action="exchangeUsdToPln" method="POST">
        <input type="hidden" id="pesel" name="pesel" value="${param.pesel}">
        <label for="amount">Amount:</label> <input type="text" id="amount" name="amount"
                                                   placeholder="What's the amount in USD to exchange to PLN?"/><br>
        <input type="submit" value="Exchange USD to PLN"/>
    </form:form>
</section>
</body>
</html>