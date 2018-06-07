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
<%!
	List<Doctor> d; 
%>
<select>
<%
 d=(List<Doctor>)request.getAttribute("AllDoctor");
for(Doctor d1:d)
{
%>
<option value="<%d1.getD_Id();%>"><%=d1.getD_Name() %></option>
<% 
	
	out.println(d1.getD_Address());
} 
%>
</select>
</body>
</html>