<%-- 
    Document   : change
    Created on : Jun 6, 2016, 9:11:46 PM
    Author     : haryoken
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
          <title>Change Category</title>
    </head>
    <body>
        <font>Welcome, ${sessionScope.USERNAME}</font><br/>
        (<a href="LogoutServlet">logout</a>)
        <h1>Change Category</h1>

        <form action="ProcessServlet" method="POST">
            <table border="0">           
                <tbody>
                    <tr >
                        <td style="width: 200px">Room</td>
                        <td style="text-align: right"><%=(String) request.getAttribute("ROOMID")%></td>
                    </tr>
                    <tr>
                        <td style="width: 200px">Category</td>
                        <c:set var="category" value="${requestScope.CATEGORY}"/>
                        <%
                            List<String> nameList = (List<String>) request.getAttribute("CATEGORYNAME");
                            String currentCategory = request.getParameter("CurrentCategoryID");
                            
                            DAO dao = new DAO();
                            currentCategory = dao.getCategoryName(currentCategory);
                        %>
                        <td> <select name="changedCategory">
                                <option><%=currentCategory%></option>
                                <%
                                    nameList.remove(currentCategory);
                                    for (String name : nameList) {
                                %><option><%=name%></option><%
                                    }
                                %>              
                            </select> </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td> <input type="submit" value="Save" name="btAction" /> </td>
                    </tr>

                </tbody>
            </table>
            <input type="hidden" name="RoomID" value=<%=(String) request.getAttribute("ROOMID")%>  /> 
        </form>
    </body>
</html>
