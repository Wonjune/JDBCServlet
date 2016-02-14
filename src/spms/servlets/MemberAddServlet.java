package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/add")
public class MemberAddServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		resp.setContentType("text/html; charset=UTF-8");
		
		PrintWriter writer = resp.getWriter();
		writer.println("<html>");
		writer.println("<head>");
		writer.println("<title>회원 등록</title>");
		writer.println("</head>");
		writer.println("<body>");
		writer.println("<h1>회원 등록</h1>");
		writer.println("<form action='add' method='post'>");
		writer.println("이름 : <input type='text' name='name' /><br>");
		writer.println("이메일 : <input type='text' name='email' /><br>");
		writer.println("비밀번호 : <input type='password' name='password' /><br>");
		writer.println("<input type='submit' value='추가'/> <input type='reset' value='취소'/>");
		writer.println("</form>");
		writer.println("</body>");
		writer.println("</html>");
		
	}

	

}
