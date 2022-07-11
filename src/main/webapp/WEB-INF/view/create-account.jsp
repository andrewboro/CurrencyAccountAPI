<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Account Creation Form</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<section>
    <h1>Enter account information below.</h1>
    <form:form action="processAccountCreationForm" method="POST">
        <label for="pesel">Pesel:</label> <input type="text" id="pesel" name="pesel"
                                                 placeholder="What's your pesel?"/><br>
        <label for="firstName">First name:</label> <input type="text" id="firstName" name="firstName"
                                                          placeholder="What's your first name?"/><br>
        <label for="lastName">Last name:</label> <input type="text" id="lastName" name="lastName"
                                                        placeholder="What's your last name?"/><br>
        <label for="initialAmount">Initial amount:</label> <input type="text" id="initialAmount" name="initialAmount"
                                                                  placeholder="What's the initial amount in PLN?"/><br>
        <input type="submit" value="Create Account"/>
    </form:form>
</section>
</body>
</html>