<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<html>
<head>
<link rel="stylesheet" type="text/css" href="css/profile.css">
<title>Profile Management</title>
</head>
<body id="viewer-bg">

<%
	String[][] allMember = (String[][])request.getAttribute("allMember");
	int rows = (int)request.getAttribute("rows");
	int cols = (int)request.getAttribute("cols");
%>
	<div id="viewer">
	<form method="post" action="ProfileController">
	<div class='table'>
		<button onclick="window.location.href='ProfileController'" type="button">back to Admin Profile</button>
		<br/>
		<br/> 
		<span>
			<select id="selectCareer" onchange="careerSearch()">
				<option>choose career</option>
				<option>all</option>
				<option>manager</option>
				<option>employee</option>
			</select>
		</span>
		<br/><br/>
		<div id="viewer-header">
			<div class='table-col'>Username</div>
			<div class='table-col'>Fullname</div>
			<div class='table-col'>Birthdate</div>
			<div class='table-col'>Phone</div>
			<div class='table-col'>Email</div>
			<div class='table-col'>View Profile</a></div>
			<div class='table-col'>Member Setup</div>
		</div>
		<% if(rows == 0){ %>
			<div id="empty" >empty</div>
		<% } %>
		<% for(int i=0;i<rows;i++){ %>
                <div class='table-row'>
                        <div class='table-col'><%= allMember[i][0] %></div>
                        <div class='table-col'><%= allMember[i][1] %></div>
                        <div class='table-col'><%= allMember[i][2] %></div>
                        <div class='table-col'><%= allMember[i][3] %></div>
                        <div class='table-col'><%= allMember[i][4] %></div>
			<div class='table-col'><div><a href='ProfileController?view=<%= allMember[i][0] %>'>view</a></div></div>
			<div class='table-col'>
				<div>
				<select name="career">
					<option value="<%= allMember[i][0] + "-nocareer" %>">no career</option>
					<option value="<%= allMember[i][0] + "-manager" %>"
					 <% if("manager".equals(allMember[i][5])) out.print("selected"); %>>manager</option>
					<option value="<%= allMember[i][0] + "-employee" %>" 
					<% if("employee".equals(allMember[i][5])) out.print("selected"); %>>employee</option>
				</select>
				</div>
			</div>
                </div>
		<% } %>
	</div><!--table-->
		<% if(rows != 0) {%>
		<input id="save-career" type="submit" value="save"/>
		<% } %>
	</form>
	</div><!--viewer-->
</body>
</html>
<script type='text/javascript' src="jquery/jquery.js"></script>
<script type="text/javascript" src="js/profile.js"></script>
