package spms.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;

import spms.vo.Member;

@WebServlet("/auth/login")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html; charset=UTF-8");
		RequestDispatcher rd = req.getRequestDispatcher("/auth/LogInForm.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		Member member = new Member();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			ServletContext sc = this.getServletContext();
			conn = (Connection)sc.getAttribute("conn");
			pstmt = conn.prepareStatement("select mname, email from members where email = ? and pwd = ?");
			pstmt.setString(1, req.getParameter("email"));
			pstmt.setString(2, req.getParameter("password"));
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				member.setEmail(rs.getString(1)).setName(rs.getString(2));
				HttpSession session = req.getSession();
				session.setAttribute("member", member);
				resp.sendRedirect("../member/list");
			}else{
				RequestDispatcher rd = req.getRequestDispatcher("/auth/LogInFail.jsp");
				rd.forward(req, resp);
			}
			
		}catch(Exception e){
			req.setAttribute("error", e);
			RequestDispatcher rd = req.getRequestDispatcher("/error.jsp");
			rd.forward(req, resp);
		}finally{
			try{ if(rs != null) rs.close(); }catch(Exception e){}
			try{ if(pstmt != null) pstmt.close(); }catch(Exception e){}
		}
		
	}

}
