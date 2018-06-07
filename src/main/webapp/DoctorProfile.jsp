<%@page import="com.makwana.project.entity.Doctor"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%! Doctor d;
	HttpSession httpSession;
%>
	<%
		httpSession=request.getSession();
		if(httpSession.getAttribute("DoctorProfile")!=null)
		{
				d=(Doctor)httpSession.getAttribute("DoctorProfile");
		}
		else
		{
			d=(Doctor)request.getAttribute("doctorProfile");
			httpSession.setAttribute("DoctorProfile", d);
		}

			
	out.print(d);
	 %>

	<form action="DoctorAppointmentView.jsp">
	<input type="submit" value="ShowAllAppointment">
	</form>
	
	
	<form action="DoctorLeave.jsp">
	<input type="submit" value="CreateLeave">
	</form>
	
	<form action="ShowAllLeave">
	<input type="submit" value="Show Leave">
	</form>
	
	
	<form action="Today_Patient">
	<input type="submit" value="Attain_Patient">
	</form>
</body>
</html>