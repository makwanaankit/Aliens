<%@page import="com.makwana.project.entity.Doctor"%>
<%@page import="java.util.List"%>
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
<%!
	Appointment appointment;
	List<Doctor> lDoctors;
%>
<form action="updatePatientAppointment">
	Doctor<select name="DoctorID">
<%
		HttpSession httpSession=request.getSession();
		lDoctors=(List<Doctor>)httpSession.getAttribute("AllDoctor");
		for(Doctor doctor:lDoctors)
		{
%>
		<option value="<%=doctor.getD_Id() %>"><%=doctor.getD_Name() %></option>
<%
		}

%>
</select>
Date <input type="date" name="date">
Time:<select name="AppointmentTime">
			<option value="1">10.00AM- 12.PM</option>
			<option value="2">6.00PM- 9.PM</option>
		
		 </select><input type="submit" name="E1" value="EditAppointment">
	</form>
	
	<%
		if(request.getParameter("E1")!=null)
		{
			out.println(request.getAttribute("DateSelectError").toString());
		}
	%>
	
	
</body>
</html>