package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

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

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		resp.setContentType("text/html; charset=UTF-8");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try{
			//request에서 getParameter 할 때 매개변수값의 인코딩을 디폴트로 ISO-8859-1로 인식하므로 UTF-8설정(하지 않으면 한글이 깨짐 발생)
			req.setCharacterEncoding("UTF-8");
			
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			conn = DriverManager.getConnection("jdbc:mysql://localhost/studydb", "study", "study");
			
			pstmt = conn.prepareStatement("insert into members(mname, email, pwd, cre_date, mod_date) values (?,?,?,now(),now())");
			pstmt.setString(1, req.getParameter("name"));
			pstmt.setString(2, req.getParameter("email"));
			pstmt.setString(3, req.getParameter("password"));
			
			pstmt.executeUpdate();
			
			PrintWriter writer = resp.getWriter();
			writer.println("<html>");
			writer.println("<head>");
			writer.println("<title>회원 등록</title>");
			writer.println("</head>");
			writer.println("<body>");
			writer.println("<h1>등록 성공입니다.</h1>");
			writer.println("</body>");
			writer.println("</html>");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{ if(pstmt != null){ pstmt.close();} }catch(Exception e){}
			try{ if(conn != null){ conn.close();} }catch(Exception e){}
		}
		
	}

	

}
