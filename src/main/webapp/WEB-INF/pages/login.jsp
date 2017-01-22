<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login Form</title>
    <link rel='stylesheet prefetch' href='http://netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap.min.css'>
    <link href="<c:url value="/resources/style.css" />" rel="stylesheet">
</head>


<body>
        <div class="wrapper">
            <form class="form-signin" action="login" method='POST' >
                <h2 class="form-signin-heading">Please login</h2>

                <input type="text" class="form-control" name="username" placeholder="Enter Username" required="" autofocus="" />
                <input type="password" class="form-control" name="password" placeholder="Enter Password" required="" /> <br>
                <button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
            </form>
        </div>
</body>
</html>
