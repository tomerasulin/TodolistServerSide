<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content='width=device-width, initial-scale=1.0' >
    <title></title>
    <link rel="stylesheet"
          href="http://code.jquery.com/mobile/1.3.1/jquery.mobile-1.3.1.min.css"/>
    <script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
    <script src="http://code.jquery.com/mobile/1.3.1/jquery.mobile-1.3.1.min.js"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <style type="text/css">
        <%@include file="css/styles.css" %>
    </style>

    <%@taglib prefix="tg" uri="/WEB-INF/tlds/mytld.tld" %>
</head>
<body>
<div class="container">
    <div>
        <div class="text-center">
            <img src="http://pluspng.com/img-png/to-do-list-png-the-power-of-a-to-do-list-imodelafrica-1024.png"
                 width="95" height="95">
            <h3>Todo List</h3>
            <form method="post" action="/todolistcontroller/user/login" data-ajax="false">
                <div class="form-group">
                    <input type="text" class="form-control" name="id" placeholder="id" required autofocus>
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" name="password" placeholder="password"
                           required>
                </div>
                <input type="submit" id="login" class="btn btn-lg btn-primary btn-block" value="Login"/>
            </form>
            <a href="/todolistcontroller/user/signup" data-role="button">Sign-Up</a>
            <%
                if (request.getAttribute("next") != null) {
                    response.sendRedirect("/todolistcontroller/user/itemlist");
                }
                String message = (String) request.getAttribute("error");
                if (message != null) {
            %><span class="error"><%=message%></span>
            <script>
                $("span").fadeOut(3000);
            </script>
            <%
                }%>
        </div>
    </div>
</div>
<div data-role="footer" data-position="fixed" data-tap-toggle="false">
    <tg:myTags/>
</div>
</div>
</body>
</html>
