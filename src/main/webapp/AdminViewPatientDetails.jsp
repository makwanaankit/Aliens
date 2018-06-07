<%@page import="com.makwana.project.dao.DoctorDao"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="com.makwana.project.entity.Doctor"%>
<%@page import="com.makwana.project.entity.Appointment"%>
<%@page import="java.text.SimpleDateFormat"%>
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
		int time = 0;
		String timeshow = "";
		String SDate;
		Date date;
		
		List<Doctor> lDoctors; 
		int flag=0;
			List<Appointment> lAppointments;
			int i=0;
		%>

		<%
			lAppointments=(List<Appointment>)request.getAttribute("PatientAllAppointment");
			DoctorDao ddao=(DoctorDao)request.getAttribute("DoctorDao");
		%>
		<table border=1>
		<tr>
			<td>Index</td>
			<td>DoctorName</td>
			<td>Date</td>
			<td>Time</td>
			<td>Discription</td>
			<td>Cost</td>
			<td>Payment type</td>
			<td>Medicine</td>
			<td>Edit</td>
		</tr>
		<%
		for (Appointment appointment : lAppointments) 
				{
					%>
							<tr>
								<td><%=++i%></td>
								<td><%= ddao.getDoctor(appointment.getD_id()).getD_Name()%></td>
					<%
											date = appointment.getDate();

											SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
											SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
											SimpleDateFormat df = new SimpleDateFormat("dd-MM-yy");

											SDate=sdf1.format(date);
						
											time = appointment.getTime();
											if (time == 1) 
											{
													timeshow = "10AM-12PM";
											}
											else 
												{
													timeshow = "6PM-9PM";
											}
						%>
			<td><%=SDate%></td>
			<td><%=timeshow%></td>
			<td><%=appointment.getDiscription()%></td>
			<td><%=appointment.getPayment()%></td>
			<td><%=appointment.getPayment_type()%></td> 
			<td><%=appointment.getMedicine()%></td>
			</tr>
		<%
		
			}
				i=0;
			//	out.println(lAppointments);
			
			%>


</body>
</html>