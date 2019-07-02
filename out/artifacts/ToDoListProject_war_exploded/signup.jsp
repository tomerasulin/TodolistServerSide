<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
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
    <%
        Cookie cookie = null;
        Cookie[] cookies = null;
        cookies = request.getCookies();
        String userId = "";

        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                cookie = cookies[i];
                if (cookie.getName().equals("id")) {
                    userId = cookie.getValue();
                }
            }
        }
    %>
</head>
<body>
<div data-role="page">
    <div data-role="header">
        <a href="/todolistcontroller/user/login" class="ui-btn-right" data-theme="c">Back</a>
        <h2>Sign up a new user</h2>
    </div>
    <div data-role="content" align="center">
        <img src="http://pluspng.com/img-png/to-do-list-png-the-power-of-a-to-do-list-imodelafrica-1024.png"
             width="95" height="95">
        <%
            if (request.getAttribute("next") != null) {
                response.sendRedirect("/todolistcontroller/user/itemlist");
            }

            String message = (String) request.getAttribute("error");
            String user = (String) request.getAttribute("id");
            if (message != null) {
        %><span class="error"><%=message%></span>
        <script>
            $("span").fadeOut(3000);
        </script>
        <%
            }%><%
        if (user != null) {%>
        <span class="error"><%=user%></span>
        <script>
            $("span").fadeOut(3000);
        </script>
        <%
            }%>
        <form method="post" action="/todolistcontroller/user/signup" data-ajax="false">
            <label for="id">Enter Your ID: <span class="error">*</span></label>
            <input type="text" id="id" name="id" required/>
            <label for="id">Enter Your First Name: <span class="error">*</span></label>
            <input type="text" id="firstName" name="firstName" required/>
            <label for="id">Enter Your Last Name: <span class="error">*</span></label>
            <input type="text" id="lastName" name="lastName" required/>
            <label for="id">Enter Your Password: <span class="error">*</span></label>
            <input type="password" id="password" name="password" required/>
            <input type="submit" value="submit" data-inline="true" data-theme="b">

        </form>
    </div>
</div>
<div data-role="footer" id="footer" data-position="fixed" data-tap-toggle="false">
    <tg:myTags/>
</div>
</body>
</html>
