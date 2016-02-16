package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberUpdateServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String no = req.getParameter("no");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			Class.forName(this.getInitParameter("driver"));
			conn = DriverManager.getConnection(this.getInitParameter("url"), this.getInitParameter("username"), this.getInitParameter("password"));
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
			writer.println("이름 : <input type='text' name='name' value='" + rs.getString("mname") + "' /><br>");
			writer.println("이메일 : <input type='text' name='email' value='" + rs.getString("email") + "'/><br>");
			writer.println("비밀번호 : <input type='password' name='password' value='" + rs.getString("pwd") + "'/><br>");
			writer.println("가입일 : " + rs.getString("cre_date") + "<br>");
			writer.println("수정일 : " + rs.getString("mod_date") + "<br>");
			writer.println("<input type='submit' value='수정'/> <input type='reset' value='취소'/>");
			writer.println("</form>");
			writer.println("</body>");
			writer.println("</html>");
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{ if(rs != null){ rs.close(); }}catch(Exception e){}
			try{ if(pstmt != null){ pstmt.close(); }}catch(Exception e){}
			try{ if(conn != null){ conn.close(); }}catch(Exception e){}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

}
