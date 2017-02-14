package command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;
import dto.Dto;

public class LoginCommand implements Command{
	public void execute(HttpServletRequest request, HttpServletResponse response){
		String userid = request.getParameter("userid");
		String userpw = request.getParameter("userpw");
		System.out.println("LoginCommand run");
		
		Dao dao = new Dao();
		System.out.println("LoginCommand - userid: " + userid);
		dao.loginUpdate(userid);
		Dto dto = dao.login(userid, userpw);
		
		request.setAttribute("loginVerification", dto);
	}
}
