<%@page import="com.makwana.project.entity.Doctor"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@page import="com.makwana.project.entity.DoctorLeave"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<title>Insert title here</title>
</head>
<body>

 <div class="dropdown">
    <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Dropdown Example
    <span class="caret"></span></button>
    <ul class="dropdown-menu">
      <li><a href="ShowAllDoctorS">HTML</a></li>
      <li><a href="#">CSS</a></li>
      <li><a href="#">JavaScript</a></li>
    </ul>
  </div>




	<%!
	Doctor doctor=null;
	List<DoctorLeave> doctorLeaves=null;
	String stime=null;
	String stimeShow=null;
	String strDate=null;
	String dname=null;
	int i=0;
	%>
	
	<%
		
	 doctorLeaves=(List<DoctorLeave>)request.getAttribute("AllLeave");
	dname=(String)request.getAttribute("DoctorName");
	%>
	
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
								<td><%=dname %></td>
								<td><%=strDate %></td>
								<td><%=stimeShow%></td>
							
						</tr>
						
						<%
					}
			i=0;	
			%>
			</table>
	<form action="ShowAllDoctorS">
		<input type="submit" value="<<Back">
	</form>

</body>
</html>