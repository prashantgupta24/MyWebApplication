<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Logging off</title>
</head>
<body>
<%

//response.sendRedirect("MyJSPFile.jsp");

%>

<h2>You are now logging off...</h2>
<%
session.removeAttribute("username");
session.invalidate();
response.setHeader("refresh", "2;MyJSPFile.jsp");
//response.sendRedirect("MyJSPFile.jsp");
%>
</body>
</html>