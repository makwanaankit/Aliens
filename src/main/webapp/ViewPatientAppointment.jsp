<%@page import="com.makwana.project.entity.FamailyMember"%>
<%@page import="org.springframework.beans.factory.annotation.Autowired"%>
<%@page import="com.makwana.project.IndexerController"%>
<%@page import="com.makwana.project.entity.Doctor"%>
<%@page import="java.util.Date"%>
<%@page import="com.makwana.project.entity.Patient"%>
<%@page import="com.makwana.project.entity.DoctorLeave"%>
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
<script type="text/javascript">
function myFunction() {
    var checkBox1 = document.getElementById("e1");
    var checkBox2 = document.getElementById("d1");
    
    
    var text = document.getElementById("text");
    if (checkBox1.checked == true && checkBox2.checked == true)
    {
    		
        text.style.display = "block";
    } else {
       text.style.display = "none";
    }
}

function selectb() {
    var checkBox1 = document.getElementById("e1");
    var checkBox2 = document.getElementById("d1");
    
    
    var text = document.getElementById("text1");
    if (checkBox1.checked == false && checkBox2.checked == false)
    {
    	alert("plz select atleast one option");
        text.style.display = "block";
    } else {
       text.style.display = "none";
    }
}


function hideText()
{
	 text.style.display = "none";	
}

</script>


</head>
<body>
<%!
	int i=0;
	int j=0;
	Date date=null;
	int time_id=0;
	int flag=0;
	HttpSession httpSession;
	String sdate;
	String s=null;
	String pdate=null;
	
	IndexerController ic;
%>
		<form action="AppointmentManagement">
		Date:<input type="date" name="ManagementDate"  >	
			
	Time:<select name="ManagementTime">
			<option value="1">10.00AM- 12.PM</option>
			<option value="2">6.00PM- 9.PM</option>
		
		 </select>
		 <input type="submit" > 
			</form>
			
			<form action="editReceptionistManagement">
			<table border=2>
			<tr>
					<td>Index</td>
					<td>Patient Name</td>
					<td>Patient Mobile</td>
					<td>Doctor Name</td>
					<td>Date</td>
					<td>Time</td>
					<td>Edit</td>
					<td>Delete</td>
			</tr>			
			<%
			if(request.getAttribute("AllAppointment")!=null)
			{
						HttpSession	httpSession=request.getSession();
				//		time_id=(Integer)httpSession.getAttribute("timeId1");
						time_id=1;
						sdate=(String)httpSession.getAttribute("Date1");
						
						List<Appointment> lAppointments=(List<Appointment>)request.getAttribute("AllAppointment");
						List<FamailyMember> lPatients=(List<FamailyMember>)request.getAttribute("AllrespectivePatient");
						List<DoctorLeave> doctorLeaves=(List<DoctorLeave>)request.getAttribute("DoctorLeave");
						List<Doctor>lDoctors=(List<Doctor>)request.getAttribute("Doctors");
						//out.print("This is appointment"+lAppointments);
						//out.print("<br>Doctor leave"+doctorLeaves);
						//out.println("<br> doctors"+lDoctors);
						//out.print("<br>patients "+lPatients);
						
						for(Appointment appointment:lAppointments)
						{
							date=appointment.getDate();
								for(DoctorLeave d:doctorLeaves)
								{
									
											if(appointment.getD_id()==d.getD_Id())
											{
												if(appointment.getTime()==d.getLeave_Time())
												{
													flag=1;				
													
												}
											}
											/* else
											{
												flag=0;
											} */
								}
								
								
							%>
						<% if(flag==1)
								{
						%>
							<tr  bgcolor="red">
									<td><%=++i %></td>
									<td><%=lPatients.get(j).getName() %></td>
								<%-- 	<td><%=lPatients.get(j).getMobileNumber()%></td> --%>
									<td><%=lDoctors.get(appointment.getD_id()-1).getD_Name() %></td>
									<td><%=sdate %></td>
									<td><%=time_id%></td>
									<td><input type="radio" name="e1" id="e1" onclick="myFunction()" value="<%=appointment.getAppointment_ID() %>"></td>
									<td><input type="radio" name="d1" id="d1" onclick="myFunction()" value="<%=appointment.getAppointment_ID() %>"></td>
							</tr>
						<%	
								
								}
							else
							{		
						%>	
								<tr  bgcolor="green">
								<td><%=++i %></td>
									<td><%=lPatients.get(j).getName() %></td>
	<%-- 								<td><%=lPatients.get(j).getMobileNumber()%></td> --%>
								 	<td><%=lDoctors.get(appointment.getD_id()-1).getD_Name() %></td>
									<td><%=sdate %></td>
									<td><%=time_id%></td>
										<td><input type="radio" name="e1"  id="e1" onclick="myFunction()" value="<%=appointment.getAppointment_ID() %>"></td>
										<td><input type="radio" name="d1" id="d1" onclick="myFunction()" value="<%=appointment.getAppointment_ID() %>"></td>
							</tr>
						<%
							}
						%>
						
							<%j=j+1;
							flag=0;
						}
						
						httpSession.removeAttribute("timeId1");
						httpSession.removeAttribute("Date1");
						j=0;
						i=0;
						
				}
			
			%>
			</table>
			<input type="reset" onclick="hideText()">
			<input type="submit" name="b1" onclick="selectb()">
		</form>
		<p id="text" style="display:none">Plz select only one option</p>
		
			<p id="text1" style="display:none">Plz select only </p>
		<%
				if(request.getParameter("back")!=null)
				{
					HttpSession httpSession=request.getSession();
					
					
					pdate=(String)httpSession.getAttribute("pdate");
					
					System.out.print("oooooooooo"+pdate);
					httpSession.setAttribute("Date1",pdate);
					httpSession.setAttribute("pdate",pdate);
				}
	/* 	if(request.getAttribute("SelectOne")==null)
		{
			out.print(request.getAttribute("SelectOne"));
		}
		else
		{
			out.print(request.getAttribute("SelectOne"));
		} */
		%>
			<form action="receptionistProfile.jsp">
			<input type="submit" value="Profile">
			</form>
			
			
</body>
</html>