package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;
import dto.Dto;

public class InformModifyCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String userid = request.getParameter("userid");
		String userpw = request.getParameter("userpw");
		String username = request.getParameter("username");
		String usernickname = request.getParameter("usernickname");
		String userphone = request.getParameter("phone1")+"-"+request.getParameter("phone2")+"-"+request.getParameter("phone3");
		String useremail = request.getParameter("useremail")+"@"+request.getParameter("site");
		
		System.out.println("userid : " + userid);
		System.out.println("userpw : " + userpw);
		System.out.println("username : " + username );
		System.out.println("usernickname : " + usernickname);
		System.out.println("useremail : " + useremail);
		System.out.println("userphone : " + userphone);
		
		
		Dao dao = new Dao();
		dao.informModify(userid, userpw, username, usernickname, useremail, userphone);
		
		Dto dto = dao.modifyUpdate(userid);
		request.setAttribute("modifyUpdate", dto);
	}

}
