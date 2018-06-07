<%@page import="com.makwana.project.entity.Appointment"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
		Integer count=(Integer)request.getAttribute("Count");
		int c=count.intValue();

		if(c>2)
		{
			out.print("appointment is full");
		}
		else
		{
			Appointment ap=(Appointment)request.getAttribute("AppointmentDetail");
			out.print(ap.getDate());
		}
	

	
%>


</body>
</html>