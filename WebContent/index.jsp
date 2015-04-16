<%@page import="com.liz.mvcapp.domain.Customer"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="scripts/jquery-2.1.1.js"></script>
<script type="text/javascript">
	
	$(function(){
		$(".delete").click(function(){
			var content = $(this).parent().parent().find("td:eq(1)").text();
			var flag = confirm("确定要删除" + content + "的信息吗？");
			return flag;
		});
		
	});

</script>
</head>
<body>
	
	<form action="query.cu" method="post">
		<table>
			<tr>
				<td>CustomerName:</td>
				<td><input type="text" name ="name"></td>
			</tr>
			
			<tr>
				<td>School:</td>
				<td><input type="text" name ="school"></td>
			</tr>
			
			<tr>
				<td>Phone:</td>
				<td><input type="text" name ="phone"></td>
			</tr>
			
			<tr>
				<td><input type="submit" value ="Query"></td>
				<td><a href="newcustomer.jsp">Add New Customer</a></td>
			</tr>
		</table>
	
	</form>
	
	<br><br>
	
	<%
		List<Customer> customers = (List<Customer>)request.getAttribute("customers");
		if(customers != null && customers.size() > 0){
		
	%>
	
	<hr>	
	<br><br>
	
	<table border="1" cellpadding="10" cellspacing="0">
		<tr>
			<th>ID</th>
			<th>CustomerName</th>
			<th>School</th>
			<th>Phone</th>
			<th>UPDATE\DELETE</th>
			
		</tr>
		<%
			for(Customer customer : customers){
		%>		
			<tr>
				<td><%=customer.getId() %></td>
				<td><%=customer.getName() %></td>
				<td><%=customer.getSchool() %></td>
				<td><%=customer.getPhone() %></td>
				<td>
					<a href="">UPDATE</a>
					<a href="delete.do?id=<%=customer.getId()%>" class="delete">DELETE</a>
				</td>
			</tr>
		
		
		<%		
			}
		%>
		
		
	</table>
	
	
	<% 
		}
	%>
</body>
</html>