<%@page import="com.makwana.project.entity.FamailyMember"%>
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
	<%!
			List<FamailyMember>lFamailyMembers ;
			int i=0;	
	%>
	<form action="ViewSelectMemberDetails">
	<table>
	<tr>
			<td>Index</td>
			<td>Name</td>
			<td>Select</td>
	</tr>
	<%
	
			lFamailyMembers=(List<FamailyMember>)request.getAttribute("FamilyMember");
			out.print(request.getAttribute("FamilyMember"));
			for(FamailyMember f:lFamailyMembers)
			{
					
	%>
			<tr>
					<td><%=++i %></td>
					<td><%=f.getName() %></td>
					<td><input type="radio"  name="e1" value="<%=f.getP_id() %>"></td>
			</tr>
	<%
			}
			i=0;
	%>
	
	</table>
	<input type="submit" value="View Member">
</form>

</body>
</html>