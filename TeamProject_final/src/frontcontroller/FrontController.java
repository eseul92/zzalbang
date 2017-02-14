package frontcontroller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.BoardCommand;
import command.Command;
import command.InformModifyCommand;
import command.JoinCommand;
import command.LoginCommand;
import command.LogoutCommand;
import command.WriteCommand;
@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public FrontController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}
	protected void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("EUC-KR");
		String viewPage = null;
		Command command = null;
		String uri = request.getRequestURI();
		System.out.println("uri: " + uri);
		String contextPath = request.getContextPath();
		System.out.println("contextPath: " + contextPath);
		String[] arrURI = uri.split("/");
		String comm = arrURI[arrURI.length-1];
		System.out.println("arrURI: " + arrURI.length);
		System.out.println("command : " + comm);
		if(comm.equals("list.do")){
			command = new BoardCommand();
			command.execute(request, response);
			viewPage="20170109.jsp";
		}else if(comm.equals("join.do")){
			command = new JoinCommand();
			command.execute(request, response);
			viewPage="list.do";
		}else if(comm.equals("informModify.do")){
			command = new InformModifyCommand();
			System.out.println("InformModify �깮�꽦");
			command.execute(request, response);
			System.out.println("inform �쟾�넚");
			viewPage="list.do";
		}else if(comm.equals("write_view.do")){
			viewPage="write.jsp";
		}else if(comm.equals("write.do")){
			command = new WriteCommand();
			command.execute(request, response);
			viewPage="list.do";
		}else if(comm.equals("logout.do")){
			command = new LogoutCommand();
			command.execute(request, response);
			viewPage="list.do";
		}else if(comm.equals("login.do")){
			command = new LoginCommand();
			command.execute(request, response);
			viewPage="list.do";
		}else if(comm.equals("idCheck.do")){
			viewPage="idCheck.jsp";
		}else if(comm.equals("nicknameCheck.do")){
			viewPage="nicknameCheck.jsp";
		}
		RequestDispatcher dispacther = request.getRequestDispatcher(viewPage);
		dispacther.forward(request, response);
	}
}
