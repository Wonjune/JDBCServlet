package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/update")
public class MemberUpdateServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String no = req.getParameter("no");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			ServletContext sc = this.getServletContext();
			conn = (Connection)sc.getAttribute("conn");
			pstmt = conn.prepareStatement("select mname, email, pwd, cre_date, mod_date from members where mno = ?");
			pstmt.setInt(1, Integer.parseInt(no));
			rs = pstmt.executeQuery();
			rs.next();
			
			resp.setContentType("text/html; charset=UTF-8");
			
			PrintWriter writer = resp.getWriter();
			writer.println("<html>");
			writer.println("<head>");
			writer.println("<title>회원 수정</title>");
			writer.println("</head>");
			writer.println("<body>");
			writer.println("<h1>회원 수정</h1>");
			writer.println("<form action='update' method='post'>");
			writer.println("번호 : <input type='text' name='no' value='" + no + "' readonly /><br>");
			writer.println("이름 : <input type='text' name='name' value='" + rs.getString("mname") + "' /><br>");
			writer.println("이메일 : <input type='text' name='email' value='" + rs.getString("email") + "'/><br>");
			writer.println("비밀번호 : <input type='password' name='password' value='" + rs.getString("pwd") + "'/><br>");
			writer.println("가입일 : " + rs.getString("cre_date") + "<br>");
			writer.println("수정일 : " + rs.getString("mod_date") + "<br>");
			writer.println("<input type='submit' value='수정'/> <input type='button' value='삭제' onclick='location.href=\"delete?no=" + no + "\"'/> <input type='button' value='취소' onclick='location.href=\"list\"'/>");
			writer.println("</form>");
			writer.println("</body>");
			writer.println("</html>");
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{ if(rs != null){ rs.close(); }}catch(Exception e){}
			try{ if(pstmt != null){ pstmt.close(); }}catch(Exception e){}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try{
			ServletContext sc = this.getServletContext();
			conn = (Connection)sc.getAttribute("conn");
			pstmt = conn.prepareStatement("update members set mname = ?, email = ?, pwd = ?, mod_date = now() where mno = ?");
			pstmt.setString(1, req.getParameter("name"));
			pstmt.setString(2, req.getParameter("email"));
			pstmt.setString(3, req.getParameter("password"));
			pstmt.setInt(4, Integer.parseInt(req.getParameter("no")));
			pstmt.execute();
			
			resp.setContentType("text/html; charset=UTF-8");
			
			resp.sendRedirect("list");
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{ if(pstmt != null){ pstmt.close(); }}catch(Exception e){}
		}
	}

}
