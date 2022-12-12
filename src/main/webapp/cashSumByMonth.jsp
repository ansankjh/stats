<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "service.*" %>
<%
	int year = 0;
	// year가 안넘어오면 오늘 날짜의 year나오게한다
	if(request.getParameter("year") == null) {
		Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
	} else {
		year = Integer.parseInt(request.getParameter("year"));
	}
	
	String category = request.getParameter("category");
	
	CashService cashService = new CashService();
	ArrayList<HashMap<String, Object>> list = cashService.getCashSumByMonth(year, category);
	
	// 페이징에 사용할 최소년도와 최대년도
	HashMap<String, Object> map = cashService.getMaxMinYear();
	int minYear = (Integer)(map.get("minYear")); // int ->> Integer 박싱  // Integer --> 언박싱
	int maxYear = (Integer)(map.get("maxYear"));
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>cashSumByMonth</title>
	</head>
	<body>
		<a href="<%=request.getContextPath()%>/index.jsp">index</a>
		<div>
			<h1><%=year%>년 월별 <%=category%> 합계</h1>
			<table>
				<tr>
					<th>년</th>
					<th><%=category%> 합계</th>
				</tr>
				<%
					for(HashMap<String, Object> m : list) {
				%>
						<tr>
							<td><%=m.get("month")%></td>
							<td><%=m.get("price")%></td>
						</tr>
				<%
					}
				%>
			</table>
			<%
				if(year > minYear) {
			%>
					<a href="<%=request.getContextPath()%>/cashSumByMonth.jsp?category=<%=category%>&year=<%=year-1%>">이전</a>
			<%
				}
			
				if(year < maxYear) {
			%>
					<a href="<%=request.getContextPath()%>/cashSumByMonth.jsp?category=<%=category%>&year=<%=year+1%>">다음</a>
			<%
				}
			%>
			
			
		</div>
	</body>
</html>