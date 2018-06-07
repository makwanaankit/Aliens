<%@page import="java.util.List"%>
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
	int i=0;
%>
<form action="AdminViewLeave">
		<table border=1>
		<tr>
				<td>Index</td>
				<td>Doctor Name</td>
				<td>Doctor Mobile Number</td>
				<td>Doctor Degree</td>
				
		</tr>
		<%	
				List<Doctor> lDoctors=(List<Doctor>)request.getAttribute("AllDoctor");
				for(Doctor d:lDoctors)
				{
					%>
					<tr>
							<td><%=++i %></td>
							<td><%=d.getD_Name() %></td>
							<td><%=d.getD_MobileNumber() %></td>
							<td><%=d.getD_Qualification() %></td>
							<td><input type="radio" name="r1" value="<%=d.getD_Id()%>">		
					</tr>
					
					
					<%
				}
			i=0;
		%>
		
		
		</table>
<input type="submit" name="n1">
</form>
</body>
</html>