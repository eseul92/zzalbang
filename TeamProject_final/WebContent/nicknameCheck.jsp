<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="dao.*"%>
<%! 
Dao dao = new Dao();
String joinNickname = null;
String str = null;
boolean isCheck = false;
%>
<%
	if(request.getParameter("usernickname") != null){
		joinNickname = request.getParameter("usernickname");
		
		System.out.println("=== nicknameCheck.jsp ===");
		System.out.println("joinNickname : " + joinNickname);
		
		isCheck = dao.nicknameCheck(joinNickname);
		
		if(isCheck){
			str = "YES";
		}else{
			str = "NO";
		}
	}
%>
<%= str %>