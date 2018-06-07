<%@page import="com.makwana.project.entity.Doctor"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="com.makwana.project.entity.Appointment"%>
<%@page import="com.makwana.project.dao.AppointmentBookDao"%>
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


	<%!int i = 0;
	int time = 0;
	String timeshow = "";
	String SDate;
	Date date;
	List<Doctor> lDoctors; 
	int flag=0;
	%>
<form action="editPatientAppointment">
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
		HttpSession httpSession=request.getSession();
		
		
			List<Appointment> lAppointments = (List<Appointment>) httpSession.getAttribute("AppointmentBookDetils");
			lDoctors=(List<Doctor>)httpSession.getAttribute("AllDoctor");
			
			if (lAppointments != null) 
			{
				
				for (Appointment appointment : lAppointments) 
				{
					%>
							<tr>
								<td><%=++i%></td>
								<td><%=lDoctors.get(appointment.getD_id()-1).getD_Name()%></td>
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
			<td><%=appointment.getDiscription() %>
			<td><%=appointment.getPayment()%>
			<td><%=appointment.getPayment_type() %></td> 
			<td><%=appointment.getMedicine() %></td>
			<%
			int id=appointment.getAppointment_ID();
				if(appointment.getDate().compareTo(new Date())<0)
				{
				
				}
				else
				{

					out.println("<td><input type=radio name=e1 value="+id+"");
					
				}
			
			
			
			
			/* if(appointment.getDiscription().equals(""))
				{
					out.println("<td><input type=radio name=e1 value="+id+"");
				}
				else
				{
					
				} */
				
			
			%>
			
			
			<%-- <%
			if(appointment.getDiscription()!=null)
			{
			
				out.println("<td><input type=radio name=e1 value="+id+"");
				
			%>	
			<td><input type="radio" name="e1" value="<%=appointment.getAppointment_ID() %>">
			<%
			}
			else
			{
				int id=appointment.getAppointment_ID();
				out.println("<td><input type=radio name=e1 value="+id+"");
				
				%>
						<td><input type="radio" name="e1" value="<%=appointment.getAppointment_ID() %>">
				
				
				<%
			}
			%> --%>
<%-- 			<td><a href="editPatientAppointment/${appointment.getAppointment_ID()}">Edit</a></td>
 --%>		</tr>
		<%
		
			}
				i=0;
			//	out.println(lAppointments);
			} 
			%>

	</table>
			<input type="submit" name="b1" value="Edit">
			 
	</form>
	
	<form action="PatientProfile.jsp">
			<input type="submit" value="back">
	</form>
	
	
	<%
			if(request.getParameter("b1")!=null)
			{
				out.print("Appointment is done");
			}
	%>
</body>
</html>