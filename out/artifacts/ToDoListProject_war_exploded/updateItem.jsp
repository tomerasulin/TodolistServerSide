<%@taglib prefix="tg" uri="/WEB-INF/tlds/mytld.tld" %>
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
<div data-role="page" id="home">
    <div data-role="header">
        <h1>Update Items</h1>
    </div>
    <div data-role="content">
        <form method="post" action="/todolistcontroller/user/updateItem?serial=<%=request.getAttribute("serial")%>" data-ajax="false">
            <label>new item:</label>
            <input type="text" id="item" name="newitem" value="<%=request.getAttribute("item")%>">
            <label>new date:</label>
            <input type="date" id="date" name="newdate" placeholder="MM/DD/YYYY" value="<%=request.getAttribute("date")%>">
            <input type="submit" value="Update"/>
            <%
                if(request.getAttribute("back") != null){
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
