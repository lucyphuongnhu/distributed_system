<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%String errorMessage = (String) request.getAttribute("errorMessage");
    if (errorMessage == null) {
        errorMessage = "";
    }%>
<html>
    <head>
        <title>Log in page</title>
        <link rel="stylesheet" href="loginPage.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
              crossorigin="anonymous">
    </head>

    <body>
        <div class="pageContainer">
            <h2> Log in </h2>
            <p><%=errorMessage%></p>
            <form action="/login" method="POST" name="loginForm" onsubmit="return handleLogin()">
                <div class="inputField">
                    <label for="usernameInput" class="form-label"> Username </label>
                    <input type="text" class="form-control" id="usernameInput" name="username">
                </div>
                <div class="inputField">
                    <label for="passwordInput" class="form-label"> Password </label>
                    <input type="password" class="form-control" id="passwordInput" name="password">
                </div>

                <button type="submit" class="btn btn-primary loginButton" > Log in </button>
            </form>
        </div>

    </body>
</html>
