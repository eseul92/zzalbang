$(document).ready(function() {
	/* img js*/
	$('.div').mouseover(function(){
		$(this).attr('class', 'target');
		$('.target .hover_title').attr('id', 'target_title');
		var target_title = document.getElementById('target_title');
		var b_title = document.getElementById('board_title');
		b_title.innerHTML = target_title.value;
		
		$('.target .hover_usernickname').attr('id', 'target_usernickname');
		var target_usernickname = document.getElementById('target_usernickname');
		var b_nickname = document.getElementById('board_nickname');
		b_nickname.innerHTML = target_usernickname.value;
		
		$('.target .hover_content').attr('id', 'target_content');
		var target_content = document.getElementById('target_content');
		var b_content = document.getElementById('board_content');
		b_content.innerHTML = target_content.value;
	});
	$(".hover").mouseover(function() {
		$(this).attr('id', 'myImg');
		var hashtag = document.getElementById('myImg');
		$('#myImg').bind('mouseup', function() {
			var imgW = this.naturalWidth;
			var imgH = this.naturalHeight;
			var modalImg = document.getElementById("img1");
			var modaltitle = document.getElementById("hover_title");
			var modalnickname = document.getElementById("hover_usernickname");
			var modalcontent = document.getElementById("hover_content");
			
			modalImg.src = this.src;
			
			if((imgH / imgW) < 1){
				if (imgW <= 200) {
					$('#total_div').css('width', imgW + 700);
					$('#img1').css('width', imgW + 300);
				} else if (imgW <= 300) {
					$('#total_div').css('width', imgW + 600);
					$('#img1').css('width', imgW + 200);
				} else if (imgW <= 400) {
					$('#total_div').css('width', imgW + 500);
					$('#img1').css('width', imgW + 100);
				} else if (imgW <= 600){
					$('#total_div').css('width', imgW + 400);
					$('#img1').css('width', imgW);
				} else{
					$('#total_div').css('width', '1000px');
					$('#img1').css('width', '600px');
				}
			} else {
				if (imgW <= 200) {
					$('#total_div').css('width', imgW + 700);
					$('#img1').css('width', imgW + 300);
					$('#img1').css('height', '600px');
				} else if (imgW <= 300) {
					$('#total_div').css('width', imgW + 600);
					$('#img1').css('width', imgW + 200);
					$('#img1').css('height', '600px');
				} else if (imgW <= 400) {
					$('#total_div').css('width', imgW + 500);
					$('#img1').css('width', imgW + 100);
					$('#img1').css('height', '600px');
				} else if (imgW <= 600){
					$('#total_div').css('width', imgW + 400);
					$('#img1').css('width', imgW);
					$('#img1').css('height', '600px');
				} else{
					$('#total_div').css('width', '1000px');
					$('#img1').css('width', '600px');
					$('#img1').css('height', '600px');
				}
			}
			$('.modal').attr('class', 'modalBlock');
			$('.search').attr('class', 'search_clicked');
		});
	});
	$(".hover").mouseout(function() {
		$(this).attr('id', '');
		$(this).attr('alt', '');
	});
	/*2 mon 7 day*/
	$('.div').mouseout(function(){
		$(this).attr('class', 'div');
	});
	$('.close').bind('mouseup', function() {
		$('.modalBlock').attr('class', 'modal');
		$('.search_clicked').attr('class', 'search');
	});
	/* 로그인 js */
	$('#login_btn').click(function() {

		$('.login').css("display", "block");
		$('.login_modal').attr('class', 'modalBlock');
		$('.close').css("display", "none");
		
	});

	$('.login_close').click(function() {
		$('.login').css('display', 'none');
		$('.modalBlock').attr('class', 'login_modal');
		$('.close').css('display', 'block');
	});

	/* 회원가입 js */
	$('.login_join').click(function() {
		$('.login').css('display', 'none');
		$('.join').css('display', 'block');
	});

	$('.join_close').click(function() {
		$('.join').css('display', 'none');
		$('.modalBlock').attr('class', 'login_modal');
		$('.close').css('display', 'block');
	});
	
	$('#btn_idCheck').click(function(){
		$.ajax({
			type:"POST",
			url:"idCheck.do",
			data:$('#join_userid').serialize(), //회원가입폼의 id 입력창 안에 있는 value값을 불러온다 
			success:function(data){
				if(jQuery.trim(data) == "YES"){
					alert("이 아이디는 사용 가능합니다.");
				}else{
					alert("이 아이디는 존재합니다. 다른 아이디를 이용해주세요.");
				}
			}
			
		})
	});
	
	$('#btn_nicknameCheck').click(function(){
		$.ajax({
			type:"POST",
			url:"nicknameCheck.do",
			data:$('#nickname').serialize(),
			success: function(data){
				if(jQuery.trim(data) == "YES"){
					alert("이 닉네임은 사용 가능합니다.");
				}else{
					alert("이 닉네임은 이미 사용중입니다. 다른 닉네임을 이용해주세요.");
				}
			}
		})
	});
	$('#btn_modifyNicknameCheck').click(function(){
		$.ajax({
			type:"POST",
			url:"nicknameCheck.do",
			data:$('#modify_nickname').serialize(),
			success: function(data){
				if(jQuery.trim(data) == "YES"){
					alert("이 닉네임은 사용 가능합니다.");
				}else{
					alert("이 닉네임은 이미 사용중입니다. 다른 닉네임을 이용해주세요.");
				}
			}
		})
	});
	
    //회원정보수정 js
	$('#modify_btn').click(function(){
		$('.informModify').css('display', 'block');
		$('.login_modal').attr('class', 'modalBlock');
		$('.close').css('display', 'none');
	});
	
	$('.modify_close').click(function(){
		$('.informModify').css('display', 'none');
		$('.modalBlock').attr('class', 'login_modal');
		$('.close').css('display','block');
	});
	
});
function openNav() {
    document.getElementById("mySidenav").style.width = "250px";
    document.getElementById("main").style.padding = "16px";
    $('.search').css('left', '250px');
    $('.search').css('padding','30px');
    $('#main').css('margin-left', '250px');
}

function closeNav() {
    document.getElementById("mySidenav").style.width = "0px";
    document.getElementById("main").style.padding= "16px";

    $('.search').css('left','45px');
    $('.search').css('padding','5px');
    $('article').css('padding-top','5px');
    $('#main').css('margin-left', '50px');
    $('#main').css('margin-top', '60px');
}
function joinField(){
	if(document.join_form.join_userid.value.length < 6){
		if(document.join_form.join_userid.value == ""){
			alert("아이디를 입력하세요.");
			document.join_form.join_userid.focus();
			return;
		}else{
			alert("아이디를 6~10자로 입력해주세요.");
			document.join_form.join_userid.focus();
			return;
		}
	}else if(document.join_form.userpw.value.length < 6){
		if(document.join_form.userpw.value == ""){
			alert("비밀번호를 입력하세요.");
			document.join_form.userpw.focus();
			return;
		}else{
			alert("비밀번호를 6~10자로 입력해주세요.");
			document.join_form.userpw.focus();
			return;
		}
	}else if(document.join_form.username.value ==""){
		alert("이름을 입력하세요.");
		document.join_form.username.focus();
		return;
	}else if(document.join_form.usernickname.value ==""){
		alert("닉네임을 입력하세요.");
		document.join_form.usernickname.focus();
		return;
	}else if(document.join_form.useremail.value==""){
		alert("이메일을 입력하세요.");
		document.join_form.useremail.focus();
		return;
	}else if((document.join_form.phone1.value=="") || (document.join_form.phone2.value=="") || (document.join_form.phone3.value=="")){
		alert("핸드폰 번호를 입력하세요.");
		if(document.join_form.phone1.value==""){
			document.join_form.phone1.focus();
		}else if(document.join_form.phone2.value==""){
			document.join_form.phone2.focus();
		}else{
			document.join_form.phone3.focus();
		}
		return;
	}
	
	document.join_form.submit(); 
	alert("회원가입 완료!");
}

function loginCheck(){
	if(document.login_form.userid.value==""){
		alert("아이디를 입력해주세요.");
		return;
	}else if(document.login_form.userpw.value==""){
		alert("비밀번호를 입력해주세요.");
		return;
	}
	
	document.login_form.submit();
}
function informModify(){
	if(document.informModify_form.userpw.value < 6){
		if(document.informModify_form.userpw.value==null){
			alert("비밀번호를 입력해주세요.");
			document.informModify_form.userpw.focus();
			return;
		}else{
			alert("비밀번호를 6~10자로 입력해주세요.");
			document.informModify_form.userpw.focus();
			return;
		}
	}
	else if(document.informModify_form.username.value==""){
		alert("이름을 입력해주세요.");
		document.informModify_form.username.focus();
		return;
	}else if(document.informModify_form.usernickname.value==""){
		alert("닉네임을 입력해주세요.");
		document.informModify_form.usernickname.focus();
		return;
	}else if(document.informModify_form.useremail.value==""){
		alert("이메일을 입력해주세요.");
		document.informModify_form.useremail.focus();
		return;
	}else if((document.informModify_form.phone1.value=="") || (document.informModify_form.phone2.value=="") || (document.informModify_form.phone3.value=="")){
		alert("핸드폰번호를 입력해주세요.");
		if(document.informModify_form.phone1.value==""){
			document.informModify_form.phone1.focus();
		}else if(document.informModify_form.phone2.value==""){
			document.informModify_form.phone2.focus();
		}else{
			document.informModify_form.phone3.focus();
		}
		
		return;
	}
	
	document.informModify_form.submit();
	alert("회원정보가 수정되었습니다.");
}
function refresh(){
	document.modal_form.submit();
}