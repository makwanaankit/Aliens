<%@page import="com.makwana.project.entity.Doctor"%>
<%@page import="java.util.List"%>
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
			List<Doctor> lDoctors=null;
			
		%>
		<%
				lDoctors=(List<Doctor>)request.getAttribute("Doctors");
		%>
		<form action="ReceptionAppointBook">
				Patient Name:<input type="text">
				Doctro:<select name="doctorID">
			<% 
				for(Doctor d:lDoctors)
				{
			%>
			<option value="<%=d.getD_Id()%>"><%=d.getD_Name()%></option>
				<%
				}
				%>
				
				
			</select>
	Date:<input type="date" name="AppointmentDate" >	
		
	Time:<select name="AppointmentTime">
			<option value="1">10.00AM- 12.PM</option>
			<option value="2">6.00PM- 9.PM</option>
			
		 </select>
		 
		 <input type="submit" value="Book">
		</form>

</body>
</html>