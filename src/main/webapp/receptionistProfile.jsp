<%@page import="com.makwana.project.entity.Receptionist"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%!HttpSession httpSession=null;
Receptionist receptionist=null;
%>
<%
httpSession=request.getSession();
if(request.getAttribute("ReceptionistProfile")!=null)
{
 receptionist=(Receptionist)request.getAttribute("ReceptionistProfile");
httpSession.setAttribute("ReceptionistProfile", receptionist);
}
else
{
 receptionist=(Receptionist)httpSession.getAttribute("ReceptionistProfile");
 httpSession.setAttribute("ReceptionistProfile", receptionist);
}
	out.println(receptionist);
%>
<form action="ViewPatientAppointment.jsp">
<input type="submit" value="View Appointment">
</form>

<form action="ReceptionistCreateAppointment">
	<input type="submit" value="Urgent Appointment Book">
</form>

</body>
</html>