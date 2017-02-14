<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="dto.*" %>
<%@ page import="dao.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%!
Dto dto = new Dto();
Dao dao = new Dao();
ArrayList<Dto> board = new ArrayList<Dto>();
String userid;
String userpw;
String username;
String usernickname;
String useremail;
String userphone;
int loginCheck;

String[] arrPhone=null;
String phone1, phone2, phone3;

String[] arrEmail=null;
String email;

String display = "none";
%>
<%
//로그인 성공시 회원정보 얻어옴
if(request.getAttribute("loginVerification") != null){
	dto = (Dto)request.getAttribute("loginVerification");
	if((userid != null) && (loginCheck == 0)){%>
	<script type="text/javascript">
		alert("아이디와 패스워드가 존재하지 않거나 일치하지 않습니다.\n다시 시도해주세요.");
<% }else if(loginCheck == 2){%>
		alert("아이디와 패스워드가 존재하지 않거나 일치하지 않습니다.\n다시 시도해주세요.");
	</script>
<%	}
	if(dto.getLoginCheck() == 1){
		userid = dto.getUserid();
		userpw = dto.getUserpw();
		username = dto.getUsername();
		usernickname = dto.getUsernickname();
		useremail = dto.getUseremail();
		userphone = dto.getUserphone();
		loginCheck = dto.getLoginCheck();
		display = "block";
		System.out.println("userid : " + userid);
		System.out.println("loginCheck : " + loginCheck);
		
		arrPhone = userphone.split("-");
		phone1 = arrPhone[0];
		phone2 = arrPhone[1];
		phone3 = arrPhone[2];
		
		arrEmail = useremail.split("@");
		email = arrEmail[0];
	}//아이디는 존재하는데 비밀번호가 다를 경우
	else if(dto.getLoginCheck() == 0){
		userid = dto.getUserid();
		loginCheck = dto.getLoginCheck();
	}//아이디가 존재하지 않을 때
	/* else if(dto.getLoginCheck() == 2){
		userid = dto.getUserid();
		loginCheck = dto.getLoginCheck();
		
		System.out.println("userid : " + userid);
		System.out.println("loginCheck : " + loginCheck);
	} */
}else if(request.getAttribute("modifyUpdate") != null){
	dto = (Dto)request.getAttribute("modifyUpdate");
	
	userid = dto.getUserid();
	userpw = dto.getUserpw();
	username = dto.getUsername();
	usernickname = dto.getUsernickname();
	useremail = dto.getUseremail();
	userphone = dto.getUserphone();
	
	arrPhone = userphone.split("-");
	phone1 = arrPhone[0];
	phone2 = arrPhone[1];
	phone3 = arrPhone[2];
	
	arrEmail = useremail.split("@");
	email = arrEmail[0];
	
	System.out.println("loginCheck: " + dto.getLoginCheck());
}else if(request.getAttribute("logout") != null){
	loginCheck = 0;
	dao.logout(userid, loginCheck);
}
%>	
<style type="text/css">
<%
   if((userid != null) && (loginCheck == 1)){ %>
	#connect{
		display:table;
	}
	#login_btn{display:none;}
	
<%
   }else if(loginCheck == 0){%>
	   #connect{
			display:none;
		}
		#login_btn{display:block;}
   <%}
   %>
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="20170109.css" rel="stylesheet" type="text/css">
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="https://use.fontawesome.com/76c836ecc8.js"></script>
<script src="20170109.js"></script>
</head>
<body>

<div id="mySidenav" class="sidenav">

  <a href="javascript:void(0)" class="openNav"  onclick="openNav()">&#9776;</a>
  <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">X</a>
  
 <div class="ncolor"> 
  <a href="#" >HOME</a>
  <a href="#" >UALOAD</a>
  <a href="#" >짤방</a>
  <a href="list.do" >게시판</a>
  <a href="#">건의사항</a>
 </div>
 <input type="button" id="login_btn" value="로그인">
 <table id="connect" width="180">
	<form name="connectFm" action="informModify.do" method="post">
		<tr>
			<td id="user"><%=userid %>님</td>
		</tr>
		<tr>
			<td><input type="button" id="modify_btn" value="정보수정"></td>
			<td><input type="submit" formaction="logout.do" value="로그아웃"></td>
		</tr>
	</form>
</table>
</div>
<div id="main">
		<!-- 상단 Search div tag (.text)-->
		<div class="search"
			style="position: fixed; top: 0px; left: 250px; background-color: white; width: 100%; z-index: 1">
			<form>
				<input type="text" name="search_image" placeholder="검색어를 입력하세요"
					style="height: 30px; width: 30%; margin: 0 auto;">
				<button style="height: 35px;">
					<i class="fa fa-search" aria-hidden="true"></i>
				</button>
			</form>
		</div>
		<!-- Image 배치 div tag -->
		<article id="area_setting"> <%
 	if (request.getAttribute("list") != null) {
 		board = (ArrayList<Dto>) request.getAttribute("list");
 		for (int i = (board.size() - 1); i >= 0; i--) {
 			dto = board.get(i);%>
		<div class="col-4">
			<div class="div">
				<img class="hover" id="" src="<%=dto.getUrl()%>">
            <input id="" class="hover_title"style="display:none" type="text" value="<%=dto.getBtitle() %>">
            <input id="" class="hover_usernickname" style="display:none" type="text" value="<%=dto.getUsernickname() %>">
            <input id="" class="hover_content" style="display:none" type="text" value="<%=dto.getBcontent() %>">
			</div>
		</div>
		<%		}
	}%> 
	</article>
		<div id="write"
			style="position: fixed; top: 50px; left: 91%; z-index: 1">
			<form action="write_view.do" method="post">
				<input style="display: none" type="text" name="usernickname"
					value="<%=usernickname%>"> <input
					style="display:<%=display%>" type="submit" value="글 쓰기">
			</form>
		</div>
	</div>

	<div class="modal">
		<form name="modal_form" action="list.do" method="POST">
			<span class="close" onclick="refresh()">&times;</span>
		</form>
		<table id="total_div" cellpadding="0" cellspacing="0" border="0" style="background-color: white;">
			<tr>
				<td rowspan="4"><img id="img1"></td>
				<td class="board_tb" id="board_title" width="300px" height="10%" style="text-align:left;vertical-align:top;font-weight:bold">제목 : <%=dto.getBtitle() %></td>
				<td class="board_tb" id="board_nickname" width="100px" height="10%" style="text-align:left;vertical-align:top;font-size:10pt">작성자 : <%=dto.getUsernickname() %></td>
			</tr>
			<tr>
				<td class="board_tb" id="board_content" colspan="2" width="400px" height="45%" style="text-align:left;vertical-align:top;font-size:10pt;overflow-y:scroll"><%=dto.getBcontent() %></td>
			</tr>
			<tr>
				<td class="board_tb" colspan="2" width="400px" height="45%" style="text-align:left;vertical-align:top;font-size:10pt;overflow-y:scroll">댓글 부분</td>
			</tr>
			<tr style="display:none">
				<td></td>
				<td></td>
			</tr>
		</table>
	</div>
	<!-- 로그인창 띄우기 -->
	<div class="login_modal">
		<div class="login">
			<table class="login_tb">
				<form action="login.do" name="login_form" method="post">
					<tr>
						<td align="center" class="login_title"><p>짤방연구소 로그인</p></td>
						<td class="login_close"><span>&times;</span></td>
					</tr>
					<tr>
						<td><input type="text" name="userid" size="10"
							placeholder="아이디 입력" autofocus></td>
					</tr>
					<tr>
						<td><input type="password" name="userpw" size="10"
							placeholder="비밀번호 입력"></td>
					</tr>
					<tr>
						<td><input type="button" class="btnLogin" onclick="loginCheck()" value="로그인"></td>
					</tr>
					<tr>
						<td><input type="button" class="login_join" value="회원가입"></td>
					</tr>
				</form>
			</table>
		</div>
	</div>	
	<!-- 회원가입 띄우기 -->
	<div class="join">
		<table align="center" class="join_tb">
						<!-- ***** 변경하기0113 ***** -->
			<form action="join.do" name="join_form" method="post">
				<tr>
					<td class="join_title" colspan="5" align="center"><p>짤방연구소
							회원가입</p></td>
					<td class="join_close"><span>&times;</span></td>
				</tr>
				<tr>
					<td class="join_text">ID</td>
					<td><input type="text" name="userid" id="join_userid" maxlength="10"
						placeholder="아이디 입력(6~10자)" autofocus></td>
					<td><input type="button" id="btn_idCheck" value="중복확인">
				</tr>
				<tr>
					<td class="join_text">비밀번호</td>
					<td colspan="2"><input type="password" maxlength="10" name="userpw"
						placeholder="비밀번호 입력(6~10자)"></td>
				</tr>
				<tr>
					<td class="join_text">이름</td>
					<td colspan="2"><input type="text" maxlength="10" name="username"
						placeholder="이름 입력"></td>
				</tr>
				<tr>
					<td class="join_text">별명</td>
					<td><input type="text" name="usernickname" id="nickname" maxlength="10"
						placeholder="별명 입력"></td>
						<!-- ***** 변경하기0113 ***** -->
					<td><input type="button" id="btn_nicknameCheck" value="중복확인"></td>
				</tr>
				<tr>
					<td class="join_text">이메일</td>
					<!-- ***** 변경하기0113 ***** -->
					<td colspan="2"><input type="text" name="useremail" size="10"
						id="email" placeholder="이메일 입력"> @ <select name="site">
							<option value="hanmail.net">hanmail.net
							<option value="hanmir.com">hanmir.com
							<option value="hotmail.com">hotmail.com
							<option value="nate.com">nate.com
							<option value="naver.com">naver.com
							<option value="yahoo.co.kr">yahoo.co.kr
					</select></td>
				</tr>
				<tr>
					<td class="join_text">핸드폰번호</td>
					<td><p>
							<input type="text" name="phone1" id="phone1" maxlength="3">-<input
								type="text" name="phone2" id="phone2" maxlength="4">-<input
								type="text" name="phone3" id="phone3" maxlength="4">
						</p></td>
				</tr>
				
				<!-- ***** 변경하기0113 ***** -->
				<tr>
					<td class="join_btn" colspan="4"><input type="reset" id="join_reset" value="다시 작성하기"></td>
				</tr>
				<tr>
				 	<td class="join_btn" colspan="4"><input type="button" id="join_btn" onclick="joinField()" value="가입하기"></td>
				</tr>
			</form>
		</table>
	</div>
	<!-- 회원정보수정 띄우기 -->
	<div class="informModify">
		<table align="center" class="join_tb">
						<!-- ***** 변경하기0113 ***** -->
			<form action="informModify.do?userid=<%=userid %>" name="informModify_form" method="post">
				<tr>
					<td class="join_title" colspan="5" align="center"><p>짤방연구소
								회원정보수정</p></td>
					<td class="modify_close"><span>&times;</span></td>
				</tr>
				<tr>
					<td class="join_text">ID</td>
					<td><input type="text" name="userid" maxlength="10" value="<%=userid%>" readonly></td>				
				</tr>
				<tr>
					<td class="join_text">비밀번호</td>
					<td colspan="2"><input type="password" maxlength="10" name="userpw"></td>
					</tr>
				<tr>
					<td class="join_text">이름</td>
					<td colspan="2"><input type="text" maxlength="10" name="username" value="<%=username%>" readonly></td>
				</tr>
				<tr>
					<td class="join_text">별명</td>
					<td><input type="text" name="usernickname" id="modify_nickname" maxlength="10" value="<%=usernickname %>"></td>
						<!-- ***** 변경하기0113 ***** -->
					<td><input type="button" id="btn_modifyNicknameCheck" value="중복확인"></td>
				</tr>
				<tr>
					<td class="join_text">이메일</td>
					<!-- ***** 변경하기0113 ***** -->
				<td colspan="2"><input type="text" name="useremail" size="10" id="email" value="<%=email%>"> @ <select name="site">
							<option value="hanmail.net">hanmail.net
							<option value="hanmir.com">hanmir.com
							<option value="hotmail.com">hotmail.com
							<option value="nate.com">nate.com
							<option value="naver.com">naver.com
							<option value="yahoo.co.kr">yahoo.co.kr
					</select></td>
				</tr>
				<tr>
					<td class="join_text">핸드폰번호</td>
					<td><p>
							<input type="text" name="phone1" id="phone1" maxlength="3" value="<%= phone1 %>">-
							<input type="text" name="phone2" id="phone2" maxlength="4" value="<%= phone2 %>">-
							<input type="text" name="phone3" id="phone3" maxlength="4" value="<%= phone3 %>">
						</p></td>
				</tr>
				<!-- ***** 변경하기0113 ***** -->
				<tr>
				 	<td class="join_btn" colspan="4"><input type="button" id="join_btn" onclick="informModify()" value="수정하기"></td>
				</tr>
			</form>
		</table>
	</div>
</body>
</html>