<%@page import="com.makwana.project.entity.FamailyMember"%>
<%@page import="com.makwana.project.entity.Appointment"%>
<%@page import="com.makwana.project.dao.AppointmentBookDao"%>
<%@page import="com.makwana.project.dao.DoctorDao"%>
<%@page import="org.springframework.beans.factory.annotation.Autowired"%>
<%@page import="com.makwana.project.entity.Patient"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%! List<FamailyMember> famailyMembers;
	int i=0;
	List<Appointment> lAppointments;
%>

<form action="AdminViewPatientdetails">
<table border=2>
<tr>
		<td>Index</td>
		<td>patient Name</td>
		<td>Mobile Number</td>
		<td>Gender</td>
		<td>User Name</td>
		<td>Visit</td>
		<td>View Informantion</td>
</tr>
	
<%
 famailyMembers=(List<FamailyMember>)request.getAttribute("AllPatient");


AppointmentBookDao appointmentBookDao=(AppointmentBookDao)request.getAttribute("AppointmentBookDao");



int chl=famailyMembers.size(); 
	int i=0;

for(FamailyMember p1 : famailyMembers)
  {
	lAppointments=appointmentBookDao.numberOfVisit(p1.getP_id());
  //out.println(p1.getMobileNumber());
  %>
  <tr>
  		<td><%=++i %></td>
  		<td><%=p1.getName() %></td>
  		<%-- <td><%=p1.getMobileNumber() %></td> --%>
  		<td><%=p1.getGender() %></td>
  	<%-- 	<td><%=p1.getUser_name() %></td> --%>
 		<td><%=lAppointments.size() %>
  		<td><input type="radio" name="p1" value="<%=p1.getP_id() %>"></td>
  		
  </tr>
  
  <%  
  }
i=0;
  %>

</table>
<input type="submit">
</form>
</body>
</html>