<%@page import="com.makwana.project.entity.Admin"%>
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
		Admin d=(Admin)request.getAttribute("AdminProfile");
	out.print(d);
	
/* 	HttpSession httpSession=request.getSession();
	httpSession.setAttribute("DoctorProfile", d);
	 */
	%>
	
	<form action="ShowAllDoctorS">
	<input type="submit">
	</form>
	
	<form action="ViewPatientAppointment.jsp">
	<input type="submit" value="Show Appointment">
	</form>
	
	<form action="ShowAll">
	<input type="submit" value="Show All Patient">
	
	</form>
	
	<!-- <a href="ShowAllDoctorS"> submit</a> -->
	
</body>
</html>