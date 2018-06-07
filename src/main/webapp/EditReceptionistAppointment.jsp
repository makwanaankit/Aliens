<%@page import="com.makwana.project.entity.FamailyMember"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="com.makwana.project.entity.DoctorLeave"%>
<%@page import="java.util.List"%>
<%@page import="com.makwana.project.entity.Appointment"%>
<%@page import="com.makwana.project.entity.Patient"%>
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
	<%! Doctor doctor=null;
	FamailyMember famailyMember=null;
	Appointment appointment=null;
	List<DoctorLeave> doctorLeaves=null;
	String stime=null;
	String stimeShow=null;
	String strDate=null;
	int i=0;
	%>
	<% HttpSession httpSession=request.getSession();
		 doctor=(Doctor)httpSession.getAttribute("Doctor");
		 famailyMember=(FamailyMember)httpSession.getAttribute("Patient");
		 appointment=(Appointment)httpSession.getAttribute("Appointment");
		 doctorLeaves=(List<DoctorLeave>)httpSession.getAttribute("DoctorLeave");
		 if(appointment.getTime()==1)
		 {
			 stime="10AM-12PM";
		 }
		 else
		 {
			 stime="6PM-9PM";
		 }
		 
	%>
	<form action="updateReceptionistAppointment">
		Patient Name<input type="text" name="name" value="<%=famailyMember.getName() %>" readonly>
		<%-- Mobile Number<input type="text" name="mobileNumber" value="<%=patient.getMobileNumber() %>" readonly>
		 --%>Doctor Name<input type="text" name="DoctorName" value="<%=doctor.getD_Name() %>" readonly>
		Date<input type="date" name="date">
		Time:<select name="editRecpAppTime">
			<option value="1">10.00AM- 12.PM</option>
			<option value="2">6.00PM- 9.PM</option>
			</select>
			<input type="submit"  name="Edit">
			</form>
			<table border=2>
			<tr>
					<td>Index</td>
					<td>Doctor Name</td>
					<td>Leave Date</td>
					<td>Leave Time</td>
			</tr>
			<%
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			
					for(DoctorLeave dl:doctorLeaves)
					{
						strDate = dateFormat.format(dl.getLeave_Date());			
						
						
						if(dl.getLeave_Time()==1)
						{
							stimeShow="10AM-12PM";
						}
						else
						{
							stimeShow="6PM-9PM";
						}
						%>
						<tr>
								<td><%=++i %></td>
								<td><%=doctor.getD_Name() %></td>
								<td><%=strDate %></td>
								<td><%=stimeShow%></td>
							
						</tr>
						
						<%
					}
			i=0;	
			%>
			</table>
<%
		if(request.getAttribute("DateError")!=null)
		{
			out.print(request.getAttribute("DateError"));
		}
		if(request.getAttribute("AppointmentUpdated")!=null)
		{
			out.print(request.getAttribute("AppointmentUpdated"));
		}
%>
		<form action="BackAfterUpdate">
		
		<input type="submit" value="back" name="back">
		</form>
		<form action="receptionistProfile.jsp">
			<input type="submit" value="Profile">
			</form>
</body>
</html>