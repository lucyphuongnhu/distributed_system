<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
            <form action="/login" method="POST">
                <div class="inputField">
                    <label for="usernameInput" class="form-label"> Username </label>
                    <input type="email" class="form-control" id="usernameInput">
                </div>
                <div class="inputField">
                    <label for="passwordInput" class="form-label"> Password </label>
                    <input type="password" class="form-control" id="passwordInput">
                </div>

                <button type="submit" class="btn btn-primary loginButton" > Log in </button>
            </form>
        </div>

    </body>
</html>
