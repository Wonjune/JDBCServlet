package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.vo.Member;

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
			

			req.setAttribute("member", new Member()
			.setNo(Integer.parseInt(no))
			.setName(rs.getString(1))
			.setEmail(rs.getString(2))
			.setPassword(rs.getString(3))
			.setCreateDate(rs.getString(4))
			.setModifiedDate(rs.getString(5)));

			resp.setContentType("text/html; charset=UTF-8");
			
			RequestDispatcher rd = req.getRequestDispatcher("/member/MemberUpdateForm.jsp");
			rd.include(req, resp);
			
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
			req.setAttribute("error", e);
			RequestDispatcher rd = req.getRequestDispatcher("Error.jsp");
			rd.forward(req, resp);
		}finally{
			try{ if(pstmt != null){ pstmt.close(); }}catch(Exception e){}
		}
	}

}
