<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	Object msg = request.getAttribute("message");
	if(msg != null){
%>
	<br>
	<font color ="red"><%=msg %></font>
	<br>
	<br>

<%	
	}
%>

<form action="addCustomer.cu" method="post">
		<table>
			<tr>
				<td>CustomerName:</td>
				<td><input type="text" name ="name" 
				value="<%=request.getParameter("name") == null ?"":request.getParameter("name") %>"></td>
			</tr>
			
			<tr>
				<td>School:</td>
				<td><input type="text" name ="school" 
				value="<%=request.getParameter("school") == null ?"":request.getParameter("school") %>"
				></td>
			</tr>
			
			<tr>
				<td>Phone:</td>
				<td><input type="text" name ="phone" 
				value="<%=request.getParameter("phone") == null ?"":request.getParameter("phone") %>"></td>
			</tr>
			
			<tr>
				<td colspan="2"><input type="submit" value ="Submit"></td>
				
			</tr>
		</table>
	
	</form>
</body>
</html>