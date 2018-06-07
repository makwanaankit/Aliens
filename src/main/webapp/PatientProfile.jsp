<%@page import="com.makwana.project.entity.FamailyMember"%>
<%@page import="com.makwana.project.entity.Appointment"%>
<%@page import="java.util.List"%>
<%@page import="com.makwana.project.entity.Patient"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%!FamailyMember famailyMember;
HttpSession httpSession;

%>
	<%
		if(request.getAttribute("PatientProfile")!=null)
		{
			famailyMember=(FamailyMember)request.getAttribute("PatientProfile");
		out.print(famailyMember);
		httpSession=request.getSession();
		httpSession.setAttribute("PatientProfile",famailyMember);
		}
		else
		{
			httpSession=request.getSession();
			famailyMember=(FamailyMember)httpSession.getAttribute("PatientProfile");
			out.print(famailyMember);
			httpSession.setAttribute("PatientProfile",famailyMember);
		}
		
		
	%>
	<form action="Appointment">
	<input type="submit" value="AppointmentBook">
	</form>
	
	<form action="PatientAppointmentView">
	<input type="submit" value="Appointmentdetails">
	</form>
	
		
	
	<form action="AddFamilyMember.jsp">
	<input type="submit" value="addFamilyMember">
	</form>
	
	<form action="ViewMember">
	<input type="submit" value="ViewFamilyMember">
	</form>
	
</body>
</html>