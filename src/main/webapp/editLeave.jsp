<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="modifyleave">
Date:<input type="date" name="Leave_Date">
Time:<select name="Leave_Time">
			<option value="1">10.00AM- 12.PM</option>
			<option value="2">6.00PM- 9.PM</option>
		<input type="submit">		
</form>
		<%
				if(request.getAttribute("dateDone")!=null)
				{
					out.println(request.getAttribute("dateDone"));
				}
		%>
				
</body>
</html>