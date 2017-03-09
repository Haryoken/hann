<%-- 
    Document   : RoomManager
    Created on : Jun 6, 2016, 9:11:07 PM
    Author     : haryoken
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Room Manage</title>
    </head>
    <body>
        <font>Welcome, ${sessionScope.USERNAME}</font><br/>
        (<a href="LogoutServlet">logout</a>)
        <h1>HOTEL ABC</h1>
        <form action="ProcessServlet" method ="POST">
            Room<input type="text" name="txtSearch" value="" />
            <input type="submit" value="Search" name="btAction"/>
            <input type="submit" value="Show All" name="btAction" /><br/>
        </form>
    <c:set var="searchValue" value="${param.txtSearch}"/>
    
        <h3>Search Result</h3>
        <c:set var="searchList" value="${requestScope.SEARCHRESULT}"/>
        <c:if test="${not empty searchList}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Room</th>
                        <th>Description</th>
                        <th>Hour Price</th>
                        <th>Day Price</th>
                        <th>Change Category</th>
                        <th>Damage Report</th>
                    </tr>
                </thead>
                <tbody> 
                    <c:forEach var="dto" items="${searchList}" varStatus="counter">
                    <form action="ProcessServlet" method="POST">
                        <tr>
                            <td>${counter.count}</td>
                            <td>${dto.roomID}</td>
                        <input type="hidden" name="RoomID" value="${dto.roomID}" />
                        <td>${dto.description}</td>
                        <td>${dto.hourPrice}</td>
                        <td>${dto.dayPrice}</td>                
                        <td style="text-align: center"> <input type="submit" value="Change" name="btAction" /> </td>
                        <td style="text-align: center"><input type="submit" value="Request" name="btAction" /></td>
                        <input type="hidden" name="CurrentCategoryID" value="${dto.categoryID}" />
                        </tr>
                    </form>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
    <c:if test="${empty searchList}">
        <h2>No Result Found</h2>
    </c:if>    



</body>
</html>
