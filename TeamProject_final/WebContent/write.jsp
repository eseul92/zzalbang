<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link href="20170109.css" rel="stylesheet" type="text/css">
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="https://use.fontawesome.com/76c836ecc8.js"></script>
<%!
String usernickname = null;
%>
<%
usernickname = request.getParameter("usernickname");
%>
</head>
<body>
	<form action="write.do" method="post">
		<table cellpadding="3px" cellspacing="0" border="1px">
			<tr>
				<td colspan="3" align="center" class="login_title"><p>�� ����</p></td>
			</tr>
			<tr id="btitle">
				<td>����</td>
				<td colspan="2">
					<input type="text" name="btitle" size="100" placeholder="����" autofocus>
				</td>
			</tr>
			<tr id="hash">
				<td>#hashtag</td>
				<td colspan="2">
					<textarea style="resize:none" rows="5" cols="102" name="hash" placeholder="Hash-Tag"></textarea>
				</td>
			</tr>
			<tr id="bcontent">
				<td>����</td>
				<td colspan="2">
					<textarea style="resize:none" rows="20" cols="102" name="bcontent" placeholder="����"></textarea>
				</td>
			</tr>
			<tr id="image">
				<td nowrap>Image ÷��</td>
				<td colspan="2">Image URL : <input type="text" size="85" name="url" placeholder="Image Url Link"></td>
			</tr>
			<tr>
				<td nowrap><input type="submit" value="�Է�" style="width:100%"></td>
				<td style="width:550px" nowrap></td>
				<td nowrap>�۾��� : <%=usernickname %></td>
			</tr>
		</table>
		<input style="display:none" type="text" name="usernickname" value="<%=usernickname %>">
	</form>
</body>
</html>