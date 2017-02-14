package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.Dao;

public class WriteCommand implements Command{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("WriteCommand Start");
		String usernickname = request.getParameter("usernickname");
		String btitle = request.getParameter("btitle");
		String hash = request.getParameter("hash");
		String bcontent = request.getParameter("bcontent");
		String url = request.getParameter("url");
		Dao dao = new Dao();
		dao.write(usernickname, btitle, hash, bcontent, url);
	}
}