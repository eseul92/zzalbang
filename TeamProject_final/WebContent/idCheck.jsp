<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="dao.*" %>
<%!
	Dao dao = new Dao();
	String str = "";
%>
<%
	String joinid = null;
	if(request.getParameter("userid") != null){
		joinid = request.getParameter("userid");
	}
	System.out.println("joinid : " + joinid);
	
	boolean isCheck = dao.idCheck(joinid);
	System.out.println("=== idCheck.jsp¿µ¿ª ===");
	System.out.println("isCheck : " + isCheck);
	
	 if(isCheck){
		str = "YES";
	}else{
		str = "NO";
	} 
%>
<%=str %>
