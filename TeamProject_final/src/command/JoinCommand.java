package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;

public class JoinCommand implements Command{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response){
		String userid = request.getParameter("userid");
		String userpw = request.getParameter("userpw");
		String username = request.getParameter("username");
		String usernickname = request.getParameter("usernickname");
		String useremail = request.getParameter("useremail")+"@"+request.getParameter("site");
		String userphone = request.getParameter("phone1")+"-"+request.getParameter("phone2")+"-"+request.getParameter("phone3");
		
		Dao dao = new Dao();
		dao.join(userid, userpw, username, usernickname, useremail, userphone);
	}
}
