<%@ page import="il.ac.hit.model.Item" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Arrays" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>User Interface</title>
    <link rel="stylesheet"
          href="http://code.jquery.com/mobile/1.3.1/jquery.mobile-1.3.1.min.css"/>
    <script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
    <script src="http://code.jquery.com/mobile/1.3.1/jquery.mobile-1.3.1.min.js"></script>
    <jsp:useBean id="user" type="il.ac.hit.model.User" scope="request"/>
</head>
<body>
<%@taglib prefix="tg" uri="/WEB-INF/tlds/mytld.tld" %>
<div data-role="page" data-title="Inbox">
    <div data-role="header">
        <h1>Welcome
            <jsp:getProperty name="user" property="firstName"/>
        </h1>
        <a href="/todolistcontroller/user/itemlist?id=logout" data-icon="power" data-inline="true"
           data-ajax="false" data-theme="b">Logout</a>
        <a href="/todolistcontroller/user/addItem" data-icon="plus" data-rel="dialog" data-inline="true" data-theme="c">Add
            Item</a>
    </div>
    <div data-role="main" class="ui-content" align="center">
        <ul data-role="listview" data-filter="true" data-inset="true">
            <%
                Item[] items = (Item[]) request.getAttribute("items");
                List<Item> itemList = Arrays.asList(items);
                if (itemList != null) {
                    for (Integer i = 0; i < itemList.size(); i++) {
                        Item item = itemList.get(i);
            %>
            <li><h2><% out.print(item.getName());%></h2><h4><%
                out.print(item.getDate());%></h4>
                <ul>
                    <li><a
                            href="/todolistcontroller/user/updateItem?name=<%=item.getName()%>&date=<%=item.getDate()%>&serial=<%=item.getSerialNum()%>"
                            data-rel="dialog">Edit</a></li>
                    <li><a
                            href="/todolistcontroller/user/itemlist?serial=<%=item.getSerialNum()%>" data-ajax="false">Delete</a>
                    </li>
                    <a href="/todolistcontroller/user/itemlist" data-role="button" data-icon="carat-l" data-inline="true">Back</a>
                </ul>
            </li>
            <% }
            }%>
        </ul>
        <%
            if (request.getAttribute("delete") == "delete") {
                response.sendRedirect("/todolistcontroller/user/itemlist");
            } else if (request.getAttribute("logout") == "logout") {
                response.sendRedirect("/todolistcontroller/user/login");
            } else if (request.getAttribute("edit") != null) {
                String url = String.format("/todolistcontroller/user/updateItem?name=%s&date=%s&serial=%s", request.getAttribute("name"),
                        request.getAttribute("date"), request.getAttribute("serial"));
                response.sendRedirect(url);
            }
        %>
    </div>
    <div data-role="footer" data-position="fixed" data-tap-toggle="false">
        <tg:myTags/>
    </div>
</div>
</body>
</html>