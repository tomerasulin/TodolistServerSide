<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title></title>
    <link rel="stylesheet"
          href="http://code.jquery.com/mobile/1.3.1/jquery.mobile-1.3.1.min.css"/>
    <script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
    <script src="http://code.jquery.com/mobile/1.3.1/jquery.mobile-1.3.1.min.js"></script>
</head>
<body>
<%@taglib prefix="tg" uri="/WEB-INF/tlds/mytld.tld" %>
<div data-role="page" id="add">
    <div data-role="header">
        <h1>Add New Item</h1>
    </div>
    <div data-role="content">
        <form method="post" action="/todolistcontroller/user/addItem" data-ajax="false">
            <label>Item:</label>
            <input type="text" name="item"/>
            <label>Date:</label>
            <input type="date" name="date" placeholder="YYYY-MM-DD"/>
            <input type="submit" value="Add"/>
            <%
                if (request.getAttribute("back") != null) {
                    response.sendRedirect("/todolistcontroller/user/itemlist");
                }
            %>
        </form>
    </div>
    <div data-role="footer" data-position="fixed" data-tap-toggle="false">
        <a href="/todolistcontroller/user/itemlist">Back</a>
        <tg:myTags/>
    </div>
</div>
</body>
</html>
