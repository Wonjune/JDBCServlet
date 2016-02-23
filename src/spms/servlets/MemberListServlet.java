package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.vo.Member;

@WebServlet("/member/list")
public class MemberListServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int mno;
		
		try{
			ServletContext sc = this.getServletContext();
			
			conn = (Connection) sc.getAttribute("conn");
			pstmt = conn.prepareStatement("select mno, email, mname, cre_date from members order by mno asc");
			rs = pstmt.executeQuery();

			ArrayList<Member> members = new ArrayList<Member>();
			while(rs.next()){
				members.add(new Member().setNo(rs.getInt("mno")).setName(rs.getString("mname")).setEmail(rs.getString("email")).setCreateDate(rs.getString("cre_date")));
			}
			request.setAttribute("members", members);
			
			RequestDispatcher rd = request.getRequestDispatcher("/member/MemberList.jsp");
			rd.include(request, response);
			
		}catch(Exception e){
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.forward(request, response);
		}finally{
			try{ if(rs != null){ rs.close();} }catch(Exception e){}
			try{ if(pstmt != null){ pstmt.close();} }catch(Exception e){}
		}
	}
}
