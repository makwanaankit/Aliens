<%@page import="org.springframework.beans.factory.annotation.Autowired"%>
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
<%
	
Doctor doctor=(Doctor)request.getAttribute("DoctorDetails");
out.println(doctor);
%>

</body>
</html>