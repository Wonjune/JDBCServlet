package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/member/list")
public class MemberListServlet extends GenericServlet {

	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			conn = DriverManager.getConnection("jdbc:mysql://localhost/studydb", "study", "study");
			pstmt = conn.prepareStatement("select mno, email, mname, cre_date from members order by mno asc");
			rs = pstmt.executeQuery();
			
			PrintWriter writer = response.getWriter();
			writer.println("<html>");
			writer.println("<head>");
			writer.println("<title>회원 목록</title>");
			writer.println("</head>");
			writer.println("<body>");
			writer.println("<h1>회원 목록</h1>");
			writer.println("<p><a href='add'>신규 회원</a></p>");
			while(rs.next()){
				writer.println(rs.getInt("mno") + ", " + rs.getString("mname") + ", " + rs.getString("email") + ", " + rs.getString("cre_date") + "<br>");
			}
			writer.println("</body>");
			writer.println("</html>");
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{ if(rs != null){ rs.close();} }catch(Exception e){}
			try{ if(pstmt != null){ pstmt.close();} }catch(Exception e){}
			try{ if(conn != null){ conn.close();} }catch(Exception e){}
		}
	}

}
