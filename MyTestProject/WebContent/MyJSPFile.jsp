<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Test page</title>
<script>
$(document).ready(function(){
	
	$("#name").focus();
	
	$("#submit_button").click(function(event){

		if($("#name").val() == "")
			{
				alert("Enter name!!")
				$("#name").focus();
					return false;
			}
		
		if($("#pass").val() == "")
			{
				alert("Enter password!");
				$("#pass").focus();
				return false;
			}
		/* $.post("servlet1", 
				{
					name: $("#name").val(),
					pass: $("#pass").val()
				}, function(data){	
				
					var obj = JSON.parse(data);
					
					var status = obj.status;

					if(status == "yes")
						{
							$("#para").css('color','blue');
							$("#para").text("Hello "+obj.info.name);
							var login = obj.info.login;
							var lTimes = "";
							for(var i=0;i<login.length;i++)
								{
									lTimes+=obj.info.login[i].time;
									lTimes+="\n";
								}
							$("#times").text("You last logged in at: "+lTimes);
						    window.location.href = "LoginSuccess.jsp";
						}
					else
						if(status == "no")
						{
							$("#para").css('color','red');
							$("#para").text("Invalid!!");
						}
		}); */
	});
});
</script>
</head>
<body>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%@page import = "java.util.Date" %>
<%!
private String getDate()
{
	Date date = new Date();
	return date.toString();
}
%>
<%
//response.addHeader("Refresh", "1");
/* if(session.getAttribute("username") != null)
{
	RequestDispatcher dispatcher = request.getRequestDispatcher("LoginSuccess.jsp");
	dispatcher.forward(request, response);
}
else
{
	String s = new String("The time now is : ");
	StringBuilder sb = new StringBuilder();
	sb.append(s); */
	%>
	
<c:if test="${not empty sessionScope.username}">
	<c:redirect url="LoginSuccess.jsp" />
</c:if>	
<c:if test="${empty sessionScope.username }">
	<c:set var="s" value="Through JSTL, the time now is : " />
	<c:set var="date" value="<%=new java.util.Date()%>" />
</c:if>
<c:out value="${s}" />
<c:out value="${date}" />

	
	<div>
	 	<form action ="servlet1" method = "post">
		<br>
		Enter Username <input type="text" name ="name" id = "name">
		<br>
		Enter Password <input type="password" name ="pass" id = "pass">
		<br>
		<input type="submit" id ="submit_button">
		</form>
		<br>
		<br>
		<div id="para">
			This is a test area. This will let you know if your login succeeded or not!
		</div>
		<br>
		<div id ="times">
		This will display all the login times of the user!
		</div>
		
		
	</div>
<%
//}
%>
</body>
</html>