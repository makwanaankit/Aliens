<%@page import="com.makwana.project.entity.FamailyMember"%>
<%@page import="com.makwana.project.entity.Appointment"%>
<%@page import="com.makwana.project.entity.Patient"%>
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
<%! List<FamailyMember> lFamailyMembers=null;
	List<Appointment> lAppointments=null;
	int i=0;
	int j=0;
	HttpSession httpSession=null;
%>
<form action="Treat_Patient">
		<table border=2>
		<tr>
			<td>Index</td>
			<td>Name</td>
			<td>Mobile No</td>
			<td>Date</td>
			<td>Time</td>
			<td>SelectPatient</td>
		</tr>
				<%
					httpSession=request.getSession();
					lAppointments=(List<Appointment>)httpSession.getAttribute("PatientAttend");				
					lFamailyMembers=(List<FamailyMember>)httpSession.getAttribute("Patients");
					out.print(lFamailyMembers);
					out.print(lAppointments);
					
					for(Appointment appointment:lAppointments)
					{
						if(appointment.getPayment()==0)
						{
					%>
						<tr>
							<td><%=++i %></td>
							<td><%=lFamailyMembers.get(j).getName()%></td>
							
						<%-- 	<td><%=lFamailyMembers.get(j).getMobileNumber()%></td>
						 --%>	<td><%=appointment.getDate() %></td>
							<td><%=appointment.getTime() %></td>
							<%-- <td><input type="radio" name="r1" value="<%=appointment.getP_id()%>"></td> --%>
							
								<td><input type="radio" name="r1" value="<%=appointment.getAppointment_ID()%>"></td>
						</tr>			
							
						<%
						j=j+1;
					}
					}
					j=0;
					i=0;
				%>
			
		</table>
	<input type="submit">
</form>

</body>
</html>