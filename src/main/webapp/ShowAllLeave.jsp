<%@page import="com.makwana.project.entity.DoctorLeave"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
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
String SDate=null;
Date date;
int time = 0;
String timeshow = "";
%>
<form action="editLeaveCheck">
<table border=1>
<tr>
	<td>Index</td>
	<td>Date</td>
	<td>Time</td>
	<td>Edit</td>
	<td>Cancel</td>
	
</tr>
<%
SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
SimpleDateFormat df = new SimpleDateFormat("dd-MM-yy");
HttpSession httpSession=request.getSession();
				if(httpSession.getAttribute("AllLeave")!=null)
				{
					List<DoctorLeave> lDoctorLeaves=(List<DoctorLeave>)httpSession.getAttribute("AllLeave");
					httpSession.setAttribute("AllLeave",lDoctorLeaves);
					for(DoctorLeave doctorLeave:lDoctorLeaves)
					{
						time = doctorLeave.getLeave_Time();
						if (time == 1) 
						{
								timeshow = "10AM-12PM";
						}
						else 
						{
								timeshow = "6PM-9PM";
						}
							SDate=sdf1.format(doctorLeave.getLeave_Date());
						
							%>
							<tr>
									<td><%=++i %></td>
									<td><%=SDate %></td>
									<td><%=timeshow%></td>
									<td><input type="radio" name="e1" value="<%=doctorLeave.getLeave_ID() %>"></td>
									<td><input type="radio" name="d1" value="<%=doctorLeave.getLeave_ID() %>"></td>	
							</tr>	
							<%
							}
					i=0;
					out.print(httpSession.getAttribute("AllLeave"));
				}
				else
				{
					
				}
		
		%>
		
</table>

	<input type="Submit">
</form>
<%
		if(request.getParameter("e1")!=null && request.getParameter("d1")!=null)
		{
			out.print("Plz select only one option");
		}
		if(request.getAttribute("DateGone")!=null)
		{
			out.println("U selected Leave date is gone");
		}

%>

</body>
</html>