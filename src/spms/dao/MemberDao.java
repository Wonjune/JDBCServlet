package spms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;

import spms.vo.*;

public class MemberDao{
	
	Connection conn = null;
	
	public void setConnection(Connection conn){
		this.conn = conn;
	}
	
	public ArrayList<Member> selectList() throws Exception{
		ArrayList<Member> members = new ArrayList<Member>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			pstmt = conn.prepareStatement("select mno, email, mname, cre_date from members order by mno asc");
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				members.add(new Member().setNo(rs.getInt(1))
						.setEmail(rs.getString(2))
						.setName(rs.getString(3))
						.setCreateDate(rs.getString(4))
						);
			}
		}catch(Exception e){
			throw e;
		}finally{
			try{ if(pstmt != null){ pstmt.close(); }}catch(Exception e){}
		}
		
		return members;
		
		
	}
}
