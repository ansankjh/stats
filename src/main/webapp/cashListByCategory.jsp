<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "service.*" %>
<%
	String category = request.getParameter("category");
	// Controller --> Servlet Class
	CashService cashService = new CashService();
	ArrayList<HashMap<String, Object>> list = cashService.getCashListByCategory(category);
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>cashListByCategory</title>
	</head>
	<body>
		<!-- View : *.jsp -->
		<a href="<%=request.getContextPath()%>/index.jsp">index</a>
		<div>
			<h1>년도별 <%=category%>합계 목록</h1>
			<table>
				<tr>
					<th>년</th>
					<th>합계</th>
				</tr>
				<%
					for(HashMap<String, Object> m : list) {
				%>
						<tr>
							<td><%=m.get("year")%></td>
							<td>
							
								<%
									if(category.equals("수입")) {
								%>
										<span style="color:blue;">+
								<%
									} else {
								%>		
										<span style="color:red;">-
								<%
									}
								%>
								<%=m.get("price")%></span>	
							</td>
						</tr>
				<%
					}
				
				%>
			</table>
		</div>
	</body>
</html>