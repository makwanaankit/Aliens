<%@page import="com.makwana.project.entity.FamailyMember"%>
<%@page import="com.makwana.project.entity.Patient"%>
<%@page import="java.util.Date"%>
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
<%! int i=0;
List<Appointment> lAppointments=null;
int time_id=0;
String stime=null;
FamailyMember famailyMember=null;
%>
<%
famailyMember=(FamailyMember)request.getAttribute("Patient");
%>
<form action="UpdateTreatMent">

	Name:<input type="text" name="name" value="<%=famailyMember.getName()%>" readonly><br>
	<%-- MobileNumber:<input type="text" name="mobileNumber"  value="<%=famailyMember.getMobileNumber() %>" readonly><br>
	Address:<input type="text" name="Address"  value="<%=patient.getAddress() %>" readonly><br>
	 --%>
	 Discription:<input type="text" name="Discription"><br>
	Medicine<input type="text" name="Medicine"><br>
	
	Payment:<input type="text"  name="payment"><br>
	Payment:<select name="payment_type">
					<option value="Cash">Cash</option>
					<option value="Card_Payment">Card_Payment</option>
			</select>
			<br>
 	<input type="submit">
 	</form>
 		<table border=2>
		<tr>
			<td>Index</td>
			<td>Date</td>
			<td>Time</td>
			<td>Discription</td>
			<td>Medicine</td>
			<td>Ammount</td>
			<td>Payment Type</td>
			</tr>
			<%
			
			lAppointments=(List<Appointment>)request.getAttribute("PatientHistory");		
			for(Appointment appointment:lAppointments)
			{
				if(appointment.getDate().compareTo(new Date())<0)
				%>
			<tr>
				<td><%=++i %></td>			
				
				<td><%=appointment.getDate() %></td>
					<%
						time_id=appointment.getTime();
						if(time_id==1)
						{
							stime="10AM-12PM";
						}
						else
						{
							stime="6PM-9PM";
						}
					%>
				
				<td><%=stime%></td>
				<td><%=appointment.getDiscription() %></td>
				<td><%=appointment.getDiscription() %></td>
					<td><%=appointment.getPayment() %></td>
						<td><%=appointment.getPayment_type() %></td>
				</tr>
				
				<%
			}
				 i=0;
			%>
		</table>

<form action="Today_Patient">
<input type="submit" value="back">
</form>

</body>
</html>