<%@page import="com.makwana.project.entity.FamailyMember"%>
<%@page import="com.makwana.project.entity.Patient"%>
<%@page import="com.makwana.project.entity.Appointment"%>
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
	int pid=0;%>


	<form action="ViewAppointment">

		<input type="date" name="date"> Time:<select
			name="AppointmentTime">
			<option value="1">10.00AM- 12.PM</option>
			<option value="2">6.00PM- 9.PM</option>

		</select> <input type="submit" value="Show">
	</form>
	<table border=1>
		<tr>
			<td>Index</td>
			<td>Patient Name</td>
			<td>Date</td>
			<td>Time</td>
		</tr>

		<%
			HttpSession httpSession = request.getSession();
			List<FamailyMember> lPatients = (List<FamailyMember>) httpSession.getAttribute("AllPatient");

			List<Appointment> lAppointments = (List<Appointment>) request.getAttribute("ListOfSpecificAppointment");

			if (lAppointments == null) {
				out.println(" from nullaaasasas");
			} else {
				String str;
				System.out.println("aaasasas"+lAppointments.toString());
				System.out.println("aaasasas"+lPatients.toString());
				
				for (Appointment appointment : lAppointments)
				{
					pid=appointment.getP_id();
					System.out.print(pid);
					if (appointment.getTime() == 1) {
						str = "10AM-12PM";
					} else {
						str = "6PM-9PM";
					}
				
		%>
		<tr>
			<td><%=++i%></td>
			<td><%=lPatients.get(pid-1).getName()%></td>
			<td><%=appointment.getDate()%></td>
			<td><%=str%></td>

		</tr>
		<%
			}

				i = 0;
			}
		%>
	</table>
	
	<form action="DoctorProfile.jsp">
		<input type="submit" value="back">
	</form>
	

</body>
</html>