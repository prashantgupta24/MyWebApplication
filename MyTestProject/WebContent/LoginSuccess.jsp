<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome!</title>
<script>
$(document).ready(function(){
	$("#logout").click(function(){
		<%
			//session.removeAttribute("username");
			//session.invalidate();
		%>
	});
});
</script>
</head>
<body>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import = "java.util.*" %>

<%-- <c:set var="username" scope="session"></c:set> --%>


<c:if test="${empty sessionScope.username}" >
	<c:redirect url="MyJSPFile.jsp"></c:redirect>
</c:if>
	<c:out value="hello ${sessionScope.username}"></c:out>
<c:if test="${not empty sessionScope.lastLogin}">
	<br>
	<br>
	<c:out value="You last logged in at : " />
	<c:out value="${sessionScope.lastLogin}" />
</c:if>
	
	
<%-- <%
String username = (String) session.getAttribute("username");
if(username == null)
{
	response.sendRedirect("MyJSPFile.jsp");
}
else
{
	%>
	Hello <%= username %>!
	<%
}
%>
 --%>
<a href="logoff.jsp">Logout!</a>
</body>
</html>