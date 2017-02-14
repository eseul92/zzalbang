package command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;
import dto.Dto;

public class BoardCommand implements Command{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		Dao dao = new Dao();
		ArrayList<Dto> dtos = dao.list();
		request.setAttribute("list", dtos);
		Dto dto = dao.tcount();
		request.setAttribute("tcount", dto);
	}
}