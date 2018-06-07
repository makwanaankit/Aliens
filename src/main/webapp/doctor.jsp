<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="addDoctor">
Name<input type="text" name="D_Name"><br>
Mobile Number<input type="text" name="D_MobileNumber"><br>
Gender:<input type="text" name="D_Gender"><br>
Address:<input type="text" name="D_Address"><br>
Salary:<input type="text"  name="D_Salary"><br>
D_Qualification:<input type="text" name="D_Qualification"><br>
Age:<input type="text" name="D_Age"><br>
userID<input type="text" name="user_name"><br>
password<input type="password"  name="password"><br>
<input type="submit">
</form>


<form action="ShowAllDoctor">
<input type="submit" value="ALLpatient">
</form>

</body>
</html>